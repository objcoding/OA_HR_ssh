package cn.edu.gcu.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.edu.gcu.oa.base.DaoSupportImpl;
import cn.edu.gcu.oa.entity.Forum;
import cn.edu.gcu.oa.service.ForumService;

@Service
//事务已经从DaoSupportImpl继承过来了无需再定义
//@Transactional
public class ForumServiceImpl extends DaoSupportImpl<Forum> implements ForumService {

	
	/**
	 * 按照坐标位置排序
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Forum> findAll() {
		return getSession().createQuery("FROM Forum f ORDER BY f.position").list();
	}
	
	
}
