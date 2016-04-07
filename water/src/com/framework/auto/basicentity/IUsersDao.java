package com.framework.auto.basicentity;

import java.util.List;

/**
* Interface object for domain model class Users.
* @see com.framework.auto.basicentity.Users
* @author Calvin. Sun
* Generated 2010-06-01 17:13:19
*/
public interface IUsersDao {

	public void save(Users object);
	public void saveOrUpdate(Users object);
	public void delete(Users object);
	public Users findById(int id);
	public List query(String hql);
	public List<Users> pager(String hql,int pageIndex);
	public int execute(String hql);
	public List<Users> search(String hql,String[]params,String values[]);
	public List<Users> search(String hql,int pageIndex);
	public List<Users> search(String hql,String[]params,String values[],int pageIndex);

}

