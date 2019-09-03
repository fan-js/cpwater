package cn.ritac.cpwater.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.ritac.cpwater.mybatis.mapper.UsersMapper;
import cn.ritac.cpwater.mybatis.model.Users;
import cn.ritac.cpwater.service.UsersService;
import cn.ritac.cpwater.web.dto.convert.ProporVO;
import cn.ritac.cpwater.web.dto.convert.RUsers;
import tk.mybatis.mapper.common.Mapper;

/**
 * 用户业务处理
 * @author admin
 *
 */
@Service
public class UsersServiceImpl extends BaseServiceImpl<Users> implements UsersService {

	@Autowired
	private UsersMapper usersMapper;

	@Autowired
	public void setInit(Mapper<Users> mapper) {
		super.setInit(mapper);
	}

	/**
	 * 得到用户
	 */
	@Override
	public PageInfo<RUsers> getUsers(String telephone, int pageSize, int pageIndex) {
		PageHelper.startPage(pageIndex, pageSize);
		List<Users> list = usersMapper.getUsers(telephone);
		List<RUsers> userList = new ArrayList<RUsers>();
		for (Users u : list) {
			RUsers ru = new RUsers();
			BeanUtils.copyProperties(u, ru);
			userList.add(ru);
		}
		return new PageInfo<>(userList);
	}

	@Override
	public Users queryUserByNameOrPhoneOrEmail(String userName, String pwd) {
		return usersMapper.queryUserByNameOrPhoneOrEmail(userName, pwd);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.ritac.cpwater.service.UsersService#userProporList()
	 */
	@Override
	public List<ProporVO> userProporList() {
		// TODO Auto-generated method stub
		return usersMapper.userProporList();
	}

}
