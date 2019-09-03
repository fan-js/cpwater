package cn.ritac.cpwater.web.controller.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.ritac.cpwater.mybatis.model.Devices;
import cn.ritac.cpwater.mybatis.model.Group;
import cn.ritac.cpwater.mybatis.model.GroupDevice;
import cn.ritac.cpwater.service.DevicesService;
import cn.ritac.cpwater.service.GroupDeviceService;
import cn.ritac.cpwater.service.GroupService;
import cn.ritac.cpwater.web.dto.GroupDto;
import cn.ritac.cpwater.web.dto.convert.CDevices;
import cn.ritac.cpwater.web.dto.convert.GroupVO;
import cn.ritac.cpwater.web.dto.convert.TreeData;
import cn.ritac.cpwater.web.dto.convert.TreeDataChilrden;
import cn.ritac.cpwater.web.dto.convert.TreeDataGrandSon;

/**
 * 分组模块
 * 
 * @author admin
 */
@RestController
@RequestMapping(value = "/group")
public class GroupController extends BaseController {

	@Autowired
	private GroupService groupService;

	@Autowired
	private GroupDeviceService groupDeviceService;

	@Autowired
	private DevicesService devicesService;

	/**
	 * 新建分组
	 * 
	 * @param gp
	 * @return msg
	 */
	@PostMapping("/addGroup")
	@Transactional
	public String addGroup(@RequestBody GroupDto gp) {

		/*-------------登录验证----<Begin>-------------*/
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		/*-------------登录验证----<End>-------------*/

		// 获取是否在选中节点下新增
		String key = gp.getKey();
		String groupName = gp.getGroupName();
		String childrenName = gp.getChildrenName();
		if (!StringUtils.isEmpty(groupName)) {
			// 判断节点重复性
			Group searchFgp = new Group();
			searchFgp.setGroupName(groupName);
			searchFgp.setDepth(1);
			Group fgpIsExits = groupService.find(searchFgp);
			if (!StringUtils.isEmpty(fgpIsExits)) {
				return returnLogic.resultErrorJsonString(206, "新建分组已经存在。");
			}

		}
		if (!StringUtils.isEmpty(childrenName)) {
			// 判断节点重复性
			Group searchCgp = new Group();
			searchCgp.setGroupName(childrenName);
			searchCgp.setDepth(2);
			Group cgpIsExits = groupService.find(searchCgp);
			if (!StringUtils.isEmpty(cgpIsExits)) {
				return returnLogic.resultErrorJsonString(206, "新建子分组已经存在。");
			}
		}
		// 无上级新增,默认两级
		if (StringUtils.isEmpty(key)) {
			Group grp = new Group();
			// 当前节点名称
			String pixStr = "0-";
			int depth = 1;
			StringBuffer sbKey = new StringBuffer();
			sbKey.append(pixStr);
			if (StringUtils.isEmpty(groupName)) {
				return returnLogic.resultErrorJsonString(206, "新建分组名称不能空");
			}
			// 取出当前深度最大节点
			int resMax = groupService.getNodeByDepthSelf(depth, null);
			int maxNodeDepth = resMax >= 0 ? ++resMax : 0;
			// 存储当前节点 "0-0"
			String groupKey = sbKey.append(maxNodeDepth).toString();
			grp.setUpdateTime(new Date());
			grp.setDepth(1);
			grp.setGroupName(groupName);
			grp.setKey(groupKey);
			grp.setSelf(maxNodeDepth);
			groupService.save(grp);
			// 子节点非必须
			if (!StringUtils.isEmpty(childrenName)) {
				// 子节点名称
				Group cgrp = new Group();
				int childrenDepth = 2;
				// 取出当前深度最大节点
				int resMaxc = groupService.getNodeByDepthSelf(childrenDepth, groupKey);
				int maxChildrenNodeDepth = resMaxc >= 0 ? ++resMaxc : 0;
				// 取出当前深度最大节点 "0-0-0"
				StringBuffer csbKey = new StringBuffer();
				csbKey.append(groupKey);
				String childrenKey = csbKey.append("-").append(maxChildrenNodeDepth).toString();
				cgrp.setDepth(2);
				cgrp.setUpdateTime(new Date());
				cgrp.setGroupName(childrenName);
				cgrp.setKey(childrenKey);
				cgrp.setSelf(maxChildrenNodeDepth);
				groupService.save(cgrp);
			}

		} else {
			// 有上级新增,可在此处实现
		}
		return returnLogic.resultJson(200, "新建分组成功。");
	}

