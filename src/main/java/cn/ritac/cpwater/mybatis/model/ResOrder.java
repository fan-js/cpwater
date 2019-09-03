package cn.ritac.cpwater.mybatis.model;

import java.io.Serializable;
import java.util.Date;

public class ResOrder implements Serializable {
	private Integer id;

	/**
	 * 建单时间
	 */
	private Date createTime;

	/**
	 * 事件外键,取(sn , content)
	 */
	private String eventName;

	private String deviceNum;

	private String groupName;

	/**
	 * 用户外键,取 (user_name)
	 */
	private String userAccount;

	/**
	 * 0 - 未派单 ; 1-已接单 ; 2 - 已处理
	 */
	private String orderAction;

	private String orderState;

	private String orderDescript;

	/**
	 * 派单时间
	 */
	private Date dispatchTime;

	/**
	 * 修改时间
	 */
	private Date updateTime;

	/**
	 * 处理时长
	 */
	private String processingTime;

	/**
	 *现场图片
	 */
	private String images;

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
	 * 获取建单时间
	 *
	 * @return create_time - 建单时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置建单时间
	 *
	 * @param createTime 建单时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return order_descript
	 */
	public String getOrderDescript() {
		return orderDescript;
	}

	/**
	 * @param orderDescript
	 */
	public void setOrderDescript(String orderDescript) {
		this.orderDescript = orderDescript == null ? null : orderDescript.trim();
	}

	/**
	 * 获取派单时间
	 *
	 * @return dispatch_time - 派单时间
	 */
	public Date getDispatchTime() {
		return dispatchTime;
	}

	/**
	 * 设置派单时间
	 *
	 * @param dispatchTime 派单时间
	 */
	public void setDispatchTime(Date dispatchTime) {
		this.dispatchTime = dispatchTime;
	}

	/**
	 * 获取修改时间
	 *
	 * @return update_time - 修改时间
	 */
	public Date getUpdateTime() {

		return updateTime;
	}

	/**
	 * 设置修改时间
	 *
	 * @param updateTime 修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getOrderAction() {
		return orderAction;
	}

	public void setOrderAction(String orderAction) {
		this.orderAction = orderAction;
	}

	public String getProcessingTime() {
		return processingTime;
	}

	public void setProcessingTime(String processingTime) {
		this.processingTime = processingTime;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getDeviceNum() {
		return deviceNum;
	}

	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}