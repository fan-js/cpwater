/*
 *
 *
 */
package cn.ritac.cpwater.web.dto.convert;

import java.util.List;

/**
 *<b>Description:</b><br>
 * @author admin
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> SysPojo
 *<br><b>CreatTime:</b> 2019年4月30日上午10:17:59
 */
public class SysPojo {
	private String title;
	// device , bakserver , dataupopt
	private String key;
	private List<SysVO> value;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<SysVO> getValue() {
		return value;
	}

	public void setValue(List<SysVO> value) {
		this.value = value;
	}

}
