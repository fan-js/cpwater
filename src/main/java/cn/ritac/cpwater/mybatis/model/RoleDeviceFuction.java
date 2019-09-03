package cn.ritac.cpwater.mybatis.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "cpwater_role_device_fuction")
public class RoleDeviceFuction implements Serializable {
    /**
     * 主键自增长
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "device_fuction_id")
    private Integer deviceFuctionId;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键自增长
     *
     * @return id - 主键自增长
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键自增长
     *
     * @param id 主键自增长
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return role_id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * @return device_fuction_id
     */
    public Integer getDeviceFuctionId() {
        return deviceFuctionId;
    }

    /**
     * @param deviceFuctionId
     */
    public void setDeviceFuctionId(Integer deviceFuctionId) {
        this.deviceFuctionId = deviceFuctionId;
    }
}