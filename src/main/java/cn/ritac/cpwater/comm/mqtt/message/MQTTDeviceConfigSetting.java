package cn.ritac.cpwater.comm.mqtt.message;

/**
 * 配置信息设置
 * @author admin
 *
 */
public class MQTTDeviceConfigSetting extends MQTTDeviceBase {

	private String productID;
	private String deviceID;
	private String deviceKey;
// private String time;
	private int msgType;
	private MQTTDeviceConfigClientUpMsgData msgData;

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

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public MQTTDeviceConfigClientUpMsgData getMsgData() {
		return msgData;
	}

	public void setMsgData(MQTTDeviceConfigClientUpMsgData msgData) {
		this.msgData = msgData;
	}

	@Override
	public String toString() {
		return "MQTTDeviceConfigSetting [deviceID=" + deviceID + ", deviceKey=" + deviceKey + ", time=" + ", msgType=" + msgType + ", msgData=" + msgData + "]";
	}

}
