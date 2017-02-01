package cn.edu.gcu.oa.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.gcu.oa.base.BaseServiceImpl;
import cn.edu.gcu.oa.entity.Role;
import cn.edu.gcu.oa.service.RoleService;

@Service(value = "roleService")
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

}
