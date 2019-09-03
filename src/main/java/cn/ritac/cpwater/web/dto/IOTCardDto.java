package cn.ritac.cpwater.web.dto;

import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

public class IOTCardDto implements Serializable {
	private Integer id;

	/**
	 *设备编号
	 */
	private String deviceNum;

	/**
	 * 激活时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date activationTime;

	/**
	 * 卡号
	 */
	private String cardNum;

	/**
	 * 总流量 MB
	 */
	private String totalFlow;

	/**
	 * 已用流量 MB
	 */
	private String usedFlow;

	/**
	 * 剩余流量 MB
	 */
	private String residueFlow;

	/**
	 * 失效时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date exceedTime;

	private Integer pageIndex;

	private Integer pageSize;

	private static final long serialVersionUID = 1L;

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeviceNum() {
		return deviceNum;
	}

	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}

	/**
	 * 获取激活时间
	 *
	 * @return activation_time - 激活时间
	 */
	public Date getActivationTime() {
		return activationTime;
	}

	/**
	 * 设置激活时间
	 *
	 * @param activationTime 激活时间
	 */
	public void setActivationTime(Date activationTime) {
		this.activationTime = activationTime;
	}

	/**
	 * 获取卡号
	 *
	 * @return card_num - 卡号
	 */
	public String getCardNum() {
		return cardNum;
	}

	/**
	 * 设置卡号
	 *
	 * @param cardNum 卡号
	 */
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum == null ? null : cardNum.trim();
	}

	/**
	 * 获取总流量 MB
	 *
	 * @return total_flow - 总流量 MB
	 */
	public String getTotalFlow() {
		return totalFlow;
	}

	/**
	 * 设置总流量 MB
	 *
	 * @param totalFlow 总流量 MB
	 */
	public void setTotalFlow(String totalFlow) {
		this.totalFlow = totalFlow == null ? null : totalFlow.trim();
	}

	/**
	 * 获取已用流量 MB
	 *
	 * @return used_flow - 已用流量 MB
	 */
	public String getUsedFlow() {
		return usedFlow;
	}

	/**
	 * 设置已用流量 MB
	 *
	 * @param usedFlow 已用流量 MB
	 */
	public void setUsedFlow(String usedFlow) {
		this.usedFlow = usedFlow == null ? null : usedFlow.trim();
	}

	/**
	 * 获取剩余流量 MB
	 *
	 * @return residue_flow - 剩余流量 MB
	 */
	public String getResidueFlow() {
		return residueFlow;
	}

	/**
	 * 设置剩余流量 MB
	 *
	 * @param residueFlow 剩余流量 MB
	 */
	public void setResidueFlow(String residueFlow) {
		this.residueFlow = residueFlow == null ? null : residueFlow.trim();
	}

	/**
	 * 获取失效时间
	 *
	 * @return exceed_time - 失效时间
	 */
	public Date getExceedTime() {
		return exceedTime;
	}

	/**
	 * 设置失效时间
	 *
	 * @param exceedTime 失效时间
	 */
	public void setExceedTime(Date exceedTime) {
		this.exceedTime = exceedTime;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}