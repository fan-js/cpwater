package cn.ritac.cpwater.web.controller.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.ritac.cpwater.mybatis.mapper.DevicesEventRecMapper;
import cn.ritac.cpwater.mybatis.model.*;
import cn.ritac.cpwater.service.*;
import cn.ritac.cpwater.web.dto.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.ritac.cpwater.mybatis.model.Bakserve;
import cn.ritac.cpwater.mybatis.model.Dataupopt;
import cn.ritac.cpwater.mybatis.model.DeviceFuction;
import cn.ritac.cpwater.mybatis.model.Devices;
import cn.ritac.cpwater.mybatis.model.DevicesReg;
import cn.ritac.cpwater.mybatis.model.GroupDevice;
import cn.ritac.cpwater.mybatis.model.RegCamera;
import cn.ritac.cpwater.mybatis.model.RoleDevice;
import cn.ritac.cpwater.mybatis.model.Roles;
import cn.ritac.cpwater.service.BakserverService;
import cn.ritac.cpwater.service.DataupoptService;
import cn.ritac.cpwater.service.DevicesAIService;
import cn.ritac.cpwater.service.DevicesFunctionService;
import cn.ritac.cpwater.service.DevicesRegService;
import cn.ritac.cpwater.service.DevicesService;
import cn.ritac.cpwater.service.GroupDeviceService;
import cn.ritac.cpwater.service.RegCameraService;
import cn.ritac.cpwater.service.RolesDeviceService;
import cn.ritac.cpwater.service.RolesService;
import cn.ritac.cpwater.util.JWTUtil;
import cn.ritac.cpwater.web.dto.convert.ProporVO;
import cn.ritac.cpwater.web.dto.convert.SettingConfigPojo;
import cn.ritac.cpwater.web.dto.convert.SettingConfigVO;
import cn.ritac.cpwater.web.dto.convert.SysPojo;
import cn.ritac.cpwater.web.dto.convert.SysVO;
import cn.ritac.cpwater.web.dto.convert.AiDiDoutVO;
import cn.ritac.cpwater.web.dto.convert.AiVO;
import cn.ritac.cpwater.web.dto.convert.EventVO;

/**
 *<b>Description:</b><br>
 * @author admin
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> DevicesController
 *<br><b>CreatTime:</b> 2019年4月30日上午10:40:22
 */

@RestController
@RequestMapping(value = "/devices")
public class DevicesController extends BaseController {
	@Autowired
	private DevicesService devicesService;
	@Autowired
	private UsersService usersService;

	@Autowired
	private MQTTService MQTTService;
	@Autowired
	private DevicesAIService devicesAIService;
	@Autowired
	private DevicesRegService devicesRegService;

	@Autowired
	private DeviceAndUserService deviceAndUserService;

	@Autowired
	private DeviceCameraService deviceCameraService;

	@Autowired
	private HttpServletRequest httpServletRequest;

	@Autowired
	private DevicesFunctionService devicesFunctionService;

	@Autowired
	private RolesDeviceService rolesDeviceService;

	@Autowired
	private GroupDeviceService groupDeviceService;

	@Autowired
	private BakserverService bakserverService;

	@Autowired
	private DataupoptService dataupoptService;

	@Autowired
	private RegCameraService regCameraService;

	@Autowired
	private RolesService rolesService;

	@Autowired
	private DevicesEventRecMapper devicesEventRecMapper;

	/**
	 * 得到设备列表
	 * @param
	 * @param
	 * @param
	 * @return
	 */
	@PostMapping("/getDevices")
	public String getDevices(@RequestBody DevicesDto devDto) {
		// 首先，检测用户登录状态；
//		Subject subject = SecurityUtils.getSubject();
//		if (!subject.isAuthenticated()) {
//			return returnLogic.resultErrorJsonString(401, "请先登录！");
//		}
		String tk = httpServletRequest.getHeader("Authorization") == null ? ""
				: httpServletRequest.getHeader("Authorization");
		// 电话号码
		String userName = JWTUtil.getUsername(tk);
//		if (StringUtils.isEmpty(userName)) {
//			return returnLogic.resultErrorJsonString(401, "登录已失效，请先登录！");
//		}
		if (StringUtils.isEmpty(devDto)) {
			return returnLogic.resultErrorJsonString(206, "输入参数有误。");
		}
		Integer pageIndex = devDto.getPageIndex();
		Integer pageSize = devDto.getPageSize();
		//分两种身份1：厂家直接获取所有设备 2：用户获取自己绑定的设备
		//判断用户身份
		PageInfo<DevicesDto> devList;
		Users searchUser = new Users();
		searchUser.setTelephone(userName);
		Users users = usersService.find(searchUser);
		String type=users.getType();
		if("1".equals(type)){
			//厂家查询设备
			devList = devicesService.findDeviceByCondition(pageIndex, pageSize);
		}else {
			//用户查询设备
			devList = devicesService.findDeviceByUser(pageIndex, pageSize,userName);
		}
		return returnLogic.resultJsonString(200, "查询成功。", devList);
	}


