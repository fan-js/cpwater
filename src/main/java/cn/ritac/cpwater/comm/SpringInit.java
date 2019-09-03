package cn.ritac.cpwater.comm;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import cn.ritac.cpwater.comm.mqtt.ServerMQTT;

@Configuration
public class SpringInit implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private ServerMQTT serverMQTT;
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
			serverMQTT.connect();
		} catch (MqttException e) {
			e.printStackTrace();
		}
		
	}
}
