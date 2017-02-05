package cn.edu.gcu.oa.web.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.edu.gcu.oa.base.BaseAction;
import cn.edu.gcu.oa.entity.Forum;

@Controller
@Scope("prototype")
public class ForumManageAction extends BaseAction<Forum> {
	private static final long serialVersionUID = 1L;

	/** 列表 */
	public String list() throws Exception{
		List<Forum> forums = forumManageService.findAll();
		ActionContext.getContext().put("forumList", forums);
		return "list";
	}
	
	/** 删除 */
	public String delete() throws Exception{
		forumManageService.delete(model.getId());
		return "toList";
	}
	
	/** 添加页面 */
	public String addUI() throws Exception{
		
		return "saveUI";
	}
	
	/** 添加 */
	public String add() throws Exception{
		forumManageService.save(model);
		return "toList";
	}
	
	/** 修改页面 */
	public String editUI() throws Exception{
		
		//准备回显数据
		Forum forum = forumManageService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(forum);
		
		return "saveUI";
	}
	
	/** 修改 */
	public String edit() throws Exception{
		//取原对象
		Forum forum = forumManageService.getById(model.getId());
		//修改
		forum.setName(model.getName());
		forum.setDescription(model.getDescription());
		//更新
		forumManageService.update(forum);
		
		return "toList";
	}
	
	/** 上移 */
	public String moveUp() throws Exception{
		forumManageService.moveUp(model.getId());
		return "toList";
	}
	
	/** 下移 */
	public String moveDown() throws Exception{
		forumManageService.moveDown(model.getId());
		return "toList";
	}
}
