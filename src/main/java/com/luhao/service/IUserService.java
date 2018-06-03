package com.luhao.service;

import java.util.Set;

import com.luhao.entity.TUser;

public interface IUserService {
	public TUser getById(String userId);
	public TUser getByNameAndPwd(String userName,String password);
	public Set<String> getPowerByName(String userName);
}
