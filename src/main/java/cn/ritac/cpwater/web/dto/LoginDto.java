package cn.ritac.cpwater.web.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class LoginDto {
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@NotEmpty(message = "账号不能为空!")
	private String loginName;
	@NotEmpty(message = "密码没有填写!")
	private String pwd;
}
