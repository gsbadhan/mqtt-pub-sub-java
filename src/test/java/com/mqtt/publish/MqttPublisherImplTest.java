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
		String topic = "test";
		byte[] payload = new StringBuilder().append("test msg ").append(new Date().toString()).toString().getBytes();
		mqttPublisherImpl.send(topic, payload);
	}

}
