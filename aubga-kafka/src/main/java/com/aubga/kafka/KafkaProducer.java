package com.aubga.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;


public class KafkaProducer {

    private final org.apache.kafka.clients.producer.KafkaProducer<String, String> producer;
    public final static String TOPIC = "aubga_topic";
    public final static String SERVER_HOST = "10.103.27.13:9092";
    public final static String MESSAGE = "asset-basic|1514351440088|dcc8deb8594704e6|73cb68a169823f80|cd98d5815d85808e|10.103.27.11|mybatis|select * from ast_matched_record|432|";
    
    private KafkaProducer(){
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,SERVER_HOST);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());   producer = new org.apache.kafka.clients.producer.KafkaProducer<String, String>(props);
    }

    void produce() {
        int messageNo = 1;
        final int COUNT = 500;

        while (messageNo < COUNT) {
	        try {
	               String data = MESSAGE+"|"+messageNo;
	               producer.send(new ProducerRecord<String, String>(TOPIC, data));
	               messageNo ++;
	               System.out.println("已经发送消息条数count->"+messageNo);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        //必须写下面这句,相当于发送
        producer.flush();
        System.out.println("flush completed......");
    }

    public static void main( String[] args ) {
        new KafkaProducer().produce();
    }
    
    public String getContent() {
    	return "";
    }

}