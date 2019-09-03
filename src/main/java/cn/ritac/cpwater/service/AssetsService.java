package cn.ritac.cpwater.service;


import com.github.pagehelper.PageInfo;

import cn.ritac.cpwater.mybatis.model.Assets;
import cn.ritac.cpwater.web.dto.convert.AssetsCount;

public interface AssetsService extends BaseService<Assets, Integer> {
	public PageInfo<Assets> findListByObj(int pageIndex, int pageSize, Assets assets);

	public AssetsCount countIfAssets();
}
