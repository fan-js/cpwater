/*
 *
 *
 */
package cn.ritac.cpwater.util;

import java.text.DecimalFormat;

import org.springframework.util.StringUtils;

/**
 *<b>Description:</b><br>
 * @author admin
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> NumberTools
 *<br><b>CreatTime:</b> 2019年6月5日下午4:31:35
 */
public class NumberTools {

	private static DecimalFormat df;

	/**
	 * 指定格式化数字,自动四舍五入 ｛ "#,###.00"--> 1,234.56; "#.00"--> 1234.56｝
	 * @param obj
	 * @param formart
	 * @return string
	 */
	public static String doFormat(Object obj, String formart) {
		if (StringUtils.isEmpty(obj)) {
			return "格式对象不能为空。";
		}
		if (StringUtils.isEmpty(formart)) {
			formart = "#.00";
		}
		if (StringUtils.isEmpty(df)) {
			df = new DecimalFormat(formart);
		}
		return df.format(obj);
	}
}
