package cn.edu.gcu.oa.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.edu.gcu.oa.service.DepartmentService;
import cn.edu.gcu.oa.service.PrivilegeService;
import cn.edu.gcu.oa.service.RoleService;
import cn.edu.gcu.oa.service.UserService;

/**
 * 显示层抽取BaseAction
 * @author zch
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	private static final long serialVersionUID = 1L;
	
	//通过反射创建T类型实例
	public BaseAction(){
		try {
			// 获取当前new的对象的 泛型的父类 类型
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass(); 
			// 获取第一个类型参数的真实类型;
			Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
			model = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/***************** modelDriven ******************************/
	protected T model;
	@Override
	public T getModel() {
		return model;
	}
	
	/***************** 业务逻辑层 ******************************/
	@Resource(name = "departmentServiceImpl")
	protected DepartmentService departmentService;
	@Resource(name = "roleServiceImpl")
	protected RoleService roleService;
	@Resource(name = "userServiceImpl")
	protected UserService userService;
	@Resource(name = "privilegeServiceImpl")
	protected PrivilegeService privilegeService;
	
}
