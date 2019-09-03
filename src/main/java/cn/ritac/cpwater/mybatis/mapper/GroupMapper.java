package cn.ritac.cpwater.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import cn.ritac.cpwater.mybatis.model.Group;
import cn.ritac.cpwater.web.dto.convert.GroupVO;
import tk.mybatis.mapper.common.Mapper;

public interface GroupMapper extends Mapper<Group> {

	public int getNodeByDepthSelf(@Param("nodeDepth") int nodeDepth, @Param("node_key") String node_key);

	@Select("SELECT  * FROM cpwater_group WHERE node_key LIKE CONCAT(#{0},'%')  ")
	@ResultMap("BaseResultMap")
	public List<Group> findChildrenByKeyLike(String key);

	public List<Group> findGroupByRoleId(int roleId);
	
	public List<GroupVO> groupProporList();

}