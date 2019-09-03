package cn.ritac.cpwater.web.dto.convert;

import java.io.Serializable;
import java.util.List;

public class CGroup implements Serializable {
	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 分组名称
	 */
	private String groupName;
	// 当前分组下设备集合

	private List<Integer> roleDeviceList;

	private List<CDevices> deviceList;

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<Integer> getRoleDeviceList() {
		return roleDeviceList;
	}

	public void setRoleDeviceList(List<Integer> roleDeviceList) {
		this.roleDeviceList = roleDeviceList;
	}

	public List<CDevices> getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(List<CDevices> deviceList) {
		this.deviceList = deviceList;
	}

}