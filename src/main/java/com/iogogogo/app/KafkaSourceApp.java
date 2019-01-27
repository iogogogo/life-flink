package com.iogogogo.app;

import com.iogogogo.conf.KafkaConf$;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer09;

import java.util.Properties;

/**
 * 从kafka获取source
 * <p>
 * Created by tao.zeng on 2019/1/27.
 */
public class KafkaSourceApp {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        final String topic = WriteToKafkaApp.TOPIC_001;

        Properties props = new Properties();
        props.put("bootstrap.servers", KafkaConf$.MODULE$.bootstrapServer().get());
        //key 序列化
        props.put("key.serializer", KafkaConf$.MODULE$.keySerializer().get());
        //value 序列化
        props.put("value.serializer", KafkaConf$.MODULE$.valueSerializer().get());
        props.put("zookeeper.connect", "192.168.1.101:2182,192.168.1.101:2183,192.168.1.101:2184");
        props.put("group.id", "metric-group");
        //key 反序列化
        props.put("key.deserializer", KafkaConf$.MODULE$.keyDeserializer());
        //value 反序列化
        props.put("value.deserializer", KafkaConf$.MODULE$.valueDeserializer());
        props.put("auto.offset.reset", "latest");

        DataStreamSource<String> dataStreamSource = environment
                .addSource(new FlinkKafkaConsumer09<>(topic, new SimpleStringSchema(), props))
                .setParallelism(1);

        //把从 kafka 读取到的数据打印在控制台
        dataStreamSource.print();

        environment.execute("Flink add data source");
    }
}
