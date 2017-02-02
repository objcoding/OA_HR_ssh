package cn.edu.gcu.oa.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.edu.gcu.oa.entity.Department;
import cn.edu.gcu.oa.service.DepartmentService;

@Controller
@Scope("prototype")
public class DepartmentAction extends ActionSupport implements ModelDriven<Department> {

	private static final long serialVersionUID = 1L;

	@Resource(name = "departmentServiceImpl")
	private DepartmentService departmentService;

	// 把请求参数封装成Department对象
	Department modelDpartment = new Department();
	@Override
	public Department getModel() {
		return modelDpartment;
	}
	
	//封装parentId
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
		List<Department> departments = departmentService.findAll();
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
		departmentService.delete(modelDpartment.getId());
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
		modelDpartment.setParent(parent);
		departmentService.save(modelDpartment);
		return "toList";
	}

	/**
	 * 添加页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception {
		//要显示部门列表,用于设置上级部门
		List<Department> departmentList = departmentService.findAll();
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
		//取出原对象
		Department department = departmentService.getById(modelDpartment.getId());
		
		department.setName(modelDpartment.getName());
		department.setDescription(modelDpartment.getDescription());
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
		List<Department> departmentList = departmentService.findAll();
		ActionContext.getContext().put("departmentList", departmentList);
		//数据回显
		Department _department = departmentService.getById(modelDpartment.getId());
		ActionContext.getContext().getValueStack().push(_department);
		if (_department.getParent() != null) {
			parentId = _department.getParent().getId();//已存到本类parentId属性中,也就是存到栈值中了
		}

		return "saveUI";
	}
}
