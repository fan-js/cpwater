package cn.ritac.cpwater.comm.mqtt;

import cn.ritac.cpwater.comm.mqtt.message.*;
import cn.ritac.cpwater.web.dto.convert.MQTTControlCommandPojo;
import cn.ritac.cpwater.web.dto.convert.MQTTControlCommandPojo1;
import cn.ritac.cpwater.web.dto.convert.MQTTControlCommandPojo2;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSONObject;
import cn.ritac.cpwater.comm.SpringContextHelper;
import cn.ritac.cpwater.comm.mqtt.newdev.MQTTDeviceStatusData;
import cn.ritac.cpwater.service.MQTTService;

/**
 * 发布消息的回调类
 *
 * 必须实现MqttCallback的接口并实现对应的相关接口方法CallBack 类将实现 MqttCallBack。
 * 每个客户机标识都需要一个回调实例。在此示例中，构造函数传递客户机标识以另存为实例数据。 在回调中，将它用来标识已经启动了该回调的哪个实例。
 * 必须在回调类中实现三个方法：
 *
 * public void messageArrived(MqttTopic topic, MqttMessage message)接收已经预订的发布。
 *
 * public void connectionLost(Throwable cause)在断开连接时调用。
 *
 * public void deliveryComplete(MqttDeliveryToken token)) 接收到已经发布的 QoS 1 或 QoS 2
 * 消息的传递令牌时调用。 由 MqttClient.connect 激活此回调。
 *
 */

public class PushCallback implements MqttCallbackExtended {

	@Autowired
	private MQTTService MQTTService;
	
	@Autowired
	private ServerMQTT serverMQTT;
	// private String ssePort;

	private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

	public PushCallback(ServerMQTT sq/*, String ssePort*/) {
		if (serverMQTT == null) {
			serverMQTT = sq;
		}
		// this.ssePort = ssePort;
	}

	@Override
	/**
	 * 客户端掉线重新订阅主题
	 */
	public void connectComplete(boolean reconnect, String serverURI) {

	}

	/***
	 * 掉线重连
	 */
	public void connectionLost(Throwable cause) {

	}

	public void deliveryComplete(IMqttDeliveryToken token) {
		System.out.println("deliveryComplete---------" + token.isComplete());
	}

	public void messageArrived(String topic, MqttMessage message) throws Exception {

		try {
			// subscribe后得到的消息会执行到这里面
			MQTTService = (cn.ritac.cpwater.service.MQTTService) SpringContextHelper.getBean("MQTTService");

			logger.info("接收消息主题 : " + topic);

			logger.info("接收消息Qos : " + message.getQos());

			String content = new String(message.getPayload());

			MQTTDeviceBase contextBase = (MQTTDeviceBase) JSONObject.parseObject(content, MQTTDeviceLogin.class);
			logger.info("MsgType=" + contextBase.getMsgType());

			logger.info("接收消息内容 : " + new String(message.getPayload()));
			switch (contextBase.getMsgType()) {
			case 60001:// 设备注册
			case 60006:
			case 60008:
			case 60009:
				logger.info("设备端向服务端发布注册信息开始");

				MQTTDeviceLogin mqttDeviceLogin = JSONObject.parseObject(content, MQTTDeviceLogin.class);
				MQTTService.sendLoginMsg(mqttDeviceLogin);
				
				MQTTService.SendVoltnetPropor(mqttDeviceLogin.getDeviceID(), mqttDeviceLogin.getDeviceKey());
				logger.info("设备端向服务端发布注册信息结束");
				break;
			case 60002:// 上传数据
			case 101:
			case 102:
			case 103:
			case 105:
				logger.info("数据上传流程开始");

				MQTTDeviceData smqttDeviceData = JSONObject.parseObject(content, MQTTDeviceData.class);
				MQTTService.getBaseData(smqttDeviceData);
				
				MQTTService.SendCallback(smqttDeviceData.getDeviceID());
				logger.info("数据上传流程结束");
				break;
			case 109:// 事件上传
				logger.info(" 事件上传流程开始");

				MQTTDeviceEventData smqttDeviceEventData = JSONObject.parseObject(content, MQTTDeviceEventData.class);
				MQTTService.getEvent(smqttDeviceEventData);
				// 推送
				MQTTService.sendEvent(smqttDeviceEventData.getMsgData().getEventInfo().length);
				logger.info("事件上传流程结束");
				break;
			case 107:// 配置信息
				logger.info(" 配置信息上传流程开始");

				MQTTDeviceConfigUp smqttDeviceConfigUp = JSONObject.parseObject(content, MQTTDeviceConfigUp.class);
				MQTTService.getDeviceConfig(smqttDeviceConfigUp);
				MQTTService.SendRegEvent();
				logger.info(" 配置信息上传流程结束");
				break;
			// 设备上线、离线状态更新
			case 60003:
			case 60004:
				logger.info(" 更新设备状态流程开始");

				MQTTDeviceStatusData mqttDeviceStatusData = JSONObject.parseObject(content, MQTTDeviceStatusData.class);

				MQTTService.getDeviceStatus(mqttDeviceStatusData);
				MQTTService.OffLine(mqttDeviceStatusData.getDeviceID());

				logger.info(" 更新设备状态流程结束");
				break;
				//设备下载更新包返回信息
			case 65000:
					logger.info("设备下载更新包");
				MQTTControlCommandPojo2 mqttControlCommandPojo1=JSONObject.parseObject(content,MQTTControlCommandPojo2.class);
					MQTTService.updateResult(mqttControlCommandPojo1);
					break;
			default:
				break;
			}
		} catch (Exception e) {
			logger.error("pushcallback-error:" + e.getMessage());
		}
	}

}
