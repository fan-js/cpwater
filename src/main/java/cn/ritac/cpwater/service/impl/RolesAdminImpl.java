package cn.ritac.cpwater.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.ritac.cpwater.mybatis.mapper.RoleAdminMapper;
import cn.ritac.cpwater.mybatis.model.RoleAdmin;
import cn.ritac.cpwater.service.RolesAdminService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/***
 * 
 * @author admin
 *
 */

@Service
public class RolesAdminImpl extends BaseServiceImpl<RoleAdmin> implements RolesAdminService {

	@Autowired
	private RoleAdminMapper roleAdminMapper;

	@Autowired
	public void setInit() {
		super.setInit(roleAdminMapper);
	}

	@Override
	public void deleteByObj(RoleAdmin ra) {
		Example exp = new Example(RoleAdmin.class);
		Criteria cra = exp.createCriteria();
		cra.andNotEqualTo("roleId", 1);
		cra.andEqualTo("adminId", ra.getAdminId());
		roleAdminMapper.deleteByExample(exp);
	}

}
