package cn.ritac.cpwater.comm.mqtt.message;

public class MsgDataEvent {
	
	private int addr;
	
	private MsgDataEventInfo[] eventInfo;

	public int getAddr() {
		return addr;
	}
	public void setAddr(int addr) {
		this.addr = addr;
	}
	
	public MsgDataEventInfo[] getEventInfo() {
		return eventInfo;
	}
	public void setEventInfo(MsgDataEventInfo[] eventInfo) {
		this.eventInfo = eventInfo;
	}

	
}
