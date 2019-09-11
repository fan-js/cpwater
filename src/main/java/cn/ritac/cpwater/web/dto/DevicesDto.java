package cn.ritac.cpwater.web.dto;

import java.io.Serializable;
import java.util.Date;

public class DevicesDto implements Serializable {
	private Integer id;

	private String userphone;

	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	private Integer groupId;

	private Date createTime;

	private Date updateTime;

	private String deviceNum;

	private String deviceKey;

	private String deviceModel;

	// 经度
	private String longitude;
	// 维度
	private String dimension;

	private String positionDes;

	private Boolean online;

	private Date onlineTime;

	private Date offlineTime;

	/**
	 * 硬件版本
	 */
	private String deviceVersion;

	/**
	 * 软件版本
	 */
	private String softVersion;

	/**
	 * 0 - 正常 ; 1 - 故障
	 */
	private Boolean isfault;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 从机个数
	 */
	private Integer slaveentry;

	/**
	 * 从机id集合
	 */
	private String slaveids;

	/**
	 * 电压
	 */
	private Float voltage;

	/**
	 * 电流
	 */
	private Float current;

	/**
	 * 首选连接方式
	 */
	private Integer firstlink;

	/**
	 * 当前连接方式
	 */
	private Integer connform;

	/**
	 * 信号强度
	 */
	private Integer signalquality;

	/**
	 * 工作时长
	 */
	private Long workinghours;

	/**
	 * 重启次数
	 */
	private Integer restartcount;

	/**
	 * 心跳间隔
	 */
	private Integer keepaliveinterval;

	/**
	 * 数据更新时间
	 */
	private Integer datauptime;

	/**
	 * 首选服务器
	 */
	private Integer firstserver;

	/**
	 * CPU温度
	 */
	private Float cputemper;

	/**
	 * RAM使用率
	 */
	private Float ramproportion;

	/**
	 * ROM使用率
	 */
	private Float romproportion;

	/**
	 * CPU使用率
	 */
	private Float cpuproportion;

	private int pageIndex;

	private int pageSize;

	private static final long serialVersionUID = 1L;

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

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
	 * @return create_time
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return update_time
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
	 * @return device_key
	 */
	public String getDeviceKey() {
		return deviceKey;
	}

