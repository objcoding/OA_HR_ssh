package cn.edu.gcu.oa.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

//@Transactional注解可以被继承
//@Transactional注解对父类中声明的方法无效
@Transactional
@SuppressWarnings("unchecked")
public class DaoSupportImpl<T> implements DaoSupport<T> {
	
	@Resource(name = "sessionFactory")
	protected SessionFactory sessionFactory;//使用protected以便子类可以继承
	private Class<T> clazz;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}	
	
	public DaoSupportImpl() {
		// 使用反射技术得到T的真实类型
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass(); // 获取当前new的对象的 泛型的父类 类型
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0]; // 获取第一个类型参数的真实类型
	}

	@Override
	public void save(T entity) {
		getSession().save(entity);
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
		if (ids == null || ids.length == 0) {//先判断数组是否为空
			return Collections.EMPTY_LIST;//此可以避免返回空指针异常
		} else {
			return getSession().createQuery(//:ids为占位符,与SQL的?N一样
					"FROM " + clazz.getSimpleName() + " WHERE id IN (:ids)")//clazz.getSimpleName()返回真实类型实例的名字
					.setParameterList("ids", ids)//设置参数
					.list();
			}
	}

	@Override
	public List<T> findAll() {
		return getSession().createCriteria(clazz).list();
	}
}
