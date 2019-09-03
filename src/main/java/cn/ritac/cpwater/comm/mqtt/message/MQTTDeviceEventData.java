package cn.ritac.cpwater.comm.mqtt.message;

/**
 * 事件上传流程
 * @author admin
 *
 */
public class MQTTDeviceEventData  extends MQTTDeviceBase{
	
	private String deviceID;
	private String deviceKey;
	private MsgDataEvent msgData;
	
	public String getDeviceKey() {
		return deviceKey;
	}
	public void setDeviceKey(String deviceKey) {
		this.deviceKey = deviceKey;
	}
	
	public MsgDataEvent getMsgData() {
		return msgData;
	}
	public void setMsgData(MsgDataEvent msgData) {
		this.msgData = msgData;
	}
	public String getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	
 
}
