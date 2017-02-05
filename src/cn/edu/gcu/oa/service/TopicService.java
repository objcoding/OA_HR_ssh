package cn.edu.gcu.oa.service;

import java.util.List;

import cn.edu.gcu.oa.base.DaoSupport;
import cn.edu.gcu.oa.entity.Forum;
import cn.edu.gcu.oa.entity.Topic;

public interface TopicService extends DaoSupport<Topic> {

	List<Topic> findByForum(Forum forum);

	
}
