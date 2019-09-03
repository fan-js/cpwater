package cn.ritac.cpwater.mybatis.mapper;

import java.util.List;

import cn.ritac.cpwater.mybatis.model.DevicesAI;
import cn.ritac.cpwater.web.dto.convert.AiVO;
import tk.mybatis.mapper.common.Mapper;

public interface DevicesAIMapper extends Mapper<DevicesAI> {

	public List<AiVO> getDeviceAiCharts(int id);
}