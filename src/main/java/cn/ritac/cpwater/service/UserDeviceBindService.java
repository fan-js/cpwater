package cn.ritac.cpwater.service;

import com.github.pagehelper.PageInfo;
import cn.ritac.cpwater.mybatis.model.UserDevicesBind;
import cn.ritac.cpwater.mybatis.model.VDeviceUserBind;

public interface UserDeviceBindService extends BaseService<UserDevicesBind,Integer>  {

	public PageInfo<VDeviceUserBind> getDeviceUserBind(int userId,int pageIndex,int pageSize);
	
}
