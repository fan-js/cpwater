package cn.ritac.cpwater.mybatis.model;

import java.io.Serializable;

public class Params implements Serializable {
	private Integer id;
	private String orderType;
	private String orderName;
	private String orderAction;
	private String orderDescript;

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

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrderAction() {
		return orderAction;
	}

	public void setOrderAction(String orderAction) {
		this.orderAction = orderAction;
	}

	public String getOrderDescript() {
		return orderDescript;
	}

	public void setOrderDescript(String orderDescript) {
		this.orderDescript = orderDescript;
	}

}