package com.mqtt.publish;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttPublisherImpl implements Publisher {
	private MqttClient client;

	MqttPublisherImpl(String serverURI, String clientId) throws MqttException {
		client = new MqttClient(serverURI, clientId, new MemoryPersistence());
		MqttConnectOptions options = new MqttConnectOptions();
		options.setAutomaticReconnect(true);
		options.setCleanSession(true);
		options.setConnectionTimeout(10);
		client.connect(options);
	}

	@Override
	public void send(String topic, byte[] payload) {
		MqttMessage msg = new MqttMessage();
		msg.setPayload(payload);
		msg.setQos(2);
		msg.setRetained(true);
		try {
			client.publish(topic, msg);
		} catch (MqttPersistenceException e) {
			e.printStackTrace();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void disconnect() {
		if (client != null)
			try {
				client.disconnect();
			} catch (MqttException e) {
				e.printStackTrace();
			}
	}

}
