package cn.ritac.cpwater.web.dto;



public class DeviceDODTO {

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


	public Boolean getDoor() {
		return door;
	}


	public void setDoor(Boolean door) {
		this.door = door;
	}


	public Boolean getLight() {
		return light;
	}


	public void setLight(Boolean light) {
		this.light = light;
	}


	public Boolean getBeep() {
		return beep;
	}


	public void setBeep(Boolean beep) {
		this.beep = beep;
	}


	public Boolean getFan() {
		return fan;
	}


	public void setFan(Boolean fan) {
		this.fan = fan;
	}


	public Boolean getHeating() {
		return heating;
	}


	public void setHeating(Boolean heating) {
		this.heating = heating;
	}


	public Boolean getBakSwitch() {
		return bakSwitch;
	}


	public void setBakSwitch(Boolean bakSwitch) {
		this.bakSwitch = bakSwitch;
	}


	public Boolean getCameraPwr() {
		return cameraPwr;
	}


	public void setCameraPwr(Boolean cameraPwr) {
		this.cameraPwr = cameraPwr;
	}


	public Boolean getFillLightPwr() {
		return fillLightPwr;
	}


	public void setFillLightPwr(Boolean fillLightPwr) {
		this.fillLightPwr = fillLightPwr;
	}


	public Boolean getOnuPwr() {
		return onuPwr;
	}


	public void setOnuPwr(Boolean onuPwr) {
		this.onuPwr = onuPwr;
	}


	public Boolean getRouterPwr() {
		return routerPwr;
	}


	public void setRouterPwr(Boolean routerPwr) {
		this.routerPwr = routerPwr;
	}


	public Boolean getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Boolean updateTime) {
		this.updateTime = updateTime;
	}


	private Integer id;


	//@NotEmpty(message="设备编号没有设置")
    private Integer deviceId;


	//@NotEmpty(message="柜门没有设置")
    private Boolean door;
//	@NotEmpty(message="灯光没有设置")
    private Boolean light;
	//@NotEmpty(message="蜂鸣器没有设置")
    private Boolean beep;
	//@NotEmpty(message="蜂鸣器没有设置")
    private Boolean fan;
//	@NotEmpty(message="加热器没有设置")
    private Boolean heating;

	//@NotEmpty(message="备用继电器没有设置")
    private Boolean bakSwitch;

	//@NotEmpty(message="摄像机电源没有设置")
    private Boolean cameraPwr;

//	@NotEmpty(message="补光灯电源没有设置")
    private Boolean fillLightPwr;

	//@NotEmpty(message="ONU电源没有设置")
    private Boolean onuPwr;

	//@NotEmpty(message="交换机、路由器电源没有设置")
    private Boolean routerPwr;


    private Boolean updateTime;
}
