package cn.edu.gcu.oa.service;

import java.util.List;

import cn.edu.gcu.oa.base.BaseService;
import cn.edu.gcu.oa.entity.Department;

public interface DepartmentService extends BaseService<Department> {

	List<Department> findAll();

	void delete(Long id);

	Department getById(Long id);

	void update(Department department);

	void save(Department department);

}
