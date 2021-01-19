package com.mqtt.subscribe;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SubscriberImplTest {

	private SubscriberImpl subscriberImpl;

	@BeforeEach
	void setUp() throws Exception {
		subscriberImpl = new SubscriberImpl("tcp://localhost:1883");
	}

	@AfterEach
	void tearDown() throws Exception {
		subscriberImpl.disconnect();
	}

	@Test
	void testListener() throws InterruptedException {
		subscriberImpl.listener("test");
	}

	@Test
	void testDisconnect() {
		subscriberImpl.disconnect();
	}

}
