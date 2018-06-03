package com.luhao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luhao.entity.TUser;
import com.luhao.service.IUserService;

@RestController
@RequestMapping("hello")
public class HelloWorldController {
	@Autowired
	private IUserService userService;
	@GetMapping("user/{userId}")
	public TUser getUser(@PathVariable String userId) {
		System.out.println("hello");
		return userService.getById(userId);
	}
}
