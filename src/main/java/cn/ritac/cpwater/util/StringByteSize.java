/*
 *
 *
 */
package cn.ritac.cpwater.util;

import java.util.HashMap;
import java.util.Map;
import org.springframework.util.StringUtils;

/**
 *<b>Description:</b><br>
 * @author admin
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> StringByteSize
 *<br><b>CreatTime:</b> 2019年5月7日下午2:53:38
 */
public class StringByteSize {

	/**
	 * 输入要统计流量的对象,自动转换进制
	 * @param obj
	 * @return byte , kb , mb  gb
	 */
	public static String getSize(Object obj) {
		if (StringUtils.isEmpty(obj)) {
			return "0B";
		}
		long size = obj.toString().getBytes().length;
		// 如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
		if (size < 1024) {
			return String.valueOf(size) + "B";
		} else {
			size = size / 1024;
		}
		// 如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
		// 因为还没有到达要使用另一个单位的时候
		// 接下去以此类推
		if (size < 1024) {
			return String.valueOf(size) + "KB";
		} else {
			size = size / 1024;
		}
		if (size < 1024) {
			// 因为如果以MB为单位的话，要保留最后1位小数，
			// 因此，把此数乘以100之后再取余
			size = size * 100;
			return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "MB";
		} else {
			// 否则如果要以GB为单位的，先除于1024再作同样的处理
			size = size * 100 / 1024;
			return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "GB";
		}
	}

	/**
	 *  计算obj流量,固定返回MB
	 * @param obj 
	 * @return MB
	 */
	public static Double getSizeMB(Object obj) {
		if (StringUtils.isEmpty(obj)) {
			return 0.0;
		}
		Double size = Double.valueOf(obj.toString().getBytes().length);
		return size / 1024;

	}

	/**
	 * 
	 * @param obj1 流量总数 GB
	 * @param obj2 已用流量累计数 GB
	 * @param obj3 本次量MB
	 * @return map  累加本次后的已用used,和剩余unused
	 */
	public static Map<String, Object> CalculationFlow(Object obj1, Object obj2, Object obj3) {
		// GB 1gb = 1024mb 1mb = 1024kb 1kb=1024byte 1byte = 8bit
		// obj1 总量GB , obj2 累计用量 GB,obj3 本次用量MB
		Map<String, Object> dataMap = new HashMap<String, Object>();
		// GB-->kb
		Double obj1_kb = (Double) obj1 * 1024 * 1024;
		// GB-->kb
		Double obj2_kb = (Double) obj2 * 1024 * 1024;
		// MB-->kb
		Double obj3_kb = (Double) obj3 * 1024;
		// 运算
		Double used_kb = obj2_kb + obj3_kb;
		Double unused_kb = obj1_kb - used_kb;
		// 转换
		// kb-->GB
		Double used = (used_kb / 1024) / 1024;
		// kb-->GB
		Double unused = (unused_kb / 1024) / 1024;

		dataMap.put("used", String.format("%.5f", used));
		dataMap.put("unused", String.format("%.5f", unused));

		return dataMap;
	}
}
