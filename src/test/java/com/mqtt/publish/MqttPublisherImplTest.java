package com.mqtt.publish;

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
		byte[] payload = "test message".getBytes();
		mqttPublisherImpl.send(topic, payload);
	}

}
