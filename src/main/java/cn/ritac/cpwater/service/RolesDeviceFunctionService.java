package cn.ritac.cpwater.service;

import cn.ritac.cpwater.mybatis.model.RoleDeviceFuction;

public interface RolesDeviceFunctionService extends BaseService<RoleDeviceFuction, Integer> {
	public void deleteByObj(RoleDeviceFuction rdf);
}
