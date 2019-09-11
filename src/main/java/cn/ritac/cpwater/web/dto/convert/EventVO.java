/*
 *
 *
 */
package cn.ritac.cpwater.web.dto.convert;

import java.io.Serializable;
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
public class EventVO implements Serializable {
	private int id;
	private String deviceNum;
	private String eventName;
	private String eventDescript;
	private Date createTime;
	private String deviceId;
	private String eventInfo;


	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getEventInfo() {
		return eventInfo;
	}

	public void setEventInfo(String eventInfo) {
		this.eventInfo = eventInfo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeviceNum() {
		return deviceNum;
	}

	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDescript() {
		return eventDescript;
	}

	public void setEventDescript(String eventDescript) {
		this.eventDescript = eventDescript;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
