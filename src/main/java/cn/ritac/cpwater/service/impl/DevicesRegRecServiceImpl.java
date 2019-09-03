package cn.ritac.cpwater.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.ritac.cpwater.mybatis.mapper.DevicesRegRecMapper;
import cn.ritac.cpwater.mybatis.model.DevicesRegRec;
import cn.ritac.cpwater.service.DevicesRegRecService;

@Service
public class DevicesRegRecServiceImpl extends BaseServiceImpl<DevicesRegRec> implements DevicesRegRecService {

	@Autowired
	private DevicesRegRecMapper devicesRegRecMapper;
	@Autowired
	public void setInit() {
		super.setInit(devicesRegRecMapper);
	}
//	@Override
//	public PageInfo<VDevicesRegRecList> getDeviceRegRecord(String beginTime, String endTime, String userTel,
//			String deviceNum, int pageIndex, int pageSize) {
//		PageHelper.startPage(pageIndex, pageSize);
//		List<VDevicesRegRecList> list=devicesRegRecMapper.getDeviceRegRecord(beginTime, endTime, userTel, deviceNum);
//		
//		return new PageInfo<>(list);
//	}
}
