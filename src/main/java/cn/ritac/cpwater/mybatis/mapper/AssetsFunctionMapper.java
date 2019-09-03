package cn.ritac.cpwater.mybatis.mapper;

import java.util.List;

import cn.ritac.cpwater.mybatis.model.AssetsFunction;
import tk.mybatis.mapper.common.Mapper;

public interface AssetsFunctionMapper extends Mapper<AssetsFunction> {

	public List<AssetsFunction> findAssetsFunctionByRoleId(String phone);
}