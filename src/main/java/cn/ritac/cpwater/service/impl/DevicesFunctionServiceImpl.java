package cn.ritac.cpwater.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ritac.cpwater.mybatis.mapper.DeviceFuctionMapper;
import cn.ritac.cpwater.mybatis.model.DeviceFuction;
import cn.ritac.cpwater.service.DevicesFunctionService;

@Service
public class DevicesFunctionServiceImpl extends BaseServiceImpl<DeviceFuction> implements DevicesFunctionService {

	@Autowired
	private DeviceFuctionMapper deviceFuctionMapper;

	@Autowired
	public void setInit() {
		super.setInit(deviceFuctionMapper);
	}

	@Override
	public List<DeviceFuction> findDeviceFunctionByRoleId(String phone) {
		return deviceFuctionMapper.findDeviceFunctionByRoleId(phone);
	}

}
