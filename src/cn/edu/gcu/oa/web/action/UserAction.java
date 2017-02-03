package cn.edu.gcu.oa.web.action;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.edu.gcu.oa.base.BaseAction;
import cn.edu.gcu.oa.entity.Department;
import cn.edu.gcu.oa.entity.Role;
import cn.edu.gcu.oa.entity.User;
import cn.edu.gcu.oa.util.DepartmentUtils;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	private static final long serialVersionUID = 1L;

	// 属性封装
	private Long departmentId;// 封装所属部门id
	private Long[] roleIds;// 封装所属岗位列表

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

	/**
	 * 用户列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		ActionContext.getContext().put("userList", userService.findAll());

		return "list";
	}

	/**
	 * 删除用户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		userService.delete(model.getId());

		return "toList";
	}

	/**
	 * 添加页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception {
		// 准备部门列表
		List<Department> topList = departmentService.findTopList();// 获得所有顶级部门
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);// 把部门显示效果改为树状结构
		ActionContext.getContext().put("departmentList", departmentList);
		
		// 准备岗位列表
		ActionContext.getContext().put("roleList", roleService.findAll());

		return "saveUI";
	}

	/**
	 * 添加
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		// 添加前需要把表单未提交即未封装的属性补全
		model.setDepartment(departmentService.getById(departmentId));// 补全部门
		
		// 设置关联的岗位
		List<Role> roleList = roleService.getByIds(roleIds);
		model.setRoles(new HashSet<Role>(roleList));
		
		// 设置默认密码为123（要使用MD5摘要）
		String md5Digest = DigestUtils.md5Hex("123");
		model.setPassword(md5Digest);
		
		userService.save(model);

		return "toList";
	}

	/**
	 * 修改页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String editUI() throws Exception {

		// 准备部门列表
		List<Department> topList = departmentService.findTopList();// 获取所有顶级部门
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);// 把部门显示效果改为树状结构
		ActionContext.getContext().put("departmentList", departmentList);

		// 准备岗位列表
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);

		// 回显自身数据信息
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);// 保存到值栈中

		// 根据自身属性,设置默认值,由于user并封装这些属性,需手动封装到action属性中
		if (user.getDepartment() != null) {
			departmentId = user.getDepartment().getId();// 封装所属部门id
		}
		if (user.getRoles() != null) {
			roleIds = new Long[user.getRoles().size()];
			int index = 0;
			for (Role role : user.getRoles()) {
				roleIds[index++] = role.getId();// 封装岗位id
			}
		}

		return "saveUI";
	}

	/**
	 * 修改
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		// 获取原对象
		User user = userService.getById(model.getId());

		// 设置要修改的属性
		user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		user.setGender(model.getGender());
		user.setPhoneNumber(model.getPhoneNumber());
		user.setEmail(model.getEmail());
		user.setDescription(model.getDescription());
		// 设置所属部门
		user.setDepartment(departmentService.getById(departmentId));
		// 设置关联的岗位
		List<Role> roleList = roleService.getByIds(roleIds);
		user.setRoles(new HashSet<Role>(roleList));

		// 更新到数据库
		userService.update(user);

		return "toList";
	}

	/**
	 * 初始化密码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initPassword() throws Exception {
		// 获取原对象
		User user = userService.getById(model.getId());
		//使用md5摘要
		String md5Digest = DigestUtils.md5Hex("123");
		user.setPassword(md5Digest);
		// 更新到数据库
		userService.update(user);
		
		return "toList";
	}
}
