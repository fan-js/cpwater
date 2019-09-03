package cn.ritac.cpwater.service;

import java.util.List;

import cn.ritac.cpwater.mybatis.model.Devices;
import cn.ritac.cpwater.mybatis.model.Group;
import cn.ritac.cpwater.web.dto.convert.GroupVO;

public interface GroupService extends BaseService<Group, Integer> {
	public int getNodeByDepthSelf(int nodeDepth, String node_key);

	public List<Group> findChildrenByKeyLike(String key);

	public List<Devices> findDeviceOfGroup(int groupId);

	public List<Group> findGroupByRoleId(int roleId);

	public List<GroupVO> groupProporList();

}
