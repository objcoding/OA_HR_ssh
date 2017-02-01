package cn.edu.gcu.oa.test;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller(value = "testAction")
@Scope("prototype")
public class TestAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "testService")
	private TestService testService;

	@Override
	public String execute() throws Exception {
		System.out.println("测试Action");
		testService.test1();
		return SUCCESS;
	}
}
