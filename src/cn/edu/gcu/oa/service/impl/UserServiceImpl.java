package cn.edu.gcu.oa.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import cn.edu.gcu.oa.base.DaoSupportImpl;
import cn.edu.gcu.oa.entity.User;
import cn.edu.gcu.oa.service.UserService;

@Service
//事务已经从DaoSupportImpl继承过来了无需再定义
//@Transactional
public class UserServiceImpl extends DaoSupportImpl<User> implements UserService {

	@Override
	public User findByLoginNameAndPassword(String loginName, String password) {
		
		String md5Digest = DigestUtils.md5Hex(password);
		return (User)getSession().createQuery("FROM User u WHERE u.loginName=? AND u.password=?")//
					.setParameter(0, loginName)//
					.setParameter(1, md5Digest)//
					.uniqueResult();//
	}
	
}
