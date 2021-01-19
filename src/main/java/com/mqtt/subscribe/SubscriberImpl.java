package com.mqtt.subscribe;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class SubscriberImpl implements Subscriber {

	private MqttClient client;

	SubscriberImpl(String serverURI, String clientId) throws MqttException {
		client = new MqttClient(serverURI, clientId, new MemoryPersistence());
		MqttConnectOptions options = new MqttConnectOptions();
		options.setAutomaticReconnect(true);
		options.setCleanSession(true);
		options.setConnectionTimeout(10);
		client.connect(options);
	}

	@Override
	public void listener(String[] topics) {
		try {
			client.subscribe("", new IMqttMessageListener() {
				@Override
				public void messageArrived(String topic, MqttMessage message) throws Exception {
					System.out.println("incoming data from topic :" + topic + " message :" + message);
				}
			});
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