	/**
	 * 管理员用户列表内得到设备列表
	 * @param
	 * @param
	 * @param
	 * @return
	 */
	@PostMapping("/getUserDevices")
	public String getUserDevices(@RequestBody DevicesDto devDto) {
		// 首先，检测用户登录状态；
//		Subject subject = SecurityUtils.getSubject();
//		if (!subject.isAuthenticated()) {
//			return returnLogic.resultErrorJsonString(401, "请先登录！");
//		}
		if (StringUtils.isEmpty(devDto)) {
			return returnLogic.resultErrorJsonString(206, "输入参数有误。");
		}
		Integer pageIndex = devDto.getPageIndex();
		Integer pageSize = devDto.getPageSize();
		//分两种身份1：厂家直接获取所有设备 2：用户获取自己绑定的设备
		//判断用户身份
		PageInfo<DevicesDto> devList;
			//根据用户的手机号查询用户的设备
			devList = devicesService.findDeviceByUser(pageIndex, pageSize,devDto.getUserphone());
		return returnLogic.resultJsonString(200, "查询成功。", devList);
	}



	/**
	 * 用户绑定设备
	 * @param
	 * @param
	 */
	@RequestMapping("/bind")
	public String bindUser(@RequestBody DeviceAndUserDto deviceAndUserDto) {
		Subject subject = SecurityUtils.getSubject();
//		if (!subject.isAuthenticated()) {
//			return returnLogic.resultErrorJsonString(401, "请先登录！");
//		}
		if (StringUtils.isEmpty(deviceAndUserDto)) {
			return returnLogic.resultErrorJsonString(206, "参数错误。");
		}

		Devices devices = new Devices();
		devices.setDeviceNum(String.valueOf(deviceAndUserDto.getDeviceNum()));
		Devices dev = devicesService.find(devices);
		if (StringUtils.isEmpty(dev)) {
			return returnLogic.resultErrorJsonString(206, "不存在该设备。");
		}
		DeviceAndUser dau=new DeviceAndUser();
		dau.setDeviceNum(deviceAndUserDto.getDeviceNum());
		DeviceAndUser deviceAndUser=deviceAndUserService.find(dau);
		if(!StringUtils.isEmpty(deviceAndUser)){
			return returnLogic.resultErrorJsonString(206, "设备已绑定，请勿重复操作。");
		}
		Date updateTime=new Date();
		dau.setDeviceNum(deviceAndUserDto.getDeviceNum());
		dau.setUserId(deviceAndUserDto.getUserId());
		dau.setUserPhone(deviceAndUserDto.getUserPhone());
		dau.setUpdateTime(updateTime);
		deviceAndUserService.save(dau);
		return returnLogic.resultErrorJsonString(206, "设备绑定成功。");
	}


	/**
	 * 为设备增加摄像头地址
	 * */
	@PostMapping("/camera")
	public String camera(@RequestBody DeviceCameraDto deviceCameraDto){
		Subject subject = SecurityUtils.getSubject();
//		if (!subject.isAuthenticated()) {
//			return returnLogic.resultErrorJsonString(401, "请先登录！");
//		}
		if (StringUtils.isEmpty(deviceCameraDto)) {
			return returnLogic.resultErrorJsonString(206, "参数错误。");
		}
		DeviceCamera deviceCamera=new DeviceCamera();
		deviceCamera.setUrl(deviceCameraDto.getUrl());
		deviceCamera=deviceCameraService.find(deviceCamera);
		if(!StringUtils.isEmpty(deviceCamera)){
			return returnLogic.resultErrorJsonString(206, "地址已被使用，请确认后操作！");
		}
		DeviceCamera dc=new DeviceCamera();
		dc.setUrl(deviceCameraDto.getUrl());
		dc.setDeviceNum(deviceCameraDto.getDeviceNum());
		dc.setUpdateTime(new Date());
		deviceCameraService.save(dc);
		return returnLogic.resultJson(200, "配置成功。");
	}

