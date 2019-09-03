package cn.ritac.cpwater.web.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class GroupDto implements Serializable {
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
	private Integer id;

	/**
	 * 数据时间
	 */
	private Date updateTime;

	/**
	 * 分组名称
	 */
	private String groupName;

	// 节点key
	private String key;

	// 子节点名称
	private String childrenName;

	/**
	 * 描述
	 */
	private String describe;

	private static final long serialVersionUID = 1L;

	/**
	 * 获取主键
	 *
	 * @return id - 主键
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置主键
	 *
	 * @param id 主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取数据时间
	 *
	 * @return update_time - 数据时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置数据时间
	 *
	 * @param updateTime 数据时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取分组名称
	 *
	 * @return group_name - 分组名称
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * 设置分组名称
	 *
	 * @param groupName 分组名称
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName == null ? null : groupName.trim();
	}

	/**
	 * 获取描述
	 *
	 * @return describe - 描述
	 */
	public String getDescribe() {
		return describe;
	}

	/**
	 * 设置描述
	 *
	 * @param describe 描述
	 */
	public void setDescribe(String describe) {
		this.describe = describe == null ? null : describe.trim();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getChildrenName() {
		return childrenName;
	}

	public void setChildrenName(String childrenName) {
		this.childrenName = childrenName;
	}

}