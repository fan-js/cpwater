package cn.ritac.cpwater.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ritac.cpwater.mybatis.model.Users;
import cn.ritac.cpwater.web.dto.convert.ProporVO;
import tk.mybatis.mapper.common.Mapper;

public interface UsersMapper extends Mapper<Users> {

	/**
	 * 根据手机号得到用户列表
	 * 
	 * @param telephone
	 * @return
	 */
	public List<Users> getUsers(@Param("telephone") String telephone);

	public Users queryUserByNameOrPhoneOrEmail(@Param("userName")String userName,@Param("pwd") String pwd);
	
	public List<ProporVO> userProporList();


}