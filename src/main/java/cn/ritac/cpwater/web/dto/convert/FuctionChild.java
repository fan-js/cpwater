package cn.ritac.cpwater.web.dto.convert;

public class FuctionChild {
	// 操作：1. query ；2. add ； 3. update ； 4. delete
	private String action;

	//描述： 1. 查询； 2. 添加 ； 3. 修改 ； 4. 删除
	private String describe;

	//权限否： 1. true ； 2. false
//	private boolean defaultCheck;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

//	public boolean isDefaultCheck() {
//		return defaultCheck;
//	}
//
//	public void setDefaultCheck(boolean defaultCheck) {
//		this.defaultCheck = defaultCheck;
//	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

}
