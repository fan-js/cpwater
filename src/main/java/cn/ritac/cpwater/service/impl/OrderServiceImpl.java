/*
 *
 *
 */
package cn.ritac.cpwater.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.ritac.cpwater.mybatis.mapper.OrderMapper;
import cn.ritac.cpwater.mybatis.model.Order;
import cn.ritac.cpwater.mybatis.model.Params;
import cn.ritac.cpwater.mybatis.model.ResOrder;
import cn.ritac.cpwater.service.OrderService;
import cn.ritac.cpwater.web.dto.convert.AiDiDoutVO;
import cn.ritac.cpwater.web.dto.convert.StatisticsData;

/**
 *<b>Description:</b><br>
 * @author admin
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> OrderServiceImpl
 *<br><b>CreatTime:</b> 2019年4月22日下午12:15:36
 */
@Service
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	public void setInit() {
		super.setInit(orderMapper);
	}

	@Override
	public PageInfo<ResOrder> findOrder(int pageIndex, int pageSize, Integer orderState, String eventName) {
		PageHelper.startPage(pageIndex, pageSize);
		List<ResOrder> list = orderMapper.findOrder(orderState, eventName);
		return new PageInfo<>(list);
	}

	@Override
	public List<Params> findEventParamList() {
		return orderMapper.findEventParamList();
	}

	@Override
	public void updateEventAction(int id, boolean orderStatus) {
		orderMapper.updateEventAction(id, orderStatus);
	}

	@Override
	public List<StatisticsData> getStatisticsOfOrder() {
		return orderMapper.getStatisticsOfOrder();
	}

	@Override
	public List<StatisticsData> statisticsOrderOfgroup() {
		return orderMapper.statisticsOrderOfgroup();
	}

	@Override
	public List<AiDiDoutVO> get_orgerCountOfgroup() {
		return orderMapper.get_orgerCountOfgroup();
	}

}
