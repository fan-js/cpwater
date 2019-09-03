package cn.ritac.cpwater.comm.mqtt.newdev;

import java.util.Date;

public class MQTTDeviceStatusData {
	private String productID;
	private String deviceID;
	private String deviceKey;
	private int msgType;
	private Date time;
	private StatusMsgData msgData;

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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public StatusMsgData getMsgData() {
		return msgData;
	}

	public void setMsgData(StatusMsgData msgData) {
		this.msgData = msgData;
	}

}
