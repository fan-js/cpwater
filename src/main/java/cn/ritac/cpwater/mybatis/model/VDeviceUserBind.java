package cn.ritac.cpwater.mybatis.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "v_devices_user_bind_list")
public class VDeviceUserBind implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    @Column(name = "device_num")
    private String deviceNum;

    @Column(name = "device_key")
    private String deviceKey;

    @Column(name = "device_model")
    private String deviceModel;

    private String lon;

    private String lat;

    @Column(name = "position_des")
    private String positionDes;

    private Boolean online;

    @Column(name = "online_time")
    private Date onlineTime;

    @Column(name = "offline_time")
    private Date offlineTime;

    private Integer signals;

    @Column(name = "device_version")
    private String deviceVersion;

    @Column(name = "soft_version")
    private String softVersion;

    private String remark;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "device_id")
    private Integer deviceId;

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

    /**
     * @return device_key
     */
    public String getDeviceKey() {
        return deviceKey;
    }

    /**
     * @param deviceKey
     */
    public void setDeviceKey(String deviceKey) {
        this.deviceKey = deviceKey == null ? null : deviceKey.trim();
    }

    /**
     * @return device_model
     */
    public String getDeviceModel() {
        return deviceModel;
    }

    /**
     * @param deviceModel
     */
    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel == null ? null : deviceModel.trim();
    }

    /**
     * @return lon
     */
    public String getLon() {
        return lon;
    }

    /**
     * @param lon
     */
    public void setLon(String lon) {
        this.lon = lon == null ? null : lon.trim();
    }

    /**
     * @return lat
     */
    public String getLat() {
        return lat;
    }

    /**
     * @param lat
     */
    public void setLat(String lat) {
        this.lat = lat == null ? null : lat.trim();
    }

    /**
     * @return position_des
     */
    public String getPositionDes() {
        return positionDes;
    }

    /**
     * @param positionDes
     */
    public void setPositionDes(String positionDes) {
        this.positionDes = positionDes == null ? null : positionDes.trim();
    }

    /**
     * @return online
     */
    public Boolean getOnline() {
        return online;
    }

    /**
     * @param online
     */
    public void setOnline(Boolean online) {
        this.online = online;
    }

    /**
     * @return online_time
     */
    public Date getOnlineTime() {
        return onlineTime;
    }

    /**
     * @param onlineTime
     */
    public void setOnlineTime(Date onlineTime) {
        this.onlineTime = onlineTime;
    }

    /**
     * @return offline_time
     */
    public Date getOfflineTime() {
        return offlineTime;
    }

    /**
     * @param offlineTime
     */
    public void setOfflineTime(Date offlineTime) {
        this.offlineTime = offlineTime;
    }

    /**
     * @return signals
     */
    public Integer getSignals() {
        return signals;
    }

    /**
     * @param signals
     */
    public void setSignals(Integer signals) {
        this.signals = signals;
    }

    /**
     * @return device_version
     */
    public String getDeviceVersion() {
        return deviceVersion;
    }

    /**
     * @param deviceVersion
     */
    public void setDeviceVersion(String deviceVersion) {
        this.deviceVersion = deviceVersion == null ? null : deviceVersion.trim();
    }

    /**
     * @return soft_version
     */
    public String getSoftVersion() {
        return softVersion;
    }

    /**
     * @param softVersion
     */
    public void setSoftVersion(String softVersion) {
        this.softVersion = softVersion == null ? null : softVersion.trim();
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
}