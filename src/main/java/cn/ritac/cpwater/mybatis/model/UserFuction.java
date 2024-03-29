package cn.ritac.cpwater.mybatis.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "cpwater_user_fuction")
public class UserFuction implements Serializable {
    /**
     * 主键,自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 用户列表权限 0 - 无权看 ; 1 - 可以看
     */
    @Column(name = "list_user")
    private Boolean listUser;

    /**
     * 新增用户: 0 - 无权新建 ; 1 - 有权新建
     */
    @Column(name = "add_user")
    private Boolean addUser;

    /**
     * 修改用户 0 - 无权修改 ; 1 - 有权修改
     */
    @Column(name = "update_user")
    private Boolean updateUser;

    /**
     * 删除用户 0 - 无权删除 ; 1 - 有权删除
     */
    @Column(name = "del_user")
    private Boolean delUser;

    /**
     * 添加 , 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键,自增
     *
     * @return id - 主键,自增
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键,自增
     *
     * @param id 主键,自增
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户列表权限 0 - 无权看 ; 1 - 可以看
     *
     * @return list_user - 用户列表权限 0 - 无权看 ; 1 - 可以看
     */
    public Boolean getListUser() {
        return listUser;
    }

    /**
     * 设置用户列表权限 0 - 无权看 ; 1 - 可以看
     *
     * @param listUser 用户列表权限 0 - 无权看 ; 1 - 可以看
     */
    public void setListUser(Boolean listUser) {
        this.listUser = listUser;
    }

    /**
     * 获取新增用户: 0 - 无权新建 ; 1 - 有权新建
     *
     * @return add_user - 新增用户: 0 - 无权新建 ; 1 - 有权新建
     */
    public Boolean getAddUser() {
        return addUser;
    }

    /**
     * 设置新增用户: 0 - 无权新建 ; 1 - 有权新建
     *
     * @param addUser 新增用户: 0 - 无权新建 ; 1 - 有权新建
     */
    public void setAddUser(Boolean addUser) {
        this.addUser = addUser;
    }

    /**
     * 获取修改用户 0 - 无权修改 ; 1 - 有权修改
     *
     * @return update_user - 修改用户 0 - 无权修改 ; 1 - 有权修改
     */
    public Boolean getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置修改用户 0 - 无权修改 ; 1 - 有权修改
     *
     * @param updateUser 修改用户 0 - 无权修改 ; 1 - 有权修改
     */
    public void setUpdateUser(Boolean updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 获取删除用户 0 - 无权删除 ; 1 - 有权删除
     *
     * @return del_user - 删除用户 0 - 无权删除 ; 1 - 有权删除
     */
    public Boolean getDelUser() {
        return delUser;
    }

    /**
     * 设置删除用户 0 - 无权删除 ; 1 - 有权删除
     *
     * @param delUser 删除用户 0 - 无权删除 ; 1 - 有权删除
     */
    public void setDelUser(Boolean delUser) {
        this.delUser = delUser;
    }

    /**
     * 获取添加 , 修改时间
     *
     * @return update_time - 添加 , 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置添加 , 修改时间
     *
     * @param updateTime 添加 , 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}