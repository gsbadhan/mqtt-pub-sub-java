package com.mqtt.publish;

import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MqttPublisherImplTest {
	private MqttPublisherImpl mqttPublisherImpl;

	@BeforeEach
	void setUp() throws Exception {
		mqttPublisherImpl = new MqttPublisherImpl("tcp://localhost:1883", "javaTest");
	}

	@AfterEach
	void tearDown() throws Exception {
		mqttPublisherImpl.disconnect();
	}

	@Test
	void testSend() {
		String deviceId = "1001";
		String topic = "car/engine/temperature/" + deviceId;
		String value = "20C";
		byte[] payload = new StringBuilder().append(new Date().getTime()).append(",").append(value).toString()
				.getBytes();
		mqttPublisherImpl.send(topic, payload);
	}

}
