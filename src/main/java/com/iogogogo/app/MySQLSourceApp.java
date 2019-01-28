package com.iogogogo.app;

import com.alibaba.fastjson.TypeReference;
import com.iogogogo.model.Employee;
import com.iogogogo.source.SourceFromMySQL;
import com.iogogogo.util.JsonParse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.PrintSinkFunction;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * -Denv="-Denv="{ 'iogogogo.name': '小花脸', 'iogogogo.password': 123456, 'kafka.zookeeper.nodes ': '192.168.1.101:2182,192.168.1.101:2183,192.168.1.101:2184', 'kafka.bootstrap.servers': '192.168.1.101:9092', 'kafka.key.serializer': 'org.apache.kafka.common.serialization.StringSerializer', 'kafka.value.serializer': 'org.apache.kafka.common.serialization.StringSerializer', 'kafka.key.deserializer': 'org.apache.kafka.common.serialization.StringDeserializer', 'kafka.value.deserializer': 'org.apache.kafka.common.serialization.StringDeserializer', 'mysql.url': 'jdbc:mysql://localhost:3306/life-xxx?autoReconnect=true&rewriteBatchedStatements=true&zeroDateTimeBehavior=convertToNull&useSSL=false', 'mysql.driver': 'com.mysql.jdbc.Driver', 'mysql.username': 'root', 'mysql.password': 'root' }""
 * Created by tao.zeng on 2019/1/27.
 */
@Slf4j
public class MySQLSourceApp {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();

        String env = System.getProperty("env");
        log.info("env:{}", env);
        InputStream inputStream = new ByteArrayInputStream(env.getBytes());
        ParameterTool parameter = ParameterTool.fromPropertiesFile(inputStream);
        environment.getConfig().setGlobalJobParameters(parameter);

        if (StringUtils.isEmpty(env)) {
            throw new IllegalArgumentException("env 参数不能为空");
        }

        Type type = new TypeReference<Map<String, Object>>() {
        }.getType();
        Map<String, Object> map = JsonParse.parse(env, type);

        log.info("map:{}", map);

        DataStreamSource<Employee> source = environment.addSource(new SourceFromMySQL());
        source.print();
        source.addSink(new PrintSinkFunction<>());

        environment.execute("Flink add data mysql source");
    }
}
