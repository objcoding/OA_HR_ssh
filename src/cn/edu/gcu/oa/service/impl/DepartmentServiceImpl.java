package cn.edu.gcu.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.edu.gcu.oa.base.DaoSupportImpl;
import cn.edu.gcu.oa.entity.Department;
import cn.edu.gcu.oa.service.DepartmentService;

@Service
//事务已经从DaoSupportImpl继承过来了无需再定义
//@Transactional
@SuppressWarnings("unchecked")
public class DepartmentServiceImpl extends DaoSupportImpl<Department> implements DepartmentService {
	
	//sessionFactory已经从DaoSupportImpl继承过来了,无需再定义
	// @Resource
	// private SessionFactory sessionFactory;
	
	public List<Department> findTopList() {
		return sessionFactory.getCurrentSession()
				.createQuery("From Department d where d.parent is NULL")
				.list();
	}

	public List<Department> findChildren(Long parentId) {
		return sessionFactory.getCurrentSession()
				.createQuery("From Department d where d.parent.id=?")
				.setParameter(0, parentId)
				.list();
	}
}
