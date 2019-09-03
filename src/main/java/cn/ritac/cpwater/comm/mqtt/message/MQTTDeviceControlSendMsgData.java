package cn.ritac.cpwater.comm.mqtt.message;

import cn.ritac.cpwater.comm.mqtt.newdev.OTAUpdatePack;

public class MQTTDeviceControlSendMsgData {
	public int addr;
	public int type;

	public KeyValue[] aout;

	public KeyValue[] douts;

	public OTAUpdatePack updatePack;

	public int getAddr() {
		return addr;
	}

	public void setAddr(int addr) {
		this.addr = addr;
	}

	public KeyValue[] getAout() {
		return aout;
	}

	public void setAout(KeyValue[] aout) {
		this.aout = aout;
	}

	public KeyValue[] getDouts() {
		return douts;
	}

	public void setDouts(KeyValue[] douts) {
		this.douts = douts;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public OTAUpdatePack getUpdatePack() {
		return updatePack;
	}

	public void setUpdatePack(OTAUpdatePack updatePack) {
		this.updatePack = updatePack;
	}

}
