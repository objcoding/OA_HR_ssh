package cn.edu.gcu.oa.service;

import java.util.List;

import cn.edu.gcu.oa.base.DaoSupport;
import cn.edu.gcu.oa.entity.Forum;
import cn.edu.gcu.oa.entity.PageBean;
import cn.edu.gcu.oa.entity.Topic;

/**
 * 主题业务逻辑层
 * @author zch
 *
 */
public interface TopicService extends DaoSupport<Topic> {

	/**
	 * 根据版块查找主题
	 * @param forum
	 * @return
	 */
	List<Topic> findByForum(Forum forum);

	/**
	 * 根据版块查找主题(分页)
	 * @param pageNum
	 * @param pageSize
	 * @param forum
	 * @return
	 */
	PageBean getPageBeanByForum(int pageNum, int pageSize, Forum forum);

	
}
