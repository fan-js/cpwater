/*
 *
 *
 */
package cn.ritac.cpwater.web.dto.convert;

import java.util.List;

import cn.ritac.cpwater.comm.mqtt.newdev.MQTTAddrVal;

/**
 *<b>Description:</b><br>
 * @author admin
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> MQTTControlCommandMsgData
 *<br><b>CreatTime:</b> 2019年5月7日上午10:53:57
 */
public class MQTTControlCommandMsgData3 {
	private int addr;

	private List<MQTTAddrVal> dout;

	public int getAddr() {
		return addr;
	}

	public void setAddr(int addr) {
		this.addr = addr;
	}

	public List<MQTTAddrVal> getDout() {
		return dout;
	}

	public void setDout(List<MQTTAddrVal> dout) {
		this.dout = dout;
	}

}
