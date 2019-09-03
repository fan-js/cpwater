package cn.ritac.cpwater.comm.mqtt.message;

/*
 * 控制命令下发流程
 */
public class MQTTDeviceControlSend extends MQTTDeviceBase {
	
	private String deviceID;
	private String deviceKey;
	private String time;
	private int msgType;
	private MQTTDeviceControlSendMsgData msgData;

	
	
	
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
	public MQTTDeviceControlSendMsgData getMsgData() {
		return msgData;
	}
	public void setMsgData(MQTTDeviceControlSendMsgData msgData) {
		this.msgData = msgData;
	}
	
	

}
