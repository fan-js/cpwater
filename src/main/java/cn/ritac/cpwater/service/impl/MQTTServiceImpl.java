package cn.ritac.cpwater.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.ritac.cpwater.comm.mqtt.message.*;
import cn.ritac.cpwater.mybatis.mapper.*;
import cn.ritac.cpwater.mybatis.model.*;
import cn.ritac.cpwater.sendMassage.SendMassage;
import cn.ritac.cpwater.service.*;
import cn.ritac.cpwater.web.dto.convert.*;
import cn.ritac.cpwater.web.dto.export.DeviceCountVo;
import com.github.pagehelper.PageInfo;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import cn.ritac.cpwater.comm.mqtt.ServerMQTT;
import cn.ritac.cpwater.comm.mqtt.newdev.MQTTAddrVal;
import cn.ritac.cpwater.comm.mqtt.newdev.MQTTDeviceStatusData;
import cn.ritac.cpwater.comm.mqtt.newdev.MsgDataDevInfo;
import cn.ritac.cpwater.comm.mqtt.newdev.MsgDataKernelInfo;
import cn.ritac.cpwater.comm.mqtt.newdev.MsgDataNewlogin;
import cn.ritac.cpwater.comm.mqtt.newdev.MsgDataNumberOfData;
import cn.ritac.cpwater.comm.mqtt.newdev.MsgDataUpOpt;
import cn.ritac.cpwater.mybatis.model.Bakserve;
import cn.ritac.cpwater.mybatis.model.Dataupopt;
import cn.ritac.cpwater.mybatis.model.Devices;
import cn.ritac.cpwater.mybatis.model.DevicesAI;
import cn.ritac.cpwater.mybatis.model.DevicesAIRec;
import cn.ritac.cpwater.mybatis.model.DevicesDI;
import cn.ritac.cpwater.mybatis.model.DevicesDIRec;
import cn.ritac.cpwater.mybatis.model.DevicesDo;
import cn.ritac.cpwater.mybatis.model.DevicesDoRec;
import cn.ritac.cpwater.mybatis.model.DevicesEventRec;
import cn.ritac.cpwater.mybatis.model.DevicesReg;
import cn.ritac.cpwater.mybatis.model.DevicesRegRec;
import cn.ritac.cpwater.mybatis.model.IOTCard;
import cn.ritac.cpwater.mybatis.model.NumberOfData;
import cn.ritac.cpwater.mybatis.model.WorkingHour;
import cn.ritac.cpwater.mybatis.model.WorkingTime;
import cn.ritac.cpwater.service.DevicesService;
import cn.ritac.cpwater.service.MQTTService;
import cn.ritac.cpwater.service.SseServices;
import cn.ritac.cpwater.util.DateTime;
import cn.ritac.cpwater.util.StringByteSize;
import cn.ritac.cpwater.web.dto.voltnetPojo;

