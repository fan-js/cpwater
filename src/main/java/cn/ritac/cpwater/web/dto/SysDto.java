/*
 *
 *
 */
package cn.ritac.cpwater.web.dto;

import java.util.List;

import cn.ritac.cpwater.web.dto.convert.SysPojo;

/**
 *<b>Description:</b><br>
 * @author admin
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> SysPojo
 *<br><b>CreatTime:</b> 2019年4月30日上午10:17:59
 */
public class SysDto {
	private int id;
	private List<SysPojo> data;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<SysPojo> getData() {
		return data;
	}

	public void setData(List<SysPojo> data) {
		this.data = data;
	}

}
