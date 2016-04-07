package com.framework.auto.basicentity;

import java.util.List;

/**
* Interface object for domain model class Operations.
* @see com.framework.auto.basicentity.Operations
* @author Calvin. Sun
* Generated 2010-06-01 17:13:18
*/
public interface IOperationsDao {

	public void save(Operations object);
	public void saveOrUpdate(Operations object);
	public void delete(Operations object);
	public Operations findById(int id);
	public List query(String hql);
	public List<Operations> pager(String hql,int pageIndex);
	public int execute(String hql);
	public List<Operations> search(String hql,String[]params,String values[]);
	public List<Operations> search(String hql,int pageIndex);
	public List<Operations> search(String hql,String[]params,String values[],int pageIndex);

}