	/**
	 * @param deviceKey
	 */
	public void setDeviceKey(String deviceKey) {
		this.deviceKey = deviceKey == null ? null : deviceKey.trim();
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

	/**
	 * @return position_des
	 */
	public String getPositionDes() {
		return positionDes;
	}

	/**
	 * @param positionDes
	 */
	public void setPositionDes(String positionDes) {
		this.positionDes = positionDes == null ? null : positionDes.trim();
	}

	/**
	 * @return online
	 */
	public Boolean getOnline() {
		return online;
	}

	/**
	 * @param online
	 */
	public void setOnline(Boolean online) {
		this.online = online;
	}

	/**
	 * @return online_time
	 */
	public Date getOnlineTime() {
		return onlineTime;
	}

	/**
	 * @param onlineTime
	 */
	public void setOnlineTime(Date onlineTime) {
		this.onlineTime = onlineTime;
	}

	/**
	 * @return offline_time
	 */
	public Date getOfflineTime() {
		return offlineTime;
	}

	/**
	 * @param offlineTime
	 */
	public void setOfflineTime(Date offlineTime) {
		this.offlineTime = offlineTime;
	}

	/**
	 * 获取硬件版本
	 *
	 * @return device_version - 硬件版本
	 */
	public String getDeviceVersion() {
		return deviceVersion;
	}

	/**
	 * 设置硬件版本
	 *
	 * @param deviceVersion 硬件版本
	 */
	public void setDeviceVersion(String deviceVersion) {
		this.deviceVersion = deviceVersion == null ? null : deviceVersion.trim();
	}

	/**
	 * 获取软件版本
	 *
	 * @return soft_version - 软件版本
	 */
	public String getSoftVersion() {
		return softVersion;
	}

	/**
	 * 设置软件版本
	 *
	 * @param softVersion 软件版本
	 */
	public void setSoftVersion(String softVersion) {
		this.softVersion = softVersion == null ? null : softVersion.trim();
	}

	/**
	 * 获取0 - 正常 ; 1 - 故障
	 *
	 * @return isFault - 0 - 正常 ; 1 - 故障
	 */
	public Boolean getIsfault() {
		return isfault;
	}

	/**
	 * 设置0 - 正常 ; 1 - 故障
	 *
	 * @param isfault 0 - 正常 ; 1 - 故障
	 */
	public void setIsfault(Boolean isfault) {
		this.isfault = isfault;
	}

	/**
	 * 获取备注
	 *
	 * @return remark - 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置备注
	 *
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	/**
	 * 获取从机个数
	 *
	 * @return slaveEntry - 从机个数
	 */
	public Integer getSlaveentry() {
		return slaveentry;
	}

	/**
	 * 设置从机个数
	 *
	 * @param slaveentry 从机个数
	 */
	public void setSlaveentry(Integer slaveentry) {
		this.slaveentry = slaveentry;
	}

	/**
	 * 获取从机id集合
	 *
	 * @return slaveIDs - 从机id集合
	 */
	public String getSlaveids() {
		return slaveids;
	}

	/**
	 * 设置从机id集合
	 *
	 * @param slaveids 从机id集合
	 */
	public void setSlaveids(String slaveids) {
		this.slaveids = slaveids == null ? null : slaveids.trim();
	}

	/**
	 * 获取电压
	 *
	 * @return voltage - 电压
	 */
	public Float getVoltage() {
		return voltage;
	}

	/**
	 * 设置电压
	 *
	 * @param voltage 电压
	 */
	public void setVoltage(Float voltage) {
		this.voltage = voltage;
	}

	/**
	 * 获取电流
	 *
	 * @return current - 电流
	 */
	public Float getCurrent() {
		return current;
	}

	/**
	 * 设置电流
	 *
	 * @param current 电流
	 */
	public void setCurrent(Float current) {
		this.current = current;
	}

	/**
	 * 获取首选连接方式
	 *
	 * @return firstLink - 首选连接方式
	 */
	public Integer getFirstlink() {
		return firstlink;
	}

	/**
	 * 设置首选连接方式
	 *
	 * @param firstlink 首选连接方式
	 */
	public void setFirstlink(Integer firstlink) {
		this.firstlink = firstlink;
	}

	/**
	 * 获取当前连接方式
	 *
	 * @return connForm - 当前连接方式
	 */
	public Integer getConnform() {
		return connform;
	}

	/**
	 * 设置当前连接方式
	 *
	 * @param connform 当前连接方式
	 */
	public void setConnform(Integer connform) {
		this.connform = connform;
	}

	/**
	 * 获取信号强度
	 *
	 * @return signalQuality - 信号强度
	 */
	public Integer getSignalquality() {
		return signalquality;
	}

	/**
	 * 设置信号强度
	 *
	 * @param signalquality 信号强度
	 */
	public void setSignalquality(Integer signalquality) {
		this.signalquality = signalquality;
	}

	/**
	 * 获取工作时长
	 *
	 * @return workingHours - 工作时长
	 */
	public Long getWorkinghours() {
		return workinghours;
	}

	/**
	 * 设置工作时长
	 *
	 * @param workinghours 工作时长
	 */
	public void setWorkinghours(Long workinghours) {
		this.workinghours = workinghours;
	}

	/**
	 * 获取重启次数
	 *
	 * @return restartCount - 重启次数
	 */
	public Integer getRestartcount() {
		return restartcount;
	}

	/**
	 * 设置重启次数
	 *
	 * @param restartcount 重启次数
	 */
	public void setRestartcount(Integer restartcount) {
		this.restartcount = restartcount;
	}

	/**
	 * 获取心跳间隔
	 *
	 * @return keepAliveInterval - 心跳间隔
	 */
	public Integer getKeepaliveinterval() {
		return keepaliveinterval;
	}

	/**
	 * 设置心跳间隔
	 *
	 * @param keepaliveinterval 心跳间隔
	 */
	public void setKeepaliveinterval(Integer keepaliveinterval) {
		this.keepaliveinterval = keepaliveinterval;
	}

	/**
	 * 获取数据更新时间
	 *
	 * @return dataUpTime - 数据更新时间
	 */
	public Integer getDatauptime() {
		return datauptime;
	}

	/**
	 * 设置数据更新时间
	 *
	 * @param datauptime 数据更新时间
	 */
	public void setDatauptime(Integer datauptime) {
		this.datauptime = datauptime;
	}

	/**
	 * 获取首选服务器
	 *
	 * @return firstServer - 首选服务器
	 */
	public Integer getFirstserver() {
		return firstserver;
	}

	/**
	 * 设置首选服务器
	 *
	 * @param firstserver 首选服务器
	 */
	public void setFirstserver(Integer firstserver) {
		this.firstserver = firstserver;
	}

	/**
	 * 获取CPU温度
	 *
	 * @return cpuTemper - CPU温度
	 */
	public Float getCputemper() {
		return cputemper;
	}

	/**
	 * 设置CPU温度
	 *
	 * @param cputemper CPU温度
	 */
	public void setCputemper(Float cputemper) {
		this.cputemper = cputemper;
	}

	/**
	 * 获取RAM使用率
	 *
	 * @return ramProportion - RAM使用率
	 */
	public Float getRamproportion() {
		return ramproportion;
	}

	/**
	 * 设置RAM使用率
	 *
	 * @param ramproportion RAM使用率
	 */
	public void setRamproportion(Float ramproportion) {
		this.ramproportion = ramproportion;
	}

	/**
	 * 获取ROM使用率
	 *
	 * @return romProportion - ROM使用率
	 */
	public Float getRomproportion() {
		return romproportion;
	}

	/**
	 * 设置ROM使用率
	 *
	 * @param romproportion ROM使用率
	 */
	public void setRomproportion(Float romproportion) {
		this.romproportion = romproportion;
	}

	/**
	 * 获取CPU使用率
	 *
	 * @return cpuProportion - CPU使用率
	 */
	public Float getCpuproportion() {
		return cpuproportion;
	}

	/**
	 * 设置CPU使用率
	 *
	 * @param cpuproportion CPU使用率
	 */
	public void setCpuproportion(Float cpuproportion) {
		this.cpuproportion = cpuproportion;
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

	private Long networkinghours;

	public Long getNetworkinghours() {
		return networkinghours;
	}

	public void setNetworkinghours(Long networkinghours) {
		this.networkinghours = networkinghours;
	}
}