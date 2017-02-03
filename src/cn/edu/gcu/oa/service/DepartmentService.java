package cn.edu.gcu.oa.service;

import java.util.List;

import cn.edu.gcu.oa.base.DaoSupport;
import cn.edu.gcu.oa.entity.Department;

public interface DepartmentService extends DaoSupport<Department> {

	List<Department> findTopList();

	List<Department> findChildren(Long parentId);

}
