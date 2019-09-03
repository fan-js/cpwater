package cn.ritac.cpwater.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.ritac.cpwater.mybatis.mapper.RolesMapper;
import cn.ritac.cpwater.mybatis.model.Roles;
import cn.ritac.cpwater.service.RolesService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/***
 * 
 * @author admin
 *
 */

@Service
public class RolesServiceImpl extends BaseServiceImpl<Roles> implements RolesService {

	@Autowired
	private RolesMapper rolesMapper;

	@Autowired
	public void setInit() {
		super.setInit(rolesMapper);
	}

	@Override
	public Roles findRoleForUser(String userName, String roleName, String phone) {
		return rolesMapper.findRoleForUser(userName, roleName, phone);
	}

	@Override
	public PageInfo<Roles> findRoleByObje(int pageIndex, int pageSize, Roles ros) {
		PageHelper.startPage(pageIndex, pageSize);
		Example exp = new Example(Roles.class);
		Criteria cra = exp.createCriteria();
		// cra.andBetween(null, exp, cra);
		// cra.andIsNotNull("roleDescript");//过滤null列
		// cra.andIsNotNull("roleName");
		// cra.andIsNotNull("roleState");
		// 自动非空拼接
		cra.andLike("roleDescript", ros == null ? null : ros.getRoleDescript());
		cra.andLike("roleName", ros == null ? null : ros.getRoleName());
		cra.andEqualTo("roleState", ros == null ? null : ros.getRoleState());
		exp.orderBy("id").asc();
		List<Roles> list = rolesMapper.selectByExample(exp);
		return new PageInfo<>(list);
	}

	@Override
	public List<Roles> findRoleForAdmin(int id) {
		return rolesMapper.findRoleForAdmin(id);
	}

	@Override
	public int getFunctionForRole(Integer rID, String upA, String upD) {
		if (!StringUtils.isEmpty(upA))
			return rolesMapper.getA_FunctionForRole(rID, upA);
		if (!StringUtils.isEmpty(upD))
			return rolesMapper.getD_FunctionForRole(rID, upD);
		return 0;
	}
}
