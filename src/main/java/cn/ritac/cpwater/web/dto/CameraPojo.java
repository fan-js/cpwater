/*
 *
 *
 */
package cn.ritac.cpwater.web.dto;

/**
 *<b>Description:</b><br>
 * @author admin
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> CameraPojo
 *<br><b>CreatTime:</b> 2019年6月3日下午3:35:20
 */
public class CameraPojo {
	private String sn;
	private String account;
	private String password;

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
