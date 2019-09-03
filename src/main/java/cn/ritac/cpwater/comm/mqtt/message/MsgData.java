package cn.ritac.cpwater.comm.mqtt.message;

import cn.ritac.cpwater.comm.mqtt.newdev.MsgDataKernelInfo;

public class MsgData {

	private KeyValue[] ain;
	private KeyValue[] din;
	private KeyValue[] dout;
	private int addr;
	private KeyValue[] reg;

	private MsgDataKernelInfo kernelInfo;

	// private int aiEntry;
	// private int diEntry;
	// private int doEntry;
	// private int regEntry;

	public KeyValue[] getAin() {
		return ain;
	}

	public void setAin(KeyValue[] ain) {
		this.ain = ain;
	}

	public KeyValue[] getDin() {
		return din;
	}

	public void setDin(KeyValue[] din) {
		this.din = din;
	}

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

	public MsgDataKernelInfo getKernelInfo() {
		return kernelInfo;
	}

	public void setKernelInfo(MsgDataKernelInfo kernelInfo) {
		this.kernelInfo = kernelInfo;
	}

	public KeyValue[] getDout() {
		return dout;
	}

	public void setDout(KeyValue[] dout) {
		this.dout = dout;
	}

}
