/*
 *
 *
 */
package cn.ritac.cpwater.web.dto.export;

import java.util.Date;

/**
 *<b>Description:</b><br>
 * @author admin / mail: ccy.175@163.com
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> OrderGroupPerVo
 *<br><b>CreatTime:</b> 2019年6月11日下午7:05:10
 */
public class OrderGroupPerVo {
	private Date data_time;
	private String group_name;
	private int num;
	private int untreated;
	private int processing;
	private int processed;
	private float processedPer;

	public Date getData_time() {
		return data_time;
	}

	public void setData_time(Date data_time) {
		this.data_time = data_time;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getUntreated() {
		return untreated;
	}

	public void setUntreated(int untreated) {
		this.untreated = untreated;
	}

	public int getProcessing() {
		return processing;
	}

	public void setProcessing(int processing) {
		this.processing = processing;
	}

	public int getProcessed() {
		return processed;
	}

	public void setProcessed(int processed) {
		this.processed = processed;
	}

	public float getProcessedPer() {
		return processedPer;
	}

	public void setProcessedPer(float processedPer) {
		this.processedPer = processedPer;
	}

}
