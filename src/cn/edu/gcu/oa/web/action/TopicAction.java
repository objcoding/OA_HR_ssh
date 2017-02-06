package cn.edu.gcu.oa.web.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.edu.gcu.oa.base.BaseAction;
import cn.edu.gcu.oa.entity.Forum;
import cn.edu.gcu.oa.entity.PageBean;
import cn.edu.gcu.oa.entity.Topic;

@Controller
@Scope("prototype")
public class TopicAction extends BaseAction<Topic> {
	private static final long serialVersionUID = 1L;
	
	//封装所属版块id
	private Long forumId; 
	public Long getForumId() {
		return forumId;
	}
	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}
	
	//封装分页相关参数
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
	 * 显示单个主题（主帖+回帖列表）
	 * @return
	 * @throws Exception
	 */
	public String show() throws Exception {
		// 准备数据：topic
		Topic topic = topicService.getById(model.getId());
		ActionContext.getContext().put("topic", topic);
		
		// 准备数据：replyList
		// List<Reply> replyList = replyService.findByTopic(topic);
		// ActionContext.getContext().put("replyList", replyList);
		
		//分页数据:replyList
		PageBean pageBean = replyService.getPageBeanByTopic(pageNum, pageSize, topic);
		ActionContext.getContext().getValueStack().push(pageBean);
		
		return "show";
	}
	
	/**
	 *  发表新主题 页面
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception {
		
		//准备版块信息
		Forum forum = forumService.getById(forumId);
		ActionContext.getContext().put("forum", forum);
		 return "addUI";
	}
	
	/**
	 *  发表新主题 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		// 封装
		// >> 表单参数，已经封装了title, content
		// model.setTitle(title);
		// model.setContent(content);
		model.setForum(forumService.getById(forumId));
		// >> 当前直接获取的信息
		model.setAuthor(getCurrentUser()); // 当前登录用户
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr()); // 当前请求中的IP
		model.setPostTime(new Date()); // 当前时间
	
		topicService.save(model);
	
		return "toShow";
	}
}