@Service("MQTTService")
public class MQTTServiceImpl implements MQTTService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DevicesMapper devicesMapper;

	@Autowired
	private UsersService usersService;

	@Autowired
	private ServerMQTT serverMQTT;

	@Autowired
	private DevicesDIMapper devicesDIMapper;

	@Autowired
	private DevicesDIRecMapper devicesDIRecMapper;

	@Autowired
	private DevicesAIMapper devicesAIMapper;

	@Autowired
	private DevicesAIRecMapper devicesAIRecMapper;

	@Autowired
	private DevicesDoMapper devicesDoMapper;

	@Autowired
	private DevicesDoRecMapper devicesDoRecMapper;

	@Autowired
	private DevicesEventRecMapper devicesEventRecMapper;

	@Autowired
	private DevicesRegMapper devicesRegMapper;

	@Autowired
	private DevicesRegRecMapper devicesRegRecMapper;

	@Autowired
	private BakserveMapper bakServerMapper;

	@Autowired
	private DataupoptMapper dataUpOptMapper;

	@Autowired
	private NumberOfDataMapper numberOfDataMapper;

	@Autowired
	private RegCameraMapper regCameraMapper;

	@Autowired
	private ExportMapper exportMapper;

	@Autowired
	private IOTCardMapper iotCardMapper;

	@Autowired
	private DevicesService devicesService;

	@Autowired
	private DeviceAndUserService deviceAndUserService;

	@Autowired
	private WorkingHourMapper workingHourMapper;

	@Autowired
	private WorkingTimeMapper workingTimeMapper;

	@Autowired
	private SseServices sseServicesImp;

	// @Value("${mqtt.server.HOST}") // private String emqHOST; // 注入普通字符串
	// emq服务器地址

	// @Value("${api.map.baidu.ak}") // private String AK;
	// @Value("${api.map.baidu.sk}") // private String SK;

	// @Value("${mqtt.server.console.PORT}") // private String ConsolePort; //
	// 注入普通字符串 emq控制台端口号

	// @Value("${mqtt.server.QOS}")
	private int Qos = 2;

	private Object getDoBooleanValue(int key, KeyValue[] list) {
		for (KeyValue keyValue : list) {
			Object kv = keyValue.getAddr();
			int val = Integer.parseInt(kv == null ? "0" : kv.toString());
			if (val == key) {
				Object object = keyValue.getVal();
				return object;
			}
		}
		return null;
	}

	private Object getAiDiValue(int key, KeyValue[] list) {
		for (KeyValue keyValue : list) {
			Object kv = keyValue.getAddr();
			int val = Integer.parseInt(kv == null ? "0" : kv.toString());
			if (val == key) {
				Object object = keyValue.getVal();
				return object;
			}
		}
		return null;
	}

	private Object getRegValue(int key, MQTTAddrVal[] list) {
		for (MQTTAddrVal keyValue : list) {
			Object kv = keyValue.getAddr();
			int val = Integer.parseInt(kv == null ? "0" : kv.toString());
			if (val == key) {
				Object object = keyValue.getVal();
				if (object == null)
					return "";
				return object.toString();
			}
		}
		return -1;
	}

	public void countCardFlow(Object msg, String deviceNum) {
		Devices devSearch = new Devices();
		devSearch.setDeviceNum(deviceNum);
		devSearch = devicesMapper.selectOne(devSearch);
		if (!StringUtils.isEmpty(devSearch)) {
			if (!StringUtils.isEmpty(devSearch.getConnform()) && devSearch.getConnform() == 1) {
				IOTCard card = new IOTCard();
				card.setDeviceNum(deviceNum);
				card = iotCardMapper.selectOne(card);
				if (!StringUtils.isEmpty(card)) {
					Double use = StringByteSize.getSizeMB(msg);
					Double total_flow = Double.valueOf(card.getTotalFlow());
					Double used_flow = Double.valueOf(card.getUsedFlow());
					Map<String, Object> data = StringByteSize.CalculationFlow(total_flow, used_flow, use);
					card.setUsedFlow(data.get("used").toString());
					card.setResidueFlow(data.get("unused").toString());
					iotCardMapper.updateByPrimaryKey(card);
				}
			}
		}

	}

	/**
	* 配置信息上传 配置设备信息 服务端下发
	*/

	@Override
	public String setDeviceConfig(MQTTDeviceConfigSetting mqttDeviceConfigSetting) {
		String resMsg = "指令执行成功";
		MqttMessage msg = new MqttMessage();
		MqttTopic topic = serverMQTT.getClient().getTopic("RITAC/IN/CPWATER/V1/" + mqttDeviceConfigSetting.getDeviceID());
		String data = JSONObject.toJSONString(mqttDeviceConfigSetting);
		msg.setQos(Qos);
		msg.setRetained(false);
		msg.setPayload(data.getBytes());
		try {
			serverMQTT.publish(topic, msg);
			// 统计流量
			countCardFlow(msg, mqttDeviceConfigSetting.getDeviceID());
			logger.info("设置设备配置命令下发---:" + data);
		} catch (MqttPersistenceException e) {
			logger.error("配置设备配置信息出错:" + data + ";请稍后重试！" + e.getMessage());
			resMsg = "配置设备配置信息出错;请联系管理员！";
			e.printStackTrace();
		} catch (MqttException e) {
			logger.error("配置设备配置信息出错:" + data + ";请稍后重试！" + e.getMessage());
			resMsg = "配置设备配置信息出错;请联系管理员！";
			e.printStackTrace();
		}
		return resMsg;

	}

	/**
	 * 修改设备自身属性
	 */
	@Override
	public String sendDeviceSelfConfigCommand(MQTTDeviceSelfConfig mqttDeviceSelfConfig) {
		String resMsg = "指令下发成功";
		MqttMessage msg = new MqttMessage();
		MqttTopic topic = serverMQTT.getClient().getTopic("RITAC/IN/CPWATER/V1/" + mqttDeviceSelfConfig.getDeviceID());
		String objData = JSONObject.toJSONString(mqttDeviceSelfConfig, SerializerFeature.WriteDateUseDateFormat);
		msg.setQos(Qos);
		msg.setRetained(false);
		msg.setPayload(objData.getBytes());
		logger.info("更新设备参数命令下发---:" + objData);
		try {
			serverMQTT.publish(topic, msg);
			// 流量统计
			countCardFlow(msg, mqttDeviceSelfConfig.getDeviceID());
		} catch (MqttPersistenceException e) {
			logger.error("更新设备参命令下发出错:---:" + objData + ";请稍后重试！" + e.getMessage());
			e.printStackTrace();
			resMsg = "更新设备参命令下发出错,请联系管理员";
		} catch (MqttException e) {
			logger.error("更新设备参命令下发出错:---:" + objData + ";请稍后重试！" + e.getMessage());
			e.printStackTrace();
			resMsg = "更新设备参命令下发出错,请联系管理员";
		}
		return resMsg;
	}



	/**
	 * 更新包下载后根据返回状态进行更新操作
	 * */
	@Override
	public void updateResult(MQTTControlCommandPojo2 mqttControlCommandPojo2) {
		MQTTDeviceControlSendParamsPjo1 mqttDeviceControlSendParamsPjo1=mqttControlCommandPojo2.getMsgData();
		int state=mqttDeviceControlSendParamsPjo1.getIsDown();
		//判断是否下载成功 0成功 1失败
		switch (state){
			case 0:
					logger.info("更新包下载成功，正在升级");
				MQTTControlCommandPojo commandPojo = new MQTTControlCommandPojo();
				commandPojo.setProductID("CPWATER");
				commandPojo.setDeviceID(mqttControlCommandPojo2.getDeviceID());
				commandPojo.setDeviceKey(mqttControlCommandPojo2.getDeviceKey());
				commandPojo.setMsgType(65001);
				MQTTDeviceControlSendParamsPjo2 mqttDeviceControlSendParamsPjo2=new MQTTDeviceControlSendParamsPjo2();
				mqttDeviceControlSendParamsPjo2.setAddr(1);
				commandPojo.setMsgData(mqttDeviceControlSendParamsPjo2);
				sendDeviceControlCommand(commandPojo);
				System.out.printf(commandPojo.toString());
				break;
			case 1:
					logger.info("更新包下载失败");
				break;
			default:
				break;

		}

	}


	/**
	 * 设备注册
	 */
	@Override
	@Transactional(readOnly = false)
	public void sendLoginMsg(MQTTDeviceLogin login) {
		// 统计接受
		countCardFlow(login, login.getDeviceID());
		if (login.getProductId().equals("CPWATER") && login.getProductKey().equals("NJcasyml")) {
			Devices devicesSerach = new Devices();
			devicesSerach.setDeviceNum(login.getDeviceID());
			devicesSerach.setDeviceKey(login.getDeviceKey());
			// 根据设备编号和设备密钥查询设备：有则修改 无则插入
			devicesSerach = devicesMapper.selectOne(devicesSerach);
			Date tmpdate = new Date();
			MsgDataNewlogin msgData = null;
			KeyValue[] bakServer = null;
			MsgDataDevInfo devInfo = null;
			MsgDataKernelInfo kernelInfo = null;
			MsgDataUpOpt dataUpOpt = null;
			MsgDataNumberOfData numberOfData = null;
			Devices devicesInfo = devicesSerach;
			// 初始化主类
			if (StringUtils.isEmpty(devicesSerach)) {
				devicesInfo = new Devices();
				devicesInfo.setOnline(true);
				devicesInfo.setOnlineTime(tmpdate);
				devicesInfo.setCreateTime(tmpdate);
			}
			// 判定注册信息
			if (!StringUtils.isEmpty(login)) {
				msgData = login.getMsgData();
				devicesInfo.setDeviceNum(login.getDeviceID());
				devicesInfo.setDeviceKey(login.getDeviceKey());
				devicesInfo.setDeviceModel(login.getModel());
				if (login.getLongitude() != null)
					devicesInfo.setLongitude(login.getLongitude());
				if (login.getDimension() != null)
					devicesInfo.setDimension(login.getDimension());
			}
			// 判定初始化数据
			if (!StringUtils.isEmpty(msgData)) {
				bakServer = msgData.getBakServer();
				devInfo = msgData.getDevInfo();
				kernelInfo = msgData.getKernelInfo();
				dataUpOpt = msgData.getDataUpOpt();
				numberOfData = msgData.getNumberOfData();
			}
			// 判定内核信息
			if (!StringUtils.isEmpty(kernelInfo)) {
				devicesInfo.setCputemper(kernelInfo.getCpuTemper());
				devicesInfo.setRamproportion(kernelInfo.getRamProportion());
				devicesInfo.setRomproportion(kernelInfo.getRomProportion());
				devicesInfo.setCpuproportion(kernelInfo.getCpuProportion());
			}
			// 判定基数数据
			if (!StringUtils.isEmpty(devInfo)) {
				devicesInfo.setVoltage((float) devInfo.getVoltage());
				devicesInfo.setCurrent(devInfo.getCurrent());
				devicesInfo.setFirstlink(devInfo.getFirstLink());
				devicesInfo.setConnform(devInfo.getConnForm());
				devicesInfo.setRestartcount(devInfo.getRestartCount());
				devicesInfo.setKeepaliveinterval(devInfo.getKeepAliveInterval());
				devicesInfo.setDatauptime(devInfo.getDataUpTime());
				devicesInfo.setFirstserver(devInfo.getFirstServer());
				devicesInfo.setUpdateTime(tmpdate);
				// 升级变更属性
				devicesInfo.setSlaveentry(devInfo.getSlaveEntry());
				devicesInfo.setSignalquality(devInfo.getSignalQuality());
				devicesInfo.setSoftVersion(devInfo.getSoftVer());
				devicesInfo.setDeviceVersion(devInfo.getHardwareVer());
				devicesInfo.setWorkinghours(devInfo.getWorkingHours());
				devicesInfo.setNetworkinghours(devInfo.getNetworkinghours());
				String slaveId = "";
				int[] slaveIds = devInfo.getSlaveIDs();
				StringBuffer sb = new StringBuffer();
				if (!StringUtils.isEmpty(slaveIds) && slaveIds.length > 0) {
					for (int sl : slaveIds) {
						if (sb.length() > 0)
							sb.append(",");
						sb.append(sl);
					}
					devicesInfo.setSlaveids(sb.toString());
				} else {
					devicesInfo.setSlaveids(slaveId);
				}

			}
			// 判定注册是否首次
			int State = 1;
			if (StringUtils.isEmpty(devicesSerach)) {
				// 插入新注册数据
				devicesMapper.insert(devicesInfo);
			} else if (login.getMsgType() == 60001) {
				devicesInfo.setOnline(true);
				devicesInfo.setOnlineTime(tmpdate);
				devicesMapper.updateByPrimaryKey(devicesInfo);
				State = 2;
//				devRegAgainOrInit(devicesInfo);
			} else {
				// 清除
				if (login.getMsgType() == 60009) {
					State = 2;
					devRegAgainOrInit(devicesInfo);
				}

				int pkid = devicesInfo.getId();
				devicesMapper.updateByPrimaryKey(devicesInfo);
				if (!StringUtils.isEmpty(bakServer)) {
					Bakserve bak = new Bakserve();
					bak.setDeviceId(pkid);
					bakServerMapper.delete(bak);
				}
				if (!StringUtils.isEmpty(dataUpOpt)) {
					Dataupopt dut = new Dataupopt();
					dut.setDeviceId(pkid);
					dataUpOptMapper.delete(dut);
				}
				if (!StringUtils.isEmpty(numberOfData)) {
					NumberOfData nod = new NumberOfData();
					nod.setDeviceId(pkid);
					numberOfDataMapper.delete(nod);
				}
				// 存入
				int id = devicesInfo.getId();
				if (!StringUtils.isEmpty(bakServer)) {
					for (KeyValue kv : bakServer) {
						Bakserve bs = new Bakserve();
						bs.setDeviceId(id);
						bs.setUrl(kv.getUrl());
						bs.setPort(Integer.parseInt(kv.getVal() == null ? "0" : kv.getVal().toString()));
						bs.setUpdateTime(tmpdate);
						bakServerMapper.insert(bs);
					}
				}
				if (!StringUtils.isEmpty(dataUpOpt)) {
					Dataupopt duo = new Dataupopt();
					BeanUtils.copyProperties(dataUpOpt, duo);
					duo.setDeviceId(id);
					duo.setAin(dataUpOpt.getAin());
					duo.setUpdateTime(tmpdate);
					dataUpOptMapper.insert(duo);
				}
				if (!StringUtils.isEmpty(numberOfData)) {
					NumberOfData nod = new NumberOfData();
					BeanUtils.copyProperties(numberOfData, nod);
					nod.setDeviceId(id);
					nod.setUpdateTime(tmpdate);
					numberOfDataMapper.insert(nod);
				}

			}

			// 返回注册成功消息
			try {
				MQTTDeviceLoginResult loginResult = new MQTTDeviceLoginResult();
				loginResult.setDeviceID(login.getDeviceID());
				loginResult.setDeviceKey(login.getDeviceKey());
				loginResult.setMsgType(login.getMsgType());
				loginResult.setMsgState(State);
				loginResult.setReairectAddr("www.addr.io");
				loginResult.setReairectPort(1883);

				MqttTopic topic = serverMQTT.getClient().getTopic("RITAC/IN/CPWATER/V1/" + login.getDeviceID());
				MqttMessage msg = new MqttMessage();
				msg.setQos(Qos);
				msg.setRetained(false);
				msg.setPayload(JSONObject.toJSONString(loginResult).getBytes());
				logger.info("返回注册成功消息给设备!! Topic:" + topic + " msg:" + msg);
				serverMQTT.publish(topic, msg);
				// 流量统计
				countCardFlow(msg, login.getDeviceID());
			} catch (MqttPersistenceException e) {
				logger.error("发送注册失败:" + e.getMessage());
				e.printStackTrace();
			} catch (MqttException e) {
				logger.error("发送注册失败:" + e.getMessage());
				e.printStackTrace();
			}
		} else {
			logger.error("注册密钥错误:" + login.getProductId() + "key:" + login.getProductKey());
		}
	}

	// 处理设备数据初始、重复注册，工作表更新
	private void devRegAgainOrInit(Devices dev) {
		// 处理cpwater_workinghour表
		// 1. 先根据当前时间去取数据库数据,若有则取当前月份,再取表数据,若有修改,否则插入
		try {
			int deviceId = dev != null ? dev.getId() : 0;
			int netType = dev == null ? 2 : dev.getConnform() == null ? 2 : dev.getConnform();
			Date d = new Date();
			// 当前月份,年
			int tmp_month = DateTime.getMonth(d);
			int tmp_year = DateTime.getYear(d);
			/*
			 * 1. work 市电
			 * 2. hour ups
			 * 3. netWork 有线
			 * 4. simWork 移动
			 */
			long work = 0;
			long hour = 0;
			long netWork = 0;
			long simWork = 0;
			// 首先去记录表worktime中分别取 市电、备电；有线网、移动网数据 ；无责初始初始
			WorkingTime wh = new WorkingTime();
			wh.setDeviceId(deviceId);
			wh = workingTimeMapper.selectOne(wh);
			Date nowDate = new Date();
			if (StringUtils.isEmpty(wh)) {// 无，出入新记录
				wh = new WorkingTime();
				wh.setDeviceId(deviceId);
				wh.setCreateTime(nowDate);
				wh.setVoltageStarttime(nowDate);
				// 判断网类型 netType =1 移动，否则有线
				if (netType == 1) {
					wh.setSimStarttime(nowDate);
				} else {
					wh.setLanStarttime(nowDate);
				}
				workingTimeMapper.insert(wh);
			} /*else {// 有，更新时间，并计算时长
				Date tmp = wh.getVoltageStarttime();
				work = DateTime.DateDiffHours(nowDate, tmp);
				// 默认市电，是否ups看事件判断
				// hour =DateTime.DateDiffHours(nowDate, tmp);
				if (netType == 1) {
					tmp = wh.getSimStarttime();
					tmp = tmp == null ? nowDate : tmp;
					simWork = DateTime.DateDiffHours(nowDate, tmp);
					wh.setSimStarttime(nowDate);
				} else {
					tmp = wh.getLanStarttime();
					tmp = tmp == null ? nowDate : tmp;
					netWork = DateTime.DateDiffHours(nowDate, tmp);
					wh.setLanStarttime(nowDate);
				}
				wh.setCreateTime(nowDate);
				wh.setVoltageStarttime(nowDate);
				workingTimeMapper.updateByPrimaryKey(wh);
			}*/
			// 根据id和月份去获取时长表
			WorkingHour hr = new WorkingHour();
			hr.setYears(tmp_year);
			hr.setMonths(tmp_month);
			hr.setDeviceId(deviceId);
			WorkingHour whList = workingHourMapper.selectOne(hr);
			if (StringUtils.isEmpty(whList)) {
				// 新增
				whList = new WorkingHour();
				whList.setDeviceId(deviceId);
				whList.setWorkinghours(work);
				whList.setHours(hour);
				whList.setNetworkinghours(netWork);
				whList.setNetHours(simWork);
				whList.setYears(tmp_year);
				whList.setMonths(tmp_month);
				whList.setCreateTime(d);
				whList.setMinutes(Long.valueOf(DateTime.getDayOfDate(d) * 24));
				workingHourMapper.insert(whList);
			} /*else {
				// 修改
				long Nwork = whList.getWorkinghours() + work;
				long Nhour = whList.getHours() + hour;
				long NnetWork = whList.getNetworkinghours() + netWork;
				long NsimWork = whList.getNetHours() + simWork;
				whList.setWorkinghours(Nwork);
				whList.setHours(Nhour);
				whList.setNetworkinghours(NnetWork);
				whList.setNetHours(NsimWork);
				whList.setMinutes(Long.valueOf(DateTime.getDayOfDate(d) * 24));
				workingHourMapper.updateByPrimaryKey(whList);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新设备上线下线状态 服务端下发
	 */
	@Override
	public void getDeviceStatus(MQTTDeviceStatusData statusData) {

		String slaveId = "";
		int[] slaveIds = null;
		if (statusData != null) {
			Date nowTime = statusData.getTime() == null ? new Date() : statusData.getTime();
			if (!StringUtils.isEmpty(statusData.getMsgData())) {
				if (!StringUtils.isEmpty(statusData.getMsgData().getDevInfo())) {
					slaveIds = statusData.getMsgData().getDevInfo().getSlaveIDs();
					StringBuffer sb = new StringBuffer();
					for (int sl : slaveIds) {
						if (sb.length() > 0)
							sb.append(",");
						sb.append(sl);
					}
					slaveId = sb.toString();
				}

			}
			slaveId = slaveIds == null ? "" : slaveId;

			String deviceId = statusData.getDeviceID();
			String deviceKey = statusData.getDeviceKey();
			int msgType = statusData.getMsgType();
			// 流量统计
			countCardFlow(statusData, deviceId);
			if (msgType == 60003) {// 上线
				devicesMapper.saveStatusOnLine(deviceId, slaveId, nowTime);
				Devices dev = devicesMapper.getDevice(deviceId, deviceKey);
				if (!StringUtils.isEmpty(dev) && !StringUtils.isEmpty(dev.getConnform())) {
					int conn = dev.getConnform();
					Date nowDate = new Date();
					if (conn == 1) { // sim移动网连接
						WorkingTime wt = new WorkingTime();
						wt.setDeviceId(dev.getId());
						wt = workingTimeMapper.selectOne(wt);
						if (!StringUtils.isEmpty(wt)) {// 修改
							/*更新数据*/
							wt.setCreateTime(nowDate);
							// 电 ,结束靠事件触发
							wt.setVoltageStarttime(nowDate);
							// 移动网开始
							wt.setSimStarttime(nowDate);
							workingTimeMapper.updateByPrimaryKey(wt);
						}
					} else {// 有线网连接
						WorkingTime wt = new WorkingTime();
						wt.setDeviceId(dev.getId());
						wt = workingTimeMapper.selectOne(wt);
						if (!StringUtils.isEmpty(wt)) {// 修改
							/*更新数据*/
							wt.setCreateTime(nowDate);
							// 电 ,结束靠事件触发
							wt.setVoltageStarttime(nowDate);
							// 有线网开始
							wt.setLanStarttime(nowDate);
							workingTimeMapper.updateByPrimaryKey(wt);
						}

					}
				}

			} else if (msgType == 60004) {// 离线
				devicesMapper.saveStatusUnOnLine(deviceId, slaveId, nowTime);

				Devices dev = devicesMapper.getDevice(deviceId, deviceKey);
				if (!StringUtils.isEmpty(dev) && !StringUtils.isEmpty(dev.getConnform())) {
					// 当前月份
					Date nowDate = new Date();
					int tmp_month = DateTime.getMonth(nowDate);
					int tmp_year = DateTime.getYear(nowDate);
					int conn = dev.getConnform();
					WorkingTime wt = new WorkingTime();
					wt.setDeviceId(dev.getId());
					wt = workingTimeMapper.selectOne(wt);
					if (!StringUtils.isEmpty(wt)) {
						/*计算时长 , 与当前时间比较*/
						WorkingHour wh = new WorkingHour();
						wh.setMonths(tmp_month);
						wh.setYears(tmp_year);
						wh.setDeviceId(dev.getId());
						wh = workingHourMapper.selectOne(wh);

						Date vs_date = wt.getVoltageStarttime();
						// 市电工作时长 nowDate - vs_date
						long workHours = DateTime.DateDiffHours(nowDate, vs_date);
						workHours += wh.getWorkinghours();
						wh.setWorkinghours(workHours);

						if (conn == 1) { // sim移动网连接
							// 移动网工作时长 nowDate - se_date
							Date ss_date = wt.getSimStarttime();
							long ss_workHours = DateTime.DateDiffHours(nowDate, ss_date);
							ss_workHours += wh.getNetHours();
							wh.setNetHours(ss_workHours);
							// 移动网
							wt.setSimEndtime(nowDate);
						} else {
							// 有线网工作时长 nowDate - le_date
							Date ls_date = wt.getLanStarttime();
							long ls_workHours = DateTime.DateDiffHours(nowDate, ls_date);
							ls_workHours += wh.getNetworkinghours();
							wh.setNetworkinghours(ls_workHours);
							// 有线网开始
							wt.setLanEndtime(nowDate);
						}
						workingHourMapper.updateByPrimaryKey(wh);
						/*更新数据*/
						wt.setCreateTime(nowDate);
						// 电 ,结束靠事件触发
						wt.setVoltageEndtime(nowDate);
						workingTimeMapper.updateByPrimaryKey(wt);
					}
				}
			}

		}
	}

	/**
	 *
	 * 控制命令下发 服务器端下发
	 * 
	 ***/
	@Override
	public void sendDeviceControlCommand(MQTTControlCommandPojo commandPojo) {
		MqttMessage msg = new MqttMessage();
		try {
			MqttTopic topic = serverMQTT.getClient().getTopic("RITAC/IN/CPWATER/V1/" + commandPojo.getDeviceID());
			msg.setQos(Qos);
			msg.setRetained(false);
			msg.setPayload(JSONObject.toJSONString(commandPojo).getBytes());
			serverMQTT.publish(topic, msg);
			// 流量统计
			countCardFlow(msg, commandPojo.getDeviceID());
			logger.info("控制命令下发---:" + JSONObject.toJSONString(commandPojo));
		} catch (MqttPersistenceException e) {
			logger.error("控制命令下发出错:" + e.getMessage() + ";请稍后重试！");
			e.printStackTrace();
		} catch (MqttException e) {
			logger.error("控制命令下发出错:" + e.getMessage() + ";请稍后重试！");
			e.printStackTrace();
		}
	}

	/**
	 * < 数据上传> 得到设备端上传 设备端
	 */
	@Override
	@Transactional
	public void getBaseData(MQTTDeviceData data) {
		String device_id = data.getDeviceID();
		String device_key = data.getDeviceKey();
		Date updateTime = new Date();
		// 流量统计
		countCardFlow(data, device_id);
		Devices devicesInfo = new Devices();
		devicesInfo.setDeviceNum(device_id);
		devicesInfo.setDeviceKey(device_key);
		List<Devices> devicesList = devicesMapper.select(devicesInfo);
		if (devicesList.size() > 0) {
			devicesInfo = devicesList.get(0);
			try {
				int deviceId = devicesInfo.getId();

				/* 判断是否包含数据 */
				if (data.getMsgData() != null) {

					int slaveId = data.getMsgData().getAddr();
					slaveId = slaveId == 0 ? 1 : slaveId;

					/* 修改设备内核信息 */
					if (data.getMsgData().getKernelInfo() != null) {
						devicesInfo.setCputemper(data.getMsgData().getKernelInfo().getCpuTemper());
						devicesInfo.setRamproportion(data.getMsgData().getKernelInfo().getRamProportion());
						devicesInfo.setRomproportion(data.getMsgData().getKernelInfo().getRomProportion());
						devicesInfo.setCpuproportion(data.getMsgData().getKernelInfo().getCpuProportion());
						devicesMapper.updateByPrimaryKey(devicesInfo);
					}

					/* AI模拟量输入 */
					if (data.getMsgData().getAin() != null) {
						DevicesAI devicesAI = new DevicesAI();
						devicesAI.setDeviceId(deviceId);// 设备表主键
						DevicesAI isdevicesAI = devicesAIMapper.selectOne(devicesAI);
						if (!StringUtils.isEmpty(isdevicesAI)) {
							devicesAI = isdevicesAI;
						}

						if (!StringUtils.isEmpty(getAiDiValue(1, data.getMsgData().getAin())))
							devicesAI.setVoltage(
									Float.parseFloat(getAiDiValue(1, data.getMsgData().getAin()).toString()));// 电压
						if (!StringUtils.isEmpty(getAiDiValue(2, data.getMsgData().getAin())))
							devicesAI.setCurrent(
									Float.parseFloat(getAiDiValue(2, data.getMsgData().getAin()).toString()));// 电流
						if (!StringUtils.isEmpty(getAiDiValue(3, data.getMsgData().getAin())))
							devicesAI.setElectricEnergy(
									Float.parseFloat(getAiDiValue(3, data.getMsgData().getAin()).toString()));// 电能

						if (!StringUtils.isEmpty(getAiDiValue(4, data.getMsgData().getAin())))
							devicesAI.setWindDirection(
									Float.parseFloat(getAiDiValue(4, data.getMsgData().getAin()).toString()));// 风向

						if (!StringUtils.isEmpty(getAiDiValue(5, data.getMsgData().getAin())))
							devicesAI.setPress(
									Float.parseFloat(getAiDiValue(5, data.getMsgData().getAin()).toString()));// 压力
						if (!StringUtils.isEmpty(getAiDiValue(6, data.getMsgData().getAin())))
							devicesAI.setWater(
									getAiDiValue(6, data.getMsgData().getAin()).toString());// 水位

						devicesAI.setUpdateTime(updateTime);
						/* 插入设备最新ai数据 */
						if (!StringUtils.isEmpty(isdevicesAI)) {
							devicesAIMapper.updateByPrimaryKey(devicesAI);
						} else {
							devicesAIMapper.insert(devicesAI);
						}

						// 记录表
						DevicesAIRec dar = new DevicesAIRec();
						dar.setDeviceId(deviceId);
						if (!StringUtils.isEmpty(getAiDiValue(1, data.getMsgData().getAin())))
							dar.setVoltage(
									Float.parseFloat(getAiDiValue(1, data.getMsgData().getAin()).toString()));// 电压
						if (!StringUtils.isEmpty(getAiDiValue(2, data.getMsgData().getAin())))
							dar.setCurrent(
									Float.parseFloat(getAiDiValue(2, data.getMsgData().getAin()).toString()));// 电流
						if (!StringUtils.isEmpty(getAiDiValue(3, data.getMsgData().getAin())))
							dar.setElectricEnergy(
									Float.parseFloat(getAiDiValue(3, data.getMsgData().getAin()).toString()));// 电能

						if (!StringUtils.isEmpty(getAiDiValue(4, data.getMsgData().getAin())))
							dar.setWindDirection(
									Float.parseFloat(getAiDiValue(4, data.getMsgData().getAin()).toString()));// 风向

						if (!StringUtils.isEmpty(getAiDiValue(5, data.getMsgData().getAin())))
							dar.setPress(
									Float.parseFloat(getAiDiValue(5, data.getMsgData().getAin()).toString()));// 压力
						if (!StringUtils.isEmpty(getAiDiValue(6, data.getMsgData().getAin())))
							dar.setWater(
									getAiDiValue(6, data.getMsgData().getAin()).toString());// 水位
						dar.setUpdateTime(updateTime);
						/* 插入设备ai数据记录表 */
						devicesAIRecMapper.insert(dar);
					}

					/* DI开关量输入 */
					if (data.getMsgData().getDin() != null) {
						DevicesDI devicesDI = new DevicesDI();
						devicesDI.setDeviceId(deviceId);
						DevicesDI isdevicesDI = devicesDIMapper.selectOne(devicesDI);
						if (!StringUtils.isEmpty(isdevicesDI)) {
							devicesDI = isdevicesDI;
						}

						// 删除设备原有di记录
						// devicesDIMapper.delete(devicesDI);
						if (!StringUtils.isEmpty(getDoBooleanValue(1, data.getMsgData().getDin())))
							devicesDI.setWaterPump((boolean) (getDoBooleanValue(1, data.getMsgData().getDin())));// 水泵
						if (!StringUtils.isEmpty(getDoBooleanValue(2, data.getMsgData().getDin())))
							devicesDI.setWastegate((boolean) (getDoBooleanValue(2, data.getMsgData().getDin())));// 泄压阀

						if (!StringUtils.isEmpty(getDoBooleanValue(3,data.getMsgData().getDin())))
							devicesDI.setWaterlevela((boolean) (getDoBooleanValue(3,data.getMsgData().getDin())));// 水位A/使能/未使能
						if (!StringUtils.isEmpty(getDoBooleanValue(4,data.getMsgData().getDin())))
							devicesDI.setWaterLevelb((boolean)(getDoBooleanValue(4,data.getMsgData().getDin())));// 水位B/使能/未使能

						if (!StringUtils.isEmpty(getDoBooleanValue(5,data.getMsgData().getDin())))
							devicesDI.setWaterLevelc((boolean)(getDoBooleanValue(5,data.getMsgData().getDin())));// 水位C/使能/未使能
						if (!StringUtils.isEmpty(getDoBooleanValue(6,data.getMsgData().getDin())))
							devicesDI.setWaterLeveld((boolean)(getDoBooleanValue(6,data.getMsgData().getDin())));// 水位D/使能/未使能
						devicesDI.setUpdateTime(updateTime);
						/* 插入最新di状态 */
						if (!StringUtils.isEmpty(isdevicesDI)) {
							devicesDIMapper.updateByPrimaryKey(devicesDI);
						} else {
							devicesDIMapper.insert(devicesDI);
						}

						// 记录表
						DevicesDIRec ddr = new DevicesDIRec();
						ddr.setDeviceId(deviceId);

						if (!StringUtils.isEmpty(getDoBooleanValue(1, data.getMsgData().getDin())))
							ddr.setWaterPump((boolean) (getDoBooleanValue(1, data.getMsgData().getDin())));// 水泵
						if (!StringUtils.isEmpty(getDoBooleanValue(2, data.getMsgData().getDin())))
							ddr.setWastegate((boolean) (getDoBooleanValue(2, data.getMsgData().getDin())));// 泄压阀

						if (!StringUtils.isEmpty(getDoBooleanValue(3,data.getMsgData().getDin())))
							ddr.setWaterlevela((boolean) (getDoBooleanValue(3,data.getMsgData().getDin())));// 水位A/使能/未使能
						if (!StringUtils.isEmpty(getDoBooleanValue(4,data.getMsgData().getDin())))
							ddr.setWaterLevelb((boolean)(getDoBooleanValue(4,data.getMsgData().getDin())));// 水位B/使能/未使能

						if (!StringUtils.isEmpty(getDoBooleanValue(5,data.getMsgData().getDin())))
							ddr.setWaterLevelc((boolean)(getDoBooleanValue(5,data.getMsgData().getDin())));// 水位C/使能/未使能
						if (!StringUtils.isEmpty(getDoBooleanValue(6,data.getMsgData().getDin())))
							ddr.setWaterLeveld((boolean)(getDoBooleanValue(6,data.getMsgData().getDin())));// 水位D/使能/未使能

						ddr.setUpdateTime(updateTime);
						/* 插入设备di状态记录表 */
						devicesDIRecMapper.insert(ddr);
					}

					/* 开关量输出 */
					if (data.getMsgData().getDout() != null) {
						DevicesDo devicesDo = new DevicesDo();
						devicesDo.setDeviceId(deviceId);
						DevicesDo isdevicesDo = devicesDoMapper.selectOne(devicesDo);
						if (!StringUtils.isEmpty(isdevicesDo)) {
							devicesDo = isdevicesDo;
						}
						// 删除原有设备do信息
						// devicesDoMapper.delete(devicesDo);
						if (!StringUtils.isEmpty(getDoBooleanValue(1,data.getMsgData().getDout())))
							devicesDo.setPump((boolean)(getDoBooleanValue(1,data.getMsgData().getDout())));//水泵
						if (!StringUtils.isEmpty(getDoBooleanValue(2,data.getMsgData().getDout())))
							devicesDo.setWastegate((boolean)(getDoBooleanValue(2,data.getMsgData().getDout())));//泄压阀
						if (!StringUtils.isEmpty(getDoBooleanValue(3,data.getMsgData().getDout())))
							devicesDo.setReset((boolean)(getDoBooleanValue(3,data.getMsgData().getDout())));//电能表复位

						devicesDo.setUpdateTime(updateTime);
						/* 插入最新do状态 */
						if (!StringUtils.isEmpty(isdevicesDo)) {
							devicesDoMapper.updateByPrimaryKey(devicesDo);
						} else {
							devicesDoMapper.insert(devicesDo);
						}
						// 记录表
						DevicesDoRec dor = new DevicesDoRec();
						dor.setDeviceId(deviceId);
						if (!StringUtils.isEmpty(getDoBooleanValue(1,data.getMsgData().getDout())))
							dor.setPump((boolean)(getDoBooleanValue(1,data.getMsgData().getDout())));//水泵
						if (!StringUtils.isEmpty(getDoBooleanValue(2,data.getMsgData().getDout())))
							dor.setWastegate((boolean)(getDoBooleanValue(2,data.getMsgData().getDout())));//泄压阀
						if (!StringUtils.isEmpty(getDoBooleanValue(3,data.getMsgData().getDout())))
							dor.setReset((boolean)(getDoBooleanValue(3,data.getMsgData().getDout())));//电能表复位

						dor.setUpdateTime(updateTime);
						/* 插入do状态记录表 */
						devicesDoRecMapper.insert(dor);
					}

				}

			} catch (Exception ex) {
			logger.error("基本数据上传修改出错:" + ex.getMessage());
		}
		} else {
			logger.error("得到设备端上传密钥错误或者该设备还没注册:" + data.getDeviceID() + "key:" + data.getDeviceKey());
		}

	}

	/**
	 * 分析事件类型，对应更新设备及工作记录
	 */
	public void analyseEvent(Devices devicesInfo, MsgDataEventInfo eventItem) {
		// 根据事件内容,更新设备表 电源状态/连接状态 sn--1; Content{mainPower-主电源;UPS-变为UPS
		// ;mobile-移动网络;LAN-LAN口通信 }
		int deviceId = devicesInfo.getId();
		int sn = eventItem.getEventSn();
		String time = eventItem.getEventTime();
		Date now = new Date();
		Date t_time = StringUtils.isEmpty(time) ? now : DateTime.format(time, "yyyy-MM-dd HH:mm:ss");
		int isBig = DateTime.dateCompare(t_time, now);
		Date eve_time = isBig == 1 ? now : t_time;
		// 当前月份
		int tmp_month = DateTime.getMonth(eve_time);
		int tmp_year = DateTime.getYear(eve_time);
		if (!StringUtils.isEmpty(sn) && sn == 1) {
			// 记录表
			WorkingTime wt = new WorkingTime();
			wt.setDeviceId(deviceId);
			wt = workingTimeMapper.selectOne(wt);
			wt.setCreateTime(now);
			// 时长表
			WorkingHour wh = new WorkingHour();
			wh.setDeviceId(deviceId);
			wh.setMonths(tmp_month);
			wh.setYears(tmp_year);
			wh = workingHourMapper.selectOne(wh);
			wh.setCreateTime(now);
			// 更新网
			String content = eventItem.getEventContent();
			if (!StringUtils.isEmpty(content) && content.equals("mobile")) {
				devicesInfo.setConnform(1);
				// 开始无线，计算有线时长
				Date ls_date = wt.getLanStarttime();
				ls_date = ls_date == null ? now : ls_date;
				long ls_netWork = DateTime.DateDiffHours(now, ls_date);
				ls_netWork += wh.getNetworkinghours();
				wh.setNetworkinghours(ls_netWork);
				// 设置记录
				wt.setLanEndtime(eve_time);
				wt.setSimStarttime(eve_time);
			}
			if (!StringUtils.isEmpty(content) && content.equals("LAN")) {
				devicesInfo.setConnform(2);
				// 开始有线，计算无线时长
				Date ss_date = wt.getSimStarttime();
				ss_date = ss_date == null ? now : ss_date;
				long ss_netHour = DateTime.DateDiffHours(now, ss_date);
				ss_netHour += wh.getNetHours();
				wh.setNetHours(ss_netHour);
				// 设置记录
				wt.setLanStarttime(eve_time);
				wt.setSimEndtime(eve_time);
			}
			// 更新电
			if (!StringUtils.isEmpty(content) && content.equals("mainPower")) {
				// 开始市电，计算ups时长
				Date us_date = wt.getUpsStarttime();
				us_date = us_date == null ? now : us_date;
				long us_hours = DateTime.DateDiffHours(now, us_date);
				us_hours += wh.getHours();
				wh.setHours(us_hours);
				// 设置记录
				wt.setVoltageStarttime(eve_time);
				wt.setUpsEndtime(eve_time);
			}
			if (!StringUtils.isEmpty(content) && content.equals("UPS")) {
				// 开始ups，计算市电时长
				Date vs_date = wt.getVoltageStarttime();
				vs_date = vs_date == null ? now : vs_date;
				long vs_workHour = DateTime.DateDiffHours(now, vs_date);
				vs_workHour += wh.getWorkinghours();
				wh.setWorkinghours(vs_workHour);
				// 设置记录
				wt.setUpsStarttime(eve_time);
				wt.setVoltageEndtime(eve_time);
			
			}
			devicesMapper.updateByPrimaryKey(devicesInfo);
			workingTimeMapper.updateByPrimaryKey(wt);
			workingHourMapper.updateByPrimaryKey(wh);
		}
	}

	/**
	 * 事件上传
	 */

	@Override
	public void getEvent(MQTTDeviceEventData eventData) {
		String deviceId = eventData.getDeviceID();
		String deviceKey = eventData.getDeviceKey();
		// 流量统计
		countCardFlow(eventData, deviceId);
		Devices devSearch = new Devices();
		devSearch.setDeviceNum(deviceId);
		devSearch.setDeviceKey(deviceKey);
		Devices devices = devicesMapper.selectOne(devSearch);
		if (!StringUtils.isEmpty(devices)) {
			Devices devicesInfo = devices;
			SendMassage sm=new SendMassage();
			DeviceAndUser dau=new DeviceAndUser();
			Users u=new Users();
			for (MsgDataEventInfo eventItem : eventData.getMsgData().getEventInfo()) {
				DevicesEventRec devicesEventRec = new DevicesEventRec();
				devicesEventRec.setDeviceId(devicesInfo.getId());
				devicesEventRec.setEventInfo(String.valueOf(eventItem.getEventSn()));
				devicesEventRec.setEventContent(eventItem.getEventContent());
				devicesEventRec.setCreateTime(new Date());
				devicesEventRec.setDeviceNum(devices.getDeviceNum());
				devicesEventRecMapper.insert(devicesEventRec);
				// 根据事件内容,更新设备表 电源状态/连接状态 sn--1; Content{mainPower-主电源;UPS-变为UPS
				// ;mobile-移动网络;LAN-LAN口通信 }
				//this.analyseEvent(devicesInfo, eventItem);
				dau.setDeviceNum(Integer.parseInt(devicesInfo.getDeviceNum()));
				DeviceAndUser deviceAndUser=deviceAndUserService.find(dau);
				String phone=deviceAndUser.getUserPhone();
				//通知用户
				sm.sendNoticeSMS(phone,eventData.getDeviceID()+"号设备，在"+eventItem.getEventTime()+"出现"+eventItem.getEventContent());
				//通知厂商
				Users users=new Users();
				users.setType("1");
				List<Users> listPhone=usersService.findList(users);
				List list=new ArrayList();
				for(Users users1 : listPhone){
						list.add(users1.getTelephone());
				}
				sm.sendBatchTemplateSMS(list,eventData.getDeviceID()+"号设备，在"+eventItem.getEventTime()+"出现"+eventItem.getEventContent());
			}
			// 事件获取成功，回传本次事件编号给设备，避免设备频繁发送重复事件
			MQTTDeviceEventResult eventResult = new MQTTDeviceEventResult();
			eventResult.setDeviceID(deviceId);
			eventResult.setDeviceKey(deviceKey);
			eventResult.setMsgState(1);
			eventResult.setMsgType(eventData.getMsgType());
			List<KeyValue> list = new ArrayList<KeyValue>();
			for (MsgDataEventInfo eventItem : eventData.getMsgData().getEventInfo()) {
				int sn = eventItem.getEventSn();
				KeyValue itm = new KeyValue();
				itm.setEventSn(sn);
				list.add(itm);
			}
			KeyValue[] kv = new KeyValue[] {};
			eventResult.setEventInfo(list.toArray(kv));
			MqttTopic topic = serverMQTT.getClient().getTopic("RITAC/IN/CPWATER/V1/" + deviceId);
			MqttMessage msg = new MqttMessage();
			msg.setQos(Qos);
			msg.setRetained(false);
			msg.setPayload(JSONObject.toJSONString(eventResult).getBytes());
			System.out.println("返回注册成功消息给设备!! Topic:" + topic + " msg:" + msg);
			try {
				serverMQTT.publish(topic, msg);
				// 统计发出
				countCardFlow(msg, deviceId);
			} catch (MqttPersistenceException e) {
				e.printStackTrace();
				logger.error("事件回馈错误:" + e.getMessage());
			} catch (MqttException e) {
				e.printStackTrace();
				logger.error("事件回馈错误:" + e.getMessage());
			}

		} else {
			logger.error("得到事件端密钥错误:" + eventData.getDeviceID() + "key:" + eventData.getDeviceKey());
		}
	}

	/**
	 * 
	 * <配置上传> 配置信息上传流程 设备端上传
	 */

	@Override
	@Transactional(readOnly = false)
	public void getDeviceConfig(MQTTDeviceConfigUp mqttDeviceConfigUp) {
		String deviceID = mqttDeviceConfigUp.getDeviceID();
		String DeviceKey = mqttDeviceConfigUp.getDeviceKey();
		// 流量统计
		countCardFlow(mqttDeviceConfigUp, deviceID);
		Devices dev = new Devices();
		dev.setDeviceNum(deviceID);
		dev.setDeviceKey(DeviceKey);
		List<Devices> devices = devicesMapper.select(dev);
		if (!devices.isEmpty()) {
			for (Devices devicesInfo : devices) {
				try {
					// 设备主键id
					int deviceId = devicesInfo.getId();
					DevicesReg reg = new DevicesReg();

					reg.setDeviceId(deviceId);
					DevicesReg isReg = devicesRegMapper.selectOne(reg);
					if (!StringUtils.isEmpty(isReg)) {
						reg = isReg;
					}

					MQTTAddrVal[] keyValues = new MQTTAddrVal[] {};
					List<MQTTAddrVal> list = mqttDeviceConfigUp.getMsgData().getReg();
					MQTTAddrVal[] data = list.toArray(keyValues);
					Date time = mqttDeviceConfigUp.getTime() == null ? new Date()
							: new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(mqttDeviceConfigUp.getTime());
					reg.setUpdateTime(time);
					reg.setWorkMode(Integer.parseInt(data[0].getVal().toString()));
					reg.setStartTimea(String.valueOf(data[1].getVal().toString()));
					reg.setEndTimea(String.valueOf(data[2].getVal().toString()));
					reg.setStatea(String.valueOf(data[3].getVal().toString()));

					reg.setStartTimeb(String.valueOf(data[4].getVal().toString()));
					reg.setEndTimeb(String.valueOf(data[5].getVal().toString()));
					reg.setStateb(String.valueOf(data[6].getVal().toString()));

					reg.setStartTimec(String.valueOf(data[7].getVal().toString()));
					reg.setEndTimec(String.valueOf(data[8].getVal().toString()));
					reg.setStatec(String.valueOf(data[9].getVal().toString()));

					reg.setStartTimed(String.valueOf(data[10].getVal().toString()));
					reg.setEndTimed(String.valueOf(data[11].getVal().toString()));
					reg.setStated(String.valueOf(data[12].getVal().toString()));

					reg.setWinda(String.valueOf(data[13].getVal().toString()));
					reg.setWindb(String.valueOf(data[14].getVal().toString()));
					reg.setPress(Float.parseFloat(data[15].getVal().toString()));
					reg.setWinda(String.valueOf(data[16].getVal().toString()));
					reg.setWater(String.valueOf(data[17].getVal().toString()));
					if (!StringUtils.isEmpty(isReg)) {
						devicesRegMapper.updateByPrimaryKey(reg);
					} else {
						devicesRegMapper.insert(reg);
					}
					// 插入设备设备配置信息记录表
					DevicesRegRec drr = new DevicesRegRec();
					drr.setUpdateTime(time);
					drr.setWorkMode(Integer.parseInt(data[0].getVal().toString()));
					drr.setStartTimea(String.valueOf(data[1].getVal().toString()));
					drr.setEndTimea(String.valueOf(data[2].getVal().toString()));
					drr.setStatea(String.valueOf(data[3].getVal().toString()));

					drr.setStartTimeb(String.valueOf(data[4].getVal().toString()));
					drr.setEndTimeb(String.valueOf(data[5].getVal().toString()));
					drr.setStateb(String.valueOf(data[6].getVal().toString()));

					drr.setStartTimec(String.valueOf(data[7].getVal().toString()));
					drr.setEndTimec(String.valueOf(data[8].getVal().toString()));
					drr.setStatec(String.valueOf(data[9].getVal().toString()));

					drr.setStartTimed(String.valueOf(data[10].getVal().toString()));
					drr.setEndTimed(String.valueOf(data[11].getVal().toString()));
					drr.setStated(String.valueOf(data[12].getVal().toString()));

					drr.setWinda(String.valueOf(data[13].getVal().toString()));
					drr.setWindb(String.valueOf(data[14].getVal().toString()));
					drr.setPress(Float.parseFloat(data[15].getVal().toString()));
					drr.setWinda(String.valueOf(data[16].getVal().toString()));
					drr.setWater(String.valueOf(data[17].getVal().toString()));
					drr.setDeviceId(deviceId);
					drr.setUpdateTime(time);
					devicesRegRecMapper.insert(drr);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					logger.error("更新配置信息出错:" + e.getMessage());
				}
			}
		} else {
			logger.error("得到配置信息密钥错误或者该设备未注册:" + mqttDeviceConfigUp.getDeviceID() + "key:"
					+ mqttDeviceConfigUp.getDeviceKey());
		}
	}

	/**
	 * obj 消息类型 判断刷新ai di do 
	 * 分别调用 device 内封装的方法
	 */
	@Autowired
	ApplicationContext applicationContext;

	public void SendCallback(Object dNum) {
		String deviceNum = dNum.toString();
		Devices devSearch = new Devices();
		devSearch.setDeviceNum(deviceNum);
		devSearch = devicesMapper.selectOne(devSearch);
		if (!StringUtils.isEmpty(devSearch)) {
			int id = devSearch.getId();
			Map<String, Object> resMap = new HashMap<String, Object>();
			Map<String, Object> aiMap = devicesService.sendAi(id);
			Map<String, Object> diMap = devicesService.sendDi(id);
			Map<String, Object> doutMap = devicesService.sendDo(id);
			Map<String, Object> chartsMap = devicesService.sendCharts(id);
			resMap.put("ain", aiMap.get("ain"));
			resMap.put("din", diMap.get("din"));
			resMap.put("dout", doutMap.get("dout"));
			resMap.put("charts", chartsMap.get("charts"));
			resMap.forEach((key, value) -> {
				sseServicesImp.sendMsg(key, value);
			});
		}

	}

	public void GetCharts(Integer id) {
		String deviceNum = id.toString();
		Devices devSearch = new Devices();
		devSearch.setDeviceNum(deviceNum);
		devSearch = devicesMapper.selectOne(devSearch);
		if (!StringUtils.isEmpty(devSearch)) {
			id = devSearch.getId();
			Map<String, Object> resMap = new HashMap<String, Object>();
			Map<String, Object> chartsMap = devicesService.sendCharts(id);
			resMap.put("charts", chartsMap.get("charts"));
			resMap.forEach((key, value) -> {
				sseServicesImp.sendMsg(key, value);
			});
		}

	}

	public void sendEvent(int length) {


			Map<String, Object> resMap = new HashMap<String, Object>();
			//有事件上报时返回最新事件
			List<EventVO> eventList = devicesService.findNewEventList(length);
			resMap.put("eventList", eventList);
			resMap.forEach((key, value) -> {
				sseServicesImp.sendMsg(key, value);
			});
	}

	/**
	 * 事件更新推送，封装json推送
	 */
	public void SendLogin(String phone) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		//用户类型统计
		List<ProporVO> proporCountList=usersService.userProporList();
		//设备总在线离线故障数量
		List<DeviceCountVo> deviceCountList = exportMapper.export_deviceCount(phone);
		//首页展示用户下所有事件前20条
		List<EventVO> eventList = devicesService.findEventListCut(phone);
		//事件类型统计
		List<ProporVO> eventProporList = devicesService.eventProporList(phone);
		resMap.put("proporCountList", proporCountList);
		resMap.put("deviceCountList", deviceCountList);
		resMap.put("eventList", eventList);
		resMap.put("eventProporList", eventProporList);
		resMap.forEach((key, value) -> {
			sseServicesImp.sendMsg(key, value);
		});
	}







	/**
	 * 电网数据推送，封装json
	 */
	public void SendVoltnetPropor(String devNum, String devKey) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Devices dev = new Devices();
		dev.setDeviceKey(devKey);
		dev.setDeviceNum(devNum);
		dev = devicesService.find(dev);
		if (!StringUtils.isEmpty(dev)) {
			int deviceId = dev.getId();
			List<voltnetPojo> resList = devicesService.voltnetPropor(deviceId, null, null);
			resMap.put("voltage_netList", resList);
			sseServicesImp.sendMsg("voltage_netList", resList);
		}
	}

	/**
	 * reg上传推送
	 */
	public void SendRegEvent() {
		sseServicesImp.sendMsg("regEvent", new Date());
	}

	/**
	 * 离线通知
	 * 
	 */
	public void OffLine(String deviceNum) {
		sseServicesImp.sendMsg("offLine", deviceNum);
	}

}
