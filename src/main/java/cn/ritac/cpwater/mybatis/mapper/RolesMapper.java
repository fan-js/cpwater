package cn.ritac.cpwater.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ritac.cpwater.mybatis.model.Roles;
import tk.mybatis.mapper.common.Mapper;

public interface RolesMapper extends Mapper<Roles> {

	public Roles findRoleForUser(@Param("userName") String userName, @Param("roleName") String roleName,
			@Param("phone") String phone);

	public List<Roles> findRoleForAdmin(int id);

	public int getA_FunctionForRole(@Param("rID") Integer rID, @Param("upA") String upA);
	
	public int getD_FunctionForRole(@Param("rID") Integer rID, @Param("upD") String upD);
}