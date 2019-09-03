package cn.ritac.cpwater.service;

import cn.ritac.cpwater.mybatis.model.UserRole;

public interface UserRolesService extends BaseService<UserRole, Integer> {
	public void deleteByObj(UserRole ur);
}
