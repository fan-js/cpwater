package cn.ritac.cpwater.comm.mqtt.message;

/**
 * 数据上传流程
 * @author admin
 *
 */
public class MQTTDeviceData extends MQTTDeviceBase {

	private String productID;
	private String deviceID;
	private String deviceKey;
	private String time;
	private int msgType;
	private MsgData msgData;

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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public MsgData getMsgData() {
		return msgData;
	}

	public void setMsgData(MsgData msgData) {
		this.msgData = msgData;
	}

}
