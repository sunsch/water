package com.framework.auto.basicentity;

import java.util.List;

/**
* Interface object for domain model class GrRelation.
* @see com.framework.auto.basicentity.GrRelation
* @author Calvin. Sun
* Generated 2010-06-01 17:13:18
*/
public interface IGrRelationDao {

	public void save(GrRelation object);
	public void saveOrUpdate(GrRelation object);
	public void delete(GrRelation object);
	public GrRelation findById(int id);
	public List query(String hql);
	public List<GrRelation> pager(String hql,int pageIndex);
	public int execute(String hql);
	public List<GrRelation> search(String hql,String[]params,String values[]);
	public List<GrRelation> search(String hql,int pageIndex);
	public List<GrRelation> search(String hql,String[]params,String values[],int pageIndex);

}

