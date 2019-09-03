package cn.ritac.cpwater.web.controller.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import cn.ritac.cpwater.mybatis.model.AssetsFunction;
import cn.ritac.cpwater.mybatis.model.DeviceFuction;
import cn.ritac.cpwater.mybatis.model.Devices;
import cn.ritac.cpwater.mybatis.model.Group;
import cn.ritac.cpwater.mybatis.model.RoleAdmin;
import cn.ritac.cpwater.mybatis.model.RoleAssetsFuction;
import cn.ritac.cpwater.mybatis.model.RoleDevice;
import cn.ritac.cpwater.mybatis.model.RoleDeviceFuction;
import cn.ritac.cpwater.mybatis.model.RoleGroup;
import cn.ritac.cpwater.mybatis.model.Roles;
import cn.ritac.cpwater.service.AssetsFunctionService;
import cn.ritac.cpwater.service.DevicesFunctionService;
import cn.ritac.cpwater.service.DevicesService;
import cn.ritac.cpwater.service.GroupService;
import cn.ritac.cpwater.service.RolesAdminService;
import cn.ritac.cpwater.service.RolesAssetsFunctionService;
import cn.ritac.cpwater.service.RolesDeviceFunctionService;
import cn.ritac.cpwater.service.RolesDeviceService;
import cn.ritac.cpwater.service.RolesGroupService;
import cn.ritac.cpwater.service.RolesService;
import cn.ritac.cpwater.util.JWTUtil;
import cn.ritac.cpwater.web.dto.RoleDto;
import cn.ritac.cpwater.web.dto.convert.CDevices;
import cn.ritac.cpwater.web.dto.convert.CGroup;
import cn.ritac.cpwater.web.dto.convert.FuctionChild;
import cn.ritac.cpwater.web.dto.convert.FuctionFather;
import cn.ritac.cpwater.web.dto.convert.TCGroup;

@RestController
@RequestMapping(value = "/role")
public class RoleController extends BaseController {

	@Autowired
	private HttpServletRequest httpServletRequest;

	@Autowired
	private RolesService rolesService;

	@Autowired
	private AssetsFunctionService assetsFunctionService;

	@Autowired
	private DevicesFunctionService devicesFunctionService;

	@Autowired
	private RolesDeviceFunctionService rolesDeviceFunctionService;

	@Autowired
	private RolesAssetsFunctionService rolesAssetsFunctionService;

	@Autowired
	private RolesGroupService rolesGroupService;

	@Autowired
	private RolesDeviceService rolesDeviceService;

	@Autowired
	private RolesAdminService rolesAdminService;

	@Autowired
	private GroupService groupService;

	@Autowired
	private DevicesService devicesService;

