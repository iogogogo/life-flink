package com.iogogogo.sink;

import com.iogogogo.mapper.EmployeeMapper;
import com.iogogogo.model.Employee;
import com.iogogogo.persistent.SqlSessionFactoryHelper;
import com.iogogogo.util.IoUtils;
import com.iogogogo.util.JsonParse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

/**
 * 本质其实就是把数据和其他组件进行 connector
 * <p>
 * Created by tao.zeng on 2019/1/27.
 */
@Slf4j
public class SinkToMySQL extends RichSinkFunction<Employee> {

    private EmployeeMapper employeeMapper;
    private SqlSession session;

    /**
     * open() 方法中建立连接，这样不用每次 invoke 的时候都要建立连接和释放连接
     *
     * @param parameters
     * @throws Exception
     */
    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        // 打开MySQL连接
        // 获取 Connection 和 PreparedStatement 对象
        session = SqlSessionFactoryHelper.openSqlSession();
        employeeMapper = session.getMapper(EmployeeMapper.class);
    }

    /**
     * 关闭连接和释放资源
     *
     * @throws Exception
     */
    @Override
    public void close() throws Exception {
        super.close();
        log.info("release resource close");
        IoUtils.close(session);
        session = null;
    }

    /**
     * 每条数据的插入都要调用一次 invoke() 方法
     *
     * @param value
     * @param context
     */
    @Override
    public void invoke(Employee value, Context context) {
        // 执行插入逻辑 ps.executeUpdate();
        int i = employeeMapper.save(value);
        session.commit();
        log.info("invoke save row count:{}", i);
    }

    public void batchSave(List<Object> data, int batchSize) {
        log.info("process data.size:{} batchSize:{} data:{}", data.size(), batchSize, JsonParse.toJson(data));
        List<Object> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(data)) {
            for (int i = 0; i < data.size(); i++) {
                list.add(data.get(i));
                if ((i + 1) % batchSize == 0 || i == (data.size() - 1)) {
                    // 逻辑处理

                    list.clear();
                }
            }
        } else {
            log.warn("data is null");
        }
    }
}
