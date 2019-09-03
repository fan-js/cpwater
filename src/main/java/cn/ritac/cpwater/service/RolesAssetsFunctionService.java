package cn.ritac.cpwater.service;

import cn.ritac.cpwater.mybatis.model.RoleAssetsFuction;

public interface RolesAssetsFunctionService extends BaseService<RoleAssetsFuction, Integer> {

	public void deleteByObj(RoleAssetsFuction raf);

}
