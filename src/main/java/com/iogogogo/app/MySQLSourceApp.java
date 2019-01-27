package com.iogogogo.app;

import com.iogogogo.model.Employee;
import com.iogogogo.source.SourceFromMySQL;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.PrintSinkFunction;

/**
 * Created by tao.zeng on 2019/1/27.
 */
@Slf4j
public class MySQLSourceApp {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<Employee> source = environment.addSource(new SourceFromMySQL());
        source.print();
        source.addSink(new PrintSinkFunction<>());

        environment.execute("Flink add data mysql source");
    }
}
