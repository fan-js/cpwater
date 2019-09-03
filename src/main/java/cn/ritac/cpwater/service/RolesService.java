package cn.ritac.cpwater.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import cn.ritac.cpwater.mybatis.model.Roles;

/**
 * 
 * @author admin
 *
 */
public interface RolesService extends BaseService<Roles, Integer> {
	public Roles findRoleForUser(String userName, String roleName, String phone);

	public PageInfo<Roles> findRoleByObje(int pageIndex, int pageSize, Roles ros);

	public List<Roles> findRoleForAdmin(int id);

	public int getFunctionForRole(Integer rID, String upA, String upD);

}
