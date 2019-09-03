package cn.ritac.cpwater.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ritac.cpwater.mybatis.mapper.GroupDeviceMapper;
import cn.ritac.cpwater.mybatis.model.GroupDevice;
import cn.ritac.cpwater.service.GroupDeviceService;

@Service
public class GroupDeviceServiceImpl extends BaseServiceImpl<GroupDevice> implements GroupDeviceService {

	@Autowired
	private GroupDeviceMapper groupDeviceMapper;

	@Autowired
	public void setInit() {
		super.setInit(groupDeviceMapper);
	}

	@Override
	public void deleteByCol(int groupId) {
		groupDeviceMapper.deleteByCol(groupId);
	}
	
	@Override
	public void deleteByObj(GroupDevice gd) {
		groupDeviceMapper.delete(gd);
	}

}
