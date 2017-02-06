package cn.edu.gcu.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.edu.gcu.oa.base.DaoSupportImpl;
import cn.edu.gcu.oa.entity.Forum;
import cn.edu.gcu.oa.entity.PageBean;
import cn.edu.gcu.oa.entity.Reply;
import cn.edu.gcu.oa.entity.Topic;
import cn.edu.gcu.oa.service.ReplyService;

@Service
//事务已经从DaoSupportImpl继承过来了无需再定义
//@Transactional
public class ReplyServiceImpl extends DaoSupportImpl<Reply> implements ReplyService {

	@Override
	public void save(Reply reply) {
		// 1，保存
		getSession().save(reply);

		// 2，维护相关的信息
		Topic topic = reply.getTopic();
		Forum forum = topic.getForum();

		forum.setArticleCount(forum.getArticleCount() + 1); // 文章数量（主题数+回复数）
		topic.setReplyCount(topic.getReplyCount() + 1); // 回复数量
		topic.setLastReply(reply); // 最后发表的回复
		topic.setLastUpdateTime(reply.getPostTime()); // 最后更新时间（主题的发表时间或最后回复的时间）

		getSession().update(topic);
		getSession().update(forum);	
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Reply> findByTopic(Topic topic) {
		return (List<Reply>)getSession().createQuery("FROM Reply r WHERE r.topic=? ORDER BY r.postTime")
				.setParameter(0, topic)
				.list();
	}


	@SuppressWarnings("rawtypes")
	@Override
	public PageBean getPageBeanByTopic(int pageNum, int pageSize, Topic topic) {
		
		//按每页显示数查询回复文章
		List recordList = getSession().createQuery("FROM Reply r WHERE r.topic=? ORDER BY r.postTime")
				.setParameter(0, topic)
				.setFirstResult((pageNum -1) * pageSize)
				.setMaxResults(pageSize)
				.list();
		
		//查询所属主题的回复数量
		Long recordCount = (Long) getSession()
				.createQuery("SELECT COUNT(*) FROM Reply r WHERE r.topic=?")
				.setParameter(0, topic)
				.uniqueResult();
		
		//返回一个pageBean
		return new PageBean(pageNum, pageSize, recordCount.intValue(), recordList);
	}
}
