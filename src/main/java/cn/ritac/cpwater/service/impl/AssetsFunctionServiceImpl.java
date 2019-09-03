package cn.ritac.cpwater.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.ritac.cpwater.mybatis.mapper.AssetsFunctionMapper;
import cn.ritac.cpwater.mybatis.model.AssetsFunction;
import cn.ritac.cpwater.service.AssetsFunctionService;

@Service
public class AssetsFunctionServiceImpl extends BaseServiceImpl<AssetsFunction> implements AssetsFunctionService {

	@Autowired
	private AssetsFunctionMapper assetsFunctionMapper;

	@Autowired
	public void setInit() {
		super.setInit(assetsFunctionMapper);
	}

	public void deleteByObj(AssetsFunction af) {
		assetsFunctionMapper.delete(af);
	}

	@Override
	public List<AssetsFunction> findAssetsFunctionByRoleId(String phone) {
		return assetsFunctionMapper.findAssetsFunctionByRoleId(phone);
	}

}
