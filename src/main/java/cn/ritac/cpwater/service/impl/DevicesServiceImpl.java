package cn.ritac.cpwater.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.ritac.cpwater.mybatis.model.*;
import cn.ritac.cpwater.web.dto.*;
import cn.ritac.cpwater.web.dto.convert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.ritac.cpwater.mybatis.mapper.DevicesAIMapper;
import cn.ritac.cpwater.mybatis.mapper.DevicesDIMapper;
import cn.ritac.cpwater.mybatis.mapper.DevicesDoMapper;
import cn.ritac.cpwater.mybatis.mapper.DevicesEventRecMapper;
import cn.ritac.cpwater.mybatis.mapper.DevicesMapper;
import cn.ritac.cpwater.mybatis.mapper.WorkingHourMapper;
import cn.ritac.cpwater.mybatis.mapper.WorkingTimeMapper;
import cn.ritac.cpwater.service.DevicesService;
import cn.ritac.cpwater.util.DateTime;
import cn.ritac.cpwater.util.NumberTools;

/**
 * 设备
 * 
 * @author admin
 *
 */
@Service
public class DevicesServiceImpl extends BaseServiceImpl<Devices> implements DevicesService {

	@Autowired
	private DevicesMapper devicesMapper;

	@Autowired
	private DevicesAIMapper devicesAIMapper;

	@Autowired
	private DevicesDIMapper devicesDIMapper;

	@Autowired
	private DevicesDoMapper devicesDOMapper;

	@Autowired
	private WorkingHourMapper workingHourMapper;

	@Autowired
	private WorkingTimeMapper workingTimeMapper;

	@Autowired
	private DevicesEventRecMapper devicesEventRecMapper;

	@Autowired
	public void setInit() {
		super.setInit(devicesMapper);
	}

	@Override
	public PageInfo<Devices> getDevices(String deviceNum, int pageIndex, int pageSize) {
		PageHelper.startPage(pageIndex, pageSize);

		Devices deviceInfo = new Devices();

		if (deviceNum != null && deviceNum != "") {
			deviceInfo.setDeviceNum(deviceNum);
		}

		List<Devices> list = devicesMapper.select(deviceInfo);

		return new PageInfo<>(list);
	}

	@Override
	public void delBindUserDev(int device_id) {
		devicesMapper.delBindUserDev(device_id);

	}

	@Override
	public DevicesState getUserDevicesState(int uid) {
		return devicesMapper.getUserDevicesState(uid);
	}

	@Override
	public List<Devices> findDeviceOfGroup(int groupId) {
		return devicesMapper.findDeviceOfGroup(groupId);
	}

	@Override
	public List<Devices> findDeviceOfRole(Integer groupId, Integer roleId) {
		return devicesMapper.findDeviceOfRole(groupId, roleId);
	}

	@Override
	public List<ProporVO> deviceProporList(String phone) {
		return devicesMapper.deviceProporList(phone);
	}

	@Override
	public List<ProporVO> eventProporList(String phone) {
		return devicesMapper.eventProporList(phone);
	}

	@Override
	public List<EventVO> findEventListCut(String phone){
		List<EventVO> list = devicesMapper.evenListCut(phone);
		return list;
	};

    @Override
	public List<EventVO> findNewEventList(Integer length){
		List<EventVO> list = devicesMapper.evenNewListCut(length);
		return list;
	};

	@Override
	public PageInfo<EventVO> findEventList(EventDto eventDto) {
		PageHelper.startPage(eventDto.getPageIndex(), eventDto.getPageSize());
		List<EventVO> list = devicesMapper.evenList(eventDto);
		return new PageInfo<>(list);
	}

