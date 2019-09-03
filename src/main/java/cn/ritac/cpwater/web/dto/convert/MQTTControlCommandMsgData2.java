/*
 *
 *
 */
package cn.ritac.cpwater.web.dto.convert;

/**
 *<b>Description:</b><br>
 * @author admin
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> MQTTControlCommandMsgData2
 *<br><b>CreatTime:</b> 2019年5月7日上午10:57:23
 */
public class MQTTControlCommandMsgData2 {
	private int addr;
	private int type;

	public int getAddr() {
		return addr;
	}

	public void setAddr(int addr) {
		this.addr = addr;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
