package cn.ritac.cpwater.web.dto.convert;

import java.io.Serializable;

public class AssetsCount implements Serializable {
	private static final long serialVersionUID = 1L;

	/*
	 * 总数
	 */
	private int assetsNum;
	/*
	 * 总价
	 */
	private float assetsNumPrice;
	/*
	 * 用数
	 */
	private int assetsUse;
	/*
	 * 用价
	 */
	private float assetsUsePrice;
	/*
	 * 用占比
	 */
	private float assetsUseProp;
	/*
	 * 闲数
	 */
	private int assetsFree;
	/*
	 * 闲价
	 */
	private float assetsFreePrice;
	/*
	 * 闲占比
	 */
	private float assetsFreeProp;
	/*
	 * 废数
	 */
	private int assetsScrap;
	/*
	 * 废价
	 */
	private float assetsScrapPrice;
	/*
	 * 废占比
	 */
	private float assetsScrapProp;

	public int getAssetsNum() {
		return assetsNum;
	}

	public void setAssetsNum(int assetsNum) {
		this.assetsNum = assetsNum;
	}

	public float getAssetsNumPrice() {
		return assetsNumPrice;
	}

	public void setAssetsNumPrice(float assetsNumPrice) {
		this.assetsNumPrice = assetsNumPrice;
	}

	public int getAssetsUse() {
		return assetsUse;
	}

	public void setAssetsUse(int assetsUse) {
		this.assetsUse = assetsUse;
	}

	public float getAssetsUsePrice() {
		return assetsUsePrice;
	}

	public void setAssetsUsePrice(float assetsUsePrice) {
		this.assetsUsePrice = assetsUsePrice;
	}

	public float getAssetsUseProp() {
		return assetsUseProp;
	}

	public void setAssetsUseProp(float assetsUseProp) {
		this.assetsUseProp = assetsUseProp;
	}

	public int getAssetsFree() {
		return assetsFree;
	}

	public void setAssetsFree(int assetsFree) {
		this.assetsFree = assetsFree;
	}

	public float getAssetsFreePrice() {
		return assetsFreePrice;
	}

	public void setAssetsFreePrice(float assetsFreePrice) {
		this.assetsFreePrice = assetsFreePrice;
	}

	public float getAssetsFreeProp() {
		return assetsFreeProp;
	}

	public void setAssetsFreeProp(float assetsFreeProp) {
		this.assetsFreeProp = assetsFreeProp;
	}

	public int getAssetsScrap() {
		return assetsScrap;
	}

	public void setAssetsScrap(int assetsScrap) {
		this.assetsScrap = assetsScrap;
	}

	public float getAssetsScrapPrice() {
		return assetsScrapPrice;
	}

	public void setAssetsScrapPrice(float assetsScrapPrice) {
		this.assetsScrapPrice = assetsScrapPrice;
	}

	public float getAssetsScrapProp() {
		return assetsScrapProp;
	}

	public void setAssetsScrapProp(float assetsScrapProp) {
		this.assetsScrapProp = assetsScrapProp;
	}

}