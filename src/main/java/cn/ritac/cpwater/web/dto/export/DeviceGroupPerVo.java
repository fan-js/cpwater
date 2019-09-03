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
 *<br><b>ClassName:</b> DeviceGroupPerVo
 *<br><b>CreatTime:</b> 2019年6月11日下午6:54:38
 */
public class DeviceGroupPerVo {
	private Date data_time;
	private String group_name;
	private float onlineper;

	public Date getData_time() {
		return data_time;
	}

	public void setData_time(Date data_time) {
		this.data_time = data_time;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public float getOnlineper() {
		return onlineper;
	}

	public void setOnlineper(float onlineper) {
		this.onlineper = onlineper;
	}

}
