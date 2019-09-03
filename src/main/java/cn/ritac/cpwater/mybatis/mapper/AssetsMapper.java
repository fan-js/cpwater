package cn.ritac.cpwater.mybatis.mapper;

import cn.ritac.cpwater.mybatis.model.Assets;
import cn.ritac.cpwater.web.dto.convert.AssetsCount;
import tk.mybatis.mapper.common.Mapper;

public interface AssetsMapper extends Mapper<Assets> {

	public AssetsCount countIfAssets();
}