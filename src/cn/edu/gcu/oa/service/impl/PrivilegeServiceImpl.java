package cn.edu.gcu.oa.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.edu.gcu.oa.base.DaoSupportImpl;
import cn.edu.gcu.oa.entity.Privilege;
import cn.edu.gcu.oa.service.PrivilegeService;

@Service
// 事务已经从DaoSupportImpl继承过来了无需再定义
// @Transactional
@SuppressWarnings("unchecked")
public class PrivilegeServiceImpl extends DaoSupportImpl<Privilege> implements PrivilegeService {

	@Override
	public List<Privilege> findTopList() {
		return (List<Privilege>) getSession().createQuery("FROM Privilege p WHERE p.parent IS NULL")//
				.setFirstResult(0)//
				.setMaxResults(3)//设置只显示已做的顶级板块
				.list();//
	}

	@Override
	public Collection<String> getAllPrivilegeUrls() {
		return (Collection<String>) getSession()
				.createQuery("SELECT p.url FROM Privilege p WHERE p.url IS NOT NULL").list();
	}
}
