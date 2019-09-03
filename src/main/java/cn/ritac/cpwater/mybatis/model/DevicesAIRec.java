package cn.ritac.cpwater.mybatis.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "cpwater_devices_ai_rec")
public class DevicesAIRec implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    @Column(name = "device_id")
    private Integer deviceId;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 温度
     */
    private Float temper;

    /**
     * 湿度
     */
    private Float humidity;

    /**
     * 照度
     */
    private Float illuminance;

    /**
     * 电压
     */
    private Float voltage;

    /**
     * 电流
     */
    private Float current;


    /**
     * 电能
     */
    @Column(name = "electric_energy")
    private Float electricEnergy;
    /**
     * 风向
     */
    @Column(name = "wind_direction")
    private Float windDirection;
    /**
     * 压力
     */
    private Float press;

    public Float getElectricEnergy() {
        return electricEnergy;
    }

    public void setElectricEnergy(Float electricEnergy) {
        this.electricEnergy = electricEnergy;
    }

    public Float getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(Float windDirection) {
        this.windDirection = windDirection;
    }

    public Float getPress() {
        return press;
    }

    public void setPress(Float press) {
        this.press = press;
    }



    /**
     * 漏电流
     */
    @Column(name = "current_leakagelimit")
    private Float currentLeakagelimit;

    /**
     * UPS电压
     */
    @Column(name = "ups_voltage")
    private Float upsVoltage;

    /**
     * UPS充放电流
     */
    @Column(name = "ups_current")
    private Float upsCurrent;

    /**
     * UPS剩余容量：毫安时
     */
    @Column(name = "ups_surpluscapacity_ma")
    private Float upsSurpluscapacityMa;

    /**
     * UPS剩余容量：百分比
     */
    @Column(name = "ups_surpluscapacity_per")
    private Float upsSurpluscapacityPer;

    /**
     * UPS最高温度
     */
    @Column(name = "ups_uppertemper")
    private Float upsUppertemper;

    /**
     * UPS最低温度
     */
    @Column(name = "ups_lowertemper")
    private Float upsLowertemper;

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
     * 获取温度
     *
     * @return temper - 温度
     */
    public Float getTemper() {
        return temper;
    }

    /**
     * 设置温度
     *
     * @param temper 温度
     */
    public void setTemper(Float temper) {
        this.temper = temper;
    }

    /**
     * 获取湿度
     *
     * @return humidity - 湿度
     */
    public Float getHumidity() {
        return humidity;
    }

    /**
     * 设置湿度
     *
     * @param humidity 湿度
     */
    public void setHumidity(Float humidity) {
        this.humidity = humidity;
    }

    /**
     * 获取照度
     *
     * @return illuminance - 照度
     */
    public Float getIlluminance() {
        return illuminance;
    }

    /**
     * 设置照度
     *
     * @param illuminance 照度
     */
    public void setIlluminance(Float illuminance) {
        this.illuminance = illuminance;
    }

    /**
     * 获取电压
     *
     * @return voltage - 电压
     */
    public Float getVoltage() {
        return voltage;
    }

    /**
     * 设置电压
     *
     * @param voltage 电压
     */
    public void setVoltage(Float voltage) {
        this.voltage = voltage;
    }

    /**
     * 获取电流
     *
     * @return current - 电流
     */
    public Float getCurrent() {
        return current;
    }

    /**
     * 设置电流
     *
     * @param current 电流
     */
    public void setCurrent(Float current) {
        this.current = current;
    }

    /**
     * 获取漏电流
     *
     * @return current_leakagelimit - 漏电流
     */
    public Float getCurrentLeakagelimit() {
        return currentLeakagelimit;
    }

    /**
     * 设置漏电流
     *
     * @param currentLeakagelimit 漏电流
     */
    public void setCurrentLeakagelimit(Float currentLeakagelimit) {
        this.currentLeakagelimit = currentLeakagelimit;
    }

    /**
     * 获取UPS电压
     *
     * @return ups_voltage - UPS电压
     */
    public Float getUpsVoltage() {
        return upsVoltage;
    }

    /**
     * 设置UPS电压
     *
     * @param upsVoltage UPS电压
     */
    public void setUpsVoltage(Float upsVoltage) {
        this.upsVoltage = upsVoltage;
    }

    /**
     * 获取UPS充放电流
     *
     * @return ups_current - UPS充放电流
     */
    public Float getUpsCurrent() {
        return upsCurrent;
    }

    /**
     * 设置UPS充放电流
     *
     * @param upsCurrent UPS充放电流
     */
    public void setUpsCurrent(Float upsCurrent) {
        this.upsCurrent = upsCurrent;
    }

    /**
     * 获取UPS剩余容量：毫安时
     *
     * @return ups_surpluscapacity_ma - UPS剩余容量：毫安时
     */
    public Float getUpsSurpluscapacityMa() {
        return upsSurpluscapacityMa;
    }

    /**
     * 设置UPS剩余容量：毫安时
     *
     * @param upsSurpluscapacityMa UPS剩余容量：毫安时
     */
    public void setUpsSurpluscapacityMa(Float upsSurpluscapacityMa) {
        this.upsSurpluscapacityMa = upsSurpluscapacityMa;
    }

    /**
     * 获取UPS剩余容量：百分比
     *
     * @return ups_surpluscapacity_per - UPS剩余容量：百分比
     */
    public Float getUpsSurpluscapacityPer() {
        return upsSurpluscapacityPer;
    }

    /**
     * 设置UPS剩余容量：百分比
     *
     * @param upsSurpluscapacityPer UPS剩余容量：百分比
     */
    public void setUpsSurpluscapacityPer(Float upsSurpluscapacityPer) {
        this.upsSurpluscapacityPer = upsSurpluscapacityPer;
    }

    /**
     * 获取UPS最高温度
     *
     * @return ups_uppertemper - UPS最高温度
     */
    public Float getUpsUppertemper() {
        return upsUppertemper;
    }

    /**
     * 设置UPS最高温度
     *
     * @param upsUppertemper UPS最高温度
     */
    public void setUpsUppertemper(Float upsUppertemper) {
        this.upsUppertemper = upsUppertemper;
    }

    /**
     * 获取UPS最低温度
     *
     * @return ups_lowertemper - UPS最低温度
     */
    public Float getUpsLowertemper() {
        return upsLowertemper;
    }

    /**
     * 设置UPS最低温度
     *
     * @param upsLowertemper UPS最低温度
     */
    public void setUpsLowertemper(Float upsLowertemper) {
        this.upsLowertemper = upsLowertemper;
    }
}