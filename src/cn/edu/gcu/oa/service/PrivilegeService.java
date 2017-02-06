package cn.edu.gcu.oa.service;

import java.util.Collection;
import java.util.List;

import cn.edu.gcu.oa.base.DaoSupport;
import cn.edu.gcu.oa.entity.Privilege;

/**
 * 权限业务逻辑层
 * @author zch
 *
 */
public interface PrivilegeService extends DaoSupport<Privilege> {

	/**
	 * 查找所有顶级权限
	 * @return
	 */
	List<Privilege> findTopList();

	/**
	 * 查找所有权限
	 * @return
	 */
	Collection<String> getAllPrivilegeUrls();

}
