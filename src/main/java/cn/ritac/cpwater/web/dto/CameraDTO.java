/*
 *
 *
 */
package cn.ritac.cpwater.web.dto;

import java.util.List;

/**
 *<b>Description:</b><br>
 * @author admin
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> CameraDTO
 *<br><b>CreatTime:</b> 2019年6月3日下午3:35:03
 */
public class CameraDTO {
	private int id;
	private List<CameraPojo> data;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<CameraPojo> getData() {
		return data;
	}

	public void setData(List<CameraPojo> data) {
		this.data = data;
	}

}
