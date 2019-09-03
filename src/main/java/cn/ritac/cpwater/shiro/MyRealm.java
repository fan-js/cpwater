package cn.ritac.cpwater.shiro;

import cn.ritac.cpwater.mybatis.model.Users;
import cn.ritac.cpwater.service.UsersService;
import cn.ritac.cpwater.util.JWTUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MyRealm extends AuthorizingRealm {

	@Autowired
	private UsersService userService;

	public boolean supports(AuthenticationToken token) {
		return token instanceof JWTToken;
	}

	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

		return simpleAuthorizationInfo;
	}

	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
		String token = (String) auth.getCredentials();

		String username = JWTUtil.getUsername(token);
		if (StringUtils.isEmpty(username)) {
			throw new AuthenticationException("登录状态失效！-401");
		}
		Users userSearch = new Users();
		userSearch.setTelephone(username);
		userSearch.setUser_state(true);
		Users userBean = (Users) this.userService.find(userSearch);
		if (userBean == null) {
			throw new AuthenticationException("账号不存在或已冻结。");
		}
		if ((!StringUtils.isEmpty(userBean.getUser_token())) && (token.equals(userBean.getUser_token()))) {
			throw new AuthenticationException("登录状态失效！请重新登录。-401");
		}
		if (!JWTUtil.verify(token, username, userBean.getPwd())) {
			throw new AuthenticationException("用户名或密码错误。");
		}

		return new SimpleAuthenticationInfo(token, token, "my_realm");
	}
}