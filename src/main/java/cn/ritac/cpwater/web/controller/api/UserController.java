package cn.ritac.cpwater.web.controller.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

import cn.jsms.api.SendSMSResult;
import cn.jsms.api.ValidSMSResult;
import cn.jsms.api.common.SMSClient;
import cn.ritac.cpwater.mybatis.model.Check;
import cn.ritac.cpwater.sendMassage.KetSecret;
import cn.ritac.cpwater.sendMassage.SendMassage;
import cn.ritac.cpwater.service.CheckService;
import cn.ritac.cpwater.web.dto.CheckDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.github.pagehelper.PageInfo;
import cn.ritac.cpwater.mybatis.model.Roles;
import cn.ritac.cpwater.mybatis.model.Users;
import cn.ritac.cpwater.service.RolesService;
import cn.ritac.cpwater.service.UsersService;
import cn.ritac.cpwater.util.JWTUtil;
import cn.ritac.cpwater.util.MD5CheckFile;
import cn.ritac.cpwater.web.dto.LoginDto;
import cn.ritac.cpwater.web.dto.UserDto;
import cn.ritac.cpwater.web.dto.convert.ProporVO;
import cn.ritac.cpwater.web.dto.convert.RUsers;

/**
 * 用户操作
 * 
 * @author admin
 *
 */
@Controller
@RequestMapping(value = "/users")
@ResponseBody
public class UserController extends BaseController {

	@Value("${site.user.telPattern}")
	private String telPattern; // 注入普通字符串

	@Value("${site.upload-folder}")
	private String UPLOAD_FOLDER;
	@Autowired
	private UsersService usersService;

	@Autowired
	private CheckService checkService;

	@Autowired
	private HttpServletRequest httpServletRequest;
	
	@Autowired
	private RolesService rolesService;

	/**
	 * 用户登录
	 * 
	 * @param
	 * @param
	 * @return
	 */

	@PostMapping("/login")
	public String login(@RequestBody LoginDto login) {
		// 首先，检测用户登录状态；
		String tk = httpServletRequest.getHeader("Authorization") == null ? ""
				: httpServletRequest.getHeader("Authorization");
		// 用户名
		String userName = login.getLoginName();
		// 密码
		String password = login.getPwd();
		// 加密明码
		String pwd = MD5CheckFile.getMD5String(password);
		Users searchUser = new Users();
		searchUser.setPwd(pwd);
		searchUser.setTelephone(userName);
		// searchUser.setUser_state(true);
		Users userExtis = usersService.find(searchUser);
		//userExtis.setPwd("");
		if (StringUtils.isEmpty(userExtis)) {
			return returnLogic.resultErrorJsonString(206, "账户、密码输入有误或账户不可用。");
		}
		// 用户登录时失效当期令牌,成功登录会返回新的有效令牌
		if (tk != null && !tk.equals("")) {
			userExtis.setUser_token(tk);
			usersService.update(userExtis);
		}
		String token = JWTUtil.sign(userName, pwd, -1);
		return returnLogic.resultJsonLogin(200, "登录成功！", token,userExtis);

	}

	/**
	 * 重置密码
	 * 
	 * @param
	 * @return
	 */

