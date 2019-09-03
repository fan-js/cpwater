package cn.ritac.cpwater.comm.mqtt.message;

public class MQTTDeviceEventResult {
	private String productID;
	private String deviceID;
	private String deviceKey;
	private int msgType;
	private KeyValue[] eventInfo;
	private int msgState;

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

	public KeyValue[] getEventInfo() {
		return eventInfo;
	}

	public void setEventInfo(KeyValue[] eventInfo) {
		this.eventInfo = eventInfo;
	}

	public int getMsgState() {
		return msgState;
	}

	public void setMsgState(int msgState) {
		this.msgState = msgState;
	}

}
