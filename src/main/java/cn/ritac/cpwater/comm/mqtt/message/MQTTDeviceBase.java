package cn.ritac.cpwater.comm.mqtt.message;

public abstract class MQTTDeviceBase {
	//private String deviceId;
	public int msgType;
	
	public int getMsgType() {
		return msgType;
	}
	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}
}
