package cn.ritac.cpwater.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.ritac.cpwater.mybatis.mapper.RoleAssetsFuctionMapper;
import cn.ritac.cpwater.mybatis.model.RoleAssetsFuction;
import cn.ritac.cpwater.service.RolesAssetsFunctionService;

@Service
public class RolesAssetsFunctionServiceImpl extends BaseServiceImpl<RoleAssetsFuction>
		implements RolesAssetsFunctionService {

	@Autowired
	private RoleAssetsFuctionMapper roleAssetsFuctionMapper;

	@Autowired
	public void setInit() {
		super.setInit(roleAssetsFuctionMapper);
	}

	@Override
	public void deleteByObj(RoleAssetsFuction raf) {
		roleAssetsFuctionMapper.delete(raf);
	}

}
