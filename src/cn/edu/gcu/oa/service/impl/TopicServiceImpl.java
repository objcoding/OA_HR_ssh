package cn.edu.gcu.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.edu.gcu.oa.base.DaoSupportImpl;
import cn.edu.gcu.oa.entity.Forum;
import cn.edu.gcu.oa.entity.Topic;
import cn.edu.gcu.oa.service.TopicService;

@Service
//事务已经从DaoSupportImpl继承过来了无需再定义
//@Transactional
public class TopicServiceImpl extends DaoSupportImpl<Topic> implements TopicService {

	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> findByForum(Forum forum) {
		return (List<Topic>)getSession()
				/*
				 * 排序语句解释:2置顶1精华0普通,当主题类型为2时就是2,当主题类型为其它时就是0(为了普通贴和精华帖互不干扰)
				 * 然后以这个来降序,再用最后更新时间降序
				 */
		.createQuery("FROM Topic t WHERE t.forum=? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC, t.lastUpdateTime DESC")
		.setParameter(0, forum)
		.list();
	}
	
	//维护特殊属性
	@Override
	public void save(Topic topic) {
		
		//维护topic特殊属性
		topic.setType(Topic.TYPE_NORMAL); // 默认为普通帖
		topic.setReplyCount(0);
		topic.setLastReply(null);
		topic.setLastUpdateTime(topic.getPostTime());
		getSession().save(topic); // 保存
		
		//维护topic的forum特殊属性
		Forum forum = topic.getForum();
		forum.setTopicCount(forum.getTopicCount() + 1); // 主题数量
		forum.setArticleCount(forum.getArticleCount() + 1);// 文章数量（主题数+回复数）
		forum.setLastTopic(topic); // 最后发表的主题
		getSession().update(forum);
	}
	
}
