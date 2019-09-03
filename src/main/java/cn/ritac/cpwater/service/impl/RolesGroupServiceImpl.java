package cn.ritac.cpwater.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ritac.cpwater.mybatis.mapper.RoleGroupMapper;
import cn.ritac.cpwater.mybatis.model.RoleGroup;
import cn.ritac.cpwater.service.RolesGroupService;

@Service
public class RolesGroupServiceImpl extends BaseServiceImpl<RoleGroup> implements RolesGroupService {

	@Autowired
	private RoleGroupMapper roleGroupMapper;

	@Autowired
	public void setInit() {
		super.setInit(roleGroupMapper);

	}

	@Override
	public void deleteRoleGroupByRoleId(int roleId) {
		roleGroupMapper.deleteRoleGroupByRoleId(roleId);
	}

	@Override
	public void delete(RoleGroup obj) {
		roleGroupMapper.delete(obj);
	}

}
