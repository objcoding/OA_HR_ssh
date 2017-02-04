package cn.edu.gcu.oa.web.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import cn.edu.gcu.oa.entity.User;

public class CheckPrivilegeInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		User user = (User) ActionContext.getContext().getSession().get("user");
		
		String actionSpace = invocation.getProxy().getNamespace();
		String actionName = invocation.getProxy().getActionName();
		String privUrl = actionSpace + actionName;//对应请求的url
		
		//System.out.println(privUrl);//例如:/department_list
		
		//未登录
		if (user == null) {
			if (privUrl.startsWith("/user_login")) { // "/user_loginUI", "/user_login"
				// 如果是去登录，就放行
				return invocation.invoke();
			} else {
				// 如果不是去登录，就转到登录页面
				return "loginUI";
			}
		}
		
		//已登录
		else{
			if (user.hasPrivilegeByUrl(privUrl)) {//有权限,放行
				return invocation.invoke();
			}else {
				return "noPrivilegeError";
			}
		}
	}
}
