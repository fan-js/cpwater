package cn.ritac.cpwater.mybatis.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "cpwater_workingtime")
public class WorkingTime implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 设备主键
     */
    @Column(name = "device_id")
    private Integer deviceId;

    /**
     * 数据时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 市电接通时间
     */
    @Column(name = "voltage_starttime")
    private Date voltageStarttime;

    /**
     * 市电结束时间
     */
    @Column(name = "voltage_endtime")
    private Date voltageEndtime;

    /**
     * ups接通时间
     */
    @Column(name = "ups_starttime")
    private Date upsStarttime;

    /**
     * ups结束时间
     */
    @Column(name = "ups_endtime")
    private Date upsEndtime;

    /**
     * 有线接通时间
     */
    @Column(name = "lan_starttime")
    private Date lanStarttime;

    /**
     * 有线结束时间
     */
    @Column(name = "lan_endtime")
    private Date lanEndtime;

    /**
     * 无线接通时间
     */
    @Column(name = "sim_starttime")
    private Date simStarttime;

    /**
     * 无线结束时间
     */
    @Column(name = "sim_endtime")
    private Date simEndtime;

    private static final long serialVersionUID = 1L;

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
     * 获取设备主键
     *
     * @return device_id - 设备主键
     */
    public Integer getDeviceId() {
        return deviceId;
    }

    /**
     * 设置设备主键
     *
     * @param deviceId 设备主键
     */
    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * 获取数据时间
     *
     * @return create_time - 数据时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置数据时间
     *
     * @param createTime 数据时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取市电接通时间
     *
     * @return voltage_starttime - 市电接通时间
     */
    public Date getVoltageStarttime() {
        return voltageStarttime;
    }

    /**
     * 设置市电接通时间
     *
     * @param voltageStarttime 市电接通时间
     */
    public void setVoltageStarttime(Date voltageStarttime) {
        this.voltageStarttime = voltageStarttime;
    }

    /**
     * 获取市电结束时间
     *
     * @return voltage_endtime - 市电结束时间
     */
    public Date getVoltageEndtime() {
        return voltageEndtime;
    }

    /**
     * 设置市电结束时间
     *
     * @param voltageEndtime 市电结束时间
     */
    public void setVoltageEndtime(Date voltageEndtime) {
        this.voltageEndtime = voltageEndtime;
    }

    /**
     * 获取ups接通时间
     *
     * @return ups_starttime - ups接通时间
     */
    public Date getUpsStarttime() {
        return upsStarttime;
    }

    /**
     * 设置ups接通时间
     *
     * @param upsStarttime ups接通时间
     */
    public void setUpsStarttime(Date upsStarttime) {
        this.upsStarttime = upsStarttime;
    }

    /**
     * 获取ups结束时间
     *
     * @return ups_endtime - ups结束时间
     */
    public Date getUpsEndtime() {
        return upsEndtime;
    }

    /**
     * 设置ups结束时间
     *
     * @param upsEndtime ups结束时间
     */
    public void setUpsEndtime(Date upsEndtime) {
        this.upsEndtime = upsEndtime;
    }

    /**
     * 获取有线接通时间
     *
     * @return lan_starttime - 有线接通时间
     */
    public Date getLanStarttime() {
        return lanStarttime;
    }

    /**
     * 设置有线接通时间
     *
     * @param lanStarttime 有线接通时间
     */
    public void setLanStarttime(Date lanStarttime) {
        this.lanStarttime = lanStarttime;
    }

    /**
     * 获取有线结束时间
     *
     * @return lan_endtime - 有线结束时间
     */
    public Date getLanEndtime() {
        return lanEndtime;
    }

    /**
     * 设置有线结束时间
     *
     * @param lanEndtime 有线结束时间
     */
    public void setLanEndtime(Date lanEndtime) {
        this.lanEndtime = lanEndtime;
    }

    /**
     * 获取无线接通时间
     *
     * @return sim_starttime - 无线接通时间
     */
    public Date getSimStarttime() {
        return simStarttime;
    }

    /**
     * 设置无线接通时间
     *
     * @param simStarttime 无线接通时间
     */
    public void setSimStarttime(Date simStarttime) {
        this.simStarttime = simStarttime;
    }

    /**
     * 获取无线结束时间
     *
     * @return sim_endtime - 无线结束时间
     */
    public Date getSimEndtime() {
        return simEndtime;
    }

    /**
     * 设置无线结束时间
     *
     * @param simEndtime 无线结束时间
     */
    public void setSimEndtime(Date simEndtime) {
        this.simEndtime = simEndtime;
    }
}