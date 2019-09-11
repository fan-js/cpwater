package cn.ritac.cpwater.web.controller.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.ritac.cpwater.comm.mqtt.message.*;
import cn.ritac.cpwater.comm.mqtt.newdev.OTAUpdatePack;
import cn.ritac.cpwater.mybatis.mapper.DevicesDoMapper;
import cn.ritac.cpwater.mybatis.model.*;
import cn.ritac.cpwater.service.DevicesRegRecService;
import cn.ritac.cpwater.service.DevicesRegService;
import cn.ritac.cpwater.web.dto.convert.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import cn.ritac.cpwater.comm.mqtt.newdev.MQTTAddrVal;
import cn.ritac.cpwater.comm.mqtt.newdev.MsgDataUpOpt;
import cn.ritac.cpwater.service.DevicesService;
import cn.ritac.cpwater.service.MQTTService;
import cn.ritac.cpwater.web.dto.CameraDTO;
import cn.ritac.cpwater.web.dto.CameraPojo;
import cn.ritac.cpwater.web.dto.MQTTCommandDTO;
import cn.ritac.cpwater.web.dto.SysDto;

@Controller
@RequestMapping(value = "/mqtt")
@ResponseBody
public class MQTTController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MQTTService mqttService;
	@Autowired
	private DevicesService devicesService;
	@Autowired
	private DevicesRegService devicesRegService;
	@Autowired
	private DevicesRegRecService devicesRegRecService;

	@Autowired
	private DevicesDoMapper devicesDoMapper;


	/**
	 * 设置设备基础信息,下发指令给设备 device
	 * @param
	 * @return msg
	 */
	@PutMapping("/setSysConfig")
	public String setSysConfig(@RequestBody SysDto sysDto) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		if (StringUtils.isEmpty(sysDto)) {
			return returnLogic.resultErrorJsonString(206, "输入参数有误。");
		}
		// 1. 解析参数,插入数据库 ; 2. 调用命令下发指令给设备
		// 涉及到表: device , bakserver , dataupopt
		// 设备主键
		String msg = "";
		int id = sysDto.getId();
		List<SysPojo> sysPojos = sysDto.getData();
		List<SysVO> sysVOs = new ArrayList<SysVO>();
		List<MQTTAddrVal> addrValList = new ArrayList<MQTTAddrVal>();
		if (!StringUtils.isEmpty(id) && !StringUtils.isEmpty(sysPojos)) {
			Devices devices = new Devices();
			devices.setId(id);
			devices = devicesService.find(devices);
			Dataupopt dataupopt = new Dataupopt();
			for (SysPojo pojo : sysPojos) {
				String key = pojo.getKey();
				switch (key) {
				case "device":// 1. 取 device
					sysVOs = pojo.getValue();
					if (!StringUtils.isEmpty(sysVOs) && !StringUtils.isEmpty(devices)) {
						for (SysVO sysVO : sysVOs) {
							String Vkey = sysVO.getKey();
							switch (Vkey) {
							case "firstLink":
								devices.setFirstlink(Integer.parseInt(sysVO.getValue().toString()));
								break;
							case "keepAliveInterval":
								devices.setKeepaliveinterval(Integer.parseInt(sysVO.getValue().toString()));
								break;
							case "dataUpTime":
								devices.setDatauptime(Integer.parseInt(sysVO.getValue().toString()));
								break;
							case "firstServer":
								devices.setFirstserver(Integer.parseInt(sysVO.getValue().toString()));
								break;
							default:
								break;
							}
						}
					}
					break;
				case "bakserver": // 2. 取 bakserver
					sysVOs = pojo.getValue();
					if (!StringUtils.isEmpty(sysVOs)) {
						MQTTAddrVal addrVal = new MQTTAddrVal();
						for (SysVO sysVO : sysVOs) {
							String Vkey = sysVO.getKey();
							switch (Vkey) {
							case "url":
								addrVal.setAddr(sysVO.getValue().toString());
								break;
							case "port":
								addrVal.setVal(Integer.parseInt(sysVO.getValue().toString()));
								break;
							default:
								break;
							}
						}
						addrValList.add(addrVal);
					}
					break;
				case "dataupopt":// 3. 取 dataupopt
					sysVOs = pojo.getValue();
					if (!StringUtils.isEmpty(sysVOs)) {
						for (SysVO sysVO : sysVOs) {
							String Vkey = sysVO.getKey();
							switch (Vkey) {
							case "ain":
								dataupopt.setAin(Integer.parseInt(sysVO.getValue().toString()));
								break;
							case "aout":
								dataupopt.setAout(Integer.parseInt(sysVO.getValue().toString()));
								break;
							case "din":
								dataupopt.setDin(Integer.parseInt(sysVO.getValue().toString()));
								break;
							case "dout":
								dataupopt.setDout(Integer.parseInt(sysVO.getValue().toString()));
								break;
							case "reg":
								dataupopt.setReg(Integer.parseInt(sysVO.getValue().toString()));
								break;
							case "event":
								dataupopt.setEvent(Integer.parseInt(sysVO.getValue().toString()));
								break;
							default:
								break;
							}
						}
					}
					break;
				default:
					break;
				}
			}
			// 进行控制命令下发<从三个对象<devices、bakserve、dataupopt>取数,并重新封装,然后调用下发指令>
			// 主类
			MQTTDeviceSelfConfig mqttDeviceSelfConfig = new MQTTDeviceSelfConfig();
			mqttDeviceSelfConfig.setProductID("CPWATER");
			mqttDeviceSelfConfig.setProductKey("NJcasyml");
			mqttDeviceSelfConfig.setMsgType(60007);
			mqttDeviceSelfConfig.setDeviceID(devices.getDeviceNum());
			mqttDeviceSelfConfig.setDeviceKey(devices.getDeviceKey());
			// msgdata 类
			MQTTDeviceSelfConfigMsgData mqttDeviceSelfConfigMsgData = new MQTTDeviceSelfConfigMsgData();
			mqttDeviceSelfConfigMsgData.setAddr(1);
			mqttDeviceSelfConfigMsgData.setTime(new Date());
			mqttDeviceSelfConfigMsgData.setFirstLink(devices.getFirstlink());
			mqttDeviceSelfConfigMsgData.setFirstServer(devices.getFirstserver());
			mqttDeviceSelfConfigMsgData.setKeepAliveInterval(devices.getKeepaliveinterval());
			//bakserver
			mqttDeviceSelfConfigMsgData.setBakServer(addrValList);
			// DataUpOpt 类
			MsgDataUpOpt msgDataUpOpt = new MsgDataUpOpt();
			msgDataUpOpt.setAin(dataupopt.getAin());
			msgDataUpOpt.setAout(dataupopt.getAout());
			msgDataUpOpt.setDin(dataupopt.getDin());
			msgDataUpOpt.setDout(dataupopt.getDout());
			msgDataUpOpt.setReg(dataupopt.getReg());
			msgDataUpOpt.setEvent(dataupopt.getEvent());
			mqttDeviceSelfConfigMsgData.setDataUpOpt(msgDataUpOpt);
			mqttDeviceSelfConfig.setMsgData(mqttDeviceSelfConfigMsgData);

			msg = mqttService.sendDeviceSelfConfigCommand(mqttDeviceSelfConfig);
		}
		return returnLogic.resultJson(200, msg);
	}

	/**
	 * 设置设备配置参数 REG工作时间段
	 *
	 * @param
	 */
	@PutMapping("/deviceConfig")
	public String setDeviceConfig(@RequestBody MQTTCommandPojo mqttCommandPojo) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