	/**
	 * 在修改角色过程调用
	 * 
	 * @param id
	 * @return 结果集
	 */
	@GetMapping("/toUpdateRole")
	public String toUpdateRole(Integer id) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		String tk = httpServletRequest.getHeader("Authorization") == null ? ""
				: httpServletRequest.getHeader("Authorization");
		// 电话号码
		String userName = JWTUtil.getUsername(tk);
		if (StringUtils.isEmpty(userName)) {
			return returnLogic.resultErrorJsonString(401, "登录已失效，请先登录！");
		}
		Roles r = rolesService.findRoleForUser("administrator", "administrator", userName);
		if (StringUtils.isEmpty(r)) {
			return returnLogic.resultErrorJsonString(403, "超管专属功能，无权操作！");
		}
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}

		if (StringUtils.isEmpty(id)) {
			return returnLogic.resultErrorJsonString(206, "调用失败，未获取到角色主键！");
		}
		// 获取1. 所有区域及对应设备 ； 2. 获取角色包含的区域及区域包含的设备 ；3. 设备、资产操作权限
		// 获取所有分组
		List<Group> allGroupList = groupService.findAll();
		// 返回数据容器
		List<TCGroup> allGroupDeviceList = new ArrayList<TCGroup>();
		// 所有数据：每个分组及其下设备
		if (!StringUtils.isEmpty(allGroupList)) {
			// 循环获取每个分组下设备
			for (Group g : allGroupList) {
				int gId = g.getId();
				TCGroup cg = new TCGroup();
				cg.setId(gId);
				cg.setGroupName(g.getGroupName());
				List<Devices> deviceList = devicesService.findDeviceOfGroup(gId);
				List<CDevices> allDeviceList = new ArrayList<CDevices>();
				if (!StringUtils.isEmpty(deviceList)) {
					for (Devices d : deviceList) {
						CDevices cd = new CDevices();
						BeanUtils.copyProperties(d, cd);
						allDeviceList.add(cd);
					}
					cg.setDeviceList(allDeviceList);
				}
				allGroupDeviceList.add(cg);
			}
		}
		// 返回数据容器
		List<TCGroup> haveGroupDeviceList = new ArrayList<TCGroup>();
		// 获取角色下分组、设备
		List<Group> groupOfRoleList = groupService.findGroupByRoleId(id);

		if (!StringUtils.isEmpty(groupOfRoleList)) {
			for (Group g : groupOfRoleList) {
				TCGroup cg = new TCGroup();
				BeanUtils.copyProperties(g, cg);
				// 角色拥有的分组下，属于角色的设备列表
				List<Devices> deviceOfRoleList = devicesService.findDeviceOfRole(g.getId(), id);
				List<CDevices> deviceRoleList = new ArrayList<CDevices>();
				if (!StringUtils.isEmpty(deviceOfRoleList)) {
					for (Devices d : deviceOfRoleList) {
						CDevices cd = new CDevices();
						BeanUtils.copyProperties(d, cd);
						deviceRoleList.add(cd);
					}
					cg.setDeviceList(deviceRoleList);
				}
				haveGroupDeviceList.add(cg);
			}
		}

		List<Object> functionList = new ArrayList<Object>();

		// 资产操作
		int upa = rolesService.getFunctionForRole(id, "af.update_assets = TRUE", null);
		List<AssetsFunction> af = assetsFunctionService.findAssetsFunctionByRoleId(userName);
		if (!StringUtils.isEmpty(af) && af.size() > 0) {
			FuctionFather assFF = new FuctionFather();
			assFF.setId("assets");
			assFF.setDescribe("资产权限");
			assFF.setChecked(upa > 0 ? true : false);
			List<FuctionChild> assFCList = new ArrayList<FuctionChild>();
			FuctionChild assFC = null;
			// 修改
			assFC = new FuctionChild();
			assFC.setAction("update");
			assFC.setDescribe("修改");
			assFCList.add(assFC);

			assFF.setActionList(assFCList);
			functionList.add(assFF);

		}
		// 设备操作
		int upd = rolesService.getFunctionForRole(id, null, "df.update_device = TRUE");
		List<DeviceFuction> df = devicesFunctionService.findDeviceFunctionByRoleId(userName);
		if (!StringUtils.isEmpty(df)) {
			FuctionFather devFF = new FuctionFather();
			devFF.setId("device");
			devFF.setDescribe("设备权限");
			devFF.setChecked(upd > 0 ? true : false);
			List<FuctionChild> devFCList = new ArrayList<FuctionChild>();
			FuctionChild devFC = null;
			// 修改
			devFC = new FuctionChild();
			devFC.setAction("update");
			devFC.setDescribe("修改");
			devFCList.add(devFC);

			devFF.setActionList(devFCList);
			functionList.add(devFF);
		}

		Map<String, Object> resList = new HashMap<String, Object>();
		// 全部分组及设备
		resList.put("allGroupDeviceList", allGroupDeviceList);
		// 包含分组及设备
		resList.put("haveGroupDeviceList", haveGroupDeviceList);
		resList.put("function", functionList);
		return returnLogic.resultJsonString(200, "查询成功。", resList);
	}

	/**
	 * 添加角色
	 * 
	 * @param roleDto
	 * @return msg
	 */
	@PostMapping("/addOrUpdateRole")
	@Transactional
	public String addRole(@RequestBody RoleDto roleDto) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		String tk = httpServletRequest.getHeader("Authorization") == null ? ""
				: httpServletRequest.getHeader("Authorization");
		// 电话号码
		String userName = JWTUtil.getUsername(tk);
		if (StringUtils.isEmpty(userName)) {
			return returnLogic.resultErrorJsonString(401, "登录已失效，请先登录！");
		}
		Roles r = rolesService.findRoleForUser("administrator", "administrator", userName);
		if (StringUtils.isEmpty(r)) {
			return returnLogic.resultErrorJsonString(403, "超管专属功能，无权操作！");
		}
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}

		/*
		 * 1. 获取、保存角色本身信息 2. 获取、保存角色区域信息 3. 获取、保存对应区域下设备 4. 获取、保存操作权限
		 */
		if (StringUtils.isEmpty(roleDto)) {
			return returnLogic.resultErrorJsonString(206, "角色信息不能为空。");
		}
		// 关键字过滤
		if (roleDto.getRoleName().equals("administrator")) {
			return returnLogic.resultErrorJsonString(206, "角色名称 <" + roleDto.getRoleName() + "> 是系统关键字，请重新输入。");
		}
		// 保存角色信息
		Roles ros = new Roles();
		BeanUtils.copyProperties(roleDto, ros);
		ros.setRoleLevel(1);
		// 如果出入对象包含主键，且主键不为0判定为修改
		String msg = "新增角色成功。";
		if (!StringUtils.isEmpty(roleDto.getId()) && roleDto.getId() > 0) {
			Roles rosSearch = new Roles();
			rosSearch.setRoleName("administrator");
			Roles isExitsRole = rolesService.find(rosSearch);
			// 防止更新掉超管
			if (!StringUtils.isEmpty(isExitsRole) && isExitsRole.getId() == roleDto.getId()) {
				return returnLogic.resultErrorJsonString(206, " <" + roleDto.getRoleName() + "> 是系统内置角色，不可操作。");
			}
			rolesService.update(ros);
			msg = "修改角色成功。";
		} else {
			rolesService.save(ros);
		}
		// 保存角色区域、设备
		List<CGroup> cList = roleDto.getRoleGroupList();
		if (!StringUtils.isEmpty(cList)) {
			// 先清掉角色原有分组信息，在插入新信息
			RoleGroup rgdel = new RoleGroup();
			rgdel.setRoleId(ros.getId());
			rolesGroupService.delete(rgdel);
			for (CGroup c : cList) {
				// 把当前角色涉及的分组全放入，如果本次没选，默认为清掉
				// 保存角色的分组
				RoleGroup rg = new RoleGroup();
				rg.setRoleId(ros.getId());
				rg.setGroupId(c.getId());
				rolesGroupService.save(rg);
				List<Integer> deviceList = c.getRoleDeviceList();
				if (!StringUtils.isEmpty(deviceList)) {
					RoleDevice rosdev = new RoleDevice();
					rosdev.setRoleId(ros.getId());
					rolesDeviceService.deleteByObj(rosdev);
					// 保存角色对应的分组及设备
					for (Integer d : deviceList) {
						// 角色、分组、设备
						RoleDevice rd = new RoleDevice();
						rd.setRoleId(ros.getId());
						rd.setGroupId(c.getId());
						rd.setDeviceId(d);
						rolesDeviceService.save(rd);
					}
				}
			}

		}

		// 保存设备操作
		DeviceFuction df = new DeviceFuction();
		boolean updateD = false;
		if (!StringUtils.isEmpty(roleDto.getDeviceFuction()))
			updateD = roleDto.getDeviceFuction().getUpdateDevice();
		df.setUpdateDevice(updateD);
		df.setUpdateTime(new Date());
		devicesFunctionService.save(df);
		// 关系表
		RoleDeviceFuction rdf = new RoleDeviceFuction();
		rdf.setRoleId(ros.getId());
		// 清
		rolesDeviceFunctionService.deleteByObj(rdf);
		// 存
		rdf.setDeviceFuctionId(df.getId());
		rolesDeviceFunctionService.save(rdf);

		// 保存资产操作
		boolean updateA = false;
		AssetsFunction af = new AssetsFunction();
		if (!StringUtils.isEmpty(roleDto.getAssetsFunction()))
			updateA = roleDto.getAssetsFunction().getUpdateAssets();
		af.setUpdateAssets(updateA);
		af.setUpdateTime(new Date());
		assetsFunctionService.save(af);
		// 关系表
		RoleAssetsFuction raf = new RoleAssetsFuction();
		raf.setRoleId(ros.getId());
		// 清
		rolesAssetsFunctionService.deleteByObj(raf);
		// 存
		raf.setAssetsFunctionId(af.getId());
		rolesAssetsFunctionService.save(raf);

		return returnLogic.resultJson(200, msg);
	}

	/**
	 * 可按条件筛选角色列表
	 * 
	 * @param roleDto
	 * @return PageInfo rolesList
	 */
	@GetMapping("/findRoles")
	public String findRoles(RoleDto roleDto) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		String tk = httpServletRequest.getHeader("Authorization") == null ? ""
				: httpServletRequest.getHeader("Authorization");
		// 电话号码
		String userName = JWTUtil.getUsername(tk);
		if (StringUtils.isEmpty(userName)) {
			return returnLogic.resultErrorJsonString(401, "登录已失效，请先登录！");
		}
		Roles r = rolesService.findRoleForUser("administrator", "administrator", userName);
		if (StringUtils.isEmpty(r)) {
			return returnLogic.resultErrorJsonString(403, "超管专属功能，无权操作！");
		}
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}

		if (StringUtils.isEmpty(roleDto)) {
			return returnLogic.resultErrorJsonString(206, "获取角色列表失败！");
		}
		int pageIndex = roleDto.getPageIndex();
		int pageSize = roleDto.getPageSize();
		Roles ros = new Roles();
		BeanUtils.copyProperties(roleDto, ros);
		ros.setRoleLevel(1);
		PageInfo<Roles> resList = rolesService.findRoleByObje(pageIndex, pageSize, ros);

		// return JSONObject.toJSON(resList);
		return returnLogic.resultJsonString(200, "查询成功。", resList);
	}

	/**
	 * 根据主键删除角色
	 * 
	 * @param id
	 * @return
	 */

	@DeleteMapping("/deleteRole")
	@Transactional
	public String deleteRole(Integer id) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		String tk = httpServletRequest.getHeader("Authorization") == null ? ""
				: httpServletRequest.getHeader("Authorization");
		// 电话号码
		String userName = JWTUtil.getUsername(tk);
		if (StringUtils.isEmpty(userName)) {
			return returnLogic.resultErrorJsonString(401, "登录已失效，请先登录！");
		}
		Roles r = rolesService.findRoleForUser("administrator", "administrator", userName);
		if (StringUtils.isEmpty(r)) {
			return returnLogic.resultErrorJsonString(403, "超管专属功能，无权操作！");
		}
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		if (StringUtils.isEmpty(id)) {
			return returnLogic.resultErrorJsonString(206, "未获取到要删除的角色主键！");
		}
		// 删除跟角色相关尾表先；最好删掉对应角色
		// cpwater_role_assets_fuction、cpwater_role_device_fuction
		// 、cpwater_role_device、cpwater_role_group、cpwater_role_admin
		// 、cpwater_roles

		// cpwater_role_assets_fuction
		RoleAssetsFuction raf = new RoleAssetsFuction();
		raf.setRoleId(id);
		rolesAssetsFunctionService.deleteByObj(raf);

		// cpwater_role_device_fuction
		RoleDeviceFuction rdf = new RoleDeviceFuction();
		rdf.setRoleId(id);
		rolesDeviceFunctionService.deleteByObj(rdf);

		// cpwater_role_device
		RoleDevice rd = new RoleDevice();
		rd.setRoleId(id);
		rolesDeviceService.deleteByObj(rd);

		// cpwater_role_group
		RoleGroup rg = new RoleGroup();
		rg.setRoleId(id);
		rolesGroupService.delete(rg);

		// cpwater_role_admin
		RoleAdmin ra = new RoleAdmin();
		ra.setRoleId(id);
		rolesAdminService.deleteByObj(ra);

		// cpwater_roles
		rolesService.delete(new Integer[] { id });

		return returnLogic.resultJson(200, "角色删除成功。");
	}

}
