package com.luhao.util;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.luhao.entity.TUser;
import com.luhao.service.IUserService;

/**
 * 用户是先认证然后授权
 * 
 * @author 43608
 *
 */
public class MyRealm extends AuthorizingRealm {
	@Autowired
	private IUserService userService;
	/**
	 * 用户的授权逻辑 授权方法:通过传入的身份信息,获取用户具有的权限信息(角色和权限),根据userName查询权限.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("开始授权.....");
        //从PrincipalCollection 中获取主体信息
        //这个主体信息是认证时创建出来的.
        String userName = (String)principals.getPrimaryPrincipal();
        Set<String> userPowers = userService.getPowerByName(userName);
        //创建返回信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //设置角色信息
        //设置权限信息
        if(userPowers != null && userPowers.size()>0) {
            info.setStringPermissions(userPowers);
        }
        return info;
		
	}

	/**
	 * 用户的认证逻辑 认证方法 : 通过用户传入的tokean对象进行认证操作,认证成功就返回用户的身份信息.认证失败就抛出异常,或者返回null.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken info) throws AuthenticationException {
		System.out.println("开始认证");
		// 将info转换成token,从而得到用户名和密码
		UsernamePasswordToken token = (UsernamePasswordToken) info;
		String userName = token.getUsername();
		String password = new String(token.getPassword());
		TUser user = userService.getByNameAndPwd(userName, password);
		//如果登陆错误,返回null,其实应该抛出异常
		if(user==null) {
			return null;
		}
		System.out.println("getName():"+getName());
		return new SimpleAuthenticationInfo(user.getUserName(), user.getUserPassword(), getName());
	}

}
