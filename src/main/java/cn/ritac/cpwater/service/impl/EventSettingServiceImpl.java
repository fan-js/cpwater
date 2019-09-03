package cn.ritac.cpwater.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.ritac.cpwater.mybatis.mapper.EventSettingMapper;
import cn.ritac.cpwater.mybatis.model.EventSetting;
import cn.ritac.cpwater.service.EventSettingService;

@Service
public class EventSettingServiceImpl  extends BaseServiceImpl<EventSetting> implements EventSettingService  {

	@Autowired
	private EventSettingMapper eventSettingMapper;
	@Autowired
	public void setInit() {
		super.setInit(eventSettingMapper);
	}
	
}
