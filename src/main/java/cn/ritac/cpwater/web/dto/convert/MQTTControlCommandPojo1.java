/*
 *
 *
 */
package cn.ritac.cpwater.web.dto.convert;

import cn.ritac.cpwater.comm.mqtt.message.MQTTDeviceControlSendParamsPjo;

/**
 *<b>Description:</b><br>
 * @author admin
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> MQTTControlCommandPojo
 *<br><b>CreatTime:</b> 2019?5?7???10:53:26
 */
public class MQTTControlCommandPojo1 {
	private String productID;
	private String deviceID;
	private String deviceKey;
	private int msgType;
	private MQTTDeviceControlSendParamsPjo msgData;

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public String getDeviceKey() {
		return deviceKey;
	}

	public void setDeviceKey(String deviceKey) {
		this.deviceKey = deviceKey;
	}

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public MQTTDeviceControlSendParamsPjo getMsgData() {
		return msgData;
	}

	public void setMsgData(MQTTDeviceControlSendParamsPjo msgData) {
		this.msgData = msgData;
	}
}
