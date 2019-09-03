package cn.ritac.cpwater.comm.mqtt.newdev;

import java.sql.Date;

import cn.ritac.cpwater.comm.mqtt.message.MQTTDeviceBase;

/**
 * 设备注册
 * 
 * @author ccy
 *
 */
public class NewMQTTDeviceLogin extends MQTTDeviceBase {
	private String productId;
	private String productKey;
	private String deviceID;
	private String deviceKey;
	private String model;
	private Date time;
	private String longitude;
	private String dimension;
	private int msgType;
	// 对象属性
	private MsgDataNewlogin msgData;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public MsgDataNewlogin getMsgData() {
		return msgData;
	}

	public void setMsgData(MsgDataNewlogin msgData) {
		this.msgData = msgData;
	}

}
