package cn.ritac.cpwater.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.ritac.cpwater.mybatis.mapper.AdminMapper;
import cn.ritac.cpwater.mybatis.model.Admin;
import cn.ritac.cpwater.service.AdminService;

@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {

	@Autowired
	private AdminMapper adminMapper;

	@Autowired
	public void setInit() {
		super.setInit(adminMapper);
	}

	@Override
	public PageInfo<Admin> findListByObj(int pageIndex, int pageSize, Admin an) {
		PageHelper.startPage(pageIndex, pageSize);
		List<Admin> list = adminMapper.findListByObj(an.getUserPhone(), an.getAdminName(), an.getAdminDescript());
		return new PageInfo<>(list);
	}

}
