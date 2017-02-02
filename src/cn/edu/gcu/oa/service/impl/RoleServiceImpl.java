package cn.edu.gcu.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.gcu.oa.base.BaseServiceImpl;
import cn.edu.gcu.oa.dao.RoleDao;
import cn.edu.gcu.oa.entity.Role;
import cn.edu.gcu.oa.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
	
	@Resource(name = "roleDaoImpl")
	private RoleDao roleDao;
	
	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}

	@Override
	public void delete(Long id) {
		roleDao.delete(id);
	}

	@Override
	public Role getById(Long id) {
		return roleDao.getById(id);
	}

	@Override
	public void update(Role role) {
		roleDao.update(role);
	}

	@Override
	public void save(Role role) {
		roleDao.save(role);
	}

}
