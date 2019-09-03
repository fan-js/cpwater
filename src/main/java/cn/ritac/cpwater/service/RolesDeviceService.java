package cn.ritac.cpwater.service;

import cn.ritac.cpwater.mybatis.model.RoleDevice;

public interface RolesDeviceService extends BaseService<RoleDevice, Integer> {
	public void deleteByObj(RoleDevice rd);
}