//		if (!subject.isAuthenticated()) {
//			return returnLogic.resultErrorJsonString(401, "请先登录！");
//		}
		if (StringUtils.isEmpty(mqttCommandPojo)) {
			return returnLogic.resultErrorJsonString(206, "输入参数有误。");
		}
		Devices devSearch = new Devices();
		devSearch.setId(mqttCommandPojo.getId());
		devSearch = devicesService.find(devSearch);
		if (StringUtils.isEmpty(devSearch)) {
			return returnLogic.resultErrorJsonString(206, "未获取到设备信息。");
		}
		MQTTControlCommandPojo commandPojo = new MQTTControlCommandPojo();
		commandPojo.setProductID("CPWATER");
		commandPojo.setDeviceID(devSearch.getDeviceNum());
		commandPojo.setDeviceKey(devSearch.getDeviceKey());
		commandPojo.setMsgType(108);

		MQTTDeviceConfigClientUpMsgData msgData = new MQTTDeviceConfigClientUpMsgData();
		msgData.setAddr(1);
		List<MQTTAddrVal> reg = new ArrayList<MQTTAddrVal>();
		List<AiDiDoutVO> listData = mqttCommandPojo.getData();
		if (!StringUtils.isEmpty(listData)) {
				for(int i=0;i<listData.size();i++){
					MQTTAddrVal re = new MQTTAddrVal();
					AiDiDoutVO vo=listData.get(i);
					re.setAddr(i+1);
					re.setVal(vo.getValue());
					reg.add(re);
				}
		}
		msgData.setReg(reg);
		commandPojo.setMsgData(msgData);
		mqttService.sendDeviceControlCommand(commandPojo);

		//控制工作时间、工作压力存入数据库
		//top1查询是否存在
		DevicesReg devicesReg=new DevicesReg();
		devicesReg.setDeviceId(mqttCommandPojo.getId());
		devicesReg=devicesRegService.find(devicesReg);

		//组装对象信息
		if(StringUtils.isEmpty(devicesReg)){
			devicesReg=new DevicesReg();
			devicesReg.setUpdateTime(new Date());
			devicesReg.setDeviceId(mqttCommandPojo.getId());
			devicesReg.setWorkMode(Integer.parseInt(listData.get(0).getValue().toString()));
			devicesReg.setStartTimea(listData.get(1).getValue().toString());
			devicesReg.setEndTimea(listData.get(2).getValue().toString());
			devicesReg.setStatea(listData.get(3).getValue().toString());

			devicesReg.setStartTimeb(listData.get(4).getValue().toString());
			devicesReg.setEndTimeb(listData.get(5).getValue().toString());
			devicesReg.setStateb(listData.get(6).getValue().toString());

			devicesReg.setStartTimec(listData.get(7).getValue().toString());
			devicesReg.setEndTimec(listData.get(8).getValue().toString());
			devicesReg.setStatec(listData.get(9).getValue().toString());

			devicesReg.setStartTimed(listData.get(10).getValue().toString());
			devicesReg.setEndTimed(listData.get(11).getValue().toString());
			devicesReg.setStated(listData.get(12).getValue().toString());

			devicesReg.setWinda(listData.get(13).getValue().toString());
			devicesReg.setWindb(listData.get(14).getValue().toString());
			devicesReg.setPress(Float.parseFloat(listData.get(15).getValue().toString()));
			devicesReg.setRate(listData.get(16).getValue().toString());
				devicesRegService.save(devicesReg);
		}else{
			//非空则更新
			devicesReg.setDeviceId(mqttCommandPojo.getId());
			devicesReg.setUpdateTime(new Date());
			devicesReg.setDeviceId(mqttCommandPojo.getId());
			devicesReg.setWorkMode(Integer.parseInt(listData.get(0).getValue().toString()));
			devicesReg.setStartTimea(listData.get(1).getValue().toString());
			devicesReg.setEndTimea(listData.get(2).getValue().toString());
			devicesReg.setStatea(listData.get(3).getValue().toString());

			devicesReg.setStartTimeb(listData.get(4).getValue().toString());
			devicesReg.setEndTimeb(listData.get(5).getValue().toString());
			devicesReg.setStateb(listData.get(6).getValue().toString());

			devicesReg.setStartTimec(listData.get(7).getValue().toString());
			devicesReg.setEndTimec(listData.get(8).getValue().toString());
			devicesReg.setStatec(listData.get(9).getValue().toString());

			devicesReg.setStartTimed(listData.get(10).getValue().toString());
			devicesReg.setEndTimed(listData.get(11).getValue().toString());
			devicesReg.setStated(listData.get(12).getValue().toString());

			devicesReg.setWinda(listData.get(13).getValue().toString());
			devicesReg.setWindb(listData.get(14).getValue().toString());
			devicesReg.setPress(Float.parseFloat(listData.get(15).getValue().toString()));
			devicesReg.setRate(listData.get(16).getValue().toString());
			devicesRegService.update(devicesReg);

		}

		//保存到记录表
		DevicesRegRec devicesRegRec=new DevicesRegRec();
		devicesRegRec.setUpdateTime(new Date());
		devicesRegRec.setDeviceId(mqttCommandPojo.getId());
		devicesRegRec.setWorkMode(Integer.parseInt(listData.get(0).getValue().toString()));
		devicesRegRec.setStartTimea(listData.get(1).getValue().toString());
		devicesRegRec.setEndTimea(listData.get(2).getValue().toString());
		devicesRegRec.setStatea(listData.get(3).getValue().toString());

        devicesRegRec.setStartTimeb(listData.get(4).getValue().toString());
        devicesRegRec.setEndTimeb(listData.get(5).getValue().toString());
        devicesRegRec.setStateb(listData.get(6).getValue().toString());

        devicesRegRec.setStartTimec(listData.get(7).getValue().toString());
        devicesRegRec.setEndTimec(listData.get(8).getValue().toString());
        devicesRegRec.setStatec(listData.get(9).getValue().toString());

        devicesRegRec.setStartTimed(listData.get(10).getValue().toString());
        devicesRegRec.setEndTimed(listData.get(11).getValue().toString());
        devicesRegRec.setStated(listData.get(12).getValue().toString());

		devicesRegRec.setWinda(listData.get(13).getValue().toString());
		devicesRegRec.setWindb(listData.get(14).getValue().toString());
        devicesRegRec.setPress(Float.parseFloat(listData.get(15).getValue().toString()));
		devicesRegRec.setRete(listData.get(16).getValue().toString());
		devicesRegRecService.save(devicesRegRec);
		return returnLogic.resultJson(200, "指令已下发！");

	}

	/**
	 * 下发命令 让设备上报参数信息
	 *
	 * @param
	 */
	@PutMapping("/getDi")
	public String getDi(Integer id) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
