package cn.ritac.cpwater.mybatis.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "cpwater_role_admin")
public class RoleAdmin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 管理员主键
     */
    @Column(name = "admin_id")
    private Integer adminId;

    /**
     * 角色主键
     */
    @Column(name = "role_id")
    private Integer roleId;

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
     * 获取管理员主键
     *
     * @return admin_id - 管理员主键
     */
    public Integer getAdminId() {
        return adminId;
    }

    /**
     * 设置管理员主键
     *
     * @param adminId 管理员主键
     */
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    /**
     * 获取角色主键
     *
     * @return role_id - 角色主键
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色主键
     *
     * @param roleId 角色主键
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}