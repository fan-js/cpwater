package cn.ritac.cpwater.mybatis.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "mmsb_statistics")
public class Statistics implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 数据时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 设备编号
     */
    @Column(name = "device_num")
    private Integer deviceNum;

    /**
     * 月度市电在线时长<当月,次月始更新改自动并累加当季,当年>
     */
    @Column(name = "monthly_electric_duration")
    private Integer monthlyElectricDuration;

    /**
     * 季度市电在线时长
     */
    @Column(name = "quarter_electric_duration")
    private Integer quarterElectricDuration;

    /**
     * 年度市电在线时长
     */
    @Column(name = "year_electric_duration")
    private Integer yearElectricDuration;

    /**
     * 月度网络在线时长<当月,次月始更新改自动并累加当季,当年>
     */
    @Column(name = "monthly_network_duration")
    private Integer monthlyNetworkDuration;

    /**
     * 季度网络在线时长
     */
    @Column(name = "quarter_network_duration")
    private Integer quarterNetworkDuration;

    /**
     * 年度网络在线时长
     */
    @Column(name = "year_network_duration")
    private Integer yearNetworkDuration;

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
     * 获取数据时间
     *
     * @return update_time - 数据时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置数据时间
     *
     * @param updateTime 数据时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取设备编号
     *
     * @return device_num - 设备编号
     */
    public Integer getDeviceNum() {
        return deviceNum;
    }

    /**
     * 设置设备编号
     *
     * @param deviceNum 设备编号
     */
    public void setDeviceNum(Integer deviceNum) {
        this.deviceNum = deviceNum;
    }

    /**
     * 获取月度市电在线时长<当月,次月始更新改自动并累加当季,当年>
     *
     * @return monthly_electric_duration - 月度市电在线时长<当月,次月始更新改自动并累加当季,当年>
     */
    public Integer getMonthlyElectricDuration() {
        return monthlyElectricDuration;
    }

    /**
     * 设置月度市电在线时长<当月,次月始更新改自动并累加当季,当年>
     *
     * @param monthlyElectricDuration 月度市电在线时长<当月,次月始更新改自动并累加当季,当年>
     */
    public void setMonthlyElectricDuration(Integer monthlyElectricDuration) {
        this.monthlyElectricDuration = monthlyElectricDuration;
    }

    /**
     * 获取季度市电在线时长
     *
     * @return quarter_electric_duration - 季度市电在线时长
     */
    public Integer getQuarterElectricDuration() {
        return quarterElectricDuration;
    }

    /**
     * 设置季度市电在线时长
     *
     * @param quarterElectricDuration 季度市电在线时长
     */
    public void setQuarterElectricDuration(Integer quarterElectricDuration) {
        this.quarterElectricDuration = quarterElectricDuration;
    }

    /**
     * 获取年度市电在线时长
     *
     * @return year_electric_duration - 年度市电在线时长
     */
    public Integer getYearElectricDuration() {
        return yearElectricDuration;
    }

    /**
     * 设置年度市电在线时长
     *
     * @param yearElectricDuration 年度市电在线时长
     */
    public void setYearElectricDuration(Integer yearElectricDuration) {
        this.yearElectricDuration = yearElectricDuration;
    }

    /**
     * 获取月度网络在线时长<当月,次月始更新改自动并累加当季,当年>
     *
     * @return monthly_network_duration - 月度网络在线时长<当月,次月始更新改自动并累加当季,当年>
     */
    public Integer getMonthlyNetworkDuration() {
        return monthlyNetworkDuration;
    }

    /**
     * 设置月度网络在线时长<当月,次月始更新改自动并累加当季,当年>
     *
     * @param monthlyNetworkDuration 月度网络在线时长<当月,次月始更新改自动并累加当季,当年>
     */
    public void setMonthlyNetworkDuration(Integer monthlyNetworkDuration) {
        this.monthlyNetworkDuration = monthlyNetworkDuration;
    }

    /**
     * 获取季度网络在线时长
     *
     * @return quarter_network_duration - 季度网络在线时长
     */
    public Integer getQuarterNetworkDuration() {
        return quarterNetworkDuration;
    }

    /**
     * 设置季度网络在线时长
     *
     * @param quarterNetworkDuration 季度网络在线时长
     */
    public void setQuarterNetworkDuration(Integer quarterNetworkDuration) {
        this.quarterNetworkDuration = quarterNetworkDuration;
    }

    /**
     * 获取年度网络在线时长
     *
     * @return year_network_duration - 年度网络在线时长
     */
    public Integer getYearNetworkDuration() {
        return yearNetworkDuration;
    }

    /**
     * 设置年度网络在线时长
     *
     * @param yearNetworkDuration 年度网络在线时长
     */
    public void setYearNetworkDuration(Integer yearNetworkDuration) {
        this.yearNetworkDuration = yearNetworkDuration;
    }
}