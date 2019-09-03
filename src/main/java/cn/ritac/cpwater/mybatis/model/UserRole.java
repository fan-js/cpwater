package cn.ritac.cpwater.mybatis.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "cpwater_user_role")
public class UserRole implements Serializable {
    /**
     * 主键,自增长
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 外键,角色主键
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 外键,用户主键
     */
    @Column(name = "user_id")
    private Integer userId;

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
     * 获取外键,角色主键
     *
     * @return role_id - 外键,角色主键
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置外键,角色主键
     *
     * @param roleId 外键,角色主键
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取外键,用户主键
     *
     * @return user_id - 外键,用户主键
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置外键,用户主键
     *
     * @param userId 外键,用户主键
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}