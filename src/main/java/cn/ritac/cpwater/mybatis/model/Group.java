package cn.ritac.cpwater.mybatis.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "cpwater_group")
public class Group implements Serializable {
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
	private Integer id;

	/**
	 * 数据时间
	 */
	@Column(name = "update_time")
	private Date updateTime;

	/**
	 * 分组名称
	 */
	@Column(name = "group_name")
	private String groupName;

	@Column(name = "node_key")
	private String key;

	@Column(name = "self")
	private Integer self;

	@Column(name = "depth")
	private Integer depth;

	/**
	 * 描述
	 */
	@Column(name = "group_describe")
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

	public Integer getSelf() {
		return self;
	}

	public void setSelf(Integer self) {
		this.self = self;
	}

	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

}