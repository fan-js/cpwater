package cn.ritac.cpwater.web.dto.convert;

import java.util.List;

public class FuctionFather {
	// 主键,用于前台区分 1. user ; 2. device
	private String id;
	// 权限描述： 1. 用户权限 ；2. 设备权限
	private String describe;

	private boolean isChecked;

	// 权限子一级
	private List<FuctionChild> actionList;

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public List<FuctionChild> getActionList() {
		return actionList;
	}

	public void setActionList(List<FuctionChild> actionList) {
		this.actionList = actionList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

}
