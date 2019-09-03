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
 *<br><b>ClassName:</b> SettingConfigPojo
 *<br><b>CreatTime:</b> 2019年5月5日上午9:33:06
 */
public class SettingConfigPojo {
	private String title;
	private String key;
	private String des;
	private List<SettingConfigVO> value;

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

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public List<SettingConfigVO> getValue() {
		return value;
	}

	public void setValue(List<SettingConfigVO> value) {
		this.value = value;
	}

}
