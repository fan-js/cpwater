package cn.ritac.cpwater.mybatis.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "cpwater_user_roles_bind")
public class UserRolesBind implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "new_user")
    private Boolean newUser;

    @Column(name = "del_user")
    private Boolean delUser;

    @Column(name = "add_dev")
    private Boolean addDev;

    @Column(name = "del_dev")
    private Boolean delDev;

    @Column(name = "up_dev")
    private Boolean upDev;

    @Column(name = "gen_opt_dev")
    private Boolean genOptDev;

    @Column(name = "set_config_dev")
    private Boolean setConfigDev;

    @Column(name = "create_time")
    private Date createTime;

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
     * @return new_user
     */
    public Boolean getNewUser() {
        return newUser;
    }

    /**
     * @param newUser
     */
    public void setNewUser(Boolean newUser) {
        this.newUser = newUser;
    }

    /**
     * @return del_user
     */
    public Boolean getDelUser() {
        return delUser;
    }

    /**
     * @param delUser
     */
    public void setDelUser(Boolean delUser) {
        this.delUser = delUser;
    }

    /**
     * @return add_dev
     */
    public Boolean getAddDev() {
        return addDev;
    }

    /**
     * @param addDev
     */
    public void setAddDev(Boolean addDev) {
        this.addDev = addDev;
    }

    /**
     * @return del_dev
     */
    public Boolean getDelDev() {
        return delDev;
    }

    /**
     * @param delDev
     */
    public void setDelDev(Boolean delDev) {
        this.delDev = delDev;
    }

    /**
     * @return up_dev
     */
    public Boolean getUpDev() {
        return upDev;
    }

    /**
     * @param upDev
     */
    public void setUpDev(Boolean upDev) {
        this.upDev = upDev;
    }

    /**
     * @return gen_opt_dev
     */
    public Boolean getGenOptDev() {
        return genOptDev;
    }

    /**
     * @param genOptDev
     */
    public void setGenOptDev(Boolean genOptDev) {
        this.genOptDev = genOptDev;
    }

    /**
     * @return set_config_dev
     */
    public Boolean getSetConfigDev() {
        return setConfigDev;
    }

    /**
     * @param setConfigDev
     */
    public void setSetConfigDev(Boolean setConfigDev) {
        this.setConfigDev = setConfigDev;
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
}