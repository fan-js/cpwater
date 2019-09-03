package cn.ritac.cpwater.mybatis.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "v_devices_reg_rec_list")
public class VDevicesRegRecList implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    @Column(name = "device_id")
    private Integer deviceId;

    @Column(name = "fan_open_temper")
    private Integer fanOpenTemper;

    @Column(name = "heater_open_temper")
    private Integer heaterOpenTemper;

    @Column(name = "vol_alarm_limit")
    private Integer volAlarmLimit;

    @Column(name = "curr_alarm_limit")
    private Integer currAlarmLimit;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "device_num")
    private String deviceNum;

    private String telephone;

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
     * @return fan_open_temper
     */
    public Integer getFanOpenTemper() {
        return fanOpenTemper;
    }

    /**
     * @param fanOpenTemper
     */
    public void setFanOpenTemper(Integer fanOpenTemper) {
        this.fanOpenTemper = fanOpenTemper;
    }

    /**
     * @return heater_open_temper
     */
    public Integer getHeaterOpenTemper() {
        return heaterOpenTemper;
    }

    /**
     * @param heaterOpenTemper
     */
    public void setHeaterOpenTemper(Integer heaterOpenTemper) {
        this.heaterOpenTemper = heaterOpenTemper;
    }

    /**
     * @return vol_alarm_limit
     */
    public Integer getVolAlarmLimit() {
        return volAlarmLimit;
    }

    /**
     * @param volAlarmLimit
     */
    public void setVolAlarmLimit(Integer volAlarmLimit) {
        this.volAlarmLimit = volAlarmLimit;
    }

    /**
     * @return curr_alarm_limit
     */
    public Integer getCurrAlarmLimit() {
        return currAlarmLimit;
    }

    /**
     * @param currAlarmLimit
     */
    public void setCurrAlarmLimit(Integer currAlarmLimit) {
        this.currAlarmLimit = currAlarmLimit;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return device_num
     */
    public String getDeviceNum() {
        return deviceNum;
    }

    /**
     * @param deviceNum
     */
    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum == null ? null : deviceNum.trim();
    }

    /**
     * @return telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }
}