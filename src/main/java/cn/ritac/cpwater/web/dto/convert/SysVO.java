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
 *<br><b>ClassName:</b> SysVO
 *<br><b>CreatTime:</b> 2019年4月30日上午10:17:16
 */
public class SysVO {
	
	private String name;
	/**
	 * device:
	 ** 	firstLink
	 ** 	keepAliveInterval
	 **  	dataUpTime
	 **  	firstServer
	 *  
	 * bakserver:
	 **		url
	 **		port
	 *
	 * dataupopt:
	 **		ain
	 **		aout
	 **		din
	 **		dout
	 **		reg
	 **		event 
	 */
	private String key;
	
	private Object value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
