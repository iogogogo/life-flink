package com.iogogogo.source;

import com.iogogogo.conf.MySQLConf$;
import com.iogogogo.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;

import java.sql.*;

/**
 * CREATE TABLE `employee` (
 * `id` int(11) NOT NULL AUTO_INCREMENT,
 * `name` varchar(50) CHARACTER SET utf8 NOT NULL,
 * `age` int(11) DEFAULT NULL,
 * `birthday` datetime DEFAULT NULL,
 * `dept_id` int(11) DEFAULT NULL,
 * PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
 * <p>
 * Created by tao.zeng on 2019/1/27.
 */
@Slf4j
public class SourceFromMySQL extends RichSourceFunction<Employee> {

    private PreparedStatement ps;
    private Connection connection;

    /**
     * open() 方法中建立连接，这样不用每次 invoke 的时候都要建立连接和释放连接。
     *
     * @param parameters
     * @throws Exception
     */
    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(MySQLConf$.MODULE$.url(), MySQLConf$.MODULE$.username(), MySQLConf$.MODULE$.password());
        log.info("open mysql connection...");
        String sql = "select * from employee;";
        ps = connection.prepareStatement(sql);
    }

    /**
     * 程序执行完毕就可以进行，关闭连接和释放资源的动作了
     *
     * @throws Exception
     */
    @Override
    public void close() throws Exception {
        super.close();
        log.info("release resource close");
        if (ps != null) {
            ps.close();
            ps = null;
        }
        if (connection != null) {
            connection.close();
            connection = null;
        }
    }

    /**
     * DataStream 调用一次 run() 方法用来获取数据
     *
     * @param sourceContext
     */
    @Override
    public void run(SourceContext<Employee> sourceContext) throws SQLException {
        ResultSet rs = ps.executeQuery();
        Employee employee;
        while (rs.next()) {
            employee = new Employee();
            employee.setId(rs.getLong("id"));
            employee.setName(rs.getString("name"));
            employee.setAge(rs.getInt("age"));
            employee.setBirthday(rs.getDate("birthday"));
            employee.setDeptId(rs.getInt("dept_id"));
            sourceContext.collect(employee);
        }
    }

    @Override
    public void cancel() {

    }
}
