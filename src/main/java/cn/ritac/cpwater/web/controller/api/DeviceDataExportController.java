package cn.ritac.cpwater.web.controller.api;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.ritac.cpwater.mybatis.mapper.ExportMapper;
import cn.ritac.cpwater.util.DateTime;
import cn.ritac.cpwater.web.dto.export.*;

/**
 *<b>Description:</b><br>
 * @author admin / mail: ccy.175@163.com
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> DeviceDataExportController
 *<br><b>CreatTime:</b> 2019年6月11日下午6:33:04
 */

@RestController
@RequestMapping(value = "/export")
public class DeviceDataExportController extends BaseController {
	@Autowired
	private ExportMapper exportMapper;

	/**
	 *  1. 分区告警 
	 * @return list
	 */
	@GetMapping(value = "/getGroupWing")
	public String getGroupWing(DateVo dateVo) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		Date startTime = null;
		Date endTime = null;
		String str_startTime = null;
		String str_endTime = null;
		if (!StringUtils.isEmpty(dateVo)) {
			str_startTime = dateVo.getStartTime() == null ? null : dateVo.getStartTime();
			str_endTime = dateVo.getEndTime() == null ? null : dateVo.getEndTime();
			if (!StringUtils.isEmpty(str_startTime) && !StringUtils.isEmpty(str_endTime)) {
				if (str_startTime.length() != 19 || str_endTime.length() != 19) {
					return returnLogic.resultErrorJsonString(206, "日期格式错误，请参考：[ yyyy-MM-dd HH:mm:ss] ");
				}
				startTime = DateTime.getTimestamp(str_startTime);
				endTime = DateTime.getTimestamp(str_endTime);
			}
		}
		List<GroupWringVo> list = exportMapper.export_groupWing(startTime, endTime);
		return returnLogic.resultJsonString(200, "查询成功。", list);
	}

	/**
	 *  2. 设备总在线状况
	 * @return list
	 */
	@GetMapping(value = "/getDeviceCount")
	public String getDeviceCount() {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
//		if (!subject.isAuthenticated()) {
//			return returnLogic.resultErrorJsonString(401, "请先登录！");
//		}
		String phone=null;
		List<DeviceCountVo> list = exportMapper.export_deviceCount(phone);
		return returnLogic.resultJsonString(200, "查询成功。", list);
	}

	/**
	 * 3. 设备区域在线状况
	 * @return list
	 */
	@GetMapping(value = "/getDeviceGroupCount")
	public String getDeviceGroupCount(DateVo dateVo) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		Date startTime = null;
		Date endTime = null;
		String str_startTime = null;
		String str_endTime = null;
		if (!StringUtils.isEmpty(dateVo)) {
			str_startTime = dateVo.getStartTime() == null ? null : dateVo.getStartTime();
			str_endTime = dateVo.getEndTime() == null ? null : dateVo.getEndTime();
			if (!StringUtils.isEmpty(str_startTime) && !StringUtils.isEmpty(str_endTime)) {
				if (str_startTime.length() != 19 || str_endTime.length() != 19) {
					return returnLogic.resultErrorJsonString(206, "日期格式错误，请参考：[ yyyy-MM-dd HH:mm:ss] ");
				}
				startTime = DateTime.getTimestamp(str_startTime);
				endTime = DateTime.getTimestamp(str_endTime);
			}
		}
		List<DeviceGroupCountVo> list = exportMapper.export_deviceGroupCount(startTime, endTime);
		return returnLogic.resultJsonString(200, "查询成功。", list);
	}

	/**
	 * 4. 区域设备在线率 
	 * @return list
	 */
	@GetMapping(value = "/getDeviceGroupPer")
	public String getDeviceGroupPer(DateVo dateVo) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		Date startTime = null;
		Date endTime = null;
		String str_startTime = null;
		String str_endTime = null;
		if (!StringUtils.isEmpty(dateVo)) {
			str_startTime = dateVo.getStartTime() == null ? null : dateVo.getStartTime();
			str_endTime = dateVo.getEndTime() == null ? null : dateVo.getEndTime();
			if (!StringUtils.isEmpty(str_startTime) && !StringUtils.isEmpty(str_endTime)) {
				if (str_startTime.length() != 19 || str_endTime.length() != 19) {
					return returnLogic.resultErrorJsonString(206, "日期格式错误，请参考：[ yyyy-MM-dd HH:mm:ss] ");
				}
				startTime = DateTime.getTimestamp(str_startTime);
				endTime = DateTime.getTimestamp(str_endTime);
			}
		}
		List<DeviceGroupPerVo> list = exportMapper.export_deviceGroupPer(startTime, endTime);
		return returnLogic.resultJsonString(200, "查询成功。", list);
	}

	/**
	 * 5. 设备月市电在线时长
	 * @return list
	 */
	@GetMapping(value = "/getDeviceMonthElectric")
	public String getDeviceMonthElectric(DateVo dateVo) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		Date startTime = null;
		Date endTime = null;
		String str_startTime = null;
		String str_endTime = null;
		if (!StringUtils.isEmpty(dateVo)) {
			str_startTime = dateVo.getStartTime() == null ? null : dateVo.getStartTime();
			str_endTime = dateVo.getEndTime() == null ? null : dateVo.getEndTime();
			if (!StringUtils.isEmpty(str_startTime) && !StringUtils.isEmpty(str_endTime)) {
				if (str_startTime.length() != 19 || str_endTime.length() != 19) {
					return returnLogic.resultErrorJsonString(206, "日期格式错误，请参考：[ yyyy-MM-dd HH:mm:ss] ");
				}
				startTime = DateTime.getTimestamp(str_startTime);
				endTime = DateTime.getTimestamp(str_endTime);
			}
		}
		List<DeviceMonthElectricVo> list = exportMapper.export_deviceMonthElectric(startTime, endTime);
		return returnLogic.resultJsonString(200, "查询成功。", list);
	}

	/**
	 *  6. 设备月网络在线时长
	 * @return list
	 */
	@GetMapping(value = "/getDeviceMonthNet")
	public String getDeviceMonthNet(DateVo dateVo) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		Date startTime = null;
		Date endTime = null;
		String str_startTime = null;
		String str_endTime = null;
		if (!StringUtils.isEmpty(dateVo)) {
			str_startTime = dateVo.getStartTime() == null ? null : dateVo.getStartTime();
			str_endTime = dateVo.getEndTime() == null ? null : dateVo.getEndTime();
			if (!StringUtils.isEmpty(str_startTime) && !StringUtils.isEmpty(str_endTime)) {
				if (str_startTime.length() != 19 || str_endTime.length() != 19) {
					return returnLogic.resultErrorJsonString(206, "日期格式错误，请参考：[ yyyy-MM-dd HH:mm:ss] ");
				}
				startTime = DateTime.getTimestamp(str_startTime);
				endTime = DateTime.getTimestamp(str_endTime);
			}
		}
		List<DeviceMonthNetVo> list = exportMapper.export_deviceMonthNet(startTime, endTime);
		return returnLogic.resultJsonString(200, "查询成功。", list);
	}

	/**
	 *  7.工单统计
	 * @return list
	 */
	@GetMapping(value = "/getOrderGroupPer")
	public String getOrderGroupPer(DateVo dateVo) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		Date startTime = null;
		Date endTime = null;
		String str_startTime = null;
		String str_endTime = null;
		if (!StringUtils.isEmpty(dateVo)) {
			str_startTime = dateVo.getStartTime() == null ? null : dateVo.getStartTime();
			str_endTime = dateVo.getEndTime() == null ? null : dateVo.getEndTime();
			if (!StringUtils.isEmpty(str_startTime) && !StringUtils.isEmpty(str_endTime)) {
				if (str_startTime.length() != 19 || str_endTime.length() != 19) {
					return returnLogic.resultErrorJsonString(206, "日期格式错误，请参考：[ yyyy-MM-dd HH:mm:ss] ");
				}
				startTime = DateTime.getTimestamp(str_startTime);
				endTime = DateTime.getTimestamp(str_endTime);
			}
		}
		List<OrderGroupPerVo> list = exportMapper.export_orderGroupPer(startTime, endTime);
		return returnLogic.resultJsonString(200, "查询成功。", list);
	}

}
