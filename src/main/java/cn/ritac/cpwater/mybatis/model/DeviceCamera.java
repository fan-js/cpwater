package cn.ritac.cpwater.mybatis.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author:FanJS
 * @Date:2019-9-6 19:11
 */

@Table(name = "cpwater_device_camera")
public class DeviceCamera implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 产品id
     * */
    @Column(name = "device_num")
    private Integer deviceNum;
    /**
     * 播放地址
     * */
    @Column(name = "url")
    private String url;
    /**
     * 添加时间
     * */
    @Column(name = "update_time")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(Integer deviceNum) {
        this.deviceNum = deviceNum;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
