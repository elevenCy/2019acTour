package com.winter.service.impl;

import com.winter.common.Page;
import com.winter.mapper.BaseDao;
import com.winter.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class BaseServiceImp<T> implements BaseService<T> {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	protected BaseDao<T> baseDao;

	@Override
	public void insert(T o) {
		baseDao.insert(o);
	}

	@Override
	public void delete(T o) {
		baseDao.delete(o);
	}
	
	@Override
	public void deleteBatch(List<T> os){
		baseDao.deleteBatch(os);
	}

	@Override
	public void update(T o) {
		baseDao.update(o);
	}

	@Override
	public List<T> find(T o) {
		return baseDao.find(o);
	}

	@Override
	public Page<T> findByPage(T o, Page<T> page) {
		page.setResults(baseDao.findByPage(o, page));
		return page;
	}

	@Override
	public int count(T o) {
		return baseDao.count(o);
	}

	@Override
	public List<T> findBySql(String sql) {
		return baseDao.findBySql(sql);
	}

	@Override
	public Integer updateBySql(String sql) {
		return baseDao.updateBySql(sql);
	}

	@Override
	public T findObjBySql(String sql) {
		return baseDao.findObjBySql(sql);
	}
}
