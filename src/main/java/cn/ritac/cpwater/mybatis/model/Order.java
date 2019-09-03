package cn.ritac.cpwater.mybatis.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "cpwater_order")
public class Order implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
	private Integer id;

	/**
	 * 建单时间
	 */
	@Column(name = "create_time")
	private Date createTime;

	/**
	 * 事件外键,取(sn , content)
	 */
	@Column(name = "event_id")
	private Integer eventId;

	/**
	 * 用户外键,取 (user_name)
	 */
	@Column(name = "user_id")
	private Integer userId;

	/**
	 * 0 - 未派单 ; 1-处理中 ; 2 - 已处理
	 */
	@Column(name = "order_state")
	private Integer orderState;

	@Column(name = "order_descript")
	private String orderDescript;

	/**
	 * 派单时间
	 */
	@Column(name = "dispatch_time")
	private Date dispatchTime;

	/**
	 * 修改时间
	 */
	@Column(name = "update_time")
	private Date updateTime;

	/**
	 * 处理时长
	 */
	@Column(name = "processing_time")
	private String processingTime;

	/**
	 *现场图片
	 */
	@Column(name = "images")
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
	 * 获取事件外键,取(sn , content)
	 *
	 * @return event_id - 事件外键,取(sn , content)
	 */
	public Integer getEventId() {
		return eventId;
	}

	/**
	 * 设置事件外键,取(sn , content)
	 *
	 * @param eventId 事件外键,取(sn , content)
	 */
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	/**
	 * 获取用户外键,取 (user_name)
	 *
	 * @return user_id - 用户外键,取 (user_name)
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 设置用户外键,取 (user_name)
	 *
	 * @param userId 用户外键,取 (user_name)
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 获取0 - 未派单 ; 1-已接单 ; 2 - 已处理
	 *
	 * @return order_state - 0 - 未派单 ; 1-已接单 ; 2 - 已处理
	 */
	public Integer getOrderState() {
		return orderState;
	}

	/**
	 * 设置0 - 未派单 ; 1-已接单 ; 2 - 已处理
	 *
	 * @param orderState 0 - 未派单 ; 1-已接单 ; 2 - 已处理
	 */
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
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

}