package cn.ritac.cpwater.web.dto;

import java.io.Serializable;

public class TAdminDto implements Serializable {
	private Integer id;

	private String adminName;

	private String userPhone;

	private String adminDescript;

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

}