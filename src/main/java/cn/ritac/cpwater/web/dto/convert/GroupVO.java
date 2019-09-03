/*
 *
 *
 */
package cn.ritac.cpwater.web.dto.convert;

/**
 *<b>Description:</b><br>
 * @author admin
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> GroupVO
 *<br><b>CreatTime:</b> 2019年4月27日下午2:30:28
 */
public class GroupVO {
	private String group_name;
	private int num;
	private int offlineNum;
	private int onlineNum;
	private int faultNum;
	private float per;
	private int allNum;

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getOfflineNum() {
		return offlineNum;
	}

	public void setOfflineNum(int offlineNum) {
		this.offlineNum = offlineNum;
	}

	public int getOnlineNum() {
		return onlineNum;
	}

	public void setOnlineNum(int onlineNum) {
		this.onlineNum = onlineNum;
	}

	public int getFaultNum() {
		return faultNum;
	}

	public void setFaultNum(int faultNum) {
		this.faultNum = faultNum;
	}

	public float getPer() {
		return per;
	}

	public void setPer(float per) {
		this.per = per;
	}

	public int getAllNum() {
		return allNum;
	}

	public void setAllNum(int allNum) {
		this.allNum = allNum;
	}

}
