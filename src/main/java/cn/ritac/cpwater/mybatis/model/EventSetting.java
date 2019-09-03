package cn.ritac.cpwater.mybatis.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "cpwater_event_setting")
public class EventSetting implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    @Column(name = "event_id")
    private Integer eventId;

    /**
     * 1是短信，2是应用
     */
    @Column(name = "push_type")
    private Integer pushType;

    @Column(name = "user_id")
    private Integer userId;

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
     * @return event_id
     */
    public Integer getEventId() {
        return eventId;
    }

    /**
     * @param eventId
     */
    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    /**
     * 获取1是短信，2是应用
     *
     * @return push_type - 1是短信，2是应用
     */
    public Integer getPushType() {
        return pushType;
    }

    /**
     * 设置1是短信，2是应用
     *
     * @param pushType 1是短信，2是应用
     */
    public void setPushType(Integer pushType) {
        this.pushType = pushType;
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
}