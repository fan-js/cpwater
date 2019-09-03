package cn.ritac.cpwater.service;

import java.util.List;

import cn.ritac.cpwater.mybatis.model.AssetsFunction;

public interface AssetsFunctionService extends BaseService<AssetsFunction, Integer> {
	public void deleteByObj(AssetsFunction af);

	public List<AssetsFunction> findAssetsFunctionByRoleId(String phone);
}
