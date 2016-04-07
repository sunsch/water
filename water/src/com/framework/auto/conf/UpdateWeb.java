package com.framework.auto.conf;

import com.framework.auto.consts.Consts;
import com.framework.auto.path.Path;
import com.framework.auto.util.FileUtil;
import com.framework.auto.util.Log;
import com.framework.auto.util.PathUtil;

public class UpdateWeb 
{
	public static void update(String entyObjClzName)
	{
		FileUtil fu=new FileUtil();
		
		fu.createDirIfNotExist(Path.ENTITY_PATH);//create a new entity dir if not exist
		if(!fu.checkExist(Path.ENTITY_PATH+entyObjClzName+".java"))
		{
			Log.warning("The java class you specified does not exist: "+Path.ENTITY_PATH+entyObjClzName+", ignored.");
			return;
		}
		
		//backup(fu);
		String tempf=Path.WEB_CONFIG_PATH+"tempweb.xml";
		fu.createFileIfNotExist(tempf);
		fu.openForRead(Path.WEB_CONFIG_FILE);
		fu.open(tempf);
		String te;
		int processed=0;
		while((te=fu.readLine())!=null)
		{
			if(te.contains("<servlet-name>Add"+entyObjClzName+"Handler</servlet-name>"))
			{
				processed=1;
				Log.console("<servlet-name>Add"+entyObjClzName+"Handler</servlet-name> exist, ignored..");
			}
			if(te.contains("</web-app>")&&(processed==0))//end of web.xml.
			{
				appendCRUDHandler( fu, entyObjClzName);
				processed=1;
			}
			
			fu.write(te);
		}
		fu.close();
		fu.closeForRead();
		
		fu.deleteFile(Path.WEB_CONFIG_FILE);
		fu.renameTo(tempf, Path.WEB_CONFIG_FILE);
		
	}
	public static void backup(FileUtil fu)
	{
		fu.backup(Path.WEB_CONFIG_FILE);
	}
	public static void appendCRUDHandler(FileUtil fu,String entyObjClzName)
	{
		appendHandler(fu,entyObjClzName,"Add");
		appendHandler(fu,entyObjClzName,"Delete");
		appendHandler(fu,entyObjClzName,"Edit");
		appendHandler(fu,entyObjClzName,"View");
	}
	public static void appendHandler(FileUtil fu,String entyObjClzName,String type)
	{
		fu.write("	<servlet>");
		fu.write("		<servlet-name>"+type+entyObjClzName+"Handler</servlet-name> ");
		fu.write("		<servlet-class>"+Path.ACTION_HANDLER_PACKAGE+"."+type+entyObjClzName+"Handler</servlet-class>");
		fu.write("	</servlet>");
		fu.write("	<servlet-mapping>");
		fu.write("		<servlet-name>"+type+entyObjClzName+"Handler</servlet-name> ");
		fu.write("		<url-pattern>/"+type+entyObjClzName+"Handler</url-pattern>");
		fu.write("	</servlet-mapping>");
		fu.addEmptyLine(1);
		
		Log.console("add "+type+entyObjClzName+"Handler successfully");
		
	}

}
