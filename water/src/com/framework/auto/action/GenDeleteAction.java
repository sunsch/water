package com.framework.auto.action;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import net.sf.json.JSONArray;

import com.framework.auto.consts.Consts;
import com.framework.auto.path.Path;
import com.framework.auto.util.DateFormate;
import com.framework.auto.util.FileUtil;
import com.framework.auto.util.Log;
import com.framework.auto.util.PathUtil;
import com.framework.auto.util.ReflectUtil;
import com.framework.auto.util.StringUtil;
import com.framework.util.SpringBean;

public class GenDeleteAction 
{

	//generate add action hadler, e.g. DeleteStudentsHandler.java
	public static void createDeleteAction(String entyObjClzName)
	{
		FileUtil fu=new FileUtil();
		/*
		fu.createDirIfNotExist(Path.ENTITY_PATH);//create a new entity dir if not exist
		
		if(!fu.checkExist(Path.ENTITY_PATH+entyObjClzName+".java"))
		{
			Log.warning("The java class you specified does not exist: "+Path.ENTITY_PATH+entyObjClzName+", ignored.");
			return;
		}
		*/
		String iname="Delete"+entyObjClzName+"Handler.java";
		String fullName=PathUtil.calAbsolutePath(Path.ACTION_HANDLER_PACKAGE)+iname;
		if(fu.checkExist(fullName))
		{
			Log.warning(iname+" exist, ignored...");
		}else
		{
			Log.console("Generating Delete Handler "+iname+" for "+entyObjClzName+" "+fullName+"...");
			fu.createFileIfNotExist(fullName);
			fu.open(fullName);
			appendContent(fu,entyObjClzName);
			fu.close();
			Log.console("Generating Delete Handler "+iname+" for "+entyObjClzName+" successfully.");
		}
	}


	public static void appendContent(FileUtil fu,String entyObjClzName)
	{
		String iname="Delete"+entyObjClzName+"Handler";
		String idao="I"+entyObjClzName+"Dao";
		String daopackage=PathUtil.calPackage(Path.ENTITY_PATH);
		fu.write("package "+Path.ACTION_HANDLER_PACKAGE+";");
		fu.addEmptyLine(0);
		fu.write("import java.util.*;");
		fu.write("import net.sf.json.JSONArray;");
		fu.write("import java.io.*;");
		fu.write("import javax.servlet.ServletException;");
		fu.write("import javax.servlet.http.*;");
		fu.write("import com.framework.auto.util.*;");
		fu.write("import "+daopackage+".*;");
		
		fu.addEmptyLine(1);
		fu.write("/**\n* Interface object for domain model class "+entyObjClzName+".");
		fu.write("* @see "+daopackage+"."+entyObjClzName);
		fu.write("* @author Calvin. Sun");
		fu.write("* Generated "+DateFormate.Format(new Date()));
		fu.write("*/");
		
		fu.write("public class "+iname+" extends HttpServlet {");
		fu.addEmptyLine(1);
		fu.write("	private static final long serialVersionUID = 1L;");
		fu.addEmptyLine(1);
		
		fu.write("	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException ");
		fu.write("	{");
		fu.addEmptyLine(1);
		fu.write("	}");
		
		fu.write("	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException ");
		fu.write("	{");
		fu.write("		"+idao+" idao=("+idao+")SpringBean.getBean(this.getServletConfig(), \""+entyObjClzName+"DaoTxProxy\");");
		fu.write("		int oid=Integer.parseInt(request.getParameter(\"oid\"));");
		fu.write("		idao.execute(\"delete from "+entyObjClzName+" e where e."+ReflectUtil.calid(entyObjClzName)+"=\"+oid);");
		fu.addEmptyLine(2);
		fu.write("	}");
		fu.addEmptyLine(1);
		fu.write("}");
		fu.addEmptyLine(10);
	}
	



}
