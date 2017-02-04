package cn.edu.gcu.oa.util;

import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.edu.gcu.oa.entity.Privilege;
import cn.edu.gcu.oa.service.PrivilegeService;

public class InitListener implements ServletContextListener {
	public void contextInitialized(ServletContextEvent sce) {

		//从ContextContext中获取Spring容器
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		// 获取容器PrivilegeService对象
		PrivilegeService privilegeService = (PrivilegeService) applicationContext.getBean("privilegeServiceImpl");

		// 准备数据：topPrivilegeList
		List<Privilege> topPrivilegeList = privilegeService.findTopList();
		sce.getServletContext().setAttribute("topPrivilegeList", topPrivilegeList);
		System.out.println("------------> 已准备数据 <------------");
		
		// 准备数据：allPrivilegeUrls
		Collection<String> allPrivilegeUrls = privilegeService.getAllPrivilegeUrls();
		sce.getServletContext().setAttribute("allPrivilegeUrls", allPrivilegeUrls);
		System.out.println("------------> 已准备数据allPrivilegeUrls <------------");
	}

	public InitListener() {
	}

	public void contextDestroyed(ServletContextEvent arg0) {
	}
}
