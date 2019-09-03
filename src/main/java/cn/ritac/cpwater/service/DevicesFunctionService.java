package cn.ritac.cpwater.service;

import java.util.List;

import cn.ritac.cpwater.mybatis.model.DeviceFuction;

public interface DevicesFunctionService extends BaseService<DeviceFuction, Integer> {
	public List<DeviceFuction> findDeviceFunctionByRoleId(String phone);
}
