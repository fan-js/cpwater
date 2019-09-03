package cn.ritac.cpwater.comm.mqtt;

/**
 * Created by Administrator on 17-2-10.
 */

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.ritac.cpwater.util.MD5CheckFile;

/**
 *<b>Description:</b><br>
 * @author admin / mail: ccy.175@163.com
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> ServerMQTT
 *<br><b>CreatTime:</b> 2019年6月5日下午5:28:02
 */
@Component
public class ServerMQTT {
	// tcp://MQTT安装的服务器地址:MQTT定义的端口号
	@Value("${MQTT.Server.Host}")
	private String HOST;

	// 定义一个主题，并订阅 (#:必须在末尾，统配所有层；+：统配当前所处整层；clientId：精准指定当前客户端)
	@Value("${MQTT.Server.Topic}")
	private String TOPIC;
	// public static final String TOPIC = "YTJK/V1/SIDO/#";
	// 定义MQTT的ID，可以在MQTT服务配置中指定
	private String clientid = "MWS-"+MD5CheckFile.codeAnyLength("#", 15);
	private MqttClient client;
	@Value("${MQTT.Server.Qos}")
	public int QOS;

	// @Value("${site.ssePort}")
	// private String ssePort;

	public MqttClient getClient() {
		return client;
	}

	@Value("${MQTT.Server.UserName}")
	private String userName;
	@Value("${MQTT.Server.PassWord}")
	private String passWord;

	/**
	 * 用来连接服务器
	 * 
	 * @throws MqttException
	 */
	public void connect() throws MqttException {

		if (client == null) {
			client = new MqttClient(HOST, clientid, new MemoryPersistence());
		}

		MqttConnectOptions options = new MqttConnectOptions();
		// 掉线自动重连
		options.setAutomaticReconnect(true);
		// 掉线回话清除
		options.setCleanSession(false);
		options.setUserName(userName);
		options.setPassword(passWord.toCharArray());
		// 设置超时时间
		options.setConnectionTimeout(20);
		// 设置会话心跳时间
		options.setKeepAliveInterval(20);
		// 当前客户机遗言、掉线触发
		// options.setWill("CPWATER/V1/SIDO/CLOSE", (clientid+":close").getBytes(), 2,
		// false);
		// 读取遗言消息
		// String str = new String(options.getWillMessage().getPayload());
		// System.out.println("遗言信息：-------->"+str);
		try {
			// 这里调用的回调
			client.setCallback(new PushCallback(ServerMQTT.this/*, ssePort*/));
			client.connect(options);
			client.subscribe(TOPIC, QOS);
			// topic11 = client.getTopic(TOPIC);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @param topic
	 * @param message
	 * @throws MqttPersistenceException
	 * @throws MqttException
	 */
	public void publish(MqttTopic topic, MqttMessage message) throws MqttPersistenceException, MqttException {
		MqttDeliveryToken token = topic.publish(message);
		// token.waitForCompletion();
		System.out.println("message is published completely! " + token.isComplete());
	}

}
