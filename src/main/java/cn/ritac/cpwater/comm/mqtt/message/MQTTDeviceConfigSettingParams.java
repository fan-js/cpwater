package cn.ritac.cpwater.comm.mqtt.message;

import java.util.List;

import cn.ritac.cpwater.web.dto.convert.SettingConfigPojo;

public class MQTTDeviceConfigSettingParams {
	private int id;

	private List<SettingConfigPojo> data;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<SettingConfigPojo> getData() {
		return data;
	}

	public void setData(List<SettingConfigPojo> data) {
		this.data = data;
	}

}
