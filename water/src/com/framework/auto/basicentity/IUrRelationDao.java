package com.framework.auto.basicentity;

import java.util.List;

/**
* Interface object for domain model class UrRelation.
* @see com.framework.auto.basicentity.UrRelation
* @author Calvin. Sun
* Generated 2010-06-01 17:13:19
*/
public interface IUrRelationDao {

	public void save(UrRelation object);
	public void saveOrUpdate(UrRelation object);
	public void delete(UrRelation object);
	public UrRelation findById(int id);
	public List query(String hql);
	public List<UrRelation> pager(String hql,int pageIndex);
	public int execute(String hql);
	public List<UrRelation> search(String hql,String[]params,String values[]);
	public List<UrRelation> search(String hql,int pageIndex);
	public List<UrRelation> search(String hql,String[]params,String values[],int pageIndex);

}

