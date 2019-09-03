package cn.ritac.cpwater.web.controller.api;

import java.util.ArrayList;
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

import cn.ritac.cpwater.mybatis.model.Admin;
import cn.ritac.cpwater.mybatis.model.RoleAdmin;
import cn.ritac.cpwater.mybatis.model.Roles;
import cn.ritac.cpwater.mybatis.model.UserRole;
import cn.ritac.cpwater.mybatis.model.Users;
import cn.ritac.cpwater.service.AdminService;
import cn.ritac.cpwater.service.RolesAdminService;
import cn.ritac.cpwater.service.RolesService;
import cn.ritac.cpwater.service.UserRolesService;
import cn.ritac.cpwater.service.UsersService;
import cn.ritac.cpwater.util.JWTUtil;
import cn.ritac.cpwater.web.dto.AdminDto;
import cn.ritac.cpwater.web.dto.TAdminDto;
import cn.ritac.cpwater.web.dto.TRoleDto;

@RestController
@RequestMapping(value = "/admin")
public class AdminController extends BaseController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private RolesAdminService rolesAdminService;

	@Autowired
	private HttpServletRequest httpServletRequest;

	@Autowired
	private RolesService rolesService;

	@Autowired
	private UsersService userService;

	@Autowired
	private UserRolesService userRolesService;

	/**
	 * 修改管理员过程中调用
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/toUpdateAdmin")
	public String toUpdateAdmin(int id) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		String tk = httpServletRequest.getHeader("Authorization") == null ? ""
				: httpServletRequest.getHeader("Authorization");
		// 电话号码
		String userName = JWTUtil.getUsername(tk);
		if (StringUtils.isEmpty(userName)) {
			return returnLogic.resultErrorJsonString(401, "登录已失效，请先登录！");
		}
		Roles role = rolesService.findRoleForUser("administrator", "administrator", userName);
		if (StringUtils.isEmpty(role)) {
			return returnLogic.resultErrorJsonString(403, "超管专属功能，无权操作！");
		}
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}

		Admin a = null;
		TAdminDto ad = new TAdminDto();
		// 角色全集合
		Roles r = new Roles();
		r.setRoleLevel(1);
		List<Roles> allRoleList = rolesService.findList(r);
		// 包含角色
		List<Roles> rolesList = null;
		List<TRoleDto> haveRolesList = new ArrayList<TRoleDto>();
		if (id > 0) {
			// 获取管理员信息
			a = adminService.findById(id);
			if (!StringUtils.isEmpty(a))
				BeanUtils.copyProperties(a, ad);
			// 包含角色
			rolesList = rolesService.findRoleForAdmin(id);
			if (!StringUtils.isEmpty(rolesList)) {
				for (Roles ros : rolesList) {
					TRoleDto rd = new TRoleDto();
					rd.setId(ros.getId());
					rd.setRoleName(ros.getRoleName());
					haveRolesList.add(rd);
				}
			}
		}
		Map<String, Object> resList = new HashMap<String, Object>();
		resList.put("admin", ad);
		resList.put("allRoleList", allRoleList);
		resList.put("haveRolesList", haveRolesList);
		return returnLogic.resultJsonString(200, "查询成功。", resList);
	}

	/**
	 * 新增、修改管理员
	 * 
	 * @param adminDto
	 * @return
	 */
	@PostMapping("/addOrUpdateAdmin")
	@Transactional
	public String addAdmin(@RequestBody AdminDto adminDto) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		String tk = httpServletRequest.getHeader("Authorization") == null ? ""
				: httpServletRequest.getHeader("Authorization");
		// 电话号码
		String userName = JWTUtil.getUsername(tk);
		if (StringUtils.isEmpty(userName)) {
			return returnLogic.resultErrorJsonString(401, "登录已失效，请先登录！");
		}
		Roles role = rolesService.findRoleForUser("administrator", "administrator", userName);
		if (StringUtils.isEmpty(role)) {
			return returnLogic.resultErrorJsonString(403, "超管专属功能，无权操作！");
		}
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}

		if (StringUtils.isEmpty(adminDto)) {
			return returnLogic.resultErrorJsonString(206, "管理员信息不能为空。");
		}
		String userPhone = adminDto.getUserPhone();
		Users user = new Users();
		user.setTelephone(userPhone);
		Users isExits = userService.find(user);
		if (StringUtils.isEmpty(isExits)) {
			return returnLogic.resultErrorJsonString(206, "输入的手机号码未注册。");
		}