	@PutMapping("/forgetPwd")
	public String forgetPwd(@RequestBody UserDto user, HttpServletRequest request) {
		//限制用户忘记密码重置密码的次数3次
		CheckDto checkDto=MonitoringAuthority(user.getTelephone(),"重置密码");
		if(checkDto.getMsg()!=null&&checkDto.getMsg()!=""){
			return returnLogic.resultErrorJsonString(206, checkDto.getMsg());
		}
		if(checkDto.getResult()){
			return returnLogic.resultErrorJsonString(206, "修改次数超限.请次日修改!");
		}
		String telephone = user.getTelephone();
		String code = user.getCheckCode();
		String new_pwd = user.getNewPwd();
		String regId=user.getMsgId();
		String pwd = MD5CheckFile.getMD5String(new_pwd);
		boolean telPass = Pattern.matches(telPattern, telephone);
		if (!telPass) {
			return returnLogic.resultErrorJsonString(206, "手机号码不正确。");
		}
		//验证码验证
				KetSecret ks=new KetSecret();
				SMSClient client = new SMSClient(ks.getMASTER_SECRET(), ks.getAPP_KEY());
					try{
					//发送验证码，注意一点。这个假如验证码错误就直接报错了
					ValidSMSResult res = client.sendValidSMSCode(regId ,code);
					//return returnLogic.resultErrorJsonString(200, "验证码正确!");
				} catch (Exception e) {
					return returnLogic.resultErrorJsonString(500, "请输入正确的验证码!");
				}
		Users userSearch = new Users();
		userSearch.setTelephone(telephone);
		// 找到对象
		Users userInfo = usersService.find(userSearch);
		if (!StringUtils.isEmpty(userInfo)) {
			userInfo.setPwd(pwd);
			usersService.update(userInfo);
			return returnLogic.resultJson(200, "密码修改成功。");
		}
		return returnLogic.resultErrorJsonString(206, "手机号码不正确");
	}


	//控制天重置密码和修改密码次数
		public CheckDto MonitoringAuthority(String phone, String type){
		CheckDto checkDto=new CheckDto();
			checkDto.setResult(false);
			//检验用户当日重置密码和修改的次数
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
			Check check=new Check();
			String format = sdf.format(new Date());
			check.setPhone(phone);
			check.setDateTime(format);
			check.setType(type);
			int num=checkService.count(check);
			if(num==1) {
				checkDto.setMsg("请输入正确的验证码!还有一次修改机会,请谨慎操作!");
			}
			if(num>=3){
			checkDto.setResult(true);
			return checkDto;
			}
			//没超过限定次数，进行插入操作
			checkService.save(check);
			//还有一次修改机会进行提示
			return checkDto;
		}

	/**
	 * 修改密码
	 * @param user
	 * @return msg
	 */
	@PutMapping("/updatePwd")
	public String updatePwd(@RequestBody UserDto user) {

		/*-------------登录验证----<Begin>-------------*/
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		/*-------------登录验证----<End>-------------*/

		// 获取待修改对象
		int userId = user.getId();
		// 获取用户输入老密码
		String pwd = user.getPwd();
		// 获取用户输入新密码
		String new_pwd = user.getNew_pwd();
		Users userInfo = usersService.findById(userId);
		if (StringUtils.isEmpty(userInfo)) {
			return returnLogic.resultErrorJsonString(206, "用户不存在。");
		}

		if (StringUtils.isEmpty(pwd)) {
			return returnLogic.resultErrorJsonString(206, "原始密码不能为空。");
		}
		if (StringUtils.isEmpty(new_pwd)) {
			return returnLogic.resultErrorJsonString(206, "新密码不能为空。");
		}
		// 加密新老密码并验证
		String md5_pwd = MD5CheckFile.getMD5String(pwd);
		if (!userInfo.getPwd().equals(md5_pwd)) {
			return returnLogic.resultErrorJsonString(206, "原始密码输入错误。");
		}
		String md5_new_pwd = MD5CheckFile.getMD5String(new_pwd);
		userInfo.setPwd(md5_new_pwd);
		usersService.update(userInfo);
		return returnLogic.resultJson(200, "密码修改成功。");

	}

	/**
	 * 向指定手机发送验证码
	 * 
	 * @param
	 * @return msg
	 */
	@GetMapping("/sendCheckCode")
	public String sengCode(String telephone) {
		SendMassage sendMassage=new SendMassage();
		// 生成随机验证码
		//String code = MessageGenerate.CreateRandom();
		// 传入手机号码
		boolean telPass = Pattern.matches(telPattern, telephone);
		if (StringUtils.isEmpty(telephone) || !telPass) {
			return returnLogic.resultErrorJsonString(206, "请输入正确的手机号码。");
		}
//		HttpSession session = request.getSession();
//		StringBuffer sessionId = new StringBuffer();
//		sessionId.append(telephone);
//		// 登录用户放入session
//		session.setAttribute(sessionId.toString(), code);

		//认证手机号是否存在
		Users users=new Users();
		users.setTelephone(telephone);
		Users u=usersService.find(users);
		if(StringUtils.isEmpty(u)){
			return returnLogic.resultErrorJsonString(206, "用户不存在。");
		}

		SendSMSResult res=sendMassage.sendSMS(telephone);
		return returnLogic.resultJsonString(200, "验证码已发送。",res.getMessageId());
	}

