package cn.ritac.cpwater.comm.mqtt.message;

public class MQTTDeviceConfigClientUpMsgDataParams {
	private int addr;
	private KeyValue[] reg;

	public int getAddr() {
		return addr;
	}

	public void setAddr(int addr) {
		this.addr = addr;
	}

	public KeyValue[] getReg() {
		return reg;
	}

	public void setReg(KeyValue[] reg) {
		this.reg = reg;
	}

}
