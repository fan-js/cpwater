package cn.ritac.cpwater.service;


import com.github.pagehelper.PageInfo;

import cn.ritac.cpwater.mybatis.model.Admin;

public interface AdminService extends BaseService<Admin, Integer> {
	public PageInfo<Admin> findListByObj(int pageIndex, int pageSize, Admin an);
}
