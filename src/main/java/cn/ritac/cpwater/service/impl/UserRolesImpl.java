package cn.ritac.cpwater.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ritac.cpwater.mybatis.mapper.UserRoleMapper;
import cn.ritac.cpwater.mybatis.model.UserRole;
import cn.ritac.cpwater.service.UserRolesService;

/***
 * 
 * @author admin
 *
 */

@Service
public class UserRolesImpl extends BaseServiceImpl<UserRole> implements UserRolesService {

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Autowired
	public void setInit() {
		super.setInit(userRoleMapper);
	}

	@Override
	public void deleteByObj(UserRole ur) {
		userRoleMapper.delete(ur);
	}

}
