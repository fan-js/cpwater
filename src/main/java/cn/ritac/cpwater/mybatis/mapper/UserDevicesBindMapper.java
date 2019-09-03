package cn.ritac.cpwater.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ritac.cpwater.mybatis.model.UserDevicesBind;
import cn.ritac.cpwater.mybatis.model.VDeviceUserBind;
import tk.mybatis.mapper.common.Mapper;

public interface UserDevicesBindMapper extends Mapper<UserDevicesBind> {
	
	public List<VDeviceUserBind> getDeviceUserBind(@Param("userId")int userId);

}