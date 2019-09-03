package cn.ritac.cpwater.mybatis.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "cpwater_dataupopt")
public class Dataupopt implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    @Column(name = "device_id")
    private Integer deviceId;

    private Integer ain;

    private Integer aout;

    private Integer din;

    private Integer dout;

    private Integer reg;

    private Integer event;

    @Column(name = "update_time")
    private Date updateTime;

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
     * @return ain
     */
    public Integer getAin() {
        return ain;
    }

    /**
     * @param ain
     */
    public void setAin(Integer ain) {
        this.ain = ain;
    }

    /**
     * @return aout
     */
    public Integer getAout() {
        return aout;
    }

    /**
     * @param aout
     */
    public void setAout(Integer aout) {
        this.aout = aout;
    }

    /**
     * @return din
     */
    public Integer getDin() {
        return din;
    }

    /**
     * @param din
     */
    public void setDin(Integer din) {
        this.din = din;
    }

    /**
     * @return dout
     */
    public Integer getDout() {
        return dout;
    }

    /**
     * @param dout
     */
    public void setDout(Integer dout) {
        this.dout = dout;
    }

    /**
     * @return reg
     */
    public Integer getReg() {
        return reg;
    }

    /**
     * @param reg
     */
    public void setReg(Integer reg) {
        this.reg = reg;
    }

    /**
     * @return event
     */
    public Integer getEvent() {
        return event;
    }

    /**
     * @param event
     */
    public void setEvent(Integer event) {
        this.event = event;
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
}