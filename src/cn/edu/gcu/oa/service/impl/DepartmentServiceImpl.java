package cn.edu.gcu.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.gcu.oa.base.BaseServiceImpl;
import cn.edu.gcu.oa.dao.DepartmentDao;
import cn.edu.gcu.oa.entity.Department;
import cn.edu.gcu.oa.service.DepartmentService;

@Service
@Transactional
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements DepartmentService {
	
	@Resource(name = "departmentDaoImpl")
	private DepartmentDao departmentDao;
	
	@Override
	public List<Department> findAll() {
		return departmentDao.findAll();
	}

	@Override
	public void delete(Long id) {
		departmentDao.delete(id);
	}

	@Override
	public Department getById(Long id) {
		return departmentDao.getById(id);
	}

	@Override
	public void update(Department department) {
		departmentDao.update(department);
	}

	@Override
	public void save(Department department) {
		departmentDao.save(department);
	}
}
