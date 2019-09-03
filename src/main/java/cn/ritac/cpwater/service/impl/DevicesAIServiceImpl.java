package cn.ritac.cpwater.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.ritac.cpwater.mybatis.mapper.DevicesAIMapper;
import cn.ritac.cpwater.mybatis.model.DevicesAI;
import cn.ritac.cpwater.service.DevicesAIService;
import cn.ritac.cpwater.web.dto.convert.AiVO;

@Service
public class DevicesAIServiceImpl extends BaseServiceImpl<DevicesAI> implements DevicesAIService {

	@Autowired
	private DevicesAIMapper devicesAIMapper;

	@Autowired
	public void setInit() {
		super.setInit(devicesAIMapper);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.ritac.cpwater.service.DevicesAIService#getDeviceAiCharts(int)
	 */
	@Override
	public List<AiVO> getDeviceAiCharts(int id) {
		return devicesAIMapper.getDeviceAiCharts(id);
	}
}
