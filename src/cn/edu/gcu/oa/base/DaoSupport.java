package cn.edu.gcu.oa.base;

import java.util.List;

public interface DaoSupport<T> {

	/**
	 * 保存实体
	 * 
	 * @param entity
	 * @return
	 */
	void save(T entity);

	/**
	 * 删除实体
	 * 
	 * @param id
	 */
	void delete(Long id);

	/**
	 * 更新实体
	 * 
	 * @param entity
	 */
	void update(T entity);

	/**
	 * 查询实体
	 * 
	 * @param id
	 * @return
	 */
	T getById(Long id);
	
	/**
	 * 查询多个实体
	 * 
	 * @param ids
	 * @return
	 */
	List<T> getByIds(Long[] ids);

	/**
	 * 查询所有实体
	 * 
	 * @return
	 */
	List<T> findAll();
}
