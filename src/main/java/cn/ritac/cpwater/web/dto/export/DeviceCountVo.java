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
 *<br><b>ClassName:</b> DeviceCountVo
 *<br><b>CreatTime:</b> 2019年6月11日下午6:47:24
 */
public class DeviceCountVo {

	private Date data_time;

	private int num;

	private int onlineNum;

	private int offlineNum;

	private int faultNum;

	private float onlineper;

	public Date getData_time() {
		return data_time;
	}

	public void setData_time(Date data_time) {
		this.data_time = data_time;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getOnlineNum() {
		return onlineNum;
	}

	public void setOnlineNum(int onlineNum) {
		this.onlineNum = onlineNum;
	}

	public int getOfflineNum() {
		return offlineNum;
	}

	public void setOfflineNum(int offlineNum) {
		this.offlineNum = offlineNum;
	}

	public int getFaultNum() {
		return faultNum;
	}

	public void setFaultNum(int faultNum) {
		this.faultNum = faultNum;
	}

	public float getOnlineper() {
		return onlineper;
	}

	public void setOnlineper(float onlineper) {
		this.onlineper = onlineper;
	}

}
