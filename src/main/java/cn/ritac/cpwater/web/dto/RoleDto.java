package cn.ritac.cpwater.web.dto;

import java.io.Serializable;
import java.util.List;

import cn.ritac.cpwater.mybatis.model.AssetsFunction;
import cn.ritac.cpwater.mybatis.model.DeviceFuction;
import cn.ritac.cpwater.web.dto.convert.CGroup;

public class RoleDto implements Serializable {

	/**
	 * 主键,自增
	 */
	private Integer id;

	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 角色状态 0 - 注销 ; 1 - 正常
	 */
	private Boolean roleState;

	private Integer roleLevel;

	private String roleDescript;

	private static final long serialVersionUID = 1L;

	// 设备操作
	private DeviceFuction deviceFuction;
	// 资产操作
	private AssetsFunction assetsFunction;
	// 区域集合
	private List<CGroup> roleGroupList;

	private int pageIndex;

	private int pageSize;

	public List<CGroup> getRoleGroupList() {
		return roleGroupList;
	}

	public void setRoleGroupList(List<CGroup> roleGroupList) {
		this.roleGroupList = roleGroupList;
	}

	/**
	 * 获取主键,自增
	 *
	 * @return id - 主键,自增
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置主键,自增
	 *
	 * @param id 主键,自增
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取角色名称
	 *
	 * @return role_name - 角色名称
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * 设置角色名称
	 *
	 * @param roleName 角色名称
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName == null ? null : roleName.trim();
	}

	/**
	 * 获取角色状态 0 - 注销 ; 1 - 正常
	 *
	 * @return role_state - 角色状态 0 - 注销 ; 1 - 正常
	 */
	public Boolean getRoleState() {
		return roleState;
	}

	/**
	 * 设置角色状态 0 - 注销 ; 1 - 正常
	 *
	 * @param roleState 角色状态 0 - 注销 ; 1 - 正常
	 */
	public void setRoleState(Boolean roleState) {
		this.roleState = roleState;
	}

	/**
	 * @return role_level
	 */
	public Integer getRoleLevel() {
		return roleLevel;
	}

	/**
	 * @param roleLevel
	 */
	public void setRoleLevel(Integer roleLevel) {
		this.roleLevel = roleLevel;
	}

	public String getRoleDescript() {
		return roleDescript;
	}

	public void setRoleDescript(String roleDescript) {
		this.roleDescript = roleDescript;
	}

	public DeviceFuction getDeviceFuction() {
		return deviceFuction;
	}

	public void setDeviceFuction(DeviceFuction deviceFuction) {
		this.deviceFuction = deviceFuction;
	}

	public AssetsFunction getAssetsFunction() {
		return assetsFunction;
	}

	public void setAssetsFunction(AssetsFunction assetsFunction) {
		this.assetsFunction = assetsFunction;
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

}
