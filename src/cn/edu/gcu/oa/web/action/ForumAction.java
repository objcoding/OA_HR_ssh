package cn.edu.gcu.oa.web.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.edu.gcu.oa.base.BaseAction;
import cn.edu.gcu.oa.entity.Forum;
import cn.edu.gcu.oa.entity.Topic;

@Controller
@Scope("prototype")
public class ForumAction extends BaseAction<Forum> {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 显示所有版块
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		List<Forum> forums = forumService.findAll();
		ActionContext.getContext().put("forumList", forums);
		return  "list";
	}
	
	/**
	 * 显示当前版块所有主题
	 * @return
	 * @throws Exception
	 */
	public String show() throws Exception {
		
		//显示当前板块
		Forum forum = forumService.getById(model.getId());
		ActionContext.getContext().put("forum", forum);
		
		//显示当前版块所有的主题
		List<Topic> topics = topicService.findByForum(forum);
		ActionContext.getContext().put("topicList", topics);
		return  "show";
	}
}
