package cn.ritac.cpwater.mybatis.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "cpwater_devices_reg")
public class DevicesReg implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    @Column(name = "device_id")
    private Integer deviceId;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 工作模式
     * */
    @Column(name = "work_mode")
    private Integer workMode;

    /**
     * 定时起始时间1
     * */
    @Column(name = "start_timea")
    private String startTimea;

    /**
     * 定时结束时间
     * */
    @Column(name = "end_timea")
    private String endTimea;

    /**
     * 定时结束时间
     * */
    @Column(name = "statea")
    private String statea;
    /**
     * 定时起始时间2
     * */
    @Column(name = "start_timeb")
    private String startTimeb;

    /**
     * 定时结束时间
     * */
    @Column(name = "end_timeb")
    private String endTimeb;

    /**
     * 定时结束时间
     * */
    @Column(name = "stateb")
    private String stateb;
    /**
     * 定时起始时间3
     * */
    @Column(name = "start_timec")
    private String startTimec;

    /**
     * 定时结束时间
     * */
    @Column(name = "end_timec")
    private String endTimec;

    /**
     * 定时结束时间
     * */
    @Column(name = "statec")
    private String statec;
    /**
     * 定时起始时间4
     * */
    @Column(name = "start_timed")
    private String startTimed;

    /**
     * 定时结束时间
     * */
    @Column(name = "end_timed")
    private String endTimed;

    /**
     * 定时结束时间
     * */
    @Column(name = "stated")
    private String stated;

    /**
     * 风向控制1
     * */
    @Column(name = "winda")
    private String winda;

    /**
     * 风向控制2
     * */
    @Column(name = "windb")
    private String windb;


    /**
     * 压力控制
     * */
    @Column(name = "press")
    private Float press;

    /**
     * 传输频率
     * */
    @Column(name = "rate")
    private String rate;

    /**
     * 水位值
     * */
    @Column(name = "water")
    private String water;

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getWorkMode() {
        return workMode;
    }

    public void setWorkMode(Integer workMode) {
        this.workMode = workMode;
    }

    public String getStartTimea() {
        return startTimea;
    }

    public void setStartTimea(String startTimea) {
        this.startTimea = startTimea;
    }

    public String getEndTimea() {
        return endTimea;
    }

    public void setEndTimea(String endTimea) {
        this.endTimea = endTimea;
    }

    public String getStatea() {
        return statea;
    }

    public void setStatea(String statea) {
        this.statea = statea;
    }

    public String getStartTimeb() {
        return startTimeb;
    }

    public void setStartTimeb(String startTimeb) {
        this.startTimeb = startTimeb;
    }

    public String getEndTimeb() {
        return endTimeb;
    }

    public void setEndTimeb(String endTimeb) {
        this.endTimeb = endTimeb;
    }

    public String getStateb() {
        return stateb;
    }

    public void setStateb(String stateb) {
        this.stateb = stateb;
    }

    public String getStartTimec() {
        return startTimec;
    }

    public void setStartTimec(String startTimec) {
        this.startTimec = startTimec;
    }

    public String getEndTimec() {
        return endTimec;
    }

    public void setEndTimec(String endTimec) {
        this.endTimec = endTimec;
    }

    public String getStatec() {
        return statec;
    }

    public void setStatec(String statec) {
        this.statec = statec;
    }

    public String getStartTimed() {
        return startTimed;
    }

    public void setStartTimed(String startTimed) {
        this.startTimed = startTimed;
    }

    public String getEndTimed() {
        return endTimed;
    }

    public void setEndTimed(String endTimed) {
        this.endTimed = endTimed;
    }

    public String getStated() {
        return stated;
    }

    public void setStated(String stated) {
        this.stated = stated;
    }

    public String getWinda() {
        return winda;
    }

    public void setWinda(String winda) {
        this.winda = winda;
    }

    public String getWindb() {
        return windb;
    }

    public void setWindb(String windb) {
        this.windb = windb;
    }

    public Float getPress() {
        return press;
    }

    public void setPress(Float press) {
        this.press = press;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}