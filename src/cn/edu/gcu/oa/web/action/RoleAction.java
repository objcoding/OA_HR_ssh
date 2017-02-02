package cn.edu.gcu.oa.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.edu.gcu.oa.entity.Role;
import cn.edu.gcu.oa.service.RoleService;

@Controller
@Scope("prototype")
public class RoleAction extends ActionSupport implements ModelDriven<Role> {

	private static final long serialVersionUID = 1L;
	
	@Resource(name = "roleServiceImpl")
	private RoleService roleService;
	
	//把请求参数封装成Role对象
	Role role = new Role();
	@Override
	public Role getModel() {
		return role ;
	}

	/**
	 * 列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		List<Role> roles = roleService.findAll();
		ActionContext.getContext().put("roleList", roles);
		return "list";
	}

	/**
	 * 删除
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		roleService.delete(role.getId());
		return "toList";
	}
	
	/**
	 * 添加
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		roleService.save(role);
		return "toList";
	}

	/**
	 * 添加页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception {
		return "saveUI";
	}

	/**
	 * 修改
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		roleService.update(role);
		return "toList";
	}

	/**
	 * 修改页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String editUI() throws Exception {
		Role _role = roleService.getById(role.getId());
		ActionContext.getContext().getValueStack().push(_role);
		return "saveUI";
	}
}
