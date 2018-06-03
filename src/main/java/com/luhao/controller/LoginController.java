package com.luhao.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.luhao.service.IUserService;

@Controller
public class LoginController {
	@Autowired
	private IUserService userService;
	@ResponseBody
	@GetMapping("login")
	public boolean login(String userName,String password) {
		return userService.getByNameAndPwd(userName, password)!=null;
	}
	@GetMapping("login2")
	public void login2(String userName,String password,HttpServletRequest request) {
		System.out.println("开始登陆");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
        try {
            subject.login(token); //login
            System.out.println("登陆成功!");
        } catch (AuthenticationException e) {
        	System.out.println("登陆失败");
        }
	}
	@GetMapping("get")
	public String get() {
		System.out.println("获得资源");
		return "这是资源";
	}
}
