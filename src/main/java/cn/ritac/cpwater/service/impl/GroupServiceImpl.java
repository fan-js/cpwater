package cn.ritac.cpwater.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ritac.cpwater.mybatis.mapper.DevicesMapper;
import cn.ritac.cpwater.mybatis.mapper.GroupMapper;
import cn.ritac.cpwater.mybatis.model.Devices;
import cn.ritac.cpwater.mybatis.model.Group;
import cn.ritac.cpwater.service.GroupService;
import cn.ritac.cpwater.web.dto.convert.GroupVO;

@Service
public class GroupServiceImpl extends BaseServiceImpl<Group> implements GroupService {

	@Autowired
	private GroupMapper groupMapper;
	@Autowired
	private DevicesMapper devicesMapper;

	@Autowired
	public void setInit() {
		super.setInit(groupMapper);
	}

	@Override
	public int getNodeByDepthSelf(int nodeDepth, String node_key) {
		return groupMapper.getNodeByDepthSelf(nodeDepth, node_key);
	}

	@Override
	public List<Group> findChildrenByKeyLike(String key) {
		return groupMapper.findChildrenByKeyLike(key);
	}

	@Override
	public List<Devices> findDeviceOfGroup(int groupId) {
		return devicesMapper.findDeviceOfGroup(groupId);
	}

	@Override
	public List<Group> findGroupByRoleId(int roleId) {
		return groupMapper.findGroupByRoleId(roleId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.ritac.cpwater.service.GroupService#groupProporList()
	 */
	@Override
	public List<GroupVO> groupProporList() {
		// TODO Auto-generated method stub
		return groupMapper.groupProporList();
	}

}
