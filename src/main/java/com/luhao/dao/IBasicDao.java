/**
 * 
 */
package com.luhao.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.luhao.util.PageBean;

/**
 * @author howroad
 * @Date 2018年4月26日
 * @version 1.0
 */
public interface IBasicDao<E, K extends Serializable> {

	public K add(E e);

	public void addMany(Collection<E> collection);

	public void addMany(@SuppressWarnings("unchecked") E... es);

	public boolean delete(E e);

	public boolean deleteByIds(Serializable... ids);

	public boolean update(E e);

	public E findById(K id);

	public PageBean<E> findAllPage(int pageNo, int pageSize);

	public PageBean<E> findAllPage(int pageNo, int pageSize, String sql, String sql2, Object... objs);

	public List<E> findAllList();

	// 各种byHQL,SQL
	public E findEntityByHQL(String hql, Object... objs);

	public E findEntityBySQL(String sql, Object... objs);

	public List<E> listByHQL(String hql, Object... objs);

	public List<E> listBySQL(String sql, Object... objs);
	
	public int countBySQL(String sql,Object...objs);
	

}
