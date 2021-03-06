package com.aubga.kafka;

import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Executors;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class ProducerTest {
	
    public final static String TOPIC = "aubga_topic";
    public final static String SERVER_HOST = "10.103.27.13:9092";
    public final static String MESSAGE = "asset-basic|"+new Date().getTime()+"|dcc8deb8594704e6|73cb68a169823f80|cd98d5815d85808e|10.103.27.11|mybatis|select * from ast_matched_record|432|";
   
	
	  private Producer<String,String> producer;

	    public static void main(String[] args) {
	        new ProducerTest().start();
	    }

	    public void init(){
	        Properties props = new Properties();
	        /**
	         * 用于自举（bootstrapping ），producer只是用它来获得元数据（topic, partition, replicas）
	         * 实际用户发送消息的socket会根据返回的元数据来确定
	         */
	        props.put("metadata.broker.list", SERVER_HOST);
	        /**
	         * 消息的序列化类
	         * 默认是 kafka.serializer.DefaultEncoder， 输入时 byte[] 返回是同样的字节数组
	         */
	        props.put("serializer.class", "kafka.serializer.StringEncoder");
	        /**
	         * producer发送消息后是否等待broker的ACK，默认是0
	         * 1 表示等待ACK，保证消息的可靠性
	         */
	        props.put("request.required.acks", "1");
	        ProducerConfig config = new ProducerConfig(props);
	        // 泛型参数分别表示 The first is the type of the Partition key, the second the type of the message
	        producer = new Producer<String, String>(config);
	    }

	    public void produceMsg(){
	        /**
	         * topic: 消息的主题
	         * key：消息的key，同时也会作为partition的key
	         * message:发送的消息
	         */
	        KeyedMessage<String, String> data = new KeyedMessage<String, String>(TOPIC,System.currentTimeMillis()+"", MESSAGE);

	        producer.send(data);
	        System.out.println("send data->"+data.toString());
	    }

	    public void start() {
	        System.out.println("开始发送消息 ...");
	        Executors.newSingleThreadExecutor().execute(new Runnable() {
	            public void run() {
	                init();
	                while (true) {
	                    try {
	                        produceMsg();
	                        Thread.sleep(1000);
	                    } catch (Throwable e) {
	                        if (producer != null) {
	                            try {
	                                producer.close();
	                            } catch (Throwable e1) {
	                                System.out.println("Turn off Kafka producer error! " + e);
	                            }
	                        }
	                    }
	                   
	                }
	            }
	        });
	    }
	}
