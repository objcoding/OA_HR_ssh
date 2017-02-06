package cn.edu.gcu.oa.service;

import cn.edu.gcu.oa.base.DaoSupport;
import cn.edu.gcu.oa.entity.Forum;

/**
 * 论坛版块管理业务逻辑层
 * @author zch
 *
 */
public interface ForumManageService extends DaoSupport<Forum> {

	/**
	 * 上移
	 * @param id
	 */
	void moveUp(Long id);

	/**
	 * 下移
	 * @param id
	 */
	void moveDown(Long id);

}
