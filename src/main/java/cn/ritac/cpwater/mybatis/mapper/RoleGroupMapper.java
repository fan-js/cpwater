package cn.ritac.cpwater.mybatis.mapper;

import org.apache.ibatis.annotations.Delete;

import cn.ritac.cpwater.mybatis.model.RoleGroup;
import tk.mybatis.mapper.common.Mapper;

public interface RoleGroupMapper extends Mapper<RoleGroup> {

	@Delete("DELETE FROM  cpwater_role_group WHERE role_id = #{0}")
	public void deleteRoleGroupByRoleId(int roleId);
}