	@Override
	public Map<String, Object> sendAi(int id) {
		DevicesAI dai = new DevicesAI();
		dai.setDeviceId(id);
		DevicesAI aiObj = devicesAIMapper.selectOne(dai);
		Map<String, Object> aiMap = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(aiObj)) {
			List<Ai> aiList = new ArrayList<Ai>();
			Ai ai = new Ai();
			ai.setName("电压");
			ai.setValue(aiObj.getVoltage());
			aiList.add(ai);

			ai = new Ai();
			ai.setName("电流");
			ai.setValue(aiObj.getCurrent());
			aiList.add(ai);

			ai = new Ai();
			ai.setName("电能");
			ai.setValue(aiObj.getElectricEnergy());
			aiList.add(ai);

			ai = new Ai();
			ai.setName("风向");
			ai.setValue(aiObj.getWindDirection());
			aiList.add(ai);

			ai = new Ai();
			ai.setName("压力");
			ai.setValue(aiObj.getPress());
			aiList.add(ai);

			aiMap.put("ain", aiList);
		}
		return aiMap;
	}

	@Override
	public Map<String, Object> sendDi(int id) {

		DevicesDI ddi = new DevicesDI();
		ddi.setDeviceId(id);
		DevicesDI doutObj = devicesDIMapper.selectOne(ddi);
		Map<String, Object> diMap = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(doutObj)) {
			List<Di> diList = new ArrayList<Di>();
			Di di = new Di();
			boolean isType = doutObj.getWaterPump();

			di.setName("水泵状态");
			di.setValue(isType == true ? 1 : 0);
			di.setDescript(isType == true ? "开启" : "关闭");
			diList.add(di);

			di = new Di();
			isType = doutObj.getWastegate();
			di.setName("泄压阀状态");
			di.setValue(isType == true ? 1 : 0);
			di.setDescript(isType == true ? "开启" : "关闭");
			diList.add(di);

			//判断水位
			String waterState="水位获取失败";
			if(doutObj.getWaterLeveld()){
				waterState="低水位";
				if(doutObj.getWaterLevelc()){
					waterState="中低水位";
					if(doutObj.getWaterLevelb()){
						waterState="中高水位";
						if(doutObj.getWaterlevela()){
							waterState="高水位";
						}
					}
				}
			}
			di = new Di();
			isType = doutObj.getWaterlevela();
			di.setName("水位状态 ");
			di.setValue(1);
			di.setDescript(waterState);
			diList.add(di);

			diMap.put("din", diList);
		}

		return diMap;
	}

	@Override
	public Map<String, Object> sendDo(int id) {
		DevicesDo dout = new DevicesDo();
		dout.setDeviceId(id);
		DevicesDo doutObj = devicesDOMapper.selectOne(dout);
		Map<String, Object> doutMap = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(doutObj)) {
			List<AiDiDoutVO> doutList = new ArrayList<AiDiDoutVO>();
			AiDiDoutVO dot = new AiDiDoutVO();
			dot.setName("水泵状态");
			dot.setValue(doutObj.getPump()?"开启":"关闭");
			doutList.add(dot);

			dot = new AiDiDoutVO();
			dot.setName("泄压阀状态");
			dot.setValue(doutObj.getWastegate()?"开启":"关闭");
			doutList.add(dot);

			dot = new AiDiDoutVO();
			dot.setName("电能表复位状态");
			dot.setValue(doutObj.getReset()?"开启":"关闭");
			doutList.add(dot);

			doutMap.put("dout", doutList);
		}
		return doutMap;
	}

	@Override
	public Map<String, Object> sendCharts(int id) {
		Map<String, Object> chartsMap = new HashMap<String, Object>();
		List<AiVO> aiChartsList = devicesAIMapper.getDeviceAiCharts(id);
		chartsMap.put("charts", aiChartsList);
		return chartsMap;
	}

	@Override
	public List<voltnetPojo> voltnetPropor(Integer id, Integer parm_year, Integer parm_month) {
		WorkingHour wh = new WorkingHour();
		Date d = new Date();
		if (!StringUtils.isEmpty(id))
			wh.setDeviceId(id);
		if (!StringUtils.isEmpty(parm_year) && !StringUtils.isEmpty(parm_month)) {
			wh.setYears(parm_year);
			wh.setMonths(parm_month);
		} else if (!StringUtils.isEmpty(parm_year)) {
			wh.setYears(parm_year);
		} else if (!StringUtils.isEmpty(parm_month)) {
			wh.setMonths(parm_month);
		} else {
			wh.setYears(DateTime.getYear(d));
			wh.setMonths(DateTime.getMonth(d));
		}
		List<WorkingHour> whList = workingHourMapper.select(wh);
		List<voltnetPojo> resList = new ArrayList<voltnetPojo>();
		if (!StringUtils.isEmpty(whList)) {
			for (WorkingHour w : whList) {
				int dev_id = w.getDeviceId();
				String devNum = "";
				Devices deb = devicesMapper.selectByPrimaryKey(dev_id);
				if (!StringUtils.isEmpty(deb) && !StringUtils.isEmpty(deb.getDeviceNum()))
					devNum = deb.getDeviceNum();
				// 总时长
				DecimalFormat df = new DecimalFormat("#.000");
				int tmp = w.getMinutes().intValue();
				int sum = whList.size() > 0 ? tmp == 0 ? 1 : tmp : 1;
				voltnetPojo vp = new voltnetPojo();
				vp.setDevNum(devNum);
				int month = w.getMonths();
				vp.setDate(String.valueOf(month));

				// 市电
				Long volt = w.getWorkinghours();
				volt = volt == null ? 0 : volt;
				vp.setElectric(volt);
				float vper = Float.parseFloat(String.valueOf(volt)) / sum;
				vp.setElePer(Float.valueOf(df.format(vper)));
				// ups
				Long ups_volt = w.getHours();
				ups_volt = ups_volt == null ? 0 : ups_volt;
				vp.setUps_electric(ups_volt);
				float ups_vper = Float.parseFloat(String.valueOf(ups_volt)) / sum;
				vp.setUps_elePer(ups_vper);

				// 有线网
				Long net = w.getNetworkinghours();
				net = net == null ? 0 : net;
				vp.setNet(net);
				float nper = Float.parseFloat(String.valueOf(net)) / sum;
				vp.setNetPer(Float.valueOf(df.format(nper)));
				// 无线网
				Long moblie_net = w.getNetHours();
				moblie_net = moblie_net == null ? 0 : moblie_net;
				vp.setMobile_net(moblie_net);
				float moblie_nper = Float.parseFloat(String.valueOf(moblie_net)) / sum;
				vp.setMobile_netPer(moblie_nper);

				vp.setSum(sum);
				resList.add(vp);
			}
		}
		return resList;
	}

	@Override
	public List<Devices> findAllNotInGroup() {
		return devicesMapper.findAllNotInGroup();
	}


	@Override
	public PageInfo<EventVO> get_eventList(Integer id,Integer pageIndex,Integer pageSize) {
		PageHelper.startPage(pageIndex, pageSize);
		List<EventVO> list = devicesEventRecMapper.get_eventList(id);
		return new PageInfo<>(list);
	}

	@Override
	public List<AiDiDoutVO> get_eventTypeOfgroup() {
		return devicesEventRecMapper.get_eventTypeOfgroup();
	}

	@Override
	public List<AiDiDoutVO> get_eventCountOfGroup() {
		return devicesEventRecMapper.get_eventCountOfGroup();
	}

	@Override
	public List<AiDiDoutVO> get_eventCount() {
		return devicesEventRecMapper.get_eventCount();
	}

	@Override
	public List<ContVoltnetPojo> countVoltnetPropor() {
		return workingHourMapper.countVoltnetPropor();
	}

	@Override
	public PageInfo<DevicesDto> findDeviceByCondition(int pageIndex, int pageSize) {
		PageHelper.startPage(pageIndex, pageSize);
		List<DevicesDto> list = devicesMapper.findDeviceByCondition();
		return new PageInfo<>(list);
	}

	@Override
	public PageInfo<DevicesDto> findDeviceByUser(int pageIndex, int pageSize, String telePhone) {
		PageHelper.startPage(pageIndex, pageSize);
		List<DevicesDto> list = devicesMapper.findDeviceByUser(telePhone);
		return  new PageInfo<>(list);
	}

	@Override
	public List<Devices> getDevicesNoPage(String phone) {
		return devicesMapper.getDevicesNoPage(phone);
	}

	/**
	 * 明天凌晨更新总时长
	 */
	public void updateHoursAll() {
		// 计算截止当前总时长，更新
		Date d = new Date();
		// 当前月份,年
		int mins = DateTime.getDayOfDate(d) * 24;
		int tmp_year = DateTime.getYear(d);
		int tmp_month = DateTime.getMonth(d);
		workingHourMapper.updateMinutesByYearMonth(mins, tmp_year, tmp_month);
	}

	/**
	 * 明天更新工作时长
	 */
	public void updateHoursAndLenthOfTime() {
		Date d = new Date();
		// 当前月份,年
		int tmp_year = DateTime.getYear(d);
		int tmp_month = DateTime.getMonth(d);
		// 取出所有截止当前在线设备，计算工作时长，并重置开始时间
		Devices dev = new Devices();
		dev.setOnline(true);
		List<Devices> devList_onLine = devicesMapper.select(dev);
		devList_onLine.forEach(device -> {
			// 取出每一台在线设备id
			int id = device.getId();
			int netType = device.getConnform();
			// 根据id取记录表
			WorkingTime wt = new WorkingTime();
			wt.setDeviceId(id);
			wt = workingTimeMapper.selectOne(wt);
			wt.setCreateTime(d);
			if (!StringUtils.isEmpty(wt)) {
				// 根据id取时长表
				WorkingHour wh = new WorkingHour();
				wh.setDeviceId(id);
				wh.setYears(tmp_year);
				wh.setMonths(tmp_month);
				wh = workingHourMapper.selectOne(wh);
				wh.setCreateTime(d);

				if (!StringUtils.isEmpty(netType) && netType == 1) {
					// 开始无线，计算有线时长
					Date ls_date = wt.getLanStarttime();
					ls_date = ls_date == null ? d : ls_date;
					long ls_netWork = DateTime.DateDiffHours(d, ls_date);
					ls_netWork += wh.getNetworkinghours();
					wh.setNetworkinghours(ls_netWork);
					// 设置记录
					wt.setLanStarttime(d);

				} else {
					// 开始有线，计算无线时长
					Date ss_date = wt.getSimStarttime();
					ss_date = ss_date == null ? d : ss_date;
					long ss_netHour = DateTime.DateDiffHours(d, ss_date);
					ss_netHour += wh.getNetHours();
					wh.setNetHours(ss_netHour);
					// 设置记录
					wt.setSimStarttime(d);
				}
				// 如果电结束时间等于ups开始时间，说明切换ups，则不计算
				Date ve_date = wt.getVoltageEndtime();
				Date us_date = wt.getUpsStarttime();
				if (StringUtils.isEmpty(ve_date) || StringUtils.isEmpty(us_date)||DateTime.dateCompare(ve_date, us_date) == 0) {
					// 开始ups，计算市电时长
					Date vs_date = wt.getVoltageStarttime();
					vs_date = vs_date == null ? d : vs_date;
					long vs_workHour = DateTime.DateDiffHours(d, vs_date);
					vs_workHour += wh.getWorkinghours();
					wh.setWorkinghours(vs_workHour);
					// 设置记录
					wt.setVoltageStarttime(d);
				}

				// 更新库
				workingTimeMapper.updateByPrimaryKey(wt);
				workingHourMapper.updateByPrimaryKey(wh);
			}

		});
	}
}
