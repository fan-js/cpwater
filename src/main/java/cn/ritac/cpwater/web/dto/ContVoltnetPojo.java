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
public class ContVoltnetPojo {
	private String name;
	private Long electric;
	private Long ups_electric;
	private Long net;
	private Long mobile_net;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getElectric() {
		return electric;
	}

	public void setElectric(Long electric) {
		this.electric = electric;
	}

	public Long getNet() {
		return net;
	}

	public void setNet(Long net) {
		this.net = net;
	}

	public Long getUps_electric() {
		return ups_electric;
	}

	public void setUps_electric(Long ups_electric) {
		this.ups_electric = ups_electric;
	}

	public Long getMobile_net() {
		return mobile_net;
	}

	public void setMobile_net(Long mobile_net) {
		this.mobile_net = mobile_net;
	}

}
