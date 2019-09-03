package cn.ritac.cpwater.mybatis.mapper;

import org.apache.ibatis.annotations.Delete;

import cn.ritac.cpwater.mybatis.model.GroupDevice;
import tk.mybatis.mapper.common.Mapper;

public interface GroupDeviceMapper extends Mapper<GroupDevice> {

	@Delete("DELETE  FROM cpwater_group_device where group_id = #{0}")
	public void deleteByCol(int groupId);

	public void deleteByObj(GroupDevice gd);
}