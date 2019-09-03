package cn.ritac.cpwater.mybatis.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "cpwater_group_device")
public class GroupDevice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 分组主键
     */
    @Column(name = "group_id")
    private Integer groupId;

    /**
     * 设备主键
     */
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
     * 获取分组主键
     *
     * @return group_id - 分组主键
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * 设置分组主键
     *
     * @param groupId 分组主键
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * 获取设备主键
     *
     * @return device_id - 设备主键
     */
    public Integer getDeviceId() {
        return deviceId;
    }

    /**
     * 设置设备主键
     *
     * @param deviceId 设备主键
     */
    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }
}