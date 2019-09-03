package cn.ritac.cpwater.web.controller.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageInfo;
import cn.ritac.cpwater.mybatis.model.Assets;
import cn.ritac.cpwater.mybatis.model.AssetsFunction;
import cn.ritac.cpwater.mybatis.model.Devices;
import cn.ritac.cpwater.mybatis.model.Roles;
import cn.ritac.cpwater.service.AssetsFunctionService;
import cn.ritac.cpwater.service.AssetsService;
import cn.ritac.cpwater.service.DevicesService;
import cn.ritac.cpwater.service.RolesService;
import cn.ritac.cpwater.util.JWTUtil;
import cn.ritac.cpwater.web.dto.AssetsDto;
import cn.ritac.cpwater.web.dto.convert.AssetsCount;

/**
 * 资产管理模块
 * 
 * @author admin
 *
 */

@RestController
@RequestMapping(value = "/assets")
public class AssetsController extends BaseController {

	@Autowired
	private AssetsService assetsService;

	@Autowired
	private DevicesService devicesService;

	@Autowired
	private HttpServletRequest httpServletRequest;

	@Autowired
	private AssetsFunctionService assetsFunctionService;

	@Autowired
	private RolesService rolesService;

	/**
	 * 按资产类型获取资产列表
	 * 
	 * @param assets
	 * @return page_list
	 */
	@GetMapping("/findAssets")
	public String findAssets(AssetsDto assets) {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		if (StringUtils.isEmpty(assets)) {
			return returnLogic.resultErrorJsonString(206, "分页信息不能为空！");
		}
		int pageIndex = assets.getPageIndex();
		int pageSize = assets.getPageSize();
		Assets ast = new Assets();
		String assetsType = assets.getAssetsType();
		if (!StringUtils.isEmpty(assetsType)) {
			ast.setAssetsType(assetsType);
		}
		PageInfo<Assets> resList = assetsService.findListByObj(pageIndex, pageSize, ast);
		return returnLogic.resultJsonString(200, "查询成功。", resList);
	}

	/**
	 * 新增资产信息
	 * 
	 * @param assets
	 * @return msg
	 */
	@PostMapping("/addAssets")
	public String addAssets(@RequestBody AssetsDto assets) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		String tk = httpServletRequest.getHeader("Authorization") == null ? ""
				: httpServletRequest.getHeader("Authorization");
		// 电话号码
		String userName = JWTUtil.getUsername(tk);
		if (StringUtils.isEmpty(userName)) {
			return returnLogic.resultErrorJsonString(401, "登录已失效，请先登录！");
		}
		if (StringUtils.isEmpty(assets)) {
			return returnLogic.resultErrorJsonString(206, "资产信息不能为空！");
		}
		Assets ast = new Assets();
		BeanUtils.copyProperties(assets, ast);
		// 验证设备编号正确性
		if (StringUtils.isEmpty(assets.getDeviceNum())) {
			return returnLogic.resultErrorJsonString(206, "设备编号不能空！");
		}
		Devices dev = new Devices();
		dev.setDeviceNum(assets.getDeviceNum());
		Devices isExtis = devicesService.find(dev);
		if (StringUtils.isEmpty(isExtis)) {
			return returnLogic.resultErrorJsonString(206, "编号: <" + assets.getDeviceNum() + "> 对应的设备不存在！");
		}
		Assets isAst = new Assets();
		isAst.setDeviceNum(assets.getDeviceNum());
		isAst = assetsService.find(isAst);
		if (!StringUtils.isEmpty(isAst)) {
			return returnLogic.resultErrorJsonString(206,
					"编号：<" + assets.getDeviceNum() + "> 的设备已归到  <" + isAst.getAssetsName() + "> 资产下了！");
		}
		assetsService.save(ast);
		return returnLogic.resultJson(200, "资产新增成功。");
	}

	/**
	 * 按主键删除资产
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/deleteAssets")
	public String deleteAssets(Integer id) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		String tk = httpServletRequest.getHeader("Authorization") == null ? ""
				: httpServletRequest.getHeader("Authorization");
		// 电话号码
		String userName = JWTUtil.getUsername(tk);
		if (StringUtils.isEmpty(userName)) {
			return returnLogic.resultErrorJsonString(401, "登录已失效，请先登录！");
		}
		if (StringUtils.isEmpty(id)) {
			return returnLogic.resultErrorJsonString(206, "资产主键不能为空！");
		}
		assetsService.delete(new Integer[] { id });
		return returnLogic.resultJson(200, "资产删除成功。");
	}

	@GetMapping("/toUpdateAssets")
	public String toUpdateAssets(Integer id) {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		if (StringUtils.isEmpty(id)) {
			return returnLogic.resultErrorJsonString(206, "资产主键不能为空！");
		}
		Assets assetPojo = assetsService.findById(id);
		return returnLogic.resultJsonString(200, "查询成功", assetPojo);
	}

	/**
	 * 修改资产信息
	 * 
	 * @param assets
	 * @return
	 */
	@PutMapping("/updateAssets")
	public String updateAssets(@RequestBody AssetsDto assets) {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		String tk = httpServletRequest.getHeader("Authorization") == null ? ""
				: httpServletRequest.getHeader("Authorization");
		// 电话号码
		String userName = JWTUtil.getUsername(tk);
		if (StringUtils.isEmpty(userName)) {
			return returnLogic.resultErrorJsonString(401, "登录已失效，请先登录！");
		}
		Roles r = rolesService.findRoleForUser("administrator", "administrator", userName);
		Boolean isAdmin = r == null ? true : false;
		// 验证角色资产修改权限
		List<AssetsFunction> af = assetsFunctionService.findAssetsFunctionByRoleId(userName);
		if ((StringUtils.isEmpty(af) || af.size() == 0) && isAdmin) {
			return returnLogic.resultErrorJsonString(403, "执行失败，当前用户无权操作。");
		}
		if (StringUtils.isEmpty(assets)) {
			return returnLogic.resultErrorJsonString(206, "资产信息不能为空！");
		}
		Assets ast = new Assets();
		BeanUtils.copyProperties(assets, ast);
		// 验证设备编号正确性
		if (StringUtils.isEmpty(assets.getDeviceNum())) {
			return returnLogic.resultErrorJsonString(206, "设备编号不能为空！");
		}
		Devices dev = new Devices();
		dev.setDeviceNum(assets.getDeviceNum());
		Devices isExtis = devicesService.find(dev);
		if (StringUtils.isEmpty(isExtis)) {
			return returnLogic.resultErrorJsonString(206, "编号: <" + assets.getDeviceNum() + "> 对应的设备不存在！");
		}
		Assets isAst = new Assets();
		isAst.setDeviceNum(assets.getDeviceNum());
		isAst = assetsService.find(isAst);
		if (!StringUtils.isEmpty(isAst) && isAst.getId() != assets.getId()) {
			return returnLogic.resultErrorJsonString(206,
					"编号：<" + assets.getDeviceNum() + "> 的设备已归到  <" + isAst.getAssetsName() + "> 资产下了！");
		}
		assetsService.update(ast);
		return returnLogic.resultJson(200, "资产修改成功。");
	}

	/**
	 * 获取资产统计信息
	 * 
	 * @return obj
	 */
	@GetMapping("/countIfAssets")
	public String countIfAssets() {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		AssetsCount resList = assetsService.countIfAssets();
		return returnLogic.resultJsonString(200, "查询成功。", resList);
	}

}
