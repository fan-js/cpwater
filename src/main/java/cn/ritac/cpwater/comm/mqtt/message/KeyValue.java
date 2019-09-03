package cn.ritac.cpwater.comm.mqtt.message;

public class KeyValue {

	private Object addr;
	private String url;
	private Object value;
	private Object val;
	private Object eventSn;

	public Object getAddr() {
		return addr;
	}

	public void setAddr(Object addr) {
		this.addr = addr;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Object getVal() {
		return val;
	}

	public void setVal(Object val) {
		this.val = val;
	}

	public Object getEventSn() {
		return eventSn;
	}

	public void setEventSn(Object eventSn) {
		this.eventSn = eventSn;
	}

}
