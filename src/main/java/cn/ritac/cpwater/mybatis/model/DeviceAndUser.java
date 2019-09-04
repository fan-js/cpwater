package cn.ritac.cpwater.mybatis.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author:FanJS
 * @Date:2019-8-30 8:45
 */

@Table(name = "cpwater_device_user")
public class DeviceAndUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 产品id
     * */
    @Column(name = "device_id")
    private Integer deviceId;

    /**
     * 用户id
     * */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 用户id
     * */
    @Column(name = "user_phone")
    private String userPhone;


    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Column(name = "update_time")
    private Date updateTime;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


}
