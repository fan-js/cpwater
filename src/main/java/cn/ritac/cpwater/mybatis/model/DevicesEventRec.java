package cn.ritac.cpwater.mybatis.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "cpwater_devices_event_rec")
public class DevicesEventRec implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    @Column(name = "device_id")
    private Integer deviceId;

    @Column(name = "event_info")
    private String eventInfo;

    @Column(name = "event_content")
    private String eventContent;

    @Column(name = "create_time")
    private Date createTime;

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
     * @return event_content
     */
    public String getEventContent() {
        return eventContent;
    }

    /**
     * @param eventContent
     */
    public void setEventContent(String eventContent) {
        this.eventContent = eventContent == null ? null : eventContent.trim();
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
}