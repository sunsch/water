package com.framework.auto.basicentity;

import java.util.List;

/**
* Interface object for domain model class UgRelation.
* @see com.framework.auto.basicentity.UgRelation
* @author Calvin. Sun
* Generated 2010-06-01 17:13:19
*/
public interface IUgRelationDao {

	public void save(UgRelation object);
	public void saveOrUpdate(UgRelation object);
	public void delete(UgRelation object);
	public UgRelation findById(int id);
	public List query(String hql);
	public List<UgRelation> pager(String hql,int pageIndex);
	public int execute(String hql);
	public List<UgRelation> search(String hql,String[]params,String values[]);
	public List<UgRelation> search(String hql,int pageIndex);
	public List<UgRelation> search(String hql,String[]params,String values[],int pageIndex);

}

