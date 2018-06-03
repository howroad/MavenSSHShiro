package com.luhao.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.luhao.dao.BasicDaoAdapter;
import com.luhao.dao.IUserDao;
import com.luhao.entity.TUser;
@Repository
public class UserDaoImpl extends BasicDaoAdapter<TUser, String> implements IUserDao{

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> getPower(String username) {
		String sql = "select t_power.power_desc from t_power "
				+ "join t_role_power on t_role_power.power_id = t_power.power_id "
				+ "join t_role on t_role.role_id = t_role_power.role_id "
				+ "join t_user_role on t_user_role.role_id = t_role.role_id "
				+ "join t_user on t_user.user_id = t_user_role.user_id "
				+ "where t_user.user_name = ?";
		Session session = sessionFactory.getCurrentSession();
		List<String> list = session.createNativeQuery(sql).setParameter(1, username).list();
		return new HashSet<String>(list);
	}

}
