package com.mqtt.subscribe;

import java.util.concurrent.CountDownLatch;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class SubscriberImpl implements Subscriber {

	private MqttClient client;
	private String clientId;
	private CountDownLatch wait = new CountDownLatch(1);

	SubscriberImpl(String serverURI) throws MqttException {
		this.clientId = MqttClient.generateClientId();
		client = new MqttClient(serverURI, this.clientId, new MemoryPersistence());
		MqttConnectOptions options = new MqttConnectOptions();
		options.setAutomaticReconnect(true);
		options.setCleanSession(true);
		options.setConnectionTimeout(10);
		client.connect(options);
	}

	@Override
	public void listener(String topic) {
		try {
			client.subscribe(topic, new IMqttMessageListener() {
				@Override
				public void messageArrived(String topic, MqttMessage message) throws Exception {
					System.out.println(clientId + "-data coming from topic :" + topic + " message :" + message
							+ " isDuplicate :" + message.isDuplicate());
				}
			});
			System.out.println(clientId + "-listening..");
			wait.await();
			System.out.println(clientId + "-listener out..");
		} catch (MqttException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void disconnect() {
		wait.countDown();
		if (client != null)
			try {
				client.disconnect();
			} catch (MqttException e) {
				e.printStackTrace();
			}
	}

}
