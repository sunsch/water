package com.framework.auto.basicentity;

import java.util.List;

/**
* Interface object for domain model class Roles.
* @see com.framework.auto.basicentity.Roles
* @author Calvin. Sun
* Generated 2010-06-01 17:13:19
*/
public interface IRolesDao {

	public void save(Roles object);
	public void saveOrUpdate(Roles object);
	public void delete(Roles object);
	public Roles findById(int id);
	public List query(String hql);
	public List<Roles> pager(String hql,int pageIndex);
	public int execute(String hql);
	public List<Roles> search(String hql,String[]params,String values[]);
	public List<Roles> search(String hql,int pageIndex);
	public List<Roles> search(String hql,String[]params,String values[],int pageIndex);

}

