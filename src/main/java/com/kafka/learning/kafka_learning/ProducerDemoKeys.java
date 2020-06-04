package com.kafka.learning.kafka_learning;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class ProducerDemoKeys {
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		final Logger logger = LoggerFactory.getLogger(ProducerDemoWithCallBack.class);
		// Create Producer properties
		String bootStrapServers = "127.0.0.1:9092";
		Properties properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		// create the producer

		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

		for (int i = 0; i < 10; i++) {
			String topic = "lockdown";
			String value = "Test for keys " + Integer.toString(i);
			String key = "id_" + Integer.toString(i);
			ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic,key,value);
			
			logger.info("key: " +key); // log the key
			// id_0 going to partition 3
			// id_1 partition 3
			// id_2 - 1
			// id_3 - 3
			// id_4 - 2
			// id_5 - 3
			// id_6 - 2
			// id_7 - 3
			// id_8 - 1
			// id_9 - 2
			
			// send data asynchronus
			producer.send(record, new Callback() {

				public void onCompletion(RecordMetadata metadata, Exception e) {
					// executes every time a record is successfully sent or an exception is thrown.
					if (e == null) {
						// The Record was successfully sent
						logger.info("Received new metadata. \n" + "Topic :" + metadata.topic() + "\n" + "Partition :"
								+ metadata.partition() + "\n" + "Offset :" + metadata.offset() + "\n" + "Timestamp :"
								+ metadata.timestamp());
					} else {
						logger.error("Error while producing" + e);

					}
				}
			}).get(); // block the .send() to make it synchronus - not needed for production. 
		}
		// flush data
		producer.flush();
		// flush and close
		producer.close();

	}
}
