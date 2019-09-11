package cn.ritac.cpwater.mybatis.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "cpwater_devices_do_rec")
public class DevicesDoRec implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
	private Integer id;

	@Column(name = "device_id")
	private Integer deviceId;

	@Column(name = "update_time")
	private Date updateTime;


	/**
	 *水泵
	 * */
	@Column(name = "pump")
	private boolean pump;

	/**
	 *泄压阀
	 * */
	@Column(name = "wastegate")
	private boolean wastegate;
	/**
	 *电能表复位
	 * */
	@Column(name = "reset")
	private boolean  reset;

	public boolean isPump() {
		return pump;
	}

	public boolean isWastegate() {
		return wastegate;
	}

	public boolean isReset() {
		return reset;
	}

	public boolean getPump() {
		return pump;
	}

	public void setPump(boolean pump) {
		this.pump = pump;
	}

	public boolean getWastegate() {
		return wastegate;
	}

	public void setWastegate(boolean wastegate) {
		this.wastegate = wastegate;
	}

	public boolean getReset() {
		return reset;
	}

	public void setReset(boolean reset) {
		this.reset = reset;
	}

	/**
	 * 电磁锁状态
	 */
	@Column(name = "elec_lock")
	private Boolean elecLock;

	/**
	 * 照明灯
	 */
	private Boolean light;

	/**
	 * 蜂鸣器
	 */
	private Boolean beep;

	/**
	 * 风扇
	 */
	private Boolean fan;

	/**
	 * 加热器
	 */
	private Boolean heating;

	/**
	 * 自动重合闸状态
	 */
	@Column(name = "reclos_state")
	private Boolean reclosState;

	/**
	 * 报警器消音
	 */
	@Column(name = "waring_silencing")
	private Boolean waringSilencing;

	/**
	 * ONU
	 */
	@Column(name = "1relay_state")
	private Boolean relayState_1;

	/**
	 * 交换机
	 */
	@Column(name = "2relay_state")
	private Boolean relayState_2;

	/**
	 * 摄相机1
	 */
	@Column(name = "3relay_state")
	private Boolean relayState_3;

	/**
	 * 摄相机2
	 */
	@Column(name = "4relay_state")
	private Boolean relayState_4;

	/**
	 * 备用1
	 */
	@Column(name = "5relay_state")
	private Boolean relayState_5;

	/**
	 * 备用2
	 */
	@Column(name = "6relay_state")
	private Boolean relayState_6;

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

	/**
	 * @return device_id
	 */
	public Integer getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceId
	 */
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * @return update_time
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取电磁锁状态
	 *
	 * @return elec_lock - 电磁锁状态
	 */
	public Boolean getElecLock() {
		return elecLock;
	}

	/**
	 * 设置电磁锁状态
	 *
	 * @param elecLock 电磁锁状态
	 */
	public void setElecLock(Boolean elecLock) {
		this.elecLock = elecLock;
	}

	/**
	 * 获取照明灯
	 *
	 * @return light - 照明灯
	 */
	public Boolean getLight() {
		return light;
	}

	/**
	 * 设置照明灯
	 *
	 * @param light 照明灯
	 */
	public void setLight(Boolean light) {
		this.light = light;
	}

	/**
	 * 获取蜂鸣器
	 *
	 * @return beep - 蜂鸣器
	 */
	public Boolean getBeep() {
		return beep;
	}

	/**
	 * 设置蜂鸣器
	 *
	 * @param beep 蜂鸣器
	 */
	public void setBeep(Boolean beep) {
		this.beep = beep;
	}

	/**
	 * 获取风扇
	 *
	 * @return fan - 风扇
	 */
	public Boolean getFan() {
		return fan;
	}

	/**
	 * 设置风扇
	 *
	 * @param fan 风扇
	 */
	public void setFan(Boolean fan) {
		this.fan = fan;
	}

	/**
	 * 获取加热器
	 *
	 * @return heating - 加热器
	 */
	public Boolean getHeating() {
		return heating;
	}

	/**
	 * 设置加热器
	 *
	 * @param heating 加热器
	 */
	public void setHeating(Boolean heating) {
		this.heating = heating;
	}

	/**
	 * 获取自动重合闸状态
	 *
	 * @return reclos_state - 自动重合闸状态
	 */
	public Boolean getReclosState() {
		return reclosState;
	}

	/**
	 * 设置自动重合闸状态
	 *
	 * @param reclosState 自动重合闸状态
	 */
	public void setReclosState(Boolean reclosState) {
		this.reclosState = reclosState;
	}

	/**
	 * 获取报警器消音
	 *
	 * @return waring_silencing - 报警器消音
	 */
	public Boolean getWaringSilencing() {
		return waringSilencing;
	}

	/**
	 * 设置报警器消音
	 *
	 * @param waringSilencing 报警器消音
	 */
	public void setWaringSilencing(Boolean waringSilencing) {
		this.waringSilencing = waringSilencing;
	}

	public Boolean getRelayState_1() {
		return relayState_1;
	}

	public void setRelayState_1(Boolean relayState_1) {
		this.relayState_1 = relayState_1;
	}

	public Boolean getRelayState_2() {
		return relayState_2;
	}

	public void setRelayState_2(Boolean relayState_2) {
		this.relayState_2 = relayState_2;
	}

	public Boolean getRelayState_3() {
		return relayState_3;
	}

	public void setRelayState_3(Boolean relayState_3) {
		this.relayState_3 = relayState_3;
	}

	public Boolean getRelayState_4() {
		return relayState_4;
	}

	public void setRelayState_4(Boolean relayState_4) {
		this.relayState_4 = relayState_4;
	}

	public Boolean getRelayState_5() {
		return relayState_5;
	}

	public void setRelayState_5(Boolean relayState_5) {
		this.relayState_5 = relayState_5;
	}

	public Boolean getRelayState_6() {
		return relayState_6;
	}

	public void setRelayState_6(Boolean relayState_6) {
		this.relayState_6 = relayState_6;
	}

}