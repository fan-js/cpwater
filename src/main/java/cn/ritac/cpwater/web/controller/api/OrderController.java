/*
 *
 *
 */
package cn.ritac.cpwater.web.controller.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageInfo;
import cn.ritac.cpwater.mybatis.model.Order;
import cn.ritac.cpwater.mybatis.model.Params;
import cn.ritac.cpwater.mybatis.model.ResOrder;
import cn.ritac.cpwater.mybatis.model.Users;
import cn.ritac.cpwater.service.OrderService;
import cn.ritac.cpwater.service.UsersService;
import cn.ritac.cpwater.util.DateTime;
import cn.ritac.cpwater.web.dto.OrderDto;
import cn.ritac.cpwater.web.dto.convert.AiDiDoutVO;
import cn.ritac.cpwater.web.dto.convert.StatisticsData;
import cn.ritac.cpwater.web.dto.convert.TUserDto;

/**
 *<b>Description:</b><br>
 * @author admin
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> OrderController
 *<br><b>CreatTime:</b> 2019年4月22日上午11:28:59
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

	@Autowired
	private UsersService userService;

	@Autowired
	private OrderService orderService;

	/**
	 * 获取工单列表
	 * @param orderDto
	 * @return 工单列表
	 */
	@GetMapping("/findOrder")
	public String findOrder(OrderDto orderDto) {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		if (StringUtils.isEmpty(orderDto)) {
			return returnLogic.resultErrorJsonString(206, "获取列表失败,输入参数有误。");
		}
		int pageIndex = orderDto.getPageIndex();
		int pageSize = orderDto.getPageSize();
		Integer orderState = orderDto.getOrderState() == null ? null
				: orderDto.getOrderState() == 3 ? null : orderDto.getOrderState();
		String eventName = orderDto.getEventName();
		PageInfo<ResOrder> orderList = orderService.findOrder(pageIndex, pageSize, orderState, eventName);
		return returnLogic.resultJsonString(200, "查询成功。", orderList);
	}

	/**
	 * 派单过程调用,用于选择派给对象
	 * @return  userList
	 */
	@GetMapping("/toDispatchOrder")
	public String toDispatchOrder() {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		List<Users> userList = userService.findAll();
		List<TUserDto> resUserList = new ArrayList<TUserDto>();
		if (!StringUtils.isEmpty(userList)) {
			for (Users u : userList) {
				TUserDto tu = new TUserDto();
				BeanUtils.copyProperties(u, tu);
				resUserList.add(tu);
			}
		}
		return returnLogic.resultJsonString(200, "查询成功。", resUserList);
	}









	/**
	 * 派单人处理完调用更新订单
	 * @param orderDto
	 * @return msg
	 */
	@PutMapping("/updateOrder")
	public String updateOrder(@RequestBody OrderDto orderDto) {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		// 接单后:1. 解决问题,修改工单状态,填备注; 2. 未解决问题,改状态,填备注
		if (StringUtils.isEmpty(orderDto) || StringUtils.isEmpty(orderDto.getOrderState())) {
			return returnLogic.resultErrorJsonString(206, "获取列表失败,输入参数有误。");
		}
		int orderState = orderDto.getOrderState();
		String orderDescript = orderDto.getOrderDescript();
		if (orderState != 2 && StringUtils.isEmpty(orderDescript)) {
			return returnLogic.resultErrorJsonString(206, "请认真填写工单处理情况描述。");
		}
		int id = orderDto.getId();
		Date nowDate = new Date();
		Order o = new Order();
		String processingTime = "";
		o = orderService.findById(id);
		if (!StringUtils.isEmpty(o) && !StringUtils.isEmpty(o.getDispatchTime())) {
			Date dispatchTime = o.getDispatchTime();
			long min = DateTime.DateDiffHours(nowDate, dispatchTime) * 60;
			Map<String, Object> map = DateTime.minutesToHours(Integer.parseInt(String.valueOf(min)));
			processingTime = map.get("day") + "天" + map.get("hours") + "小时" + map.get("minutes") + "分";
		}
		o.setOrderState(orderState);
		o.setOrderDescript(orderDescript);
		o.setUpdateTime(nowDate);
		o.setProcessingTime(processingTime);
		o.setImages(orderDto.getImages());
		orderService.update(o);
		return returnLogic.resultJson(200, "更新成功。");
	}

	/**
	 * 设置告警派单模式
	 * @param id
	 * @param orderAction
	 * @return msg
	 */
	@PutMapping("/orderAction")
	public String updateOrderParams(Integer id, Integer orderAction) {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		if (StringUtils.isEmpty(id) || StringUtils.isEmpty(orderAction)) {
			return returnLogic.resultErrorJsonString(206, "修改失败,输入参数有误。");
		}
		boolean isO = orderAction == 1 ? true : false;
		orderService.updateEventAction(id, isO);
		return returnLogic.resultJson(200, "操作成功。");
	}

	/**
	 * 告警可配置参数列表
	 * @return paramsList
	 */
	@GetMapping("/getOrderParams")
	public String findParams() {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		List<Params> paramList = orderService.findEventParamList();
		return returnLogic.resultJsonString(200, "查询成功", paramList);

	}

	/**
	 * 获取各种状态订单占比数据
	 * @return statisticsOrderList
	 */
	@GetMapping("/statisticsOrder")
	public String statisticsOrder() {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		List<StatisticsData> statisticsOrderList = orderService.getStatisticsOfOrder();
		Map<String, Object> rows = new HashMap<String, Object>();
		rows.put("rows", statisticsOrderList);
		return returnLogic.resultJsonString(200, "查询成功。", rows);
	}

	/**
	 * 获取每个分组下工单总数
	 */
	@GetMapping("/statisticsOrderOfgroup")
	public String statisticsOrderOfgroup() {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		List<AiDiDoutVO> statisticsOrderList = orderService.get_orgerCountOfgroup();
		Map<String, Object> rows = new HashMap<String, Object>();
		rows.put("rows", statisticsOrderList);
		return returnLogic.resultJsonString(200, "查询成功。", rows);
	}

}
