package cn.ritac.cpwater.mybatis.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "cpwater_role_group")
public class RoleGroup implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 角色主键
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 分组主键
     */
    @Column(name = "group_id")
    private Integer groupId;

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
}