package cn.ritac.cpwater.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ritac.cpwater.mybatis.mapper.RoleDeviceFuctionMapper;
import cn.ritac.cpwater.mybatis.model.RoleDeviceFuction;
import cn.ritac.cpwater.service.RolesDeviceFunctionService;

@Service
public class RolesDeviceFunctionServiceImpl extends BaseServiceImpl<RoleDeviceFuction>
		implements RolesDeviceFunctionService {

	@Autowired
	private RoleDeviceFuctionMapper roleDeviceFuctionMapper;

	@Autowired
	public void setInit() {
		super.setInit(roleDeviceFuctionMapper);
	}

	public void deleteByObj(RoleDeviceFuction rdf) {
		roleDeviceFuctionMapper.delete(rdf);
	}

}
