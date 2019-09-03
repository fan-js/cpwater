/*
 *
 *
 */
package cn.ritac.cpwater.web.dto.convert;

/**
 *<b>Description:</b><br>
 *	用于转换页面数据格式,当做工具实体类用
 * @author admin
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> StatisticsData
 *<br><b>CreatTime:</b> 2019年4月23日上午9:17:42
 */
public class StatisticsData {
	/**
	 * 程序转出中文,给用户看
	 */
	private String name;
	/**
	 * 个数数据
	 */
	private int value;
	/**
	 * 百分百
	 */
	private float per;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public float getPer() {
		return per;
	}

	public void setPer(float per) {
		this.per = per;
	}

}
