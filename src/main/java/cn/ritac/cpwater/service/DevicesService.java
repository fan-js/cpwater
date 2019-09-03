package cn.ritac.cpwater.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import cn.ritac.cpwater.mybatis.model.Devices;
import cn.ritac.cpwater.mybatis.model.DevicesState;
import cn.ritac.cpwater.web.dto.ContVoltnetPojo;
import cn.ritac.cpwater.web.dto.EventDto;
import cn.ritac.cpwater.web.dto.voltnetPojo;
import cn.ritac.cpwater.web.dto.convert.AiDiDoutVO;
import cn.ritac.cpwater.web.dto.convert.EventVO;
import cn.ritac.cpwater.web.dto.convert.ProporVO;

public interface DevicesService extends BaseService<Devices, Integer> {

	/**
	 * 得到设备列表
	 * 
	 * @param deviceNum
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	PageInfo<Devices> getDevices(String deviceNum, int pageIndex, int pageSize);

	public DevicesState getUserDevicesState(int uid);

	public void delBindUserDev(int device_id);

	public List<Devices> findDeviceOfGroup(int groupId);

	public List<Devices> findDeviceOfRole(Integer groupId, Integer roleId);

	public List<ProporVO> deviceProporList();

	public List<ProporVO> eventProporList();

	PageInfo<EventVO> findEventList(EventDto eventDto);

	// 封装ai , di , do , charts

	public Map<String, Object> sendAi(int id);

	public Map<String, Object> sendDi(int id);

	public Map<String, Object> sendDo(int id);

	public Map<String, Object> sendCharts(int id);

	public List<voltnetPojo> voltnetPropor(Integer id, Integer parm_year, Integer parm_month);

	public List<Devices> findAllNotInGroup();

	public List<EventVO> get_eventList(Integer id, String eventName, String devNum);

	public List<AiDiDoutVO> get_eventTypeOfgroup();

	public List<AiDiDoutVO> get_eventCountOfGroup();

	public List<AiDiDoutVO> get_eventCount();

	public List<ContVoltnetPojo> countVoltnetPropor();

	public PageInfo<Devices> findDeviceByCondition(int pageIndex, int pageSize, String devNum, String devModel,
			String devPosit, String devRemark, Boolean on_line, Integer groupId);

	public List<Devices> getDevicesNoPage();

	public void updateHoursAndLenthOfTime();
	
	public void updateHoursAll();

}
