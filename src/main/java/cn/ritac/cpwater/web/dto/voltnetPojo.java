/*
 *
 *
 */
package cn.ritac.cpwater.web.dto;

/**
 *<b>Description:</b><br>
 * @author admin
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> voltnetPojo
 *<br><b>CreatTime:</b> 2019年5月14日下午5:56:48
 */
public class voltnetPojo {
	private String date;
	private int sum;

	private Long electric;
	private float elePer;

	private Long ups_electric;
	private float ups_elePer;

	private Long net;
	private float netPer;

	private Long mobile_net;
	private float mobile_netPer;

	private String devNum;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public Long getElectric() {
		return electric;
	}

	public void setElectric(Long electric) {
		this.electric = electric;
	}

	public float getElePer() {
		return elePer;
	}

	public void setElePer(float elePer) {
		this.elePer = elePer;
	}

	public Long getNet() {
		return net;
	}

	public void setNet(Long net) {
		this.net = net;
	}

	public float getNetPer() {
		return netPer;
	}

	public void setNetPer(float netPer) {
		this.netPer = netPer;
	}

	public Long getUps_electric() {
		return ups_electric;
	}

	public void setUps_electric(Long ups_electric) {
		this.ups_electric = ups_electric;
	}

	public float getUps_elePer() {
		return ups_elePer;
	}

	public void setUps_elePer(float ups_elePer) {
		this.ups_elePer = ups_elePer;
	}

	public Long getMobile_net() {
		return mobile_net;
	}

	public void setMobile_net(Long mobile_net) {
		this.mobile_net = mobile_net;
	}

	public float getMobile_netPer() {
		return mobile_netPer;
	}

	public void setMobile_netPer(float mobile_netPer) {
		this.mobile_netPer = mobile_netPer;
	}

	public String getDevNum() {
		return devNum;
	}

	public void setDevNum(String devNum) {
		this.devNum = devNum;
	}

}
