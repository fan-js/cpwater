package cn.ritac.cpwater.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.ritac.cpwater.mybatis.mapper.DevicesDIMapper;
import cn.ritac.cpwater.mybatis.model.DevicesDI;
import cn.ritac.cpwater.service.DevicesDIService;

@Service
public class DevicesDIServiceImpl extends BaseServiceImpl<DevicesDI> implements DevicesDIService   {

	@Autowired
	private DevicesDIMapper devicesDIMapper;
	@Autowired
	public void setInit() {
		super.setInit(devicesDIMapper);
	}
}
