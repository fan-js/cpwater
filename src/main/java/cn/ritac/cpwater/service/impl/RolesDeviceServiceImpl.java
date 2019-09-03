package cn.ritac.cpwater.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ritac.cpwater.mybatis.mapper.RoleDeviceMapper;
import cn.ritac.cpwater.mybatis.model.RoleDevice;
import cn.ritac.cpwater.service.RolesDeviceService;

@Service
public class RolesDeviceServiceImpl extends BaseServiceImpl<RoleDevice> implements RolesDeviceService {

	@Autowired
	private RoleDeviceMapper roleDeviceMapper;

	@Autowired
	public void setInit() {
		super.setInit(roleDeviceMapper);
	}

	@Override
	public void deleteByObj(RoleDevice rd) {
		roleDeviceMapper.delete(rd);
	}
}
