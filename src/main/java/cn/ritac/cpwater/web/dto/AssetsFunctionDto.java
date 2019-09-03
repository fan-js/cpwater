package cn.ritac.cpwater.web.dto;

import java.io.Serializable;

public class AssetsFunctionDto implements Serializable {

	/**
	 * 修改
	 */
	private Boolean updateAssets;

	private static final long serialVersionUID = 1L;

	public Boolean getUpdateAssets() {
		return updateAssets;
	}

	public void setUpdateAssets(Boolean updateAssets) {
		this.updateAssets = updateAssets;
	}

}