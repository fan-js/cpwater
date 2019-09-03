package cn.ritac.cpwater.service;

import cn.ritac.cpwater.mybatis.model.RoleGroup;

public interface RolesGroupService extends BaseService<RoleGroup, Integer> {
	public void deleteRoleGroupByRoleId(int roleId);

	public void delete(RoleGroup obj);

}
