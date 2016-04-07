package com.exam.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.framework.auto.consts.Consts;

/**
* Dao object for domain model class Userlog.
* @see com.exam.entity.Userlog
* @author Calvin. Sun
* Generated 2010-06-06 11:48:42
*/
public class UserlogDao extends HibernateDaoSupport implements IUserlogDao {

private static final Logger l=Logger.getLogger(UserlogDao.class);


public void save(Object object){
	getHibernateTemplate().save(object);
}

	public void save(String entityName,Userlog object){
		getHibernateTemplate().save(entityName,object);
	}
	public void commonsave(String entityName,Object object){
		getHibernateTemplate().save(entityName,object);
	}
	public HibernateTemplate getNativeHibernateTemplate()
	{
		return getHibernateTemplate();
	}
	public void commonsave(Object object){
		getHibernateTemplate().save(object);
	}
	public void commonsaveOrUpdate(Object object){
		getHibernateTemplate().saveOrUpdate(object);
	}
	
	public void commonsaveOrUpdate(String entityName,Object object){
		getHibernateTemplate().saveOrUpdate(entityName,object);
	}
	
	public void saveOrUpdate(Userlog object){
		getHibernateTemplate().saveOrUpdate(object);
	}


	public void delete(Userlog object){
		getHibernateTemplate().delete(object);
	}
	public void commondelete(String entityName,Object object){
		getHibernateTemplate().delete(entityName,object);
	}

	public Userlog findById(int id){
		Userlog instance = (Userlog) getHibernateTemplate().get("com.exam.entity.Userlog", id);
		return instance;
	}
	
	public Object findByEntityId(Class z,int id){
		Object instance =  getHibernateTemplate().get(z, id);
		return instance;
	}
	public Object findByEntityName(String entityName,int id){
		Object instance =  getHibernateTemplate().get(entityName, id);
		return instance;
	}

	
	@SuppressWarnings("unchecked")
	public List<Object> commonpager(String hql,int pageIndex){
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
	public List<Userlog> search(String hql,String[]params,String values[]){
		Query q=getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		for(int i=0;i<params.length;i++)
		{
				q.setParameter(params[i],"%"+values[i]+"%");
		}
		return q.list();
	}


	@SuppressWarnings("unchecked")
	public List<Userlog> search(String hql,int pageIndex){
		Query q=getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		q.setFirstResult(pageIndex*Consts.ITEMS_PER_PAGE);
		q.setMaxResults(Consts.ITEMS_PER_PAGE);
		return q.list();
	}


	@SuppressWarnings("unchecked")
	public List<Userlog> search(String hql,String[]params,String values[],int pageIndex){
		Query q=getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		for(int i=0;i<params.length;i++)
		{
				q.setParameter(params[i],"%"+values[i]+"%");
		}
		q.setFirstResult(pageIndex*Consts.ITEMS_PER_PAGE);
		q.setMaxResults(Consts.ITEMS_PER_PAGE);
		return q.list();
	}


	@SuppressWarnings("unchecked")
	public List<Userlog> exactFind(String hql,String[]params,String values[]){
		Query q=getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		for(int i=0;i<params.length;i++)
		{
				q.setParameter(params[i],values[i]);
		}
		return q.list();
	}
	
	public List querySql(String sql,String[] IntColsArray,String[] StringColsArray)
	{
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		if(IntColsArray!=null)
		{
			for(String s:IntColsArray)
			{
				query.addScalar(s, Hibernate.INTEGER);
			}
		}
		if(StringColsArray!=null)
		{
			for(String s:StringColsArray)
			{
				query.addScalar(s, Hibernate.STRING);
			}
		}
		List l=query.list();
		return l;
	}
	public List querySqlPager(String sql,String[] IntColsArray,String[] StringColsArray,Class clz,int page_index)
	{
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		if(IntColsArray!=null)
		{
			for(String s:IntColsArray)
			{
				query.addScalar(s, Hibernate.INTEGER);
			}
		}
		if(StringColsArray!=null)
		{
			for(String s:StringColsArray)
			{
				query.addScalar(s, Hibernate.STRING);
			}
		}
		List l = query.setResultTransformer(Transformers.aliasToBean(clz)).setFirstResult(page_index*Consts.ITEMS_PER_PAGE).setMaxResults(Consts.ITEMS_PER_PAGE).list();

		return l;
	}

	@SuppressWarnings("unchecked")
	public Vector sqlFind(String sql){
		Vector us=new Vector();
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = getHibernateTemplate().getSessionFactory().getCurrentSession().connection();
		pstmt = con.prepareStatement( sql );
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
		}
		rs.close();
		}catch(Exception exp){
			exp.printStackTrace();
		}finally{
		try{
			pstmt.close();
			con.close();
		}
		catch( Exception e ){
			e.printStackTrace();}
		}
		return us;
	}


	@SuppressWarnings("unchecked")
	public List query(String hql){
		Query q=getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		return q.list();
	}



	@SuppressWarnings("unchecked")
	public List<Userlog> pager(String hql,int pageIndex){
		Query q=getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		q.setFirstResult(pageIndex*Consts.ITEMS_PER_PAGE);
		q.setMaxResults(Consts.ITEMS_PER_PAGE);
		return q.list();
	}




}

