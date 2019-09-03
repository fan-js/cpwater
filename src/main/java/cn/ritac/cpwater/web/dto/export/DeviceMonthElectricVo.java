package cn.ritac.cpwater.web.dto.export;

import java.util.Date;

/**
 *<b>Description:</b><br>
 * @author admin / mail: ccy.175@163.com
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> DeviceMonthElectricVo
 *<br><b>CreatTime:</b> 2019年6月11日下午6:57:21
 */
public class DeviceMonthElectricVo {
	private Date data_time;
	private String device_num;
	private String group_name;
	private String device_model;
	private int minutes;
	private int electric;
	private float electricPer;
	private int ups_electric;
	private float ups_electricPer;

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

	public int getElectric() {
		return electric;
	}

	public void setElectric(int electric) {
		this.electric = electric;
	}

	public float getElectricPer() {
		return electricPer;
	}

	public void setElectricPer(float electricPer) {
		this.electricPer = electricPer;
	}

	public int getUps_electric() {
		return ups_electric;
	}

	public void setUps_electric(int ups_electric) {
		this.ups_electric = ups_electric;
	}

	public float getUps_electricPer() {
		return ups_electricPer;
	}

	public void setUps_electricPer(float ups_electricPer) {
		this.ups_electricPer = ups_electricPer;
	}

}
