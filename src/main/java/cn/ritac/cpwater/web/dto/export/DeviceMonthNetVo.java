/*
 *
 *
 */
package cn.ritac.cpwater.web.dto.export;

import java.util.Date;

/**
 *<b>Description:</b><br>
 * @author admin / mail: ccy.175@163.com
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> DeviceMonthNetVo
 *<br><b>CreatTime:</b> 2019年6月11日下午7:01:58
 */
public class DeviceMonthNetVo {
	private Date data_time;
	private String device_num;
	private String group_name;
	private String device_model;
	private int minutes;
	private int net;
	private float netPer;
	private int mobile_net;
	private float mobile_netPer;

	public Date getData_time() {
		return data_time;
	}

	public void setData_time(Date data_time) {
		this.data_time = data_time;
	}

	public String getDevice_num() {
		return device_num;
	}

	public void setDevice_num(String device_num) {
		this.device_num = device_num;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public String getDevice_model() {
		return device_model;
	}

	public void setDevice_model(String device_model) {
		this.device_model = device_model;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getNet() {
		return net;
	}

	public void setNet(int net) {
		this.net = net;
	}

	public float getNetPer() {
		return netPer;
	}

	public void setNetPer(float netPer) {
		this.netPer = netPer;
	}

	public int getMobile_net() {
		return mobile_net;
	}

	public void setMobile_net(int mobile_net) {
		this.mobile_net = mobile_net;
	}

	public float getMobile_netPer() {
		return mobile_netPer;
	}

	public void setMobile_netPer(float mobile_netPer) {
		this.mobile_netPer = mobile_netPer;
	}

}
