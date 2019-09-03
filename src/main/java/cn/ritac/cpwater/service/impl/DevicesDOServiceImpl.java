package cn.ritac.cpwater.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.ritac.cpwater.mybatis.mapper.DevicesDoMapper;
import cn.ritac.cpwater.mybatis.model.DevicesDo;
import cn.ritac.cpwater.service.DevicesDOService;

@Service
public class DevicesDOServiceImpl extends BaseServiceImpl<DevicesDo> implements DevicesDOService {
	
	@Autowired
	private DevicesDoMapper devicesDoMapper;
	@Autowired
	public void setInit() {
		super.setInit(devicesDoMapper);
	}
}
