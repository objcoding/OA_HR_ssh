package cn.edu.gcu.oa.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class BaseDaoImpl<T> implements BaseDao<T> {
	
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	private Class<T> clazz;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}	
	
	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		// 使用反射技术得到T的真实类型
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass(); // 获取当前new的对象的 泛型的父类 类型
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0]; // 获取第一个类型参数的真实类型
	}

	@Override
	public Serializable save(T entity) {
		return getSession().save(entity);
	}

	@Override
	public void delete(Long id) {
		T obj = getById(id);
		if (obj != null) {
			getSession().delete(obj);
		}
	}
	
	@Override
	public T getById(Long id) {
		if (id == null) {
			return null;
		}else {
			return (T) getSession().get(clazz, id);
		}
	}

	@Override
	public void update(T entity) {
		getSession().update(entity);
		
	}

	@Override
	public List<T> getByIds(Long[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAll() {
		return getSession().createCriteria(clazz).list();
	}

}
