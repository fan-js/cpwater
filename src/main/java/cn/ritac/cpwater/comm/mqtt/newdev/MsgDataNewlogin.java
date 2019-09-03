package cn.ritac.cpwater.comm.mqtt.newdev;

import cn.ritac.cpwater.comm.mqtt.message.KeyValue;

public class MsgDataNewlogin {

	private KeyValue[] bakServer;

	private MsgDataDevInfo devInfo;

	private MsgDataKernelInfo kernelInfo;

	private MsgDataUpOpt dataUpOpt;

	private MsgDataNumberOfData numberOfData;

	public KeyValue[] getBakServer() {
		return bakServer;
	}

	public void setBakServer(KeyValue[] bakServer) {
		this.bakServer = bakServer;
	}

	public MsgDataDevInfo getDevInfo() {
		return devInfo;
	}

	public void setDevInfo(MsgDataDevInfo devInfo) {
		this.devInfo = devInfo;
	}

	public MsgDataKernelInfo getKernelInfo() {
		return kernelInfo;
	}

	public void setKernelInfo(MsgDataKernelInfo kernelInfo) {
		this.kernelInfo = kernelInfo;
	}

	public MsgDataUpOpt getDataUpOpt() {
		return dataUpOpt;
	}

	public void setDataUpOpt(MsgDataUpOpt dataUpOpt) {
		this.dataUpOpt = dataUpOpt;
	}

	public MsgDataNumberOfData getNumberOfData() {
		return numberOfData;
	}

	public void setNumberOfData(MsgDataNumberOfData numberOfData) {
		this.numberOfData = numberOfData;
	}

}
