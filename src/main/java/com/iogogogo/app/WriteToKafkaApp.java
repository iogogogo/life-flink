package com.iogogogo.app;

import com.iogogogo.conf.KafkaConf$;
import com.iogogogo.model.Metric;
import com.iogogogo.util.JsonParse;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 写数据到kafka
 * <p>
 * Created by tao.zeng on 2019/1/27.
 */
@Slf4j
public class WriteToKafkaApp {

    public final static String TOPIC_001 = "topic_001";

    public static void main(String[] args) throws InterruptedException {
        for (; ; ) {
            Thread.sleep(300);
            writeToKafka();
        }
    }

    private static void writeToKafka() {
        Properties props = new Properties();
        props.put("bootstrap.servers", KafkaConf$.MODULE$.bootstrapServer().get());
        //key 序列化
        props.put("key.serializer", KafkaConf$.MODULE$.keySerializer().get());
        //value 序列化
        props.put("value.serializer", KafkaConf$.MODULE$.valueSerializer().get());
        KafkaProducer producer = new KafkaProducer<String, String>(props);

        Metric metric = new Metric();
        metric.setTimestamp(System.currentTimeMillis());
        metric.setName("mem");
        Map<String, String> tags = new HashMap<>();
        Map<String, Object> fields = new HashMap<>();

        tags.put("cluster", "iogogogo");
        tags.put("host_ip", "127.0.0.1");

        fields.put("used_percent", 90d);
        fields.put("max", 27244873d);
        fields.put("used", 17244873d);
        fields.put("init", 27244873d);

        metric.setTags(tags);
        metric.setFields(fields);

        String data = JsonParse.toJson(metric);
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_001, null, null, data);
        producer.send(record);
        log.info("writeToKafka data:{}", data);
        producer.flush();
    }
}
