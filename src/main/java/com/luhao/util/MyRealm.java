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
 * �û�������֤Ȼ����Ȩ
 * 
 * @author 43608
 *
 */
public class MyRealm extends AuthorizingRealm {
	@Autowired
	private IUserService userService;
	/**
	 * �û�����Ȩ�߼� ��Ȩ����:ͨ������������Ϣ,��ȡ�û����е�Ȩ����Ϣ(��ɫ��Ȩ��),����userName��ѯȨ��.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("��ʼ��Ȩ.....");
        //��PrincipalCollection �л�ȡ������Ϣ
        //���������Ϣ����֤ʱ����������.
        String userName = (String)principals.getPrimaryPrincipal();
        Set<String> userPowers = userService.getPowerByName(userName);
        //����������Ϣ
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //���ý�ɫ��Ϣ
        //����Ȩ����Ϣ
        if(userPowers != null && userPowers.size()>0) {
            info.setStringPermissions(userPowers);
        }
        return info;
		
	}

	/**
	 * �û�����֤�߼� ��֤���� : ͨ���û������tokean���������֤����,��֤�ɹ��ͷ����û��������Ϣ.��֤ʧ�ܾ��׳��쳣,���߷���null.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken info) throws AuthenticationException {
		System.out.println("��ʼ��֤");
		// ��infoת����token,�Ӷ��õ��û���������
		UsernamePasswordToken token = (UsernamePasswordToken) info;
		String userName = token.getUsername();
		String password = new String(token.getPassword());
		TUser user = userService.getByNameAndPwd(userName, password);
		//�����½����,����null,��ʵӦ���׳��쳣
		if(user==null) {
			return null;
		}
		System.out.println("getName():"+getName());
		return new SimpleAuthenticationInfo(user.getUserName(), user.getUserPassword(), getName());
	}

}
