package com.lee.qiniu.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.lee.qiniu.entity.Admin;
import com.lee.qiniu.json.ResultCodeEnum;
import com.lee.qiniu.service.IAdminService;

public class CustomRealm extends AuthorizingRealm {
	
	@Autowired
	private IAdminService adminservice;
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		LoginSessionCache cache = (LoginSessionCache)principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//加入角色
		info.addRoles(adminservice.getRoles(cache.getId()));
		
		//加入权限
		info.addStringPermissions(adminservice.getPermissions(cache.getId()));
		return null;	
	}
	
	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = ((UsernamePasswordToken)token).getUsername();
		String password = String.valueOf(((UsernamePasswordToken)token).getPassword());
		com.lee.qiniu.json.JsonResult result = adminservice.doLogin(username, password);
		if(result.getStatus() == ResultCodeEnum.USER_DISABLED.getCode()){
			throw new LockedAccountException(); //帐号锁定
		} else if(result.getStatus() == ResultCodeEnum.OBJECT_UN_EXIST.getCode()) {
			throw new UnknownAccountException();//没找到帐号
		} else if(result.getStatus() != ResultCodeEnum.OK.getCode()) {
			throw new AuthenticationException();
		}
		//登录成功后，记录登录信息
		Admin user = adminservice.getByName(username);
		LoginSessionCache sc = new LoginSessionCache();
		sc.setLogin(user.getId(),user.getUserName());	
		//
		//登录校验
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
				sc, 
				password,
				getName());
		return info;
	}

	

}
