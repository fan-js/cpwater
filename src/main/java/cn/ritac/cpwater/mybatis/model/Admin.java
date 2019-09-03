package cn.ritac.cpwater.mybatis.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "cpwater_admin")
public class Admin implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
	private Integer id;

	@Column(name = "admin_name")
	private String adminName;

	@Column(name = "user_phone")
	private String userPhone;

	@Column(name = "admin_descript")
	private String adminDescript;

	@Transient
	@Column(name = "role_name")
	private String roleName;

	@Transient
	@Column(name = "userName")
	private String userName;

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
	 * @return admin_name
	 */
	public String getAdminName() {
		return adminName;
	}

	/**
	 * @param adminName
	 */
	public void setAdminName(String adminName) {
		this.adminName = adminName == null ? null : adminName.trim();
	}

	/**
	 * @return user_phone
	 */
	public String getUserPhone() {
		return userPhone;
	}

	/**
	 * @param userPhone
	 */
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone == null ? null : userPhone.trim();
	}

	/**
	 * @return admin_descript
	 */
	public String getAdminDescript() {
		return adminDescript;
	}

	/**
	 * @param adminDescript
	 */
	public void setAdminDescript(String adminDescript) {
		this.adminDescript = adminDescript == null ? null : adminDescript.trim();
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}