package cn.ritac.cpwater.comm.mqtt.message;

import cn.ritac.cpwater.comm.mqtt.newdev.OTAUpdatePack;

public class MQTTDeviceControlSendParamsPjo {

	public int addr;

	//public int type;

	public OTAUpdatePack updatePack;

	//public KeyValue[] aout;
	//public KeyValue[] dout;

	public int getAddr() {
		return addr;
	}

	public void setAddr(int addr) {
		this.addr = addr;
	}

	public OTAUpdatePack getUpdatePack() {
		return updatePack;
	}

	public void setUpdatePack(OTAUpdatePack updatePack) {
		this.updatePack = updatePack;
	}

//	public KeyValue[] getAout() {
//		return aout;
//	}
//
//	public void setAout(KeyValue[] aout) {
//		this.aout = aout;
//	}
//
//	public KeyValue[] getDout() {
//		return dout;
//	}
//
//	public void setDout(KeyValue[] dout) {
//		this.dout = dout;
//	}

//	public int getType() {
//		return type;
//	}
//
//	public void setType(int type) {
//		this.type = type;
//	}

}
