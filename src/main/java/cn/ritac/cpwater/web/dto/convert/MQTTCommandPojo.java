/*
 *
 *
 */
package cn.ritac.cpwater.web.dto.convert;

import java.util.List;

/**
 *<b>Description:</b><br>
 * @author admin
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> MQTTCommandPojo
 *<br><b>CreatTime:</b> 2019年5月11日上午11:22:27
 */
public class MQTTCommandPojo {
	private int id;
	private List<AiDiDoutVO> data;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<AiDiDoutVO> getData() {
		return data;
	}

	public void setData(List<AiDiDoutVO> data) {
		this.data = data;
	}

}
