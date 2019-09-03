package cn.ritac.cpwater.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.ritac.cpwater.mybatis.mapper.DevicesEventRecMapper;
import cn.ritac.cpwater.mybatis.model.DevicesEventRec;
import cn.ritac.cpwater.service.DevicesEventRecService;

@Service
public class DevicesEventRecServiceImpl extends BaseServiceImpl<DevicesEventRec> implements DevicesEventRecService {

	@Autowired
	private DevicesEventRecMapper devicesEventRecMapper;

	@Autowired
	public void setInit() {
		super.setInit(devicesEventRecMapper);
	}
}
