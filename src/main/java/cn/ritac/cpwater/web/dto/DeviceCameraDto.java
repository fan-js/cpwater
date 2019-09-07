package cn.ritac.cpwater.web.dto;

import javax.persistence.Column;

/**
 * @Author:FanJS
 * @Date:2019-9-6 19:16
 */
public class DeviceCameraDto {

    private Integer id;
    /**
     * 产品id
     * */
    private Integer deviceNum;
    /**
     * 播放地址
     * */
    private String url;
    /**
     * 添加时间
     * */
    private Integer updateTime;

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

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }
}