	/**
	 * 查看设备摄像头
	 * */
	@GetMapping("/getCamera")
	public String getCamera(@RequestBody DeviceCameraDto deviceCameraDto){
		Subject subject = SecurityUtils.getSubject();
//		if (!subject.isAuthenticated()) {
//			return returnLogic.resultErrorJsonString(401, "请先登录！");
//		}
		if (StringUtils.isEmpty(deviceCameraDto)) {
			return returnLogic.resultErrorJsonString(206, "参数错误。");
		}
		DeviceCamera deviceCamera=new DeviceCamera();
		deviceCamera.setDeviceNum(deviceCameraDto.getDeviceNum());
		deviceCamera=deviceCameraService.find(deviceCamera);
		if(StringUtils.isEmpty(deviceCamera)){
			return returnLogic.resultErrorJsonString(206, "地址不存在，请联系管理员添加！");
		}
		DeviceCamera dc=new DeviceCamera();
		dc.setUrl(deviceCamera.getUrl());
		dc.setDeviceNum(deviceCamera.getDeviceNum());
		dc.setUpdateTime(deviceCamera.getUpdateTime());
		dc.setId(deviceCamera.getId());
		return returnLogic.resultJsonString(200, "获取地址成功！",dc);
	}



	/**
	 * 保存修改设备基本信息
	 *
	 * @param
	 * @return
	 */
	@PostMapping("/saveDevice")
	public String saveDevice(@RequestBody DevicesDto devDto) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
//		if (!subject.isAuthenticated()) {
//			return returnLogic.resultErrorJsonString(401, "请先登录！");
//		}
		String tk = httpServletRequest.getHeader("Authorization") == null ? ""
				: httpServletRequest.getHeader("Authorization");
		// 电话号码
		String userName = JWTUtil.getUsername(tk);
//		if (StringUtils.isEmpty(userName)) {
//			return returnLogic.resultErrorJsonString(401, "登录已失效，请先登录！");
//		}
		List<DeviceFuction> df = devicesFunctionService.findDeviceFunctionByRoleId(userName);

