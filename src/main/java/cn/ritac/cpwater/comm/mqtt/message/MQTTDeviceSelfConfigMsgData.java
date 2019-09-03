/*
 *
 *
 */
package cn.ritac.cpwater.comm.mqtt.message;

import java.util.Date;
import java.util.List;

import cn.ritac.cpwater.comm.mqtt.newdev.MQTTAddrVal;
import cn.ritac.cpwater.comm.mqtt.newdev.MsgDataUpOpt;

/**
 *<b>Description:</b><br>
 * @author admin
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> MQTTDeviceSelfConfigMsgData
 *<br><b>CreatTime:</b> 2019年5月6日上午9:32:57
 */
public class MQTTDeviceSelfConfigMsgData {
	private Object addr;
	private Date time;
	private int firstLink;
	private int keepAliveInterval;
	private int firstServer;
	private List<MQTTAddrVal> bakServer;
	private MsgDataUpOpt dataUpOpt;

	public Object getAddr() {
		return addr;
	}

	public void setAddr(Object addr) {
		this.addr = addr;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getFirstLink() {
		return firstLink;
	}

	public void setFirstLink(int firstLink) {
		this.firstLink = firstLink;
	}

	public int getKeepAliveInterval() {
		return keepAliveInterval;
	}

	public void setKeepAliveInterval(int keepAliveInterval) {
		this.keepAliveInterval = keepAliveInterval;
	}

	public int getFirstServer() {
		return firstServer;
	}

	public void setFirstServer(int firstServer) {
		this.firstServer = firstServer;
	}

	public List<MQTTAddrVal> getBakServer() {
		return bakServer;
	}

	public void setBakServer(List<MQTTAddrVal> bakServer) {
		this.bakServer = bakServer;
	}

	public MsgDataUpOpt getDataUpOpt() {
		return dataUpOpt;
	}

	public void setDataUpOpt(MsgDataUpOpt dataUpOpt) {
		this.dataUpOpt = dataUpOpt;
	}

}
