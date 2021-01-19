package com.mqtt.subscribe;

public interface Subscriber {
	void listener(String[] topics);

	void disconnect();
}
