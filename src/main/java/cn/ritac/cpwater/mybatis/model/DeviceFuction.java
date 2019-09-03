package cn.ritac.cpwater.mybatis.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "cpwater_device_fuction")
public class DeviceFuction implements Serializable {
    /**
     * 主键,自增长
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    @Column(name = "list_device")
    private Boolean listDevice;

    @Column(name = "add_device")
    private Boolean addDevice;

    @Column(name = "update_device")
    private Boolean updateDevice;

    @Column(name = "del_device")
    private Boolean delDevice;

    @Column(name = "param_device")
    private Boolean paramDevice;

    @Column(name = "update_time")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键,自增长
     *
     * @return id - 主键,自增长
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键,自增长
     *
     * @param id 主键,自增长
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return list_device
     */
    public Boolean getListDevice() {
        return listDevice;
    }

    /**
     * @param listDevice
     */
    public void setListDevice(Boolean listDevice) {
        this.listDevice = listDevice;
    }

    /**
     * @return add_device
     */
    public Boolean getAddDevice() {
        return addDevice;
    }

    /**
     * @param addDevice
     */
    public void setAddDevice(Boolean addDevice) {
        this.addDevice = addDevice;
    }

    /**
     * @return update_device
     */
    public Boolean getUpdateDevice() {
        return updateDevice;
    }

    /**
     * @param updateDevice
     */
    public void setUpdateDevice(Boolean updateDevice) {
        this.updateDevice = updateDevice;
    }

    /**
     * @return del_device
     */
    public Boolean getDelDevice() {
        return delDevice;
    }

    /**
     * @param delDevice
     */
    public void setDelDevice(Boolean delDevice) {
        this.delDevice = delDevice;
    }

    /**
     * @return param_device
     */
    public Boolean getParamDevice() {
        return paramDevice;
    }

    /**
     * @param paramDevice
     */
    public void setParamDevice(Boolean paramDevice) {
        this.paramDevice = paramDevice;
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