package cn.edu.gcu.oa.web.action;

import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.edu.gcu.oa.base.BaseAction;
import cn.edu.gcu.oa.entity.Privilege;
import cn.edu.gcu.oa.entity.Role;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {
	private static final long serialVersionUID = 1L;
	
	//封装权限数据id
	private Long[] privilegeIds;

	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
		roleService.delete(model.getId());
		
		return "toList";
	}
	
	/**
	 * 添加
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		roleService.save(model);
		
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
		roleService.update(model);
		
		return "toList";
	}

	/**
	 * 修改页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String editUI() throws Exception {
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role);
		
		return "saveUI";
	}

	/**
	 * 设置权限页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String setPrivilegeUI() throws Exception {
		//回显自身数据
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role);
		
		//回显权限属性
		if (role.getPrivileges() != null) {
			privilegeIds = new Long[role.getPrivileges().size()];
			int index = 0;
			for (Privilege priv : role.getPrivileges()) {
				privilegeIds[index ++] = priv.getId();// 已封装到action属性保存到栈值中了
			}
		}
		
		// 准备数据 privilegeList
		List<Privilege> privilegeList = privilegeService.findAll();
		ActionContext.getContext().put("privilegeList", privilegeList); 

		return "setPrivilegeUI";
	}

	/**
	 * 设置权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String setPrivilege() throws Exception {
		//取原对象
		Role role = roleService.getById(model.getId());
		//设置权限关联
		List<Privilege> privileges = privilegeService.getByIds(privilegeIds);
		role.setPrivileges(new HashSet<Privilege>(privileges));
		roleService.update(role);

		return "toList";
	}
}
