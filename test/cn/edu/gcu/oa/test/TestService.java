package cn.edu.gcu.oa.test;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.gcu.oa.entity.User;


@Service
public class TestService {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Transactional
	public void test1() {
		Session session = sessionFactory.getCurrentSession();
		User user = new User();
		user.setName("整合成功!");
		// int a = 10/0;
		session.save(user);
	}
}
