package cn.edu.gcu.oa.service;

import java.util.Collection;
import java.util.List;

import cn.edu.gcu.oa.base.DaoSupport;
import cn.edu.gcu.oa.entity.Privilege;

public interface PrivilegeService extends DaoSupport<Privilege> {

	List<Privilege> findTopList();

	Collection<String> getAllPrivilegeUrls();

}
