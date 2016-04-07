package com.framework.auto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Vector;

import com.framework.auto.path.Path;
import com.framework.auto.util.DateFormate;
import com.framework.auto.util.FileUtil;
import com.framework.auto.util.Log;
import com.framework.auto.util.PathUtil;

public class GenDao 
{
	
	//generate dao, e.g. UsersDao
	public static void createDao(String entyObjClzName)
	{
		FileUtil fu=new FileUtil();
		fu.createDirIfNotExist(Path.ENTITY_PATH);//create a new entity dir if not exist
		
		if(!fu.checkExist(Path.ENTITY_PATH+entyObjClzName+".java"))
		{
			Log.warning("The java class you specified does not exist: "+Path.ENTITY_PATH+entyObjClzName+", ignored.");
			return;
		}
		String iname=entyObjClzName+"Dao.java";
		String fullName=Path.ENTITY_PATH+iname;
		if(fu.checkExist(fullName))
		{
			Log.warning(iname+" exist, ignored...");
		}else
		{
			Log.console("Generating Dao "+iname+" for "+entyObjClzName+"...");
			fu.createFileIfNotExist(fullName);
			fu.open(fullName);
			appendContent(fu,entyObjClzName);
			fu.close();
			Log.console("Generating Dao "+iname+" for "+entyObjClzName+" successfully.");
		}
	}
	
	
	public static void appendContent(FileUtil fu,String entyObjClzName)
	{
		String daoName=entyObjClzName+"Dao";
		String daopackage=PathUtil.calPackage(Path.ENTITY_PATH);
		fu.write("package "+daopackage+";");
		fu.addEmptyLine(0);
		fu.write("import java.sql.*;");
		fu.write("import java.util.*;");
		fu.write("import com.framework.auto.consts.Consts;");
		fu.write("import org.apache.log4j.Logger;");
		fu.write("import org.hibernate.*;");
		fu.write("import org.springframework.orm.hibernate3.support.HibernateDaoSupport;");
		fu.addEmptyLine(1);
		fu.write("/**\n* Dao object for domain model class "+entyObjClzName+".");
		fu.write("* @see "+daopackage+"."+entyObjClzName);
		fu.write("* @author Calvin. Sun");
		fu.write("* Generated "+DateFormate.Format(new Date()));
		fu.write("*/");
		
		fu.write("public class "+daoName+" extends HibernateDaoSupport implements I"+daoName+" {");
		fu.addEmptyLine(1);
		fu.write("private static final Logger l=Logger.getLogger("+daoName+".class);");
		fu.addEmptyLine(2);
		
		fu.write("	public void save("+entyObjClzName+" object){\n" +
				"		getHibernateTemplate().save(object);\n" +
				"	}\n");
		fu.addEmptyLine(1);
		fu.write("	public void saveOrUpdate("+entyObjClzName+" object){\n" +
				"		getHibernateTemplate().saveOrUpdate(object);\n" +
		"	}\n");
		fu.addEmptyLine(1);
		fu.write("	public void delete("+entyObjClzName+" object){\n" +
				"		getHibernateTemplate().delete(object);\n" +
		"	}\n");
		fu.addEmptyLine(1);
		fu.write("	public "+entyObjClzName+" findById(int id){\n" +
				"		"+entyObjClzName+" instance = ("+entyObjClzName+") getHibernateTemplate().load(\""+PathUtil.calPackage(Path.ENTITY_PATH)+"."+entyObjClzName+"\", id);\n" +
				"		return instance;\n" +
		"	}\n");
		fu.addEmptyLine(1);
		fu.write("	@SuppressWarnings(\"unchecked\")");
		fu.write("	public List query(String hql){\n" +
				"		Query q=getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);\n" +
				"		return q.list();\n" +
		"	}\n");
		fu.addEmptyLine(1);
		fu.write("	@SuppressWarnings(\"unchecked\")");
		fu.write("	public List<"+entyObjClzName+"> pager(String hql,int pageIndex){\n" +
				"		Query q=getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);\n" +
				"		q.setFirstResult(pageIndex*Consts.ITEMS_PER_PAGE);\n" +
				"		q.setMaxResults(Consts.ITEMS_PER_PAGE);\n" +
				"		return q.list();\n" +
		"	}\n");
		fu.addEmptyLine(1);
		fu.write("	public int execute(String hql){\n" +
				"		Query q=getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);\n" +
				"		return q.executeUpdate();\n" +
		"	}\n");
		fu.addEmptyLine(1);
		fu.write("	@SuppressWarnings(\"unchecked\")");
		fu.write("	public List<"+entyObjClzName+"> search(String hql,String[]params,String values[]){\n" +
				"		Query q=getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);\n" +
				"		for(int i=0;i<params.length;i++)\n" +
				"		{\n				q.setParameter(params[i],\"%\"+values[i]+\"%\");\n		}\n" +
				"		return q.list();\n" +
		"	}\n");
		fu.addEmptyLine(1);
		fu.write("	@SuppressWarnings(\"unchecked\")");
		fu.write("	public List<"+entyObjClzName+"> search(String hql,int pageIndex){\n" +
				"		Query q=getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);\n" +
				"		q.setFirstResult(pageIndex*Consts.ITEMS_PER_PAGE);\n" +
				"		q.setMaxResults(Consts.ITEMS_PER_PAGE);\n" +
				"		return q.list();\n" +
		"	}\n");
		fu.addEmptyLine(1);
		fu.write("	@SuppressWarnings(\"unchecked\")");
		fu.write("	public List<"+entyObjClzName+"> search(String hql,String[]params,String values[],int pageIndex){\n" +
				"		Query q=getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);\n" +
				"		for(int i=0;i<params.length;i++)\n" +
				"		{\n				q.setParameter(params[i],\"%\"+values[i]+\"%\");\n		}\n" +
				"		q.setFirstResult(pageIndex*Consts.ITEMS_PER_PAGE);\n" +
				"		q.setMaxResults(Consts.ITEMS_PER_PAGE);\n" +
				"		return q.list();\n" +
		"	}\n");
		fu.addEmptyLine(1);
		fu.write("	@SuppressWarnings(\"unchecked\")");
		fu.write("	public List<"+entyObjClzName+"> exactFind(String hql,String[]params,String values[]){\n" +
				"		Query q=getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);\n" +
				"		for(int i=0;i<params.length;i++)\n" +
				"		{\n				q.setParameter(params[i],values[i]);\n		}\n" +
				"		return q.list();\n" +
		"	}\n");
		fu.addEmptyLine(1);
		fu.write("	@SuppressWarnings(\"unchecked\")");
		fu.write("	public Vector sqlFind(String sql){\n" +
				"		Vector us=new Vector();\n" +
				"		Connection con = null;\n" +
				"		PreparedStatement pstmt = null;\n" +
				"		try{\n" +
				"			con = getHibernateTemplate().getSessionFactory().getCurrentSession().connection();\n" +
				"		pstmt = con.prepareStatement( sql );\n" +
				"		ResultSet rs = pstmt.executeQuery();\n" +
				"		while(rs.next()){\n" +
				"		}\n" +
				"		rs.close();\n" +
				"		}catch(Exception exp){\n" +
				"			exp.printStackTrace();\n" +
				"		}finally{\n" +
				"		try{\n" +
				"			pstmt.close();\n" +
				"			con.close();\n" +
				"		}\n" +
				"		catch( Exception e ){\n" +
				"			e.printStackTrace();}\n" +
				"		}\n" +
				"		return us;\n" +
		"	}\n");
		fu.addEmptyLine(4);
		fu.write("}");
		fu.addEmptyLine(10);
	}
	
}
