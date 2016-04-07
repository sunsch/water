package com.framework.auto.basicentity;

import java.util.List;
import com.framework.auto.consts.Consts;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
* Dao object for domain model class UrRelation.
* @see com.framework.auto.basicentity.UrRelation
* @author Calvin. Sun
* Generated 2010-06-01 17:29:17
*/
public class UrRelationDao extends HibernateDaoSupport implements IUrRelationDao {

private static final Logger l=Logger.getLogger(UrRelationDao.class);

	public void save(UrRelation object){
		getHibernateTemplate().save(object);
	}


	public void saveOrUpdate(UrRelation object){
		getHibernateTemplate().saveOrUpdate(object);
	}


	public void delete(UrRelation object){
		getHibernateTemplate().delete(object);
	}


	public UrRelation findById(int id){
		UrRelation instance = (UrRelation) getHibernateTemplate().get("com.framework.auto.basicentity.UrRelation", id);
		return instance;
	}


	@SuppressWarnings("unchecked")
	public List query(String hql){
		Query q=getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		return q.list();
	}


	@SuppressWarnings("unchecked")
	public List<UrRelation> pager(String hql,int pageIndex){
		Query q=getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		q.setFirstResult(pageIndex*Consts.ITEMS_PER_PAGE);
		q.setMaxResults(Consts.ITEMS_PER_PAGE);
		return q.list();
	}


	public int execute(String hql){
		Query q=getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		return q.executeUpdate();
	}


	@SuppressWarnings("unchecked")
	public List<UrRelation> search(String hql,String[]params,String values[]){
		Query q=getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		for(int i=0;i<params.length;i++)
		{
				q.setParameter(params[i],"%"+values[i]+"%");
		}
		return q.list();
	}


	@SuppressWarnings("unchecked")
	public List<UrRelation> search(String hql,int pageIndex){
		Query q=getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		q.setFirstResult(pageIndex*Consts.ITEMS_PER_PAGE);
		q.setMaxResults(Consts.ITEMS_PER_PAGE);
		return q.list();
	}


	@SuppressWarnings("unchecked")
	public List<UrRelation> search(String hql,String[]params,String values[],int pageIndex){
		Query q=getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		for(int i=0;i<params.length;i++)
		{
				q.setParameter(params[i],"%"+values[i]+"%");
		}
		q.setFirstResult(pageIndex*Consts.ITEMS_PER_PAGE);
		q.setMaxResults(Consts.ITEMS_PER_PAGE);
		return q.list();
	}


}

