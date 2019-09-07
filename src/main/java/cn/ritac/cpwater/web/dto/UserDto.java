package cn.ritac.cpwater.web.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression.DateTime;

public class UserDto {

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public DateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(DateTime createTime) {
		this.createTime = createTime;
	}

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getUser_token() {
		return user_token;
	}

	public void setUser_token(String user_token) {
		this.user_token = user_token;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getHead_portrait() {
		return head_portrait;
	}

	public void setHead_portrait(String head_portrait) {
		this.head_portrait = head_portrait;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public String getUser_company() {
		return user_company;
	}

	public void setUser_company(String user_company) {
		this.user_company = user_company;
	}

	public String getUser_department() {
		return user_department;
	}

	public void setUser_department(String user_department) {
		this.user_department = user_department;
	}

	public String getUser_sex() {
		return user_sex;
	}

	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}

	public String getNew_pwd() {
		return newPwd;
	}

	public void setNew_pwd(String new_pwd) {
		this.newPwd = newPwd;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	private String user_name;

	private String userAccount;

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	@NotEmpty(message = "密码不能为空!")
	@Length(min = 5, max = 30, message = "密码必须在6-30位!")
	private String pwd;

	@NotEmpty(message = "手机号不能为空")
	private String telephone;

	private DateTime createTime;

	@NotEmpty(message = "验证码不能为空")
	private String checkCode;

	private String user_token;

	private String user_email;

	private String head_portrait;

	private String user_address;

	private String user_company;

	private String user_department;

	private String user_sex;
	@NotEmpty(message = "密码没有填写!")
	private String newPwd;

	private int pageIndex;
	private int pageSize;


	private String regId;

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	private String msgId;

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String regId) {
		this.msgId = regId;
	}

private String getNewPwd;

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getGetNewPwd() {
		return getNewPwd;
	}

	public void setGetNewPwd(String getNewPwd) {
		this.getNewPwd = getNewPwd;
	}
}
