/*
 *
 *
 */
package cn.ritac.cpwater.comm.mqtt.message;

/**
 *<b>Description:</b><br>
 * @author admin
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> MQTTDeviceSelfConfig
 *<br><b>CreatTime:</b> 2019年5月6日上午9:29:57
 */
public class MQTTDeviceSelfConfig {
	private String productID;
	private String productKey;
	private String deviceID;
	private String deviceKey;
	private int msgType;
	private MQTTDeviceSelfConfigMsgData msgData;

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductKey() {
		return productKey;
	}

	public void setProductKey(String productKey) {
		this.productKey = productKey;
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

	public MQTTDeviceSelfConfigMsgData getMsgData() {
		return msgData;
	}

	public void setMsgData(MQTTDeviceSelfConfigMsgData msgData) {
		this.msgData = msgData;
	}

}
