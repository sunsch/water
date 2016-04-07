package com.framework.auto.conf;

import com.framework.auto.path.Path;
import com.framework.auto.util.FileUtil;
import com.framework.auto.util.Log;
import com.framework.auto.util.PathUtil;

public class UpdateHibernate {

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
		String tempf=Path.CONFIG_PATH+"temphibernate.cfg.xml";
		fu.createFileIfNotExist(tempf);
		fu.openForRead(Path.HIBERNATE_CONFIG_FILE);
		fu.open(tempf);
		String te;
		int processed=0;
		while((te=fu.readLine())!=null)
		{
			if(te.contains("<mapping resource=\""+PathUtil.calDir(Path.ENTITY_PATH)+"/"+entyObjClzName+".hbm.xml\"/>"))
			{
				processed=1;
				Log.console("<mapping resource=\""+PathUtil.calDir(Path.ENTITY_PATH)+"/"+entyObjClzName+".hbm.xml\"/> exist, ignored..");
			}
			if(te.contains("<!-- MAPPING END HERE.")&&(processed==0))
			{
				fu.write("		<mapping resource=\""+PathUtil.calDir(Path.ENTITY_PATH)+"/"+entyObjClzName+".hbm.xml\"/>");
				Log.console("add <mapping resource=\""+PathUtil.calDir(Path.ENTITY_PATH)+"/"+entyObjClzName+".hbm.xml\"/> successfully");
				processed=1;
			}
			if(te.contains("</session-factory>")&&(processed==0))//end of hibernate.cfg.xml no comment in it.
			{
				fu.write("		<mapping resource=\""+PathUtil.calDir(Path.ENTITY_PATH)+"/"+entyObjClzName+".hbm.xml\"/>");
				Log.console("add <mapping resource=\""+PathUtil.calDir(Path.ENTITY_PATH)+"/"+entyObjClzName+".hbm.xml\"/> successfully");
				processed=1;
			}
			fu.write(te);
		}
		fu.close();
		fu.closeForRead();
		
		fu.deleteFile(Path.HIBERNATE_CONFIG_FILE);
		fu.renameTo(tempf, Path.HIBERNATE_CONFIG_FILE);
		
	}
	public static void backup(FileUtil fu)
	{
		fu.backup(Path.HIBERNATE_CONFIG_FILE);
	}
}
