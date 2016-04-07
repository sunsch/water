package com.framework.auto.basicentity;

import java.sql.*;
import java.util.*;
import java.util.Vector;

import com.framework.auto.consts.Consts;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
* Dao object for domain model class Users.
* @see com.framework.auto.basicentity.Users
* @author Calvin. Sun
* Generated 2010-06-01 17:29:17
*/
public class UsersDao extends HibernateDaoSupport implements IUsersDao {

private static final Logger l=Logger.getLogger(UsersDao.class);

	public void save(Users object){
		getHibernateTemplate().save(object);
	}


	public void saveOrUpdate(Users object){
		getHibernateTemplate().saveOrUpdate(object);
	}


	public void delete(Users object){
		getHibernateTemplate().delete(object);
	}


	public Users findById(int id){
		Users instance = (Users) getHibernateTemplate().get("com.framework.auto.basicentity.Users", id);
		return instance;
	}


	@SuppressWarnings("unchecked")
	public List query(String hql){
		Query q=getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		return q.list();
	}


	@SuppressWarnings("unchecked")
	public List<Users> pager(String hql,int pageIndex){
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
	public List<Users> search(String hql,String[]params,String values[]){
		Query q=getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		for(int i=0;i<params.length;i++)
		{
				q.setParameter(params[i],"%"+values[i]+"%");
		}
		return q.list();
	}


	@SuppressWarnings("unchecked")
	public List<Users> search(String hql,int pageIndex){
		Query q=getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		q.setFirstResult(pageIndex*Consts.ITEMS_PER_PAGE);
		q.setMaxResults(Consts.ITEMS_PER_PAGE);
		return q.list();
	}


	@SuppressWarnings("unchecked")
	public List<Users> search(String hql,String[]params,String values[],int pageIndex){
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
	public List<Users> exactSearch(String hql,String[]params,String values[],int pageIndex){
		Query q=getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		for(int i=0;i<params.length;i++)
		{
				q.setParameter(params[i],values[i]);
		}
		return q.list();
	}
	@SuppressWarnings("unchecked")
	public Vector sqlFind(String sql)
	{
		Vector us=new Vector();

		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = getHibernateTemplate().getSessionFactory().getCurrentSession().connection();

			pstmt = con.prepareStatement( sql );
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				
			}
			rs.close();

		}catch(Exception exp)
		{
			exp.printStackTrace();
		}finally
		{
			try
			{
				pstmt.close();
				con.close();
			}
			catch( Exception e )
			{
				e.printStackTrace();
			}
		}
    	return us;
	}



}

