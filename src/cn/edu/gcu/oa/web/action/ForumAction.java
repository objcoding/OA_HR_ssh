package cn.edu.gcu.oa.web.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.edu.gcu.oa.base.BaseAction;
import cn.edu.gcu.oa.entity.Forum;
import cn.edu.gcu.oa.entity.PageBean;

@Controller
@Scope("prototype")
public class ForumAction extends BaseAction<Forum> {
	private static final long serialVersionUID = 1L;
	
	//封装分页信息
	private int pageNum = 1;//当前页,如果页面不传当前页,就默认第一页
	private int pageSize = 3;//每页显示数目
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
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
		// List<Topic> topics = topicService.findByForum(forum);
		// ActionContext.getContext().put("topicList", topics);
		
		//分页准备主题数据:topics
		PageBean pageBean = topicService.getPageBeanByForum(pageNum, pageSize, forum);
		ActionContext.getContext().getValueStack().push(pageBean);
		
		return  "show";
	}
}
