package com.luhao.dao;

import java.util.Set;

import com.luhao.entity.TUser;

public interface IUserDao extends IBasicDao<TUser, String>{
	public Set<String> getPower(String userId);
}
