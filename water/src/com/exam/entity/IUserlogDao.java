package com.exam.entity;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

import org.springframework.orm.hibernate3.HibernateTemplate;

/**
* Interface object for domain model class Userlog.
* @see com.exam.entity.Userlog
* @author Calvin. Sun
* Generated 2010-06-06 11:48:42
*/
public interface IUserlogDao {

	public void save(Object object);
	public void save(String entityName,Userlog object);
	public void commonsave(String entityName,Object object);
	public void commondelete(String entityName,Object object);
	public void commonsave(Object object);
	public HibernateTemplate getNativeHibernateTemplate();
	public List querySql(String sql,String[] IntColsArray,String[] StringColsArray);
	public List querySqlPager(String sql,String[] IntColsArray,String[] StringColsArray,Class clz,int page_index);
	public void commonsaveOrUpdate(Object object);
	public void commonsaveOrUpdate(String entityName,Object object);
	public List<Object> commonpager(String hql,int pageIndex);
	public void saveOrUpdate(Userlog object);
	public Object findByEntityId(Class z,int id);
	public Object findByEntityName(String entityName,int id);
	public void delete(Userlog object);
	public Userlog findById(int id);
	public List query(String hql);
	public List<Userlog> pager(String hql,int pageIndex);
	public int execute(String hql);
	public List<Userlog> exactFind(String hql,String[]params,String values[]);
	public Vector sqlFind(String sql);
	public List<Userlog> search(String hql,String[]params,String values[]);
	public List<Userlog> search(String hql,int pageIndex);
	public List<Userlog> search(String hql,String[]params,String values[],int pageIndex);
}

