package com.luhao.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luhao.dao.IUserDao;
import com.luhao.entity.TUser;
import com.luhao.service.IUserService;
@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	private IUserDao userDao;
	@Override
	public TUser getById(String userId) {
		return userDao.findById(userId);
	}
	@Override
	public TUser getByNameAndPwd(String userName, String password) {
		return userDao.findEntityBySQL("select * from t_user where user_name = ? and user_password = ?", userName,password);
	}
	@Override
	public Set<String> getPowerByName(String userId) {
		return userDao.getPower(userId);
	}

}
