package cn.ritac.cpwater.mybatis.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Table(name = "cpwater_assets_function")
public class AssetsFunction implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
	private Integer id;

	/**
	 * 查看资产
	 */
	@Column(name = "list_assets")
	private Boolean listAssets;

	/**
	 * 新增资产
	 */
	@Column(name = "add_assets")
	private Boolean addAssets;

	/**
	 * 修改
	 */
	@Column(name = "update_assets")
	private Boolean updateAssets;

	/**
	 * 删除资产
	 */
	@Column(name = "delete_assets")
	private Boolean deleteAssets;

	@Column(name = "update_time")
	private Date updateTime;

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
	 * 获取查看资产
	 *
	 * @return list_assets - 查看资产
	 */
	public Boolean getListAssets() {
		return listAssets;
	}

	/**
	 * 设置查看资产
	 *
	 * @param listAssets 查看资产
	 */
	public void setListAssets(Boolean listAssets) {
		this.listAssets = listAssets;
	}

	/**
	 * 获取新增资产
	 *
	 * @return add_assets - 新增资产
	 */
	public Boolean getAddAssets() {
		return addAssets;
	}

	/**
	 * 设置新增资产
	 *
	 * @param addAssets 新增资产
	 */
	public void setAddAssets(Boolean addAssets) {
		this.addAssets = addAssets;
	}

	/**
	 * 获取修改
	 *
	 * @return update_assets - 修改
	 */
	public Boolean getUpdateAssets() {
		return updateAssets;
	}

	/**
	 * 设置修改
	 *
	 * @param updateAssets 修改
	 */
	public void setUpdateAssets(Boolean updateAssets) {
		this.updateAssets = updateAssets;
	}

	/**
	 * 获取删除资产
	 *
	 * @return delete_assets - 删除资产
	 */
	public Boolean getDeleteAssets() {
		return deleteAssets;
	}

	/**
	 * 设置删除资产
	 *
	 * @param deleteAssets 删除资产
	 */
	public void setDeleteAssets(Boolean deleteAssets) {
		this.deleteAssets = deleteAssets;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}