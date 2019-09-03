package cn.ritac.cpwater.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.ritac.cpwater.mybatis.mapper.UserDevicesBindMapper;
import cn.ritac.cpwater.mybatis.model.UserDevicesBind;
import cn.ritac.cpwater.mybatis.model.VDeviceUserBind;
import cn.ritac.cpwater.service.UserDeviceBindService;

@Service
public class UserDeviceBindServiceImpl  extends BaseServiceImpl<UserDevicesBind> implements UserDeviceBindService {

	@Autowired
	private UserDevicesBindMapper userDevicesBindMapper;
	@Autowired
	public void setInit() {
		super.setInit(userDevicesBindMapper);
	}
	@Override
	public PageInfo<VDeviceUserBind> getDeviceUserBind(int userId,int pageIndex,int pageSize) {
		
		
		PageHelper.startPage(pageIndex, pageSize);
		List<VDeviceUserBind> list=userDevicesBindMapper.getDeviceUserBind(userId);
		
		return new PageInfo<>(list);
	}
}
