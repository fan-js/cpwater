package cn.ritac.cpwater.web.dto;

import java.util.Date;

public class DeviceRegDto {
	 public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getDeviceId() {
		return deviceId;
	}


	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}


	public Integer getFanOpenTemper() {
		return fanOpenTemper;
	}


	public void setFanOpenTemper(Integer fanOpenTemper) {
		this.fanOpenTemper = fanOpenTemper;
	}


	public Integer getHeaterOpenTemper() {
		return heaterOpenTemper;
	}


	public void setHeaterOpenTemper(Integer heaterOpenTemper) {
		this.heaterOpenTemper = heaterOpenTemper;
	}


	public Integer getVolAlarmLimit() {
		return volAlarmLimit;
	}


	public void setVolAlarmLimit(Integer volAlarmLimit) {
		this.volAlarmLimit = volAlarmLimit;
	}


	public Integer getCurrAlarmLimit() {
		return currAlarmLimit;
	}


	public void setCurrAlarmLimit(Integer currAlarmLimit) {
		this.currAlarmLimit = currAlarmLimit;
	}


	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	private Integer id;

	
	    private Integer deviceId;


	    private Integer fanOpenTemper;


	    private Integer heaterOpenTemper;


	    private Integer volAlarmLimit;


	    private Integer currAlarmLimit;

	
	    private Date updateTime;

}
