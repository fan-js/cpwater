package cn.ritac.cpwater.mybatis.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "cpwater_devices_di")
public class DevicesDI implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    @Column(name = "device_id")
    private Integer deviceId;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 水泵
     */
    @Column(name = "water_pump")
    private Boolean waterPump;
    /**
     * 泄压阀
     */
    @Column(name = "wastegate")
    private Boolean wastegate;
    /**
     * 水位高
     */
    @Column(name = "water_levela")
    private Boolean waterlevela;
    /**
     * 水位中
     */
    @Column(name = "water_levelb")
    private Boolean waterLevelb;
    /**
     * 水位低
     */
    @Column(name = "water_levelc")
    private Boolean waterLevelc;

    public Boolean getWaterPump() {
        return waterPump;
    }

    public void setWaterPump(Boolean waterPump) {
        this.waterPump = waterPump;
    }

    public Boolean getWastegate() {
        return wastegate;
    }

    public void setWastegate(Boolean wastegate) {
        this.wastegate = wastegate;
    }

    public Boolean getWaterlevela() {
        return waterlevela;
    }

    public void setWaterlevela(Boolean waterlevela) {
        this.waterlevela = waterlevela;
    }

    public Boolean getWaterLevelb() {
        return waterLevelb;
    }

    public void setWaterLevelb(Boolean waterLevelb) {
        this.waterLevelb = waterLevelb;
    }

    public Boolean getWaterLevelc() {
        return waterLevelc;
    }

    public void setWaterLevelc(Boolean waterLevelc) {
        this.waterLevelc = waterLevelc;
    }

    /**
     * 电源状态 0 - ups ; 1 - 市电
     */
    @Column(name = "main_power_status")
    private Boolean mainPowerStatus;

    /**
     * 柜门状态 0 - 关闭 ; 1 - 开启
     */
    @Column(name = "door_status")
    private Boolean doorStatus;

    /**
     * UPS状态 0 - 不存在 ; 1 - 存在
     */
    @Column(name = "ups_status")
    private Boolean upsStatus;

    /**
     * 浪涌保护器状态 0 - 未动作 ; 1 - 已动作
     */
    @Column(name = "protect__status")
    private Boolean protectStatus;

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
     * 获取电源状态 0 - ups ; 1 - 市电
     *
     * @return main_power_status - 电源状态 0 - ups ; 1 - 市电
     */
    public Boolean getMainPowerStatus() {
        return mainPowerStatus;
    }

    /**
     * 设置电源状态 0 - ups ; 1 - 市电
     *
     * @param mainPowerStatus 电源状态 0 - ups ; 1 - 市电
     */
    public void setMainPowerStatus(Boolean mainPowerStatus) {
        this.mainPowerStatus = mainPowerStatus;
    }

    /**
     * 获取柜门状态 0 - 关闭 ; 1 - 开启
     *
     * @return door_status - 柜门状态 0 - 关闭 ; 1 - 开启
     */
    public Boolean getDoorStatus() {
        return doorStatus;
    }

    /**
     * 设置柜门状态 0 - 关闭 ; 1 - 开启
     *
     * @param doorStatus 柜门状态 0 - 关闭 ; 1 - 开启
     */
    public void setDoorStatus(Boolean doorStatus) {
        this.doorStatus = doorStatus;
    }

    /**
     * 获取UPS状态 0 - 不存在 ; 1 - 存在
     *
     * @return ups_status - UPS状态 0 - 不存在 ; 1 - 存在
     */
    public Boolean getUpsStatus() {
        return upsStatus;
    }

    /**
     * 设置UPS状态 0 - 不存在 ; 1 - 存在
     *
     * @param upsStatus UPS状态 0 - 不存在 ; 1 - 存在
     */
    public void setUpsStatus(Boolean upsStatus) {
        this.upsStatus = upsStatus;
    }

    /**
     * 获取浪涌保护器状态 0 - 未动作 ; 1 - 已动作
     *
     * @return protect__status - 浪涌保护器状态 0 - 未动作 ; 1 - 已动作
     */
    public Boolean getProtectStatus() {
        return protectStatus;
    }

    /**
     * 设置浪涌保护器状态 0 - 未动作 ; 1 - 已动作
     *
     * @param protectStatus 浪涌保护器状态 0 - 未动作 ; 1 - 已动作
     */
    public void setProtectStatus(Boolean protectStatus) {
        this.protectStatus = protectStatus;
    }
}