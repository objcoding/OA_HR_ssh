package cn.edu.gcu.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.edu.gcu.oa.base.DaoSupportImpl;
import cn.edu.gcu.oa.entity.Forum;
import cn.edu.gcu.oa.service.ForumManageService;

@Service
//事务已经从DaoSupportImpl继承过来了无需再定义
//@Transactional
public class ForumManageServiceImpl extends DaoSupportImpl<Forum> implements ForumManageService {

	@SuppressWarnings("unchecked")
	@Override
	public List<Forum> findAll() {
		return getSession().createQuery("FROM Forum f ORDER BY f.position").list();
	}
	
	@Override
	public void moveUp(Long id) {
		//要上移的forum对象
		Forum forum = getById(id);
		
		//上面的那个forum对象
		/*
		 * 搜索结果截至到要上移的forum的position-1位置(即upForum的position位置),
		 * 然后倒序,把upForum排到第一,然后利用分页查询第一个,取得upForum对象
		 */
		Forum upForum = (Forum)getSession()//
				.createQuery("From Forum f WHERE f.position<? ORDER BY f.position DESC")//
				.setParameter(0, forum.getPosition())//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.uniqueResult();//
									
		//互换position
		int temp = forum.getPosition();
		forum.setPosition(upForum.getPosition());
		upForum.setPosition(temp);
	}

	@Override
	public void moveDown(Long id) {
		//要下移的forum对象
		Forum forum = getById(id);
		
		//下面的那个forum对象
		/*
		 * 搜索结果截至到要下移的forum的position+1位置(即upForum的position位置),
		 * 按升序把downForum排到第一,然后利用分页查询第一个,取得upForum对象
		 */
		Forum downForum = (Forum)getSession()
				.createQuery("From Forum f WHERE f.position>? ORDER BY f.position ASC")//
				.setParameter(0, forum.getPosition())//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.uniqueResult();//
		
		//互换position
		int temp = forum.getPosition();
		forum.setPosition(downForum.getPosition());
		downForum.setPosition(temp);
	}
	
	@Override
	public void save(Forum forum) {
		//先保存,让实体自动生成主键
		super.save(forum);
		
		//让主键作为position值
		forum.setPosition(forum.getId().intValue());
	}
	
}
