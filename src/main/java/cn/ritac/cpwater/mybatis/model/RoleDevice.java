package cn.ritac.cpwater.mybatis.model;

import java.io.Serializable;
import javax.persistence.*;
			   
@Table(name = "cpwater_role_device")
public class RoleDevice implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
	private Integer id;

	/**
	 * 角色主键
	 */
	@Column(name = "role_id")
	private Integer roleId;

	/**
	 * 分组表主键
	 */
	@Column(name = "group_id")
	private Integer groupId;

	/**
	 * 设备表主键
	 */
	@Column(name = "device_id")
	private Integer deviceId;

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
	 * 获取角色主键
	 *
	 * @return role_id - 角色主键
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * 设置角色主键
	 *
	 * @param roleId 角色主键
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * 获取设备表主键
	 *
	 * @return device_id - 设备表主键
	 */
	public Integer getDeviceId() {
		return deviceId;
	}

	/**
	 * 设置设备表主键
	 *
	 * @param deviceId 设备表主键
	 */
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

}