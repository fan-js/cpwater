package cn.ritac.cpwater.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ritac.cpwater.mybatis.model.DevicesEventRec;
import cn.ritac.cpwater.web.dto.convert.AiDiDoutVO;
import cn.ritac.cpwater.web.dto.convert.EventVO;
import tk.mybatis.mapper.common.Mapper;

public interface DevicesEventRecMapper extends Mapper<DevicesEventRec> {

	public List<EventVO> get_eventList(@Param("id") Integer id ,@Param("eventName") String eventName, @Param("devNum") String devNum);

	public List<AiDiDoutVO> get_eventTypeOfgroup();

	public List<AiDiDoutVO> get_eventCountOfGroup();

	public List<AiDiDoutVO> get_eventCount();

}