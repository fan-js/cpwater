/*
 *
 *
 */
package cn.ritac.cpwater.web.dto;

/**
 *<b>Description:</b><br>
 * @author admin
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> MQTTCommandDTO
 *<br><b>CreatTime:</b> 2019年5月7日上午10:40:31
 */
public class MQTTCommandDTO {
	/**
	 * 设备主键
	 */
	private int id;
	/**
	 * 指令类型
	 */
	private int msgType;
	/**
	 * 读取片段
	 */
	private int type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
