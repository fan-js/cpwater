package cn.ritac.cpwater.mybatis.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "cpwater_workinghour")
public class WorkingHour implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
	private Integer id;

	/**
	 * 数据时间
	 */
	@Column(name = "create_time")
	private Date createTime;

	/**
	 * 设备主键
	 */
	@Column(name = "device_id")
	private Integer deviceId;

	/**
	 * 年份
	 */
	private Integer years;

	/**
	 * 月份
	 */
	private Integer months;

	/**
	 * 电当前时长
	 */
	@Column(name = "workingHours")
	private Long workinghours;

	/**
	 * 电 小时
	 */
	private Long hours;

	/**
	 * 电 分钟
	 */
	private Long minutes;

	/**
	 * 网当前时长
	 */
	@Column(name = "networkingHours")
	private Long networkinghours;

	/**
	 * 网 小时
	 */
	@Column(name = "net_hours")
	private Long netHours;

	/**
	 * 网 分钟
	 */
	@Column(name = "net_minutes")
	private Long netMinutes;

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
	 * 获取数据时间
	 *
	 * @return create_time - 数据时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置数据时间
	 *
	 * @param createTime 数据时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取设备主键
	 *
	 * @return device_id - 设备主键
	 */
	public Integer getDeviceId() {
		return deviceId;
	}

	/**
	 * 设置设备主键
	 *
	 * @param deviceId 设备主键
	 */
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * 获取月份
	 *
	 * @return month - 月份
	 */
	public Integer getMonths() {
		return months;
	}

	/**
	 * 设置月份
	 *
	 * @param months 月份
	 */
	public void setMonths(Integer months) {
		this.months = months;
	}

	/**
	 * 获取电当前时长
	 *
	 * @return workingHours - 电当前时长
	 */
	public Long getWorkinghours() {
		return workinghours;
	}

	/**
	 * 设置电当前时长
	 *
	 * @param workinghours 电当前时长
	 */
	public void setWorkinghours(Long workinghours) {
		this.workinghours = workinghours;
	}

	/**
	 * 获取电 小时
	 *
	 * @return hours - 电 小时
	 */
	public Long getHours() {
		return hours;
	}

	/**
	 * 设置电 小时
	 *
	 * @param hours 电 小时
	 */
	public void setHours(Long hours) {
		this.hours = hours;
	}

	/**
	 * 获取电 分钟
	 *
	 * @return minutes - 电 分钟
	 */
	public Long getMinutes() {
		return minutes;
	}

	/**
	 * 设置电 分钟
	 *
	 * @param minutes 电 分钟
	 */
	public void setMinutes(Long minutes) {
		this.minutes = minutes;
	}

	/**
	 * 获取网当前时长
	 *
	 * @return networkingHours - 网当前时长
	 */
	public Long getNetworkinghours() {
		return networkinghours;
	}

	/**
	 * 设置网当前时长
	 *
	 * @param networkinghours 网当前时长
	 */
	public void setNetworkinghours(Long networkinghours) {
		this.networkinghours = networkinghours;
	}

	/**
	 * 获取网 小时
	 *
	 * @return net_hours - 网 小时
	 */
	public Long getNetHours() {
		return netHours;
	}

	/**
	 * 设置网 小时
	 *
	 * @param netHours 网 小时
	 */
	public void setNetHours(Long netHours) {
		this.netHours = netHours;
	}

	/**
	 * 获取网 分钟
	 *
	 * @return net_minutes - 网 分钟
	 */
	public Long getNetMinutes() {
		return netMinutes;
	}

	/**
	 * 设置网 分钟
	 *
	 * @param netMinutes 网 分钟
	 */
	public void setNetMinutes(Long netMinutes) {
		this.netMinutes = netMinutes;
	}

	public Integer getYears() {
		return years;
	}

	public void setYears(Integer years) {
		this.years = years;
	}

}