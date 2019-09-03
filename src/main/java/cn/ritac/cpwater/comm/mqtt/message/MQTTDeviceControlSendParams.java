package cn.ritac.cpwater.comm.mqtt.message;

import java.util.Date;

public class MQTTDeviceControlSendParams {

	private String deviceNum;
	private String deviceKey;
	private Date time;
	private int msgType;
	private MQTTDeviceControlSendParamsPjo msgData;

	public String getDeviceNum() {
		return deviceNum;
	}

	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}

	public String getDeviceKey() {
		return deviceKey;
	}

	public void setDeviceKey(String deviceKey) {
		this.deviceKey = deviceKey;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
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
