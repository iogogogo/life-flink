package com.iogogogo.sink;

import com.iogogogo.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * 本质其实就是把数据和其他组件进行 connector
 * <p>
 * Created by tao.zeng on 2019/1/27.
 */
@Slf4j
public class SinkToMySQL extends RichSinkFunction<Employee> {

    private PreparedStatement ps;
    private Connection connection;

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
    }

    /**
     * 关闭连接和释放资源
     *
     * @throws Exception
     */
    @Override
    public void close() throws Exception {
        super.close();
    }

    /**
     * 每条数据的插入都要调用一次 invoke() 方法
     *
     * @param value
     * @param context
     * @throws Exception
     */
    @Override
    public void invoke(Employee value, Context context) throws Exception {
        // 执行插入逻辑

        // ps.executeUpdate();
    }
}
