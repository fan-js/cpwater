package cn.ritac.cpwater.web.dto;

import java.io.Serializable;

public class DeviceFuctionDto implements Serializable {
	private Boolean updateDevice;

	private static final long serialVersionUID = 1L;

	public Boolean getUpdateDevice() {
		return updateDevice;
	}

	public void setUpdateDevice(Boolean updateDevice) {
		this.updateDevice = updateDevice;
	}

}