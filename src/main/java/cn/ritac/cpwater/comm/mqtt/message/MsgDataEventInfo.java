package cn.ritac.cpwater.comm.mqtt.message;

public class MsgDataEventInfo {
	private int eventSn;
	private String eventTime;
	private String eventContent;

	public int getEventSn() {
		return eventSn;
	}

	public void setEventSn(int eventSn) {
		this.eventSn = eventSn;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public String getEventContent() {
		return eventContent;
	}

	public void setEventContent(String eventContent) {
		this.eventContent = eventContent;
	}

}
