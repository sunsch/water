package com.framework.auto.dao;

import java.io.File;
import java.util.Date;
import java.util.Vector;

import com.framework.auto.path.Path;
import com.framework.auto.util.DateFormate;
import com.framework.auto.util.FileUtil;
import com.framework.auto.util.Log;
import com.framework.auto.util.PathUtil;

public class GenFace 
{
	
	//generate dao interface, e.g. IUsersDao
	public static void createIDao(String entyObjClzName)
	{
		FileUtil fu=new FileUtil();
		fu.createDirIfNotExist(Path.ENTITY_PATH);//create a new entity dir if not exist
		
		if(!fu.checkExist(Path.ENTITY_PATH+entyObjClzName+".java"))
		{
			Log.warning("The java class you specified does not exist: "+Path.ENTITY_PATH+entyObjClzName+", ignored.");
			return;
		}
		String iname="I"+entyObjClzName+"Dao.java";
		String fullName=Path.ENTITY_PATH+iname;
		if(fu.checkExist(fullName))
		{
			Log.warning(iname+" exist, ignored...");
		}else
		{
			Log.console("Generating Dao interface "+iname+" for "+entyObjClzName+"...");
			fu.createFileIfNotExist(fullName);
			fu.open(fullName);
			appendContent(fu,entyObjClzName);
			fu.close();
			Log.console("Generating Dao interface "+iname+" for "+entyObjClzName+" successfully.");
		}
	}
	
	
	
	public static void appendContent(FileUtil fu,String entyObjClzName)
	{
		String iterfaceName="I"+entyObjClzName+"Dao";
		String daopackage=PathUtil.calPackage(Path.ENTITY_PATH);
		fu.write("package "+daopackage+";");
		fu.addEmptyLine(0);
		fu.write("import java.util.*;");
		fu.addEmptyLine(1);
		fu.write("/**\n* Interface object for domain model class "+entyObjClzName+".");
		fu.write("* @see "+daopackage+"."+entyObjClzName);
		fu.write("* @author Calvin. Sun");
		fu.write("* Generated "+DateFormate.Format(new Date()));
		fu.write("*/");
		
		fu.write("public interface "+iterfaceName+" {");
		fu.addEmptyLine(2);
		fu.write("	public void save("+entyObjClzName+" object);");
		fu.write("	public void saveOrUpdate("+entyObjClzName+" object);");
		fu.write("	public void delete("+entyObjClzName+" object);");
		fu.write("	public "+entyObjClzName+" findById(int id);");
		fu.write("	public List query(String hql);");
		fu.write("	public List<"+entyObjClzName+"> pager(String hql,int pageIndex);");
		fu.write("	public int execute(String hql);");
		fu.write("	public List<"+entyObjClzName+"> exactFind(String hql,String[]params,String values[]);");
		fu.write("	public Vector sqlFind(String sql);");
		fu.write("	public List<"+entyObjClzName+"> search(String hql,String[]params,String values[]);");
		fu.write("	public List<"+entyObjClzName+"> search(String hql,int pageIndex);");
		fu.write("	public List<"+entyObjClzName+"> search(String hql,String[]params,String values[],int pageIndex);");
		fu.addEmptyLine(4);
		fu.write("}");
		fu.addEmptyLine(10);
	}
	
}
