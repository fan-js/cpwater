package cn.ritac.cpwater.comm.mqtt.message;

/**
 * 配置信息上传流程
 * @author admin
 *
 */
public class MQTTDeviceConfigUp extends MQTTDeviceBase {
	
	private String deviceID;
	private String deviceKey;
	private String time;
	private int msgType;
	private MQTTDeviceConfigClientUpMsgData msgData;
	
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
	
	public MQTTDeviceConfigClientUpMsgData getMsgData() {
		return msgData;
	}
	public void setMsgData(MQTTDeviceConfigClientUpMsgData msgData) {
		this.msgData = msgData;
	}
	public String getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
	
	
	
}