		if (StringUtils.isEmpty(devDto)) {
			return returnLogic.resultErrorJsonString(206, "输入参数有误。");
		}
		int devId = devDto.getId() == null ? 0 : devDto.getId();
		ModelMapper modelMapper = new ModelMapper();
		Devices pojo = modelMapper.map(devDto, Devices.class);
		String msg = "添加成功。";
		// 修改
		if (devId > 0) {
			Devices devicesInfo = devicesService.findById(devId);
			if (devicesInfo == null) {
				return returnLogic.resultErrorJsonString(206, "不存在该设备");
			}
			devicesInfo.setUpdateTime(new Date());
			devicesInfo.setLongitude(devDto.getLongitude());
			devicesInfo.setDimension(devDto.getDimension());
			devicesInfo.setPositionDes(devDto.getPositionDes());
			devicesInfo.setIsfault(devDto.getIsfault());
			devicesInfo.setRemark(devDto.getRemark());
			devicesInfo.setFirstlink(devDto.getFirstlink());
			devicesInfo.setKeepaliveinterval(devDto.getKeepaliveinterval());
			devicesInfo.setDatauptime(devDto.getDatauptime());
			devicesInfo.setFirstserver(devDto.getFirstserver());
			devicesInfo.setDeviceModel(devDto.getDeviceModel());
			devicesService.update(devicesInfo);
			msg = "修改成功。";
		} else {
			// 新增
			pojo.setCreateTime(new Date());
			devicesService.save(pojo);
		}
		return returnLogic.resultJson(200, msg);
	}



	/**
	 * 修改设备信息时，返回设备的基本信息
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/getDeviceBase")
	public String getDeviceBase(Integer id) {
		Subject subject = SecurityUtils.getSubject();
//		if (!subject.isAuthenticated()) {
//			return returnLogic.resultErrorJsonString(401, "请先登录！");
//		}
		if (StringUtils.isEmpty(id)) {
			return returnLogic.resultErrorJsonString(206, "输入参数有误。");
		}
		Devices devices = new Devices();
		devices.setId(id);
		Devices dev = devicesService.find(devices);
		return returnLogic.resultJsonString(200, "查询成功。", dev);
	}



	/**
	 * 删除设备
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteDevice")
	@Transactional
	public String deleteDevice(Integer id) {
		// 首先，检测用户登录状态；
//		Subject subject = SecurityUtils.getSubject();
//		if (!subject.isAuthenticated()) {
//			return returnLogic.resultErrorJsonString(401, "请先登录！");
//		}
		String tk = httpServletRequest.getHeader("Authorization") == null ? ""
				: httpServletRequest.getHeader("Authorization");
		// 电话号码
		String userName = JWTUtil.getUsername(tk);
//		if (StringUtils.isEmpty(userName)) {
//			return returnLogic.resultErrorJsonString(401, "登录已失效，请先登录！");
//		}
		if (StringUtils.isEmpty(id)) {
			return returnLogic.resultErrorJsonString(206, "输入参数有误。");
		}
		Devices deviceInfo = devicesService.findById(id);
		if (deviceInfo == null) {
			return returnLogic.resultErrorJsonString(206, "没有该设备,删除失败");
		}
		// 删除设备列表里的设备
		devicesService.delete(new Integer[] { id });
		//删除与用户的绑定
		DeviceAndUser deviceAndUser=new DeviceAndUser();
		deviceAndUser.setDeviceNum(id);
		deviceAndUser=deviceAndUserService.find(deviceAndUser);
		deviceAndUserService.delete(new Integer[] { deviceAndUser.getId() });
		return returnLogic.resultSuccess();
	}

	/**
	 * 设备占比可视化
	 * @return deviceProporList
	 */
	@GetMapping("/devicePropor")
	public String devicePropor() {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
//		if (!subject.isAuthenticated()) {
//			return returnLogic.resultErrorJsonString(401, "请先登录！");
//		}
		String tk = httpServletRequest.getHeader("Authorization") == null ? ""
				: httpServletRequest.getHeader("Authorization");
		// 电话号码
		String userPhone = JWTUtil.getUsername(tk);
		List<ProporVO> deviceProporList;
		Users searchUser = new Users();
		searchUser.setTelephone(userPhone);
		Users users = usersService.find(searchUser);
		String type=users.getType();
		String phone=null;
		if("0".equals(type)){
			//厂家查询设备
			phone=userPhone;
		}
			deviceProporList = devicesService.deviceProporList("phone");
		return returnLogic.resultJsonString(200, "查询成功", deviceProporList);
	}

	/**
	 * 事件占比可视化
	 * @return eventProporList
	 */
	@GetMapping("/eventPropor")
	public String eventPropor() {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
//		if (!subject.isAuthenticated()) {
//			return returnLogic.resultErrorJsonString(401, "请先登录！");
//		}
		List<ProporVO> eventProporList = devicesService.eventProporList("");
		return returnLogic.resultJsonString(200, "查询成功", eventProporList);
	}

	/**
	*
	* @param
	* @return
	*/

	@GetMapping("/getDeviceAI")
	public String getDeviceAI(Integer deviceId) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
//		if (!subject.isAuthenticated()) {
//			return returnLogic.resultErrorJsonString(401, "请先登录！");
//		}
		if (StringUtils.isEmpty(deviceId)) {
			return returnLogic.resultErrorJsonString(206, "输入参数有误。");
		}
		Map<String, Object> aiMap = devicesService.sendAi(deviceId);
		return returnLogic.resultJsonString(200, "查询成功", aiMap);
	}

	/**
	*
	* @param
	* @return
	*/

	@GetMapping("/getDeviceDI")
	public String getDeviceDI(int id) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
//		if (!subject.isAuthenticated()) {
//			return returnLogic.resultErrorJsonString(401, "请先登录！");
//		}
		if (StringUtils.isEmpty(id)) {
			return returnLogic.resultErrorJsonString(206, "输入参数有误。");
		}
		Map<String, Object> diMap = devicesService.sendDi(id);
		return returnLogic.resultJsonString(200, "查询成功", diMap);

	}

	/**
	*
	* @param
	* @return
	*/

	@GetMapping("/getDeviceDO")
	public String getDeviceDO(Integer id) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
