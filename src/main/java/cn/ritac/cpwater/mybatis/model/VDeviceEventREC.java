package cn.ritac.cpwater.mybatis.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "v_devices_event_rec_list")
public class VDeviceEventREC implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    @Column(name = "device_id")
    private Integer deviceId;

    @Column(name = "event_info")
    private String eventInfo;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "device_num")
    private String deviceNum;

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
     * @return event_info
     */
    public String getEventInfo() {
        return eventInfo;
    }

    /**
     * @param eventInfo
     */
    public void setEventInfo(String eventInfo) {
        this.eventInfo = eventInfo == null ? null : eventInfo.trim();
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
}