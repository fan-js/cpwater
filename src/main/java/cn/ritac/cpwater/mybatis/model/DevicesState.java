package cn.ritac.cpwater.mybatis.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "v_devices_state_user_bind")
public class DevicesState implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "uid")
	private int uid;

	@Column(name = "num")
	private int num;

	@Column(name = "onnum")
	private int onnum;

	@Column(name = "offnum")
	private int offnum;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getOnnum() {
		return onnum;
	}

	public void setOnnum(int onnum) {
		this.onnum = onnum;
	}

	public int getOffnum() {
		return offnum;
	}

	public void setOffnum(int offnum) {
		this.offnum = offnum;
	}

}