//		if (!subject.isAuthenticated()) {
//			return returnLogic.resultErrorJsonString(401, "请先登录！");
//		}
		if (StringUtils.isEmpty(id)) {
			return returnLogic.resultErrorJsonString(206, "输入参数有误。");
		}
		Map<String, Object> doutMap = devicesService.sendDo(id);
		return returnLogic.resultJsonString(200, "查询成功", doutMap);
	}

	@GetMapping("/getDeviceAiCharts")
	public String findDeviceAiCharts(Integer id) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
//		if (!subject.isAuthenticated()) {
//			return returnLogic.resultErrorJsonString(401, "请先登录！");
//		}
		if (StringUtils.isEmpty(id)) {
			return returnLogic.resultErrorJsonString(206, "输入参数有误。");
		}
		List<AiVO> aiChartsList = devicesAIService.getDeviceAiCharts(id);
		return returnLogic.resultJsonString(200, "查询成功", aiChartsList);
	}

	/**
		* 得到事件列表
		* @param
		* @param
		* @param
		* @param
		* @param
		* @return
		*/

	@RequestMapping("/getDeviceEventRecord")
	public String getDeviceEventRecord(EventDto eventDto) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
//		if (!subject.isAuthenticated()) {
//			return returnLogic.resultErrorJsonString(401, "请先登录！");
//		}
		if (StringUtils.isEmpty(eventDto)) {
			return returnLogic.resultErrorJsonString(206, "输入参数有误。");
		}
		PageInfo<EventVO> eventList = devicesService.findEventList(eventDto);
		return returnLogic.resultJsonString(200, "查询成功", eventList);
	}

	/**
	 * 根据设备主键获取设备基础设置
	 * @param id
	 * @return SysPojo
	 */
	@GetMapping("/findSysConfig")
	public String findSysConfig(Integer id) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
