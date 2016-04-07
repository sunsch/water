package com.framework.auto.action;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.framework.auto.consts.Consts;
import com.framework.auto.json.JsonDateProcessor;
import com.framework.auto.path.Path;
import com.framework.auto.util.DateFormate;
import com.framework.auto.util.FileUtil;
import com.framework.auto.util.Log;
import com.framework.auto.util.PathUtil;
import com.framework.auto.util.ReflectUtil;
import com.framework.auto.util.StringUtil;

public class GenViewAction 
{

	//generate view action hadler, e.g. ViewStudentsHandler.java
	public static void createViewAction(String entyObjClzName)
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
		String iname="View"+entyObjClzName+"Handler.java";
		String fullName=PathUtil.calAbsolutePath(Path.ACTION_HANDLER_PACKAGE)+iname;
		if(fu.checkExist(fullName))
		{
			Log.warning(iname+" exist, ignored...");
		}else
		{
			Log.console("Generating View Handler "+iname+" for "+entyObjClzName+" "+fullName+"...");
			fu.createFileIfNotExist(fullName);
			fu.open(fullName);
			appendContent(fu,entyObjClzName);
			fu.close();
			Log.console("Generating View Handler "+iname+" for "+entyObjClzName+" successfully.");
		}
	}


	public static void appendContent(FileUtil fu,String entyObjClzName)
	{
		String iname="View"+entyObjClzName+"Handler";
		String idao="I"+entyObjClzName+"Dao";
		String daopackage=PathUtil.calPackage(Path.ENTITY_PATH);
		fu.write("package "+Path.ACTION_HANDLER_PACKAGE+";");
		fu.addEmptyLine(0);
		fu.write("import java.util.*;");
		fu.write("import net.sf.json.*;");
		fu.write("import java.io.*;");
		fu.write("import javax.servlet.ServletException;");
		fu.write("import com.framework.auto.json.JsonDateProcessor;");
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
		fu.write("	{");//begin get
		fu.write("		String action=request.getParameter(\"action\");");
		fu.write("		"+idao+" idao=("+idao+")SpringBean.getBean(this.getServletConfig(), \""+entyObjClzName+"DaoTxProxy\");");
		fu.write("		if(action.equals(\"qcount\"))");
		fu.write("		{");
		fu.write("			String hql=\"select count(*) from "+entyObjClzName+"\";");
		fu.write("			List l=idao.query(hql);");
		fu.write("			int count=Integer.parseInt(l.get(0).toString());");
		fu.write("			String res=\"{count:\"+count+\"}\";");
		fu.write("			JSONObject jo=JSONObject.fromObject(res);");
		fu.write("			response.getWriter().write(jo.toString());");
		fu.write("		}else if(action.equals(\"qobj\"))");
		fu.write("		{");
		fu.write("			int page_index=Integer.parseInt(request.getParameter(\"page_index\"));");
		fu.write("			List<"+entyObjClzName+"> ol=idao.pager(\"from "+entyObjClzName+" e order by e."+ReflectUtil.calid(entyObjClzName)+"\",page_index);");
		fu.write("			for(int i=0;i<ol.size();i++)");
		fu.write("			{");
		fu.write("				"+entyObjClzName+" u1=ol.get(i);");
		for(Field f:ReflectUtil.calLargeObj(entyObjClzName))
		{
			fu.write("				u1.set"+StringUtil.changeFirstToUpper(f.getName())+"(null);");
		}
		fu.write("			}");
		fu.write("			JsonConfig jsonConfig = new JsonConfig();");
		fu.write("			jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JsonDateProcessor());");
		fu.write("			JSONArray jsonObject = JSONArray.fromObject(ol,jsonConfig);");
		fu.write("			response.getWriter().write(jsonObject.toString());");
		fu.addEmptyLine(2);
		fu.write("		}");
		fu.addEmptyLine(2);
		fu.write("		try {Thread.sleep(0);} catch (InterruptedException ex) {ex.printStackTrace();}");
		fu.write("	}");//end get
		fu.addEmptyLine(2);
		
		fu.write("	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException ");
		fu.write("	{");
		fu.addEmptyLine(1);
		fu.write("	}");
		
		fu.addEmptyLine(2);
		fu.write("}");
		fu.addEmptyLine(10);
	}

}
