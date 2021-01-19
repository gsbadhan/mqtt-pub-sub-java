package com.mqtt.publish;

public interface Publisher {
	void send(String topic, byte[] payload);

	void disconnect();
}