	/**
	 * 添加子节点
	 * @param key 父节点key
	 * @param childrenName 子节点名称
	 * @return  String msg 提示信息
	 */
	@PostMapping("/addChildrenNode")
	public String addChildrenNode(@RequestBody GroupDto gp) {
		String key = gp.getKey();
		String childrenName = gp.getChildrenName();
		if (StringUtils.isEmpty(key)) {
			return returnLogic.resultErrorJsonString(206, "参数分组主键获取失败。");
		}
		if (StringUtils.isEmpty(childrenName)) {
			return returnLogic.resultErrorJsonString(206, "新建分组名称不能空");
		}
		// 判断节点重复性
		Group searchCgp = new Group();
		searchCgp.setGroupName(childrenName);
		searchCgp.setDepth(2);
		Group cgpIsExits = groupService.find(searchCgp);
		if (!StringUtils.isEmpty(cgpIsExits)) {
			return returnLogic.resultErrorJsonString(206, "新建子分组已经存在。");
		}
		Group grp = new Group();
		grp.setKey(key);
		grp = groupService.find(grp);
		if (StringUtils.isEmpty(grp)) {
			return returnLogic.resultErrorJsonString(206, "新建子分组失败，选择父分组不存在。");
		}
		int childrenDepth = 2;
		// 取出当前深度最大节点
		int resMaxc = groupService.getNodeByDepthSelf(childrenDepth, key);
		int maxChildrenNodeDepth = resMaxc >= 0 ? ++resMaxc : 0;
		// 取出当前深度最大节点 "0-0-0"
		StringBuffer csbKey = new StringBuffer();
		csbKey.append(key);
		String childrenKey = csbKey.append("-").append(maxChildrenNodeDepth).toString();
		// 子节点名称
		Group cgrp = new Group();
		cgrp.setDepth(2);
		cgrp.setUpdateTime(new Date());
		cgrp.setGroupName(childrenName);
		cgrp.setKey(childrenKey);
		cgrp.setSelf(maxChildrenNodeDepth);
		groupService.save(cgrp);
		return returnLogic.resultJson(200, "新建子分组成功。");
	}

	/**
	 * 删除分组
	 * 
	 * @param id
	 * @return msg
	 */
	@DeleteMapping("/deleteGroup")
	@Transactional
	public String deleteGroup(Integer id) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		if (StringUtils.isEmpty(id)) {
			return returnLogic.resultErrorJsonString(206, "未获取到要删除分组主键。");
		}
		// 获取当前节点下子节点
		Group gp = groupService.findById(id);
		if (StringUtils.isEmpty(gp)) {
			return returnLogic.resultErrorJsonString(206, "分组不存在。");
		}
		// 取到当前节点key
		String key = gp.getKey();
		// 通过key获取当前节点下子节点
		List<Group> childrenGroup = groupService.findChildrenByKeyLike(key);
		if (!StringUtils.isEmpty(childrenGroup)) {
			int i = 0;
			Integer[] ids = new Integer[childrenGroup.size()];
			for (Group cgp : childrenGroup) {
				ids[i] = cgp.getId();
				i++;
			}
			// 删除子节点
			groupService.delete(ids);
		}

