package cn.edu.gcu.oa.service;

import java.util.List;

import cn.edu.gcu.oa.base.DaoSupport;
import cn.edu.gcu.oa.entity.Reply;
import cn.edu.gcu.oa.entity.Topic;

public interface ReplyService extends DaoSupport<Reply> {

	List<Reply> findByTopic(Topic topic);

	
}
