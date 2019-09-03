package cn.ritac.cpwater.comm.mqtt.message;

import java.util.List;

import cn.ritac.cpwater.comm.mqtt.newdev.MQTTAddrVal;

public class MQTTDeviceConfigClientUpMsgData {
	private int addr;

	private List<MQTTAddrVal> reg;

	public int getAddr() {
		return addr;
	}

	public void setAddr(int addr) {
		this.addr = addr;
	}

	public List<MQTTAddrVal> getReg() {
		return reg;
	}

	public void setReg(List<MQTTAddrVal> reg) {
		this.reg = reg;
	}

}