//		if (!subject.isAuthenticated()) {
//			return returnLogic.resultErrorJsonString(401, "请先登录！");
//		}
		if (StringUtils.isEmpty(id)) {
			return returnLogic.resultErrorJsonString(206, "输入参数有误。");
		}
		Devices devSearch = new Devices();
		devSearch.setId(id);
		devSearch = devicesService.find(devSearch);
		if (StringUtils.isEmpty(devSearch)) {
			return returnLogic.resultErrorJsonString(206, "未获取到设备信息。");
		}
		MQTTControlCommandPojo commandPojo = new MQTTControlCommandPojo();
		commandPojo.setProductID("CPWATER");
		commandPojo.setDeviceID(devSearch.getDeviceNum());
		commandPojo.setDeviceKey(devSearch.getDeviceKey());
		commandPojo.setMsgType(102);

		MQTTDeviceConfigClientUpMsgData msgData = new MQTTDeviceConfigClientUpMsgData();
		msgData.setAddr(1);
		commandPojo.setMsgData(msgData);
		mqttService.sendDeviceControlCommand(commandPojo);
		return returnLogic.resultJson(200, "指令已下发！");

	}

	/**
	 * 下发命令 让设备上报参数信息
	 *
	 * @param
	 */
	@PutMapping("/getDo")
	public String getDo(Integer id) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
