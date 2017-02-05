package cn.edu.gcu.oa.service;

import cn.edu.gcu.oa.base.DaoSupport;
import cn.edu.gcu.oa.entity.Forum;

public interface ForumManageService extends DaoSupport<Forum> {

	void moveUp(Long id);

	void moveDown(Long id);

}
