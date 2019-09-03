package cn.ritac.cpwater.web.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AssetsDto implements Serializable {
	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 资产编码
	 */
	private String assetsCode;

	/**
	 * 资产名称
	 */
	private String assetsName;

	/**
	 * 资产类别
	 */
	private String assetsType;

	/**
	 * 资产型号
	 */
	private String assetsModel;

	/**
	 * 资产来源
	 */
	private String assetsSource;

	/**
	 * 买入时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date buyTime;

	/**
	 * 安装时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date installTime;

	/**
	 * 过保时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date outTime;

	/**
	 * 安装位置
	 */
	private String assetsAddress;

	/**
	 * 设备编号
	 */
	private String deviceNum;

	/**
	 * 价格
	 */
	private Float price;

	/**
	 * 图片
	 */
	private String imgUrl;

	/**
	 * 描述
	 */
	private String descript;

	private static final long serialVersionUID = 1L;

	private int pageIndex;

	private int pageSize;

	/**
	 * 获取主键
	 *
	 * @return id - 主键
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置主键
	 *
	 * @param id 主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取资产编码
	 *
	 * @return assets_code - 资产编码
	 */
	public String getAssetsCode() {
		return assetsCode;
	}

	/**
	 * 设置资产编码
	 *
	 * @param assetsCode 资产编码
	 */
	public void setAssetsCode(String assetsCode) {
		this.assetsCode = assetsCode == null ? null : assetsCode.trim();
	}

	/**
	 * 获取资产名称
	 *
	 * @return assets_name - 资产名称
	 */
	public String getAssetsName() {
		return assetsName;
	}

	/**
	 * 设置资产名称
	 *
	 * @param assetsName 资产名称
	 */
	public void setAssetsName(String assetsName) {
		this.assetsName = assetsName == null ? null : assetsName.trim();
	}

	/**
	 * 获取资产类别
	 *
	 * @return assets_type - 资产类别
	 */
	public String getAssetsType() {
		return assetsType;
	}

	/**
	 * 设置资产类别
	 *
	 * @param assetsType 资产类别
	 */
	public void setAssetsType(String assetsType) {
		this.assetsType = assetsType == null ? null : assetsType.trim();
	}

	/**
	 * 获取资产型号
	 *
	 * @return assets_model - 资产型号
	 */
	public String getAssetsModel() {
		return assetsModel;
	}

	/**
	 * 设置资产型号
	 *
	 * @param assetsModel 资产型号
	 */
	public void setAssetsModel(String assetsModel) {
		this.assetsModel = assetsModel == null ? null : assetsModel.trim();
	}

	/**
	 * 获取资产来源
	 *
	 * @return assets_source - 资产来源
	 */
	public String getAssetsSource() {
		return assetsSource;
	}

	/**
	 * 设置资产来源
	 *
	 * @param assetsSource 资产来源
	 */
	public void setAssetsSource(String assetsSource) {
		this.assetsSource = assetsSource == null ? null : assetsSource.trim();
	}

	/**
	 * 获取买入时间
	 *
	 * @return buy_time - 买入时间
	 */
	public Date getBuyTime() {
		return buyTime;
	}

	/**
	 * 设置买入时间
	 *
	 * @param buyTime 买入时间
	 */
	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}

	/**
	 * 获取安装时间
	 *
	 * @return install_time - 安装时间
	 */
	public Date getInstallTime() {
		return installTime;
	}

	/**
	 * 设置安装时间
	 *
	 * @param installTime 安装时间
	 */
	public void setInstallTime(Date installTime) {
		this.installTime = installTime;
	}

	/**
	 * 获取过保时间
	 *
	 * @return out_time - 过保时间
	 */
	public Date getOutTime() {
		return outTime;
	}

	/**
	 * 设置过保时间
	 *
	 * @param outTime 过保时间
	 */
	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	/**
	 * 获取安装位置
	 *
	 * @return assets_address - 安装位置
	 */
	public String getAssetsAddress() {
		return assetsAddress;
	}

	/**
	 * 设置安装位置
	 *
	 * @param assetsAddress 安装位置
	 */
	public void setAssetsAddress(String assetsAddress) {
		this.assetsAddress = assetsAddress == null ? null : assetsAddress.trim();
	}

	/**
	 * 获取设备主键
	 *
	 * @return deviceNum - 设备编号
	 */
	public String getDeviceNum() {
		return deviceNum;
	}

	/**
	 * 设置设备主键
	 *
	 * @param deviceNum 设备编号
	 */
	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}

	/**
	 * 获取价格
	 *
	 * @return price - 价格
	 */
	public Float getPrice() {
		return price;
	}

	/**
	 * 设置价格
	 *
	 * @param price 价格
	 */
	public void setPrice(Float price) {
		this.price = price;
	}

	/**
	 * 获取图片
	 *
	 * @return img_url - 图片
	 */
	public String getImgUrl() {
		return imgUrl;
	}

	/**
	 * 设置图片
	 *
	 * @param imgUrl 图片
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl == null ? null : imgUrl.trim();
	}

	/**
	 * 获取描述
	 *
	 * @return descript - 描述
	 */
	public String getDescript() {
		return descript;
	}

	/**
	 * 设置描述
	 *
	 * @param descript 描述
	 */
	public void setDescript(String descript) {
		this.descript = descript == null ? null : descript.trim();
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}