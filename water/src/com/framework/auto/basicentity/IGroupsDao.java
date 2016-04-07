package com.framework.auto.basicentity;

import java.util.List;

/**
* Interface object for domain model class Groups.
* @see com.framework.auto.basicentity.Groups
* @author Calvin. Sun
* Generated 2010-06-01 17:00:19
*/
public interface IGroupsDao {

	public void save(Groups object);
	public void saveOrUpdate(Groups object);
	public void delete(Groups object);
	public Groups findById(int id);
	public List query(String hql);
	public List<Groups> pager(String hql,int pageIndex);
	public int execute(String hql);
	public List<Groups> search(String hql,String[]params,String values[]);
	public List<Groups> search(String hql,int pageIndex);
	public List<Groups> search(String hql,String[]params,String values[],int pageIndex);

}