//		Admin anSearch = new Admin();
//		anSearch.setAdminName(adminDto.getAdminName());
//		Admin isExitsAdmin = adminService.find(anSearch);
//		if (!StringUtils.isEmpty(isExitsAdmin)) {
//			return returnLogic.resultErrorJsonString(206, "管理员名字已存在。");
//		}
		// 保存住表信息
		String msg = "新增管理员成功。";
		Admin an = new Admin();
		BeanUtils.copyProperties(adminDto, an);
		if (!StringUtils.isEmpty(adminDto.getId()) && adminDto.getId() > 0) {
			msg = "修改管理员成功。";
			adminService.update(an);
		} else {
			adminService.save(an);
		}

		// 设置cpwater_user_role
		UserRole ur = new UserRole();
		ur.setRoleId(an.getId());
		// userRolesService.deleteByObj(ur);
		ur.setUserId(isExits.getId());
		userRolesService.save(ur);

		// 设置相关尾表信息cpwater_role_admin
		RoleAdmin ra = new RoleAdmin();
		ra.setAdminId(an.getId());
		rolesAdminService.deleteByObj(ra);
		// 获取角色集合
		List<Integer> roleIds = adminDto.getRolesIds();
		if (!StringUtils.isEmpty(roleIds)) {
			for (Integer id : roleIds) {
				RoleAdmin r = new RoleAdmin();
				r.setRoleId(id);
				r.setAdminId(an.getId());
				// 存储尾表数据
				rolesAdminService.save(r);
			}
		}
		return returnLogic.resultJson(200, msg);
	}

	/**
	 * 获取管理员列表
	 * 
	 * @param adminDto
	 * @return
	 */
	@GetMapping("/findAdmin")
	public String findAdmin(AdminDto adminDto) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		String tk = httpServletRequest.getHeader("Authorization") == null ? ""
				: httpServletRequest.getHeader("Authorization");
		// 电话号码
		String userName = JWTUtil.getUsername(tk);
		if (StringUtils.isEmpty(userName)) {
			return returnLogic.resultErrorJsonString(401, "登录已失效，请先登录！");
		}
		Roles role = rolesService.findRoleForUser("administrator", "administrator", userName);
		if (StringUtils.isEmpty(role)) {
			return returnLogic.resultErrorJsonString(403, "超管专属功能，无权操作！");
		}
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}

		if (StringUtils.isEmpty(adminDto)) {
			return returnLogic.resultErrorJsonString(206, "管理员信息不能为空。");
		}
		Admin an = new Admin();
		BeanUtils.copyProperties(adminDto, an);
		int pageIndex = adminDto.getPageIndex();
		int pageSize = adminDto.getPageSize();
		PageInfo<Admin> resList = adminService.findListByObj(pageIndex, pageSize, an);
		return returnLogic.resultJsonString(200, "查询成功。", resList);
	}

	/**
	 * 按主键删除管理员
	 * 
	 * @param id
	 * @return msg
	 */
	@DeleteMapping("/deleteAdmin")
	@Transactional
	public String deleteAdmin(int id) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		String tk = httpServletRequest.getHeader("Authorization") == null ? ""
				: httpServletRequest.getHeader("Authorization");
		// 电话号码
		String userName = JWTUtil.getUsername(tk);
		if (StringUtils.isEmpty(userName)) {
			return returnLogic.resultErrorJsonString(401, "登录已失效，请先登录！");
		}
		Roles role = rolesService.findRoleForUser("administrator", "administrator", userName);
		if (StringUtils.isEmpty(role)) {
			return returnLogic.resultErrorJsonString(403, "超管专属功能，无权操作！");
		}
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		if (StringUtils.isEmpty(id)) {
			return returnLogic.resultErrorJsonString(206, "删除的管理员主键不能为空。");
		}
		// 设置相关尾表信息cpwater_role_admin
		RoleAdmin ra = new RoleAdmin();
		ra.setAdminId(id);
		rolesAdminService.deleteByObj(ra);
		adminService.delete(new Integer[] { id });
		// 设置相关尾表信息cpwater_user_role
		UserRole ur = new UserRole();
		ur.setRoleId(id);
		userRolesService.deleteByObj(ur);
		return returnLogic.resultJson(200, "删除成功。");
	}

}