//		if (!subject.isAuthenticated()) {
//			return returnLogic.resultErrorJsonString(401, "请先登录！");
//		}
		if (StringUtils.isEmpty(id)) {
			return returnLogic.resultErrorJsonString(206, "输入参数有误。");
		}

		// 涉及到表: device , bakserver , dataupopt
		// 数据封装
		List<SysPojo> sysPojos = new ArrayList<SysPojo>();

		// 1. 取 device
		Devices devSearch = new Devices();
		devSearch.setId(id);
		Devices dev = devicesService.find(devSearch);
		if (!StringUtils.isEmpty(dev)) {
			List<SysVO> sysVOs = new ArrayList<SysVO>();
			SysPojo sysPojo = new SysPojo();
			SysVO sysVO = new SysVO();
			sysVO.setName("首选");
			sysVO.setKey("firstLink");
			sysVO.setValue(dev.getFirstlink());
			sysVOs.add(sysVO);
			sysPojo.setTitle("首选连接方式");
			sysPojo.setKey("device");
			sysPojo.setValue(sysVOs);
			sysPojos.add(sysPojo);

			sysVOs = new ArrayList<SysVO>();
			sysPojo = new SysPojo();
			sysVO = new SysVO();
			sysVO.setName("心跳间隔");
			sysVO.setKey("keepAliveInterval");
			sysVO.setValue(dev.getKeepaliveinterval());
			sysVOs.add(sysVO);
			sysPojo.setTitle("心跳间隔");
			sysPojo.setKey("device");
			sysPojo.setValue(sysVOs);
			sysPojos.add(sysPojo);

			sysVOs = new ArrayList<SysVO>();
			sysPojo = new SysPojo();
			sysVO = new SysVO();
			sysVO.setName("周期");
			sysVO.setKey("dataUpTime");
			sysVO.setValue(dev.getDatauptime());
			sysVOs.add(sysVO);
			sysPojo.setTitle("数据更新周期");
			sysPojo.setKey("device");
			sysPojo.setValue(sysVOs);
			sysPojos.add(sysPojo);

			sysVOs = new ArrayList<SysVO>();
			sysPojo = new SysPojo();
			sysVO = new SysVO();
			sysVO.setName("url");
			sysVO.setKey("firstServer");
			sysVO.setValue(dev.getFirstserver());
			sysVOs.add(sysVO);
			sysPojo.setTitle("首选服务器");
			sysPojo.setKey("device");
			sysPojo.setValue(sysVOs);
			sysPojos.add(sysPojo);
		}
		// 2. 取 bakserver
		Bakserve bakserveSerach = new Bakserve();
		bakserveSerach.setDeviceId(id);
		List<Bakserve> bakserveList = bakserverService.findList(bakserveSerach);
		if (!StringUtils.isEmpty(bakserveList)) {
			bakserveList.forEach(bakserve -> {
				List<SysVO> sysVOs = new ArrayList<SysVO>();
				SysPojo sysPojo = new SysPojo();
				sysPojo.setTitle("服务器列表");
				sysPojo.setKey("bakserver");

				SysVO sysVO = new SysVO();
				sysVO.setName("url");
				sysVO.setKey("url");
				sysVO.setValue(bakserve.getUrl());
				sysVOs.add(sysVO);

				sysVO = new SysVO();
				sysVO.setName("port");
				sysVO.setKey("port");
				sysVO.setValue(bakserve.getPort());
				sysVOs.add(sysVO);
				sysPojo.setValue(sysVOs);
				sysPojos.add(sysPojo);
			});
		}

		// 3. 取 dataupopt
		Dataupopt dataupoptSearch = new Dataupopt();
		dataupoptSearch.setDeviceId(id);
		Dataupopt dataupopt = dataupoptService.find(dataupoptSearch);
		if (!StringUtils.isEmpty(dataupopt)) {
			List<SysVO> sysVOs = new ArrayList<SysVO>();
			SysPojo sysPojo = new SysPojo();
			sysPojo.setTitle("数据更新选项");
			sysPojo.setKey("dataupopt");

			SysVO sysVO = new SysVO();
			sysVO.setName("测量数据");
			sysVO.setKey("ain");
			sysVO.setValue(dataupopt.getAin());
			sysVOs.add(sysVO);

			sysVO = new SysVO();
			sysVO.setName("设备模拟量输出");
			sysVO.setKey("aout");
			sysVO.setValue(dataupopt.getAout());
			sysVOs.add(sysVO);

			sysVO = new SysVO();
			sysVO.setName("开关量输入");
			sysVO.setKey("din");
			sysVO.setValue(dataupopt.getDin());
			sysVOs.add(sysVO);

			sysVO = new SysVO();
			sysVO.setName("开关量输出");
			sysVO.setKey("dout");
			sysVO.setValue(dataupopt.getDout());
			sysVOs.add(sysVO);

			sysVO = new SysVO();
			sysVO.setName("参数/规则");
			sysVO.setKey("reg");
			sysVO.setValue(dataupopt.getReg());
			sysVOs.add(sysVO);

			sysVO = new SysVO();
			sysVO.setName("事件");
			sysVO.setKey("event");
			sysVO.setValue(dataupopt.getEvent());
			sysVOs.add(sysVO);
			sysPojo.setValue(sysVOs);
			sysPojos.add(sysPojo);
		}

		return returnLogic.resultJsonString(200, "查询成功", sysPojos);
	}

	/**
	 * 根据主键获取设备配置
	 * REG配置信息
	 * @param id
	 * @return settingConfigPojo
	 */
	@GetMapping("/getDeviceConfig")
	public String getDeviceConfig(Integer id) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
//		if (!subject.isAuthenticated()) {
//			return returnLogic.resultErrorJsonString(401, "请先登录！");
//		}
		if (StringUtils.isEmpty(id)) {
			return returnLogic.resultErrorJsonString(206, "输入参数有误。");
		}
		// 获取设备当前配置
		DevicesReg devReg = new DevicesReg();
		devReg.setDeviceId(id);
		devReg = devicesRegService.find(devReg);
		if(StringUtils.isEmpty(devReg)){
			return returnLogic.resultErrorJsonString(206, "未获取到配置信息。");
		}
		return returnLogic.resultJsonString(200, "查询成功", devReg);
	}

	/**
	 * 获取电/网在线情况
	 * @param id 设备主键
	 * @return list 特定封装
	 */
	@GetMapping(value = "/voltnetPropor")
	public String voltnetPropor(Integer id, Integer parm_year, Integer parm_month) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
