package cn.edu.gcu.oa.service;

import cn.edu.gcu.oa.base.DaoSupport;
import cn.edu.gcu.oa.entity.User;

/**
 * 用户业务逻辑层
 * @author zch
 *
 */
public interface UserService extends DaoSupport<User> {

	/**
	 * 用户登录验证
	 * @param loginName
	 * @param password
	 * @return
	 */
	User findByLoginNameAndPassword(String loginName, String password);

}
