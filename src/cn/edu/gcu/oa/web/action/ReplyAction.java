package cn.edu.gcu.oa.web.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.edu.gcu.oa.base.BaseAction;
import cn.edu.gcu.oa.entity.Reply;
import cn.edu.gcu.oa.entity.Topic;

@Controller
@Scope("prototype")
public class ReplyAction extends BaseAction<Reply> {

	private static final long serialVersionUID = 1L;
	
	private Long topicId;
	public Long getTopicId() {
		return topicId;
	}
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
	
	/**
	 * 发表新回复页面
	 * @return
	 */
	public String addUI(){
		//数据准备:topic
		Topic topic = topicService.getById(topicId);
		ActionContext.getContext().put("topic", topic);
		
		return "addUI";
	}
	
	/**
	 * 发表新回复
	 * @return
	 */
	public String add(){
		// 封装
		// >> 表单字段，已经封装了title, content
		// model.setTitle(title);
		// model.setContent(content);
		model.setTopic(topicService.getById(topicId));
		// >> 当前信息
		model.setAuthor(getCurrentUser()); // 当前用户
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		model.setPostTime(new Date()); // 当前时间

		replyService.save(model);

		return "toTopicShow"; // 转到新回复所在主题的显示页面,调用TopicAction的show方法,可显示topic和replyList内容
	}
}
