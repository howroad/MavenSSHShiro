/**
 * 
 */
package com.luhao.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.luhao.util.PageBean;

/**
 * @author howroad
 * @Date 2018年4月26日
 * @version 1.0
 */
public abstract class BasicDaoAdapter<E, K extends Serializable> implements IBasicDao<E, K> {
	@Autowired
	protected SessionFactory sessionFactory;
	private Class<E> entityClass;

	@SuppressWarnings("unchecked")
	protected BasicDaoAdapter() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		entityClass = (Class<E>) pt.getActualTypeArguments()[0];
	}

	@SuppressWarnings("unchecked")
	@Override
	public K add(E e) {
		return (K) sessionFactory.getCurrentSession().save(e);
	}

	@Override
	public boolean delete(E e) {
		sessionFactory.getCurrentSession().delete(e);
		return true;
	}

	@Override
	public boolean update(E e) {
		sessionFactory.getCurrentSession().update(e);
		return true;
	}

	@Override
	public E findById(K id) {
		return sessionFactory.getCurrentSession().get(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageBean<E> findAllPage(int pageNo, int pageSize) {
		Session session = sessionFactory.getCurrentSession();
		List<E> list = session.createQuery("from " + entityClass.getSimpleName()).setMaxResults(pageSize)
				.setFirstResult((pageNo - 1) * pageSize).list();
		long totalRecord = (long) session.createQuery("select count(*) from " + entityClass.getSimpleName())
				.uniqueResult();
		return new PageBean<E>(list, pageNo, pageSize, new Long(totalRecord).intValue());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> findAllList() {
		return sessionFactory.getCurrentSession().createQuery("from " + entityClass.getSimpleName()).list();
	}

	@Override
	public boolean deleteByIds(Serializable... ids) {
		Session session = sessionFactory.getCurrentSession();
		for (Serializable id : ids) {
			session.delete(session.load(entityClass, id));
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E findEntityByHQL(String hql, Object... objs) {
		Query<E> query = sessionFactory.getCurrentSession().createQuery(hql);
		for (int i = 0; i < objs.length; i++) {
			query.setParameter(i, objs[i]);
		}
		return (E) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public E findEntityBySQL(String sql, Object... objs) {
		Query<E> query = sessionFactory.getCurrentSession().createNativeQuery(sql).addEntity(entityClass);
		for (int i = 0; i < objs.length; i++) {
			query.setParameter(i+1, objs[i]);
		}
		return (E) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> listByHQL(String hql, Object... objs) {
		Query<E> query = sessionFactory.getCurrentSession().createQuery(hql);
		for (int i = 0; i < objs.length; i++) {
			query.setParameter(i, objs[i]);
		}
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> listBySQL(String sql, Object... objs) {
		Query<E> query = sessionFactory.getCurrentSession().createNativeQuery(sql).addEntity(entityClass);
		for (int i = 0; i < objs.length; i++) {
			query.setParameter(i+1, objs[i]);
		}
		return query.list();
	}

	@Override
	public void addMany(Collection<E> collection) {
		Session session = sessionFactory.getCurrentSession();
		for (E e : collection) {
			session.save(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addMany(E... es) {
		Session session = sessionFactory.getCurrentSession();
		for (int i = 0; i < es.length; i++) {
			session.save(es[i]);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageBean<E> findAllPage(int pageNo, int pageSize, String sql, String sql2, Object... objs) {
		Session session = sessionFactory.getCurrentSession();
		Query<E> query = session.createNativeQuery(sql).addEntity(entityClass);
		// 设置参数
		for (int i = 0; i < objs.length; i++) {
			query.setParameter(i + 1, objs[i]);
		}
		// 获得结果集
		List<E> list = query.setMaxResults(pageSize).setFirstResult((pageNo - 1) * pageSize).list();
		// 获得该条件下总记录条数
		Query<BigInteger> query2 =  session.createNativeQuery(sql2);
		for (int i = 0; i < objs.length; i++) {
			query2.setParameter(i + 1, objs[i]);
		}
		int count = query2.uniqueResult().intValue();
		return new PageBean<E>(list, pageNo, pageSize, count);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int countBySQL(String sql,Object...objects) {
		Session session = sessionFactory.getCurrentSession();
		Query<BigInteger> query =  session.createNativeQuery(sql);
		for(int i =0;i<objects.length;i++) {
			query.setParameter(i+1, objects[i]);
		}
		return query.uniqueResult().intValue();
	}

}
