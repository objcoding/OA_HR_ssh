package cn.edu.gcu.oa.test;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestHibernate {
	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");

	// 测试sessionFactory
	@Test
	public void testSessionFactory() {
		SessionFactory sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");
		System.out.println(sessionFactory);
	}

	// 测试事务
	@Test
	public void testService() {
		TestService testTransaction = (TestService) applicationContext.getBean("testService");
		testTransaction.test1();
	}
}
