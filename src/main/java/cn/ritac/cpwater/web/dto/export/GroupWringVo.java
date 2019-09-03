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
 *<br><b>ClassName:</b> GroupWringVo
 *<br><b>CreatTime:</b> 2019年6月11日下午6:39:41
 */
public class GroupWringVo {
	private Date data_time;
	private String group_name;
	private String device_num;
	private String device_model;
	private String param_descript;

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

	public String getDevice_num() {
		return device_num;
	}

	public void setDevice_num(String device_num) {
		this.device_num = device_num;
	}

	public String getDevice_model() {
		return device_model;
	}

	public void setDevice_model(String device_model) {
		this.device_model = device_model;
	}

	public String getParam_descript() {
		return param_descript;
	}

	public void setParam_descript(String param_descript) {
		this.param_descript = param_descript;
	}

}
