package cn.ritac.cpwater.web.dto.convert;

import java.io.Serializable;

public class CDevices implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String deviceNum;
	private String deviceModel;

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return device_num
	 */
	public String getDeviceNum() {
		return deviceNum;
	}

	/**
	 * @param deviceNum
	 */
	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum == null ? null : deviceNum.trim();
	}

	/**
	 * @return device_model
	 */
	public String getDeviceModel() {
		return deviceModel;
	}

	/**
	 * @param deviceModel
	 */
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel == null ? null : deviceModel.trim();
	}

}