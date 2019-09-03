package cn.ritac.cpwater.service;

import cn.ritac.cpwater.mybatis.model.GroupDevice;

public interface GroupDeviceService extends BaseService<GroupDevice, Integer> {

	public void deleteByCol(int groupId);

	public void deleteByObj(GroupDevice gd);

}
