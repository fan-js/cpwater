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
 *<br><b>ClassName:</b> AiDiDoutVO
 *<br><b>CreatTime:</b> 2019年4月27日下午3:24:54
 */
public class Di {
	private String name;
	private Object value;
	private String descript;
	//private String deviceNum;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}


}
