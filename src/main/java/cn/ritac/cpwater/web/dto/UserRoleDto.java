package cn.ritac.cpwater.web.dto;

import java.util.Date;



public class UserRoleDto {
	
	
	    private Integer id;

	 
	    private Integer userId;


	    private Boolean newUser;


	    private Boolean delUser;


	    private Boolean addDev;


	    private Boolean delDev;


	    private Boolean upDev;

	
	    private Boolean genOptDev;


	    private Boolean setConfigDev;


	    private Date createTime;



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
