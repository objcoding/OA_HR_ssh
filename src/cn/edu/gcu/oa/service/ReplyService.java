package cn.edu.gcu.oa.service;

import java.util.List;

import cn.edu.gcu.oa.base.DaoSupport;
import cn.edu.gcu.oa.entity.PageBean;
import cn.edu.gcu.oa.entity.Reply;
import cn.edu.gcu.oa.entity.Topic;

/**
 * 回复业务逻辑层
 * @author zch
 *
 */
public interface ReplyService extends DaoSupport<Reply> {

	/**
	 * 根据主题查找回复
	 * @param topic
	 * @return
	 */
	List<Reply> findByTopic(Topic topic);

	/**
	 * 根据主题查找
	 * @param pageNum
	 * @param pageSize
	 * @param topic
	 * @return
	 */
	PageBean getPageBeanByTopic(int pageNum, int pageSize, Topic topic);
}