//		if (!subject.isAuthenticated()) {
//			return returnLogic.resultErrorJsonString(401, "请先登录！");
//		}

		List<voltnetPojo> voltnetList = devicesService.voltnetPropor(id, parm_year, parm_month);
		return returnLogic.resultJsonString(200, "查询成功", voltnetList);
	}

	/**
	 * 获取电/网在线情况 区域、月汇总
	 * @param
	 * @return list 特定封装
	 */
	@GetMapping(value = "/countVoltnetPropor")
	public String countVoltnetPropor() {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
//		if (!subject.isAuthenticated()) {
//			return returnLogic.resultErrorJsonString(401, "请先登录！");
//		}
		List<ContVoltnetPojo> voltnetList = devicesService.countVoltnetPropor();
		return returnLogic.resultJsonString(200, "查询成功", voltnetList);
	}

	/**
	 * 获取事件列表,按时间倒序
	 * @return List<EventVO>
	 */
	@GetMapping(value = "/getEventList")
	public String get_eventList(Integer id,Integer pageIndex,Integer pageSize) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
//		if (!subject.isAuthenticated()) {
//			return returnLogic.resultErrorJsonString(401, "请先登录！");
//		}
		PageInfo<EventVO> resList = devicesService.get_eventList(id,pageIndex,pageSize);
		if(StringUtils.isEmpty(resList)){
			return returnLogic.resultErrorJsonString(206, "该设备暂未上报事件！");
		}
		return returnLogic.resultJsonString(200, "查询成功", resList);
	}

	/**
	 * 折线图
	 * @return List<EventVO>
	 */
	@GetMapping(value = "/getCharts")
	public String getCharts(Integer id) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
//		if (!subject.isAuthenticated()) {
//			return returnLogic.resultErrorJsonString(401, "请先登录！");
//		}
		MQTTService.GetCharts(id);
		return returnLogic.resultJson(200, "查询成功"	);
	}



	/**
	 * 按区域及事件类别统计,获取每个分组下每种事件数量
	 * @return List<AiDiDoutVO>
	 */
	@GetMapping(value = "/getEventTypeOfgroup")
	public String get_eventTypeOfgroup() {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
//		if (!subject.isAuthenticated()) {
//			return returnLogic.resultErrorJsonString(401, "请先登录！");
//		}
		List<AiDiDoutVO> resList = devicesService.get_eventTypeOfgroup();
		return returnLogic.resultJsonString(200, "查询成功", resList);
	}

	/**
	 * 按区域统计,获取每个区域事件总数
	 * @return List<AiDiDoutVO>
	 */
	@GetMapping(value = "/getEventCountOfGroup")
	public String get_eventCountOfGroup() {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		List<AiDiDoutVO> resList = devicesService.get_eventCountOfGroup();
		return returnLogic.resultJsonString(200, "查询成功", resList);
	}

	/**
	 * 按事件种类统计,获取每种事件数量
	 * @return List<AiDiDoutVO>
	 */
	@GetMapping(value = "/getEventCount")
	public String get_eventCount() {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
//		if (!subject.isAuthenticated()) {
//			return returnLogic.resultErrorJsonString(401, "请先登录！");
//
//		}
		List<AiDiDoutVO> resList = devicesService.get_eventCount();
		return returnLogic.resultJsonString(200, "查询成功", resList);
	}







		/**
	 * 返回所有设备
	 * @return list
	 */
	@GetMapping(value = "/allDevices")
	public String allDevices() {

		String tk = httpServletRequest.getHeader("Authorization") == null ? ""
				: httpServletRequest.getHeader("Authorization");
		// 电话号码
		String phone = JWTUtil.getUsername(tk);
		// 首先，检测用户登录状态；
//		Subject subject = SecurityUtils.getSubject();
//		if (!subject.isAuthenticated()) {
//			return returnLogic.resultErrorJsonString(401, "请先登录！");
//		}
		List<Devices> devList;
		Users searchUser = new Users();
		searchUser.setTelephone(phone);
		Users users = usersService.find(searchUser);
		String type=users.getType();
		String userPhone=null;
		if("0".equals(type)){
			//用户查询设备
			userPhone=phone;
		}
		devList = devicesService.getDevicesNoPage(userPhone);
		return returnLogic.resultJsonString(200, "查询成功。", devList);
	}
}
