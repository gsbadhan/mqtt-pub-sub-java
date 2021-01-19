package com.mqtt.subscribe;

public interface Subscriber {
	void listener(String topic);

	void disconnect();
}
