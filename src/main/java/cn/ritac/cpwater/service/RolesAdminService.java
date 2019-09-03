package cn.ritac.cpwater.service;

import cn.ritac.cpwater.mybatis.model.RoleAdmin;

public interface RolesAdminService extends BaseService<RoleAdmin, Integer> {
	public void deleteByObj(RoleAdmin ra);
}