		// 删除当前节点
		groupService.delete(new Integer[] { id });
		return returnLogic.resultJson(200, "删除分组成功。");

	}

	/**
	 * 修改分组名称
	 * 
	 * @param gp
	 * @return msg
	 */
	@PutMapping("/updateGroup")
	public String updateGroup(@RequestBody GroupDto gp) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}

		if (StringUtils.isEmpty(gp)) {
			return returnLogic.resultErrorJsonString(206, "未获取到要修改分组信息。");
		}
		int groupId = gp.getId();
		String groupName = gp.getGroupName();
		if (StringUtils.isEmpty(groupId) || StringUtils.isEmpty(groupName)) {
			return returnLogic.resultErrorJsonString(206, "未获取到要修改分组主键或名称。");
		}
		Group grp = groupService.findById(groupId);
		if (StringUtils.isEmpty(grp)) {
			return returnLogic.resultErrorJsonString(206, "当前分组不存在。");
		}
		grp.setGroupName(groupName);

		groupService.update(grp);
		return returnLogic.resultJson(200, "修改分组名称成功。");
	}

	/**
	 * 获取指定分组下设备
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/deviceOfGroup")
	public String findDeviceOfGroup(Integer id) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		if (StringUtils.isEmpty(id)) {
			return returnLogic.resultErrorJsonString(206, "未获取到要分组主键。");
		}
		List<Devices> devicesOfGroup = groupService.findDeviceOfGroup(id);
		List<CDevices> resList = new ArrayList<CDevices>();
		if (!StringUtils.isEmpty(devicesOfGroup)) {
			for (Devices d : devicesOfGroup) {
				CDevices cd = new CDevices();
				BeanUtils.copyProperties(d, cd);
				if (!StringUtils.isEmpty(d.getDeviceModel())) {
					cd.setDeviceNum(d.getDeviceNum() + "(" + d.getDeviceModel() + ")");
				}
				resList.add(cd);
			}
		}
		return returnLogic.resultJsonString(200, "查询成功。", resList);
	}

	/**
	 * 获取指定分组下设备
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/deviceOfGroupPage")
	public String deviceOfGroupPage(Integer id, Integer pageIndex, Integer pageSize) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		if (StringUtils.isEmpty(id)) {
			return returnLogic.resultErrorJsonString(206, "未获取到要分组主键。");
		}
		List<Devices> devicesOfGroup = groupService.findDeviceOfGroup(id);
		List<CDevices> resList = new ArrayList<CDevices>();
		if (!StringUtils.isEmpty(devicesOfGroup)) {
			for (Devices d : devicesOfGroup) {
				CDevices cd = new CDevices();
				BeanUtils.copyProperties(d, cd);
				if (!StringUtils.isEmpty(d.getDeviceModel())) {
					cd.setDeviceNum(d.getDeviceNum() + "(" + d.getDeviceModel() + ")");
				}
				resList.add(cd);
			}
		}
		PageHelper.startPage(pageIndex, pageSize);

		return returnLogic.resultJsonString(200, "查询成功。", new PageInfo<>(resList));
	}

	/**
	 * 分组树形列表
	 * 
	 * @return datalist
	 */
	@GetMapping("/groupTree")
	public String findGroupTree() {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		List<Object> treeList = new ArrayList<Object>();
		// 取出所有顶层节点
		int depth = 1;
		Group searchgp = new Group();
		searchgp.setDepth(depth);
		List<Group> list_1 = groupService.findList(searchgp);
		for (Group g : list_1) {
			// 设置父节点
			TreeData td = new TreeData();
			String key = g.getKey();
			td.setId(g.getId());
			td.setTitle(g.getGroupName());
			td.setKey(key);
			// 获取子节点
			List<Group> childrenGp = groupService.findChildrenByKeyLike(key);
			if (!StringUtils.isEmpty(childrenGp) && childrenGp.size() > 1) {
				List<Object> cList = new ArrayList<Object>();
				for (Group p : childrenGp) {
					if (p.getDepth() - g.getDepth() != 1)
						continue;
					String ckey = p.getKey();
					TreeDataChilrden tdc = new TreeDataChilrden();
					tdc.setId(p.getId());
					tdc.setTitle(p.getGroupName());
					tdc.setKey(ckey);
					List<Group> grandSonGp = groupService.findChildrenByKeyLike(ckey);
					if (!StringUtils.isEmpty(grandSonGp) && grandSonGp.size() > 1) {
						List<Object> sList = new ArrayList<Object>();
						for (Group s : grandSonGp) {
							if (s.getDepth() - p.getDepth() != 1)
								continue;
							TreeDataGrandSon tdgs = new TreeDataGrandSon();
							tdgs.setId(s.getId());
							tdgs.setTitle(s.getGroupName());
							tdgs.setKey(s.getKey());
							// 放入每个孙子
							sList.add(tdgs);
						}
						// 把孙子放入儿子里边
						tdc.setChildren(sList);
						// 放入每个儿子
						cList.add(tdc);
					} else {
						TreeDataGrandSon tdcs = new TreeDataGrandSon();
						BeanUtils.copyProperties(tdc, tdcs);
						cList.add(tdcs);
					}
				}
				// 把儿子放入老子里边
				td.setChildren(cList);
				// 放入结果集
				treeList.add(td);
			} else {
				TreeDataGrandSon tds = new TreeDataGrandSon();
				BeanUtils.copyProperties(td, tds);
				treeList.add(tds);
			}

		}

		return returnLogic.resultJsonString(200, "查询成功。", treeList);
	}

	/**
	 * 获取当前分组下设备情况
	 * 
	 * @param id
	 * @return map
	 */
	@GetMapping("/toManageGroup")
	public String tomanageGroup(Integer id) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		if (StringUtils.isEmpty(id)) {
			return returnLogic.resultErrorJsonString(206, "请输入要查询的分组主键。");
		}
		Map<String, Object> dataList = new HashMap<String, Object>();
		// 取出所有设备
		List<Devices> allList = devicesService.findAllNotInGroup();
		if (!StringUtils.isEmpty(allList)) {
			List<CDevices> allDeviceList = new ArrayList<CDevices>();
			for (Devices d : allList) {
				CDevices cd = new CDevices();
				BeanUtils.copyProperties(d, cd);
				if (!StringUtils.isEmpty(d.getDeviceModel())) {
					cd.setDeviceNum(d.getDeviceNum() + "(" + d.getDeviceModel() + ")");
				}
				allDeviceList.add(cd);
			}
			dataList.put("allDeviceList", allDeviceList);
		}

		// 取出分组内设备
		List<Devices> deviceList = groupService.findDeviceOfGroup(id);
		if (!StringUtils.isEmpty(deviceList)) {
			List<CDevices> gropeDeviceList = new ArrayList<CDevices>();
			for (Devices d : deviceList) {
				CDevices cd = new CDevices();
				BeanUtils.copyProperties(d, cd);
				if (!StringUtils.isEmpty(d.getDeviceModel())) {
					cd.setDeviceNum(d.getDeviceNum() + "(" + d.getDeviceModel() + ")");
				}
				gropeDeviceList.add(cd);
			}
			dataList.put("gropeDeviceList", gropeDeviceList);
		}

		return returnLogic.resultJsonString(200, "查询成功。", dataList);
	}

	/**
	 * 分组设备管理
	 * 
	 * @param id
	 * @param ids
	 * @return msg
	 */
	@PostMapping("/manageGroup")
	public String manageGroup(@RequestBody Map<String, Object> param) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		int id = Integer.parseInt(param.get("id").toString());
		@SuppressWarnings("unchecked")
		List<Integer> ids = (List<Integer>) param.get("ids");
		if (StringUtils.isEmpty(ids)) {
			return returnLogic.resultErrorJsonString(206, "请输入要设置的分组主键。");
		}
		// 清掉原关系
		groupDeviceService.deleteByCol(id);
		if (!StringUtils.isEmpty(ids)) {
			for (int d : ids) {
				GroupDevice gd = new GroupDevice();
				gd.setGroupId(id);
				gd.setDeviceId(d);
				// 建立新关系
				groupDeviceService.save(gd);
			}

		}
		return returnLogic.resultJson(200, "设置成功。");
	}

	/**
	 * 分组可视化
	 * @return groupProporList
	 */
	@GetMapping("/groupPropor")
	public String groupPropor() {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		List<GroupVO> groupProporList = groupService.groupProporList();
		return returnLogic.resultJsonString(200, "查询成功", groupProporList);
	}

	/**
	 * 清除指定分组下指定设备
	 * @return msg
	 */
	@DeleteMapping("/delDeviceOfGroup")
	public String delDeviceOfGroup(Integer gID, Integer dID) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		if (StringUtils.isEmpty(gID) || StringUtils.isEmpty(dID)) {
			return returnLogic.resultErrorJsonString(206, "操作失败！传入参数异常。");
		}
		GroupDevice gd = new GroupDevice();
		gd.setDeviceId(dID);
		gd.setGroupId(gID);
		groupDeviceService.deleteByObj(gd);
		return returnLogic.resultJson(200, "操作成功！");
	}

}
