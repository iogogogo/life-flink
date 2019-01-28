package com.iogogogo.test;

import com.iogogogo.mapper.EmployeeMapper;
import com.iogogogo.model.Employee;
import com.iogogogo.persistent.SqlSessionFactoryHelper;
import com.iogogogo.util.IoUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * Created by tao.zeng on 2019/1/27.
 */
@Slf4j
public class Test {

    private SqlSession session;
    private EmployeeMapper employeeMapper;

    @Before
    public void before() {
        session = SqlSessionFactoryHelper.openSqlSession();
        employeeMapper = session.getMapper(EmployeeMapper.class);
    }

    @After
    public void after() {
        IoUtils.close(session);
        session = null;
    }

    @Ignore
    @org.junit.Test
    public void test() {
        Employee employee = new Employee("小花脸", 18, new Date(), 1);
        int i = employeeMapper.save(employee);
        session.rollback();
        log.info("row count:{}", i);
    }

    @org.junit.Test
    public void test1() throws IOException {
        String[] args = {"--input hdfs:///mydata", "--elements 42"};
        ParameterTool fromArgs = ParameterTool.fromArgs(args);
        log.info("{}", fromArgs);

        InputStream inputStream = ClassLoader.getSystemResourceAsStream("application.properties");
        ParameterTool parameter = ParameterTool.fromPropertiesFile(inputStream);
        log.info("{}", parameter);
    }
}
