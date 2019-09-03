package cn.ritac.cpwater.mybatis.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "cpwater_devices")
public class Devices implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
	private Integer id;

	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "update_time")
	private Date updateTime;

	@Column(name = "device_num")
	private String deviceNum;

	@Column(name = "device_key")
	private String deviceKey;

	@Column(name = "device_model")
	private String deviceModel;

	private String longitude;

	private String dimension;

	// 设备位置
	@Column(name = "position_des")
	private String positionDes;

	private Boolean online;

	@Column(name = "online_time")
	private Date onlineTime;

	@Column(name = "offline_time")
	private Date offlineTime;

	/**
	 * 硬件版本
	 */
	@Column(name = "device_version")
	private String deviceVersion;

	/**
	 * 软件版本
	 */
	@Column(name = "soft_version")
	private String softVersion;

	/**
	 * 0 - 正常 ; 1 - 故障
	 */
	@Column(name = "isFault")
	private Boolean isfault;

	// @Transient
	// private String isfault_zh;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 从机个数
	 */
	@Column(name = "slaveEntry")
	private Integer slaveentry;

	/**
	 * 从机id集合
	 */
	@Column(name = "slaveIDs")
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
	@Column(name = "firstLink")
	private Integer firstlink;

	// @Transient
	// private String firstlink_zh;

	/**
	 * 当前连接方式
	 */
	@Column(name = "connForm")
	private Integer connform;

	@Transient
	private String connform_zh;

	@Transient
	private String img;

	@Transient
	private String groupName;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * 信号强度
	 */
	@Column(name = "signalQuality")
	private Integer signalquality;

	/**
	 * 工作时长
	 */
	@Column(name = "workingHours")
	private Long workinghours;

	/**
	 * 工作时长
	 */
	@Column(name = "networkingHours")
	private Long networkinghours;

	/**
	 * 重启次数
	 */
	@Column(name = "restartCount")
	private Integer restartcount;

	/**
	 * 心跳间隔
	 */
	@Column(name = "keepAliveInterval")
	private Integer keepaliveinterval;

	/**
	 * 数据更新时间
	 */
	@Column(name = "dataUpTime")
	private Integer datauptime;

	/**
	 * 首选服务器
	 */
	@Column(name = "firstServer")
	private Integer firstserver;

	// @Transient
	// private String firstserver_zh;

	/**
	 * CPU温度
	 */
	@Column(name = "cpuTemper")
	private Integer cputemper;

	/**
	 * RAM使用率
	 */
	@Column(name = "ramProportion")
	private Integer ramproportion;

	/**
	 * ROM使用率
	 */
	@Column(name = "romProportion")
	private Integer romproportion;

	/**
	 * CPU使用率
	 */
	@Column(name = "cpuProportion")
	private Integer cpuproportion;

	private static final long serialVersionUID = 1L;

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

	public Long getNetworkinghours() {
		return networkinghours;
	}

	public void setNetworkinghours(Long networkinghours) {
		this.networkinghours = networkinghours;
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
	public Integer getCputemper() {
		return cputemper;
	}

	/**
	 * 设置CPU温度
	 *
	 * @param cputemper CPU温度
	 */
	public void setCputemper(Integer cputemper) {
		this.cputemper = cputemper;
	}

	/**
	 * 获取RAM使用率
	 *
	 * @return ramProportion - RAM使用率
	 */
	public Integer getRamproportion() {
		return ramproportion;
	}

	/**
	 * 设置RAM使用率
	 *
	 * @param ramproportion RAM使用率
	 */
	public void setRamproportion(Integer ramproportion) {
		this.ramproportion = ramproportion;
	}

	/**
	 * 获取ROM使用率
	 *
	 * @return romProportion - ROM使用率
	 */
	public Integer getRomproportion() {
		return romproportion;
	}

	/**
	 * 设置ROM使用率
	 *
	 * @param romproportion ROM使用率
	 */
	public void setRomproportion(Integer romproportion) {
		this.romproportion = romproportion;
	}

	/**
	 * 获取CPU使用率
	 *
	 * @return cpuProportion - CPU使用率
	 */
	public Integer getCpuproportion() {
		return cpuproportion;
	}

	/**
	 * 设置CPU使用率
	 *
	 * @param cpuproportion CPU使用率
	 */
	public void setCpuproportion(Integer cpuproportion) {
		this.cpuproportion = cpuproportion;
	}

	public String getConnform_zh() {
		return connform_zh;
	}

	public void setConnform_zh(String connform_zh) {
		this.connform_zh = connform_zh;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

}