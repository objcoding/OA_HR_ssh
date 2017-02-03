package cn.edu.gcu.oa.web.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.edu.gcu.oa.base.BaseAction;
import cn.edu.gcu.oa.entity.Department;
import cn.edu.gcu.oa.util.DepartmentUtils;

@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department> {
	private static final long serialVersionUID = 1L;

	// 封装parentId
	private Long parentId;
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		List<Department> departments = null;
		if (parentId == null) {// 显示顶级部门
			departments = departmentService.findTopList();
		} else {// 显示下级部门
			departments = departmentService.findChildren(parentId);
			// 用于返回上一级,取到上一级部门对象
			Department department = departmentService.getById(parentId);
			ActionContext.getContext().put("parent", department);
		}
		ActionContext.getContext().put("departmentList", departments);
		
		return "list";
	}

	/**
	 * 删除
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		departmentService.delete(model.getId());
		
		return "toList";
	}

	/**
	 * 添加
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		Department parent = departmentService.getById(parentId);
		model.setParent(parent);
		departmentService.save(model);
		
		return "toList";
	}

	/**
	 * 添加页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception {
		// 要显示部门列表,用于设置上级部门
		// List<Department> departmentList = departmentService.findAll();
		
		// 准备部门列表
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);//把部门显示效果改为树状结构
		ActionContext.getContext().put("departmentList", departmentList);
		
		return "saveUI";
	}

	/**
	 * 修改
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		// 取出原对象
		Department department = departmentService.getById(model.getId());
		//设置修改的数据
		department.setName(model.getName());
		department.setDescription(model.getDescription());
		department.setParent(departmentService.getById(parentId));
		
		departmentService.update(department);

		return "toList";
	}

	/**
	 * 修改页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String editUI() throws Exception {
		// 要显示部门列表,用于设置上级部门
		// List<Department> departmentList = departmentService.findAll();
		// ActionContext.getContext().put("departmentList", departmentList);
		
		//准备部门列表
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);//把部门显示效果改为树状结构
		ActionContext.getContext().put("departmentList", departmentList);
		
		// 回显本部门信息
		Department _department = departmentService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(_department);
		
		//设置默认值,由于model并未封装parentId,需手动提取出来在回显
		if (_department.getParent() != null) {
			parentId = _department.getParent().getId();// 已存到本类parentId属性中,也就是存到栈值中了
		}

		return "saveUI";
	}
}
