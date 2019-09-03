package cn.ritac.cpwater.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ritac.cpwater.mybatis.model.Order;
import cn.ritac.cpwater.mybatis.model.Params;
import cn.ritac.cpwater.mybatis.model.ResOrder;
import cn.ritac.cpwater.web.dto.convert.AiDiDoutVO;
import cn.ritac.cpwater.web.dto.convert.StatisticsData;
import tk.mybatis.mapper.common.Mapper;

public interface OrderMapper extends Mapper<Order> {

	public List<ResOrder> findOrder(@Param("orderState") Integer orderState, @Param("eventName") String eventName);

	public List<Params> findEventParamList();

	public void updateEventAction(@Param("id") int id,@Param("orderStatus") boolean orderStatus);

	public List<StatisticsData> getStatisticsOfOrder();

	public List<StatisticsData> statisticsOrderOfgroup();

	public List<AiDiDoutVO> get_orgerCountOfgroup();

	public String get_paramVal(String pKye);

}