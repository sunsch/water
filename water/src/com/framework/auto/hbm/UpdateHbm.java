package com.framework.auto.hbm;

import com.framework.auto.path.Path;
import com.framework.auto.util.FileUtil;
import com.framework.auto.util.Log;

public class UpdateHbm 
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
		if(!fu.checkExist(Path.ENTITY_PATH+entyObjClzName+".hbm.xml"))
		{
			Log.warning("The java class you specified does not exist: "+Path.ENTITY_PATH+entyObjClzName+", ignored.");
			return;
		}
		//backup(fu);
		String tempf=Path.ENTITY_PATH+"temphbm.xml";
		String hbmf=Path.ENTITY_PATH+entyObjClzName+".hbm.xml";
		fu.createFileIfNotExist(tempf);
		fu.openForRead(hbmf);
		fu.open(tempf);
		String te;
		int processed=0;
		while((te=fu.readLine())!=null)
		{
			if(te.contains("<generator class=\"assigned\" />"))
			{
				fu.write("			<generator class=\"native\" />");
				processed=1;
				Log.console("update for entyObjClzName <generator class=\"assigned\" /> successfully");
			}else
			{
				fu.write(te);
			}
		}
		fu.close();
		fu.closeForRead();
		
		fu.deleteFile(hbmf);
		fu.renameTo(tempf, hbmf);
		
	}
	public static void backup(FileUtil fu)
	{
		fu.backup(Path.WEB_CONFIG_FILE);
	}
}
