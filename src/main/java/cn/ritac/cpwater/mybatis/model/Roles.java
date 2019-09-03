package cn.ritac.cpwater.mybatis.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "cpwater_roles")
public class Roles implements Serializable {
	/**
	 * 主键,自增
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
	private Integer id;

	/**
	 * 角色名称
	 */
	@Column(name = "role_name")
	private String roleName;

	/**
	 * 角色状态 0 - 注销 ; 1 - 正常
	 */
	@Column(name = "role_state")
	private Boolean roleState;

	@Column(name = "role_level")
	private Integer roleLevel;

	@Column(name = "role_descript")
	private String roleDescript;

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

}