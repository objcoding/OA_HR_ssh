package cn.edu.gcu.oa.service;

import java.util.List;

import cn.edu.gcu.oa.base.DaoSupport;
import cn.edu.gcu.oa.entity.Department;

/**
 * 部门业务逻辑层
 * @author zch
 *
 */
public interface DepartmentService extends DaoSupport<Department> {

	/**
	 * 查找所有顶级部门
	 * @return
	 */
	List<Department> findTopList();

	/**
	 * 查找所有下级部门
	 * @param parentId
	 * @return
	 */
	List<Department> findChildren(Long parentId);

}
