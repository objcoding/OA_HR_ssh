package cn.edu.gcu.oa.service;

import java.util.List;

import cn.edu.gcu.oa.base.BaseService;
import cn.edu.gcu.oa.entity.Role;

public interface RoleService extends BaseService<Role> {

	List<Role> findAll();

	void delete(Long id);

	Role getById(Long id);

	void update(Role role);

	void save(Role role);

}