	/***
	 * 通用图片上传,此处只做图片写入,不做数据库更新
	 * 
	 * @param imgFile
	 * @return
	 */
	@PostMapping("/upLoadImage")
	public String upLoadImage(@RequestBody MultipartFile imgFile) {

		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}

		if (StringUtils.isEmpty(imgFile)) {
			return returnLogic.resultErrorJsonString(206, "请选择要上传的图片。");
		}
		try {
			//文件全称
			String imgName = imgFile.getOriginalFilename();
			//转换字节
			byte[] bytes = imgFile.getBytes();
			//封装全路径
			Path path = Paths.get(UPLOAD_FOLDER + "/" + imgName);
			// 如果没有files文件夹，则创建
			if (!Files.isWritable(path)) {
				//支持多层目录
				Files.createDirectories(Paths.get(UPLOAD_FOLDER));
			}
			// 文件写入指定路径
			Files.write(path, bytes);
			return returnLogic.resultJson(200, "上传成功。");
		} catch (IOException e) {
			e.printStackTrace();
			return returnLogic.resultErrorJsonString(206, "发送错误,上传失败。");
		}

	}

	/**
	 * 保存修改用户
	 * 
	 * @param user
	 * @param result
	 * @return
	 */

	@PostMapping("/registerOrupdate")
	public String saveUser(@RequestBody UserDto user, BindingResult result, HttpServletRequest request) {
		String returnMsg = returnLogic.defalutValidate(result);
		if (!result.hasErrors()) {
			if (user.getId() > 0) {
				// 首先，检测用户登录状态；
				Subject subject = SecurityUtils.getSubject();
//				if (!subject.isAuthenticated()) {
//					return returnLogic.resultErrorJsonString(401, "请先登录！");
//				}
				String tk = httpServletRequest.getHeader("Authorization") == null ? ""
						: httpServletRequest.getHeader("Authorization");
				// 电话号码
				String userName = JWTUtil.getUsername(tk);
				Roles r = rolesService.findRoleForUser("administrator", "administrator", userName);
				Boolean isAdmin = r == null ? true : false;
				if(!StringUtils.isEmpty(user.getUser_name()) && user.getUser_name().equals("administrator")&& isAdmin) {
					return returnLogic.resultErrorJsonString(206, "< "+user.getUser_name()+"> 是系统关键字，请重新输入！");
				}
				Users userInfo = usersService.findById(user.getId());
				userInfo.setHead_portrait(user.getHead_portrait());
				userInfo.setUser_company(user.getUser_company());
				userInfo.setUserAccount(user.getUserAccount());
				userInfo.setUser_sex(user.getUser_sex());
				userInfo.setUser_department(user.getUser_department());
				userInfo.setUser_address(user.getUser_address());
				userInfo.setUser_email(user.getUser_email());
				// 图片上传需要单独写一个方法,直接把文件写入指定目录,然后把文件名字存入对应用户信息处
				// "001234.jpg"
				userInfo.setHead_portrait(user.getHead_portrait());
				usersService.update(userInfo);
				return returnLogic.resultJson(200, "修改成功。");

			} else {
				String telephone=user.getTelephone();
				boolean telPass = Pattern.matches(telPattern, telephone);
				if (StringUtils.isEmpty(telephone) || !telPass) {
					return returnLogic.resultErrorJsonString(206, "请输入正确的手机号码。");
				}

				// 用户注册不需要鉴权
				ModelMapper modelMapper = new ModelMapper();
				// 映射dto属性到java对象
				Users pojo = modelMapper.map(user, Users.class);
				// 验证当前手机号是否已经注册
				Users userSearch = new Users();
				userSearch.setTelephone(user.getTelephone());
				Users userExit = usersService.find(userSearch);
				// 加密传入明码
				String pwd = MD5CheckFile.getMD5String(user.getPwd());
				// 如果传入dto用户主键大于0则为修改 ; 否则为注册
				// 用户注册
				if (userExit != null) {
					return returnLogic.resultErrorJsonString(206, "该手机号已经注册!");
				}
//				// 获取用户输入的验证码
//				String regId=user.getMsgId();
//				String code = user.getCheckCode();
//				if (StringUtils.isEmpty(code)) {
//					return returnLogic.resultErrorJsonString(206, "请输入验证码!");
//				}
//				//验证码验证
//				KetSecret ks=new KetSecret();
//				SMSClient client = new SMSClient(ks.getMASTER_SECRET(), ks.getAPP_KEY());
//					try{
//					//发送验证码，注意一点。这个假如验证码错误就直接报错了
//					ValidSMSResult res = client.sendValidSMSCode(regId ,code);
//					//return returnLogic.resultErrorJsonString(200, "验证码正确!");
//				} catch (Exception e) {
//					return returnLogic.resultErrorJsonString(500, "请输入正确的验证码!");
//				}

				// 用户名默认为手机号码
				//pojo.setUserAccount(user.getUserAccount());
				pojo.setTelephone(user.getTelephone());
				pojo.setPwd(pwd);
				pojo.setCreateTime(new Date());
				// 保存用户
				usersService.save(pojo);
				return returnLogic.resultJson(200, "添加账号成功 !");
			}
		}

		return returnLogic.resultErrorJsonString(206, returnMsg);
	}

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @return
	 */

	@DeleteMapping("/deleteUser")
	public String deleteUser(int id) {

		/*-------------登录验证----<Begin>-------------*/
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		/*-------------登录验证----<End>-------------*/

		Users userInfo = usersService.findById(id);
		if (userInfo != null) {
			usersService.delete(new Integer[] { id });
		}
		return returnLogic.resultJson(200, "删除成功 !");
	}

	/**
	 * 得到单个用户
	 * 
	 * @param id
	 * @param
	 * @return
	 */

	@RequestMapping("/getuser")
	public String getuser(int id) {
		;
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		String tk = httpServletRequest.getHeader("Authorization") == null ? ""
				: httpServletRequest.getHeader("Authorization");
		// 电话号码
		String telephone = JWTUtil.getUsername(tk);

		Users user = new Users();
		if (id > 0) {
			user.setId(id);
		} else {
			user.setTelephone(telephone);
		}
		user.setUser_state(true);
		user = usersService.find(user);
		RUsers userData = new RUsers();
		BeanUtils.copyProperties(user, userData);
		return returnLogic.resultJsonString(200, "查询成功。", userData);
	}

	/**
	 * 得到用户列表
	 * 
	 * @param
	 * @param
	 * @param
	 * @return
	 */
	@RequestMapping("/findUser")
	public String findUser(UserDto user) {
		Subject subject = SecurityUtils.getSubject();
//		if (!subject.isAuthenticated()) {
//			return returnLogic.resultErrorJsonString(401, "请先登录！");
//		}
		String telephone = user.getTelephone();
		int pageIndex = user.getPageIndex();
		int pageSize = user.getPageSize();
		PageInfo<RUsers> userList = usersService.getUsers(telephone,pageSize, pageIndex);
		return returnLogic.resultJsonString(206, "查询列表成功 !", userList);

	}

	/**
	 * 用户可视化
	 * @return userProporList
	 */
	@GetMapping("/userPropor")
	public String userPropor() {
		// 首先，检测用户登录状态；
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		List<ProporVO> userProporList = usersService.userProporList();
		return returnLogic.resultJsonString(200, "查询成功", userProporList);
	}

}
