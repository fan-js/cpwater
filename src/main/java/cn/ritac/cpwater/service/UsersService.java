package cn.ritac.cpwater.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.ritac.cpwater.mybatis.model.Users;
import cn.ritac.cpwater.web.dto.convert.ProporVO;
import cn.ritac.cpwater.web.dto.convert.RUsers;

public interface UsersService extends BaseService<Users, Integer> {

	PageInfo<RUsers> getUsers(String telephone, int pageSize, int pageIndex);

	public Users queryUserByNameOrPhoneOrEmail(String userName, String pwd);

	public List<ProporVO> userProporList();

}
