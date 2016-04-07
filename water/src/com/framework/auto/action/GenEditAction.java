package com.framework.auto.action;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Vector;
import com.framework.auto.consts.Consts;
import com.framework.auto.path.Path;
import com.framework.auto.util.DateFormate;
import com.framework.auto.util.FileUtil;
import com.framework.auto.util.Log;
import com.framework.auto.util.PathUtil;
import com.framework.auto.util.ReflectUtil;
import com.framework.auto.util.StringUtil;

public class GenEditAction 
{

	//generate add action hadler, e.g. DeleteStudentsHandler.java
	public static void createEditAction(String entyObjClzName)
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
		String iname="Edit"+entyObjClzName+"Handler.java";
		String fullName=PathUtil.calAbsolutePath(Path.ACTION_HANDLER_PACKAGE)+iname;
		if(fu.checkExist(fullName))
		{
			Log.warning(iname+" exist, ignored...");
		}else
		{
			Log.console("Generating Edit Handler "+iname+" for "+entyObjClzName+" "+fullName+"...");
			fu.createFileIfNotExist(fullName);
			fu.open(fullName);
			appendContent(fu,entyObjClzName);
			fu.close();
			Log.console("Generating Edit Handler "+iname+" for "+entyObjClzName+" successfully.");
		}
	}


	public static void appendContent(FileUtil fu,String entyObjClzName)
	{
		String iname="Edit"+entyObjClzName+"Handler";
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
		fu.write("		"+idao+" idao=("+idao+")SpringBean.getBean(this.getServletConfig(), \""+entyObjClzName+"DaoTxProxy\");");
		fu.write("		String oid=request.getParameter(\"oid\");");
		fu.write("		if(oid==null)");
		fu.write("		{");
		fu.write("			response.getWriter().write(\"NO_OBJECT_ID:NO_OBJECT_ID\");");
		fu.write("			return;");
		fu.write("		}");
		fu.write("		"+entyObjClzName+" e=idao.findById(Integer.parseInt(oid));");
		fu.write("		if(e!=null)");
		fu.write("		{");
		for(Field f:ReflectUtil.calLargeObj(entyObjClzName))
		{
			fu.write("			e.set"+StringUtil.changeFirstToUpper(f.getName())+"(null);");
		}
		fu.write("			JsonConfig jsonConfig = new JsonConfig();");
		fu.write("			jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JsonDateProcessor());");
		fu.write("			JSONObject json=JSONObject.fromObject(e,jsonConfig);");
		fu.write("			response.getWriter().write(json.toString());");
		fu.write("			Log.warning(json.toString());");
		fu.write("		}else");
		fu.write("		{");
		fu.write("			response.getWriter().write(\"NO_OBJECT_GOT:NO_OBJECT_GOT\");");
		fu.write("		}");
		fu.write("	}");//end get
		fu.addEmptyLine(2);
		
		fu.write("	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException ");
		fu.write("	{");
		fu.write("		"+idao+" idao=("+idao+")SpringBean.getBean(this.getServletConfig(), \""+entyObjClzName+"DaoTxProxy\");");
		fu.write("		String oid=request.getParameter(\"oid\");");
		fu.write("		if(oid==null)");
		fu.write("		{");
		fu.write("			response.getWriter().write(\"NO_OBJECT_ID:NO_OBJECT_ID\");");
		fu.write("			return;");
		fu.write("		}");
		fu.write("		"+entyObjClzName+" e=idao.findById(Integer.parseInt(oid));");
		fu.write("		if(e!=null)");
		fu.write("		{");
		Vector<Field> visible=ReflectUtil.calVisibles(entyObjClzName);
		for(Field f:visible)
		{
			if(ReflectUtil.isDate(f))
			{
				fu.write("			try{");
				fu.write("			e.set"+StringUtil.changeFirstToUpper(f.getName())+"(java.sql.Date.valueOf(request.getParameter(\""+f.getName()+"\")));");
				fu.write("			}catch(IllegalArgumentException ie)");
				fu.write("			{");
				fu.write("				ie.printStackTrace();");
				fu.write("			}");
			}else
			{
				fu.write("			e.set"+StringUtil.changeFirstToUpper(f.getName())+"(request.getParameter(\""+f.getName()+"\"));");
			}
		}
		fu.write("			idao.saveOrUpdate(e);");
		fu.write("			response.getWriter().write(\"success\");");
		fu.write("		}");
		
		fu.write("		try {Thread.sleep(0);} catch (InterruptedException ex) {ex.printStackTrace();}");
		fu.write("	}");
		fu.addEmptyLine(1);
		fu.write("}");
		fu.addEmptyLine(10);
	}

}
