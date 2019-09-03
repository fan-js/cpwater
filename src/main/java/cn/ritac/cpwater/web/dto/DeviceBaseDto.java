package cn.ritac.cpwater.web.dto;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class DeviceBaseDto {
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeviceNum() {
		return deviceNum;
	}

	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getPositionDes() {
		return positionDes;
	}

	public void setPositionDes(String positionDes) {
		this.positionDes = positionDes;
	}

	public Boolean getOnline() {
		return online;
	}

	public void setOnline(Boolean online) {
		this.online = online;
	}

	public Date getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(Date onlineTime) {
		this.onlineTime = onlineTime;
	}

	public Date getOfflineTime() {
		return offlineTime;
	}

	public void setOfflineTime(Date offlineTime) {
		this.offlineTime = offlineTime;
	}

	public Integer getSignal() {
		return signal;
	}

	public void setSignal(Integer signal) {
		this.signal = signal;
	}

	public String getDeviceVersion() {
		return deviceVersion;
	}

	public void setDeviceVersion(String deviceVersion) {
		this.deviceVersion = deviceVersion;
	}

	public String getSoftVersion() {
		return softVersion;
	}

	public void setSoftVersion(String softVersion) {
		this.softVersion = softVersion;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	private Integer id;

	@NotEmpty(message = "设备编号不能为空")
	private String deviceNum;

	@NotEmpty(message = "设备私钥不能为空")
	private String deviceKey;

	public String getDeviceKey() {
		return deviceKey;
	}

	public void setDeviceKey(String deviceKey) {
		this.deviceKey = deviceKey;
	}

	@NotEmpty(message = "设备型号不能为空")
	private String deviceModel;

	private String lon;

	private String lat;

	private String positionDes;

	private Boolean online;

	private Date onlineTime;

	private Date offlineTime;

	private Integer signal;

	@NotEmpty(message = "硬件版本不允许为空")
	private String deviceVersion;

	@NotEmpty(message = "软件版本不允许为空")
	private String softVersion;

	private String remark;

	private Date createTime;

	private Date updateTime;

}
