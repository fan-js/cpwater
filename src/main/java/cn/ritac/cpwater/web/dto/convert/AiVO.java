/*
 *
 *
 */
package cn.ritac.cpwater.web.dto.convert;

import java.util.Date;

/**
 *<b>Description:</b><br>
 * @author admin
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> AiVO
 *<br><b>CreatTime:</b> 2019年4月29日上午11:57:04
 */
public class AiVO {
	private Date time;
	private float vol;
	private float cur;
	// private float leakCur;
	private float temp;
	private float hum;
	private float ill;
	private int ups_voltage;
	private float ups_surpluscapacity_ma;
	private float ups_surpluscapacity_per;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public float getVol() {
		return vol;
	}

	public void setVol(float vol) {
		this.vol = vol;
	}

	public float getCur() {
		return cur;
	}

	public void setCur(float cur) {
		this.cur = cur;
	}

	public float getTemp() {
		return temp;
	}

	public void setTemp(float temp) {
		this.temp = temp;
	}

	public float getHum() {
		return hum;
	}

	public void setHum(float hum) {
		this.hum = hum;
	}

	public int getUps_voltage() {
		return ups_voltage;
	}

	public void setUps_voltage(int ups_voltage) {
		this.ups_voltage = ups_voltage;
	}

	public float getUps_surpluscapacity_ma() {
		return ups_surpluscapacity_ma;
	}

	public void setUps_surpluscapacity_ma(float ups_surpluscapacity_ma) {
		this.ups_surpluscapacity_ma = ups_surpluscapacity_ma;
	}

	public float getUps_surpluscapacity_per() {
		return ups_surpluscapacity_per;
	}

	public void setUps_surpluscapacity_per(float ups_surpluscapacity_per) {
		this.ups_surpluscapacity_per = ups_surpluscapacity_per;
	}

	public float getIll() {
		return ill;
	}

	public void setIll(float ill) {
		this.ill = ill;
	}

}
