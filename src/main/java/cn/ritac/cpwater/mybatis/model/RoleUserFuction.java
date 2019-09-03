package cn.ritac.cpwater.mybatis.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "cpwater_role_user_fuction")
public class RoleUserFuction implements Serializable {
    /**
     * 主键自增长
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
     * 外键,用户操作主键
     */
    @Column(name = "user_fuction_id")
    private Integer userFuctionId;

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
     * 获取外键,用户操作主键
     *
     * @return user_fuction_id - 外键,用户操作主键
     */
    public Integer getUserFuctionId() {
        return userFuctionId;
    }

    /**
     * 设置外键,用户操作主键
     *
     * @param userFuctionId 外键,用户操作主键
     */
    public void setUserFuctionId(Integer userFuctionId) {
        this.userFuctionId = userFuctionId;
    }
}