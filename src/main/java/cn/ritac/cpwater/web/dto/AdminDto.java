package cn.ritac.cpwater.web.dto;

import java.io.Serializable;
import java.util.List;

public class AdminDto implements Serializable {
	private Integer id;

	private String adminName;

	private String userPhone;

	private String adminDescript;

	private List<Integer> rolesIds;

	private int pageIndex;

	private int pageSize;

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

	public List<Integer> getRolesIds() {
		return rolesIds;
	}

	public void setRolesIds(List<Integer> rolesIds) {
		this.rolesIds = rolesIds;
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