package cn.ritac.cpwater.web.dto;

import java.io.Serializable;

public class TRoleDto implements Serializable {

	/**
	 * 主键,自增
	 */
	private Integer id;

	/**
	 * 角色名称
	 */
	private String roleName;

	private static final long serialVersionUID = 1L;

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

}
