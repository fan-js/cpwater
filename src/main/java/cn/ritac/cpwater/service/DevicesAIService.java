package cn.ritac.cpwater.service;

import java.util.List;

import cn.ritac.cpwater.mybatis.model.DevicesAI;
import cn.ritac.cpwater.web.dto.convert.AiVO;

public interface DevicesAIService extends BaseService<DevicesAI, Integer> {
	public List<AiVO> getDeviceAiCharts(int id);
}