//		if (!subject.isAuthenticated()) {
//			return returnLogic.resultErrorJsonString(401, "请先登录！");
//		}
		if (StringUtils.isEmpty(id)) {
			return returnLogic.resultErrorJsonString(206, "输入参数有误。");
		}
		Devices devSearch = new Devices();
		devSearch.setId(id);
		devSearch = devicesService.find(devSearch);
		if (StringUtils.isEmpty(devSearch)) {
			return returnLogic.resultErrorJsonString(206, "未获取到设备信息。");
		}
		MQTTControlCommandPojo commandPojo = new MQTTControlCommandPojo();
		commandPojo.setProductID("CPWATER");
		commandPojo.setDeviceID(devSearch.getDeviceNum());
		commandPojo.setDeviceKey(devSearch.getDeviceKey());
		commandPojo.setMsgType(105);

		MQTTDeviceConfigClientUpMsgData msgData = new MQTTDeviceConfigClientUpMsgData();
		msgData.setAddr(1);
		commandPojo.setMsgData(msgData);
		mqttService.sendDeviceControlCommand(commandPojo);
		return returnLogic.resultJson(200, "指令已下发！");

	}

	/**
	 * 下发命令 让设备上报参数信息
	 *
	 * @param
	 */
	@PutMapping("/getReg")
	public String getReg(Integer id) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
