/*
 *
 *
 */
package cn.ritac.cpwater.web.dto.convert;

import java.util.Date;

/**
 *<b>Description:</b><br>
 * @author admin
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> eventVO
 *<br><b>CreatTime:</b> 2019年4月27日下午1:56:34
 */
public class EventVO {
	private int id;
	private String device_num;
	private String event_name;
	private String event_descript;
	private Date create_time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDevice_num() {
		return device_num;
	}

	public void setDevice_num(String device_num) {
		this.device_num = device_num;
	}

	public String getEvent_name() {
		return event_name;
	}

	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}

	public String getEvent_descript() {
		return event_descript;
	}

	public void setEvent_descript(String event_descript) {
		this.event_descript = event_descript;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

}
