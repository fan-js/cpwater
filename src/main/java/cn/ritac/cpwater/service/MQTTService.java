package cn.ritac.cpwater.service;

import cn.ritac.cpwater.comm.mqtt.message.*;
import cn.ritac.cpwater.comm.mqtt.newdev.MQTTDeviceStatusData;
import cn.ritac.cpwater.web.dto.convert.MQTTControlCommandPojo;
import cn.ritac.cpwater.web.dto.convert.MQTTControlCommandPojo1;
import cn.ritac.cpwater.web.dto.convert.MQTTControlCommandPojo2;

public interface MQTTService {
	/**
		* 登录信息
		* 
		* @param login
		*/
	public void sendLoginMsg(MQTTDeviceLogin login);


	/**
	 * 数据包更新，返回信息
	 * */
public void updateResult(MQTTControlCommandPojo2 mqttControlCommandPojo2);
	/**
	 * 数据上传流程
	 * 
	 * @param data
	 */

	public void getBaseData(MQTTDeviceData data);

	/**
		* 事件上传流程
		* 
		* @param eventData
		*/

	public void getEvent(MQTTDeviceEventData eventData);

	/**
		* 配置信息上传
		* 
		* @param mqttDeviceConfigUp
		*/

	public void getDeviceConfig(MQTTDeviceConfigUp mqttDeviceConfigUps);

	/**
		* 配置信息的设置
		* 
		* @param setting
		*/
	public String setDeviceConfig(MQTTDeviceConfigSetting mqttDeviceConfigSetting);

	/**
	 * 控制命令下发
	 * 
	 * @param controlCommand
	 */
	public void sendDeviceControlCommand(MQTTControlCommandPojo commandPojo);

	public void getDeviceStatus(MQTTDeviceStatusData statusData);

	public String sendDeviceSelfConfigCommand(MQTTDeviceSelfConfig mqttDeviceSelfConfig);

	public String SendCallback(Object obj);

	public void sendEvent();

	public void SendVoltnetPropor(String devNum, String devKey);
	
	public void OffLine(String deviceNum) ;
	public void SendRegEvent() ;
}
