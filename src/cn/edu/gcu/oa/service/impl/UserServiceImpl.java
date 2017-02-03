package cn.edu.gcu.oa.service.impl;

import org.springframework.stereotype.Service;

import cn.edu.gcu.oa.base.DaoSupportImpl;
import cn.edu.gcu.oa.entity.User;
import cn.edu.gcu.oa.service.UserService;

@Service
//事务已经从DaoSupportImpl继承过来了无需再定义
//@Transactional
public class UserServiceImpl extends DaoSupportImpl<User> implements UserService {
	
}