//		if (!subject.isAuthenticated()) {
//			return returnLogic.resultErrorJsonString(401, "请先登录！");
//		}
		if (StringUtils.isEmpty(id)) {
			return returnLogic.resultErrorJsonString(206, "输入参数有误。");
		}
		Devices devSearch = new Devices();
		devSearch.setId(id);
		devSearch = devicesService.find(devSearch);
		if (StringUtils.isEmpty(devSearch)) {
			return returnLogic.resultErrorJsonString(206, "未获取到设备信息。");
		}
		MQTTControlCommandPojo commandPojo = new MQTTControlCommandPojo();
		commandPojo.setProductID("CPWATER");
		commandPojo.setDeviceID(devSearch.getDeviceNum());
		commandPojo.setDeviceKey(devSearch.getDeviceKey());
		commandPojo.setMsgType(107);

		MQTTDeviceConfigClientUpMsgData msgData = new MQTTDeviceConfigClientUpMsgData();
		msgData.setAddr(1);
		commandPojo.setMsgData(msgData);
		mqttService.sendDeviceControlCommand(commandPojo);
		return returnLogic.resultJson(200, "指令已下发！");

	}

	/**
	 * 设置设备配置参数 REG之camera
	 * 
	 * @param setting
	 */
	@PutMapping("/cameraConfig")
	public String cameraConfig(@RequestBody CameraDTO setting) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		if (StringUtils.isEmpty(setting)) {
			return returnLogic.resultErrorJsonString(206, "输入参数有误。");
		}
		Devices deviesInfo = new Devices();
		deviesInfo.setId(setting.getId());
		deviesInfo = devicesService.find(deviesInfo);
		// 检测当前指令下发对象合法 性
		if (!StringUtils.isEmpty(deviesInfo)) {
			MQTTDeviceConfigSetting mqttDeviceConfigSetting = new MQTTDeviceConfigSetting();
			mqttDeviceConfigSetting.setProductID("CPWATER");
			mqttDeviceConfigSetting.setDeviceID(deviesInfo.getDeviceNum());
			mqttDeviceConfigSetting.setDeviceKey(deviesInfo.getDeviceKey());
			mqttDeviceConfigSetting.setMsgType(108);
			MQTTDeviceConfigClientUpMsgData msgData = new MQTTDeviceConfigClientUpMsgData();
			msgData.setAddr(1);
			List<CameraPojo> dataList = setting.getData();
			if (!StringUtils.isEmpty(dataList)) {
				List<MQTTAddrVal> regList = new ArrayList<MQTTAddrVal>();
				MQTTAddrVal addrVal = new MQTTAddrVal();
				// camera1
				CameraPojo pojo = dataList.size() >= 1 ? dataList.get(0) : null;
				if (pojo != null) {
					addrVal = new MQTTAddrVal();
					addrVal.setAddr(33);// sn
					addrVal.setVal(pojo.getSn());
					regList.add(addrVal);
					addrVal = new MQTTAddrVal();
					addrVal.setAddr(43);// account
					addrVal.setVal(pojo.getAccount());
					regList.add(addrVal);
					addrVal = new MQTTAddrVal();
					addrVal.setAddr(44);// password
					addrVal.setVal(pojo.getPassword());
					regList.add(addrVal);
				}
				// camera2
				pojo = dataList.size() >= 2 ? dataList.get(1) : null;
				if (pojo != null) {
					addrVal = new MQTTAddrVal();
					addrVal.setAddr(38);// sn
					addrVal.setVal(pojo.getSn());
					regList.add(addrVal);
					addrVal = new MQTTAddrVal();
					addrVal.setAddr(45);// account
					addrVal.setVal(pojo.getAccount());
					regList.add(addrVal);
					addrVal = new MQTTAddrVal();
					addrVal.setAddr(46);// password
					addrVal.setVal(pojo.getPassword());
					regList.add(addrVal);
				}
				msgData.setReg(regList);
				mqttDeviceConfigSetting.setMsgData(msgData);
			}
			String resMsg = mqttService.setDeviceConfig(mqttDeviceConfigSetting);
			return returnLogic.resultJson(200, resMsg);
		}
		return returnLogic.resultErrorJsonString(206, "执行失败，设备不存在。");

	}

	/**
	 * 控制命令下发<读取数据>
	 * 
	 * @param controlCommand
	 */
	@PutMapping("/deviceControlCommand")
	public String sendDeviceControlCommand(@RequestBody MQTTCommandDTO controlCommand) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		if (StringUtils.isEmpty(controlCommand)) {
			return returnLogic.resultErrorJsonString(206, "输入参数有误。");
		}
		Devices devSearch = new Devices();
		devSearch.setId(controlCommand.getId());
		devSearch = devicesService.find(devSearch);
		if (StringUtils.isEmpty(devSearch)) {
			return returnLogic.resultErrorJsonString(206, "未获取到设备信息。");
		}
		MQTTControlCommandPojo commandPojo = new MQTTControlCommandPojo();
		commandPojo.setProductID("CPWATER");
		commandPojo.setDeviceID(devSearch.getDeviceNum());
		commandPojo.setDeviceKey(devSearch.getDeviceKey());
		int msgType = controlCommand.getMsgType();
		commandPojo.setMsgType(msgType);
		if (msgType == 60006) { // 读设备信息,指定板块读取
			MQTTControlCommandMsgData2 msgData = new MQTTControlCommandMsgData2();
			msgData.setAddr(1);
			msgData.setType(controlCommand.getType());
			commandPojo.setMsgData(msgData);
		} else {// 读ai di do 等
			MQTTControlCommandMsgData msgData = new MQTTControlCommandMsgData();
			msgData.setAddr(1);
			commandPojo.setMsgData(msgData);
		}
		mqttService.sendDeviceControlCommand(commandPojo);
		return returnLogic.resultJson(200, "指令已下发！");

	}

	/**
	 * 控制水泵命令下发<写 do>
	 * 
	 * @param controlCommand
	 */
	@PutMapping("/changeDO")
	public void changeDO(@RequestBody MQTTCommandPojo controlCommand) {
		run(1,controlCommand);
	}

	/**
	 * 控制泄压阀命令下发<写 do>
	 *
	 * @param controlCommand
	 */
	@PutMapping("/changeDOs")
	public void changeDOs(@RequestBody MQTTCommandPojo controlCommand) {
		run(2,controlCommand);
	}

	/**
	 * 控制泄压阀命令下发<写 do>
	 *
	 * @param controlCommand
	 */
	@PutMapping("/changeDOReset")
	public void changeDOReset(@RequestBody MQTTCommandPojo controlCommand) {
		run(3,controlCommand);
	}

	public String run(int num,MQTTCommandPojo controlCommand){
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
	//		if (!subject.isAuthenticated()) {
	//			return returnLogic.resultErrorJsonString(401, "请先登录！");
	//		}
		if (StringUtils.isEmpty(controlCommand)) {
			return returnLogic.resultErrorJsonString(206, "输入参数有误。");
		}
		try {
		Devices devSearch = new Devices();
		devSearch.setId(controlCommand.getId());
		devSearch = devicesService.find(devSearch);
		if (StringUtils.isEmpty(devSearch)) {
			return returnLogic.resultErrorJsonString(206, "未获取到设备信息。");
		}
		MQTTControlCommandPojo commandPojo = new MQTTControlCommandPojo();
		commandPojo.setProductID("CPWATER");
		commandPojo.setDeviceID(devSearch.getDeviceNum());
		commandPojo.setDeviceKey(devSearch.getDeviceKey());
		commandPojo.setMsgType(106);

		MQTTControlCommandMsgData3 msgData = new MQTTControlCommandMsgData3();
		msgData.setAddr(1);
		List<MQTTAddrVal> dout = new ArrayList<MQTTAddrVal>();
		List<AiDiDoutVO> listData = controlCommand.getData();
		if (!StringUtils.isEmpty(listData)) {
			for (AiDiDoutVO vo : listData) {
				MQTTAddrVal dot = new MQTTAddrVal();
				dot.setAddr(num);
				dot.setVal(vo.getValue());
				dout.add(dot);
			}
		}
		msgData.setDout(dout);
		commandPojo.setMsgData(msgData);
		mqttService.sendDeviceControlCommand(commandPojo);
		//存库
		DevicesDo devicesDo=new DevicesDo();
		switch (num){
			case 1:
				devicesDo.setPump((boolean)(controlCommand.getData().get(0).getValue()));
				break;
			case 2:
				devicesDo.setWastegate((boolean)(controlCommand.getData().get(0).getValue()));
				break;
			case 3:
				devicesDo.setReset((boolean)(controlCommand.getData().get(0).getValue()));
				break;
		}
		devicesDoMapper.updateByPrimaryKey(devicesDo);
		} catch (Exception ex) {
			logger.error("数据配置出错:" + ex.getMessage());
		}
		return returnLogic.resultJson(200, "指令已下发！");
	}




	/**
	 * 远程升级设备端的应用软件
	 *
	 * */
	@PutMapping("/update")
	public String update(@RequestBody OTAUpdatePack otaUpdatePack ){
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		if (StringUtils.isEmpty(otaUpdatePack)) {
			return returnLogic.resultErrorJsonString(206, "输入参数有误。");
		}
		Devices devSearch = new Devices();
		devSearch.setId(Integer.valueOf(80));
		devSearch = devicesService.find(devSearch);
		if (StringUtils.isEmpty(devSearch)) {
			return returnLogic.resultErrorJsonString(206, "未获取到设备信息。");
		}
		MQTTControlCommandPojo commandPojo = new MQTTControlCommandPojo();
		commandPojo.setProductID("CPWATER");
		commandPojo.setDeviceID(devSearch.getDeviceNum());
		commandPojo.setDeviceKey(devSearch.getDeviceKey());
		commandPojo.setMsgType(65000);

		MQTTDeviceControlSendParamsPjo mcspp=new MQTTDeviceControlSendParamsPjo();
		mcspp.setAddr(1);
		OTAUpdatePack updatePack = new OTAUpdatePack();
		updatePack.setUrl(otaUpdatePack.getUrl());
		updatePack.setVersion(otaUpdatePack.getVersion());
		updatePack.setPackName(otaUpdatePack.getPackName());
		updatePack.setCheckCode(otaUpdatePack.getCheckCode());
		updatePack.setPackSize(otaUpdatePack.getPackSize());

		mcspp.setUpdatePack(updatePack);
		commandPojo.setMsgData(mcspp);
		mqttService.sendDeviceControlCommand(commandPojo);
		return returnLogic.resultJson(200, "指令已下发！");
	}

}
