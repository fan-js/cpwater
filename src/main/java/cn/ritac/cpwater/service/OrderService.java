/*
 *
 *
 */
package cn.ritac.cpwater.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.ritac.cpwater.mybatis.model.Order;
import cn.ritac.cpwater.mybatis.model.Params;
import cn.ritac.cpwater.mybatis.model.ResOrder;
import cn.ritac.cpwater.web.dto.convert.AiDiDoutVO;
import cn.ritac.cpwater.web.dto.convert.StatisticsData;

/**
 *<b>Description:</b><br>
 * @author admin
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> OrderService
 *<br><b>CreatTime:</b> 2019年4月22日下午12:14:21
 */
public interface OrderService extends BaseService<Order, Integer> {

	public PageInfo<ResOrder> findOrder(int pageIndex, int pageSize, Integer orderState, String eventName);

	public List<Params> findEventParamList();

	public void updateEventAction(int id, boolean orderStatus);

	public List<StatisticsData> getStatisticsOfOrder();

	public List<StatisticsData> statisticsOrderOfgroup();

	public List<AiDiDoutVO> get_orgerCountOfgroup();


}
