package com.framework.auto.opt;

import java.io.File;
import java.util.Vector;
import com.framework.auto.path.Path;
import com.framework.auto.util.FileUtil;

public class ResetOpts 
{
	/*
	 * This method will flush all the action handlers to DB, the previous will be deleted
	 * Table: operations
	 * Action: Handlers
	 * 
	 */
	public static void resetDBOpts()
	{
		DBOpts.reset(getAllHandlers());
	}
	
	/*
	 * get all the servlets' names
	 * 
	 */
	public static Vector<String> getAllHandlers()
	{
		Vector<String> res=new Vector<String>(50,20);
		File src=new File(Path.SRC_PATH);
		
		//addHandlerByFileContent(res, src);
		addHandlerByFileName(res, src);
		
		return res;
		
	}
	
	/*
	 * get all the handlers by the file name which end with "Handler.java"
	 * 
	 */
	public static void addHandlerByFileName(Vector<String> res, File cur)
	{
		if(cur.isFile())
		{
			if(isHandlerByFileName(cur))
			{
				res.add(cur.getName().substring(0,cur.getName().length()-5));//remove ".java"
			}else
			{
				
			}
			
		}else//is directory
		{
			for(File f:cur.listFiles())
			{
				addHandlerByFileName(res,f);
			}
		}
		
	}
	/*
	 * get all the handlers by the file content which contains "extends HttpServlet"
	 * 
	 */
	public static void addHandlerByFileContent(Vector<String> res,File cur)
	{
		if(cur.isFile())
		{
			if(isHandlerByFileContent(cur))
			{
				res.add(cur.getName());
			}else
			{
				
			}
			
		}else//is directory
		{
			for(File f:cur.listFiles())
			{
				addHandlerByFileContent(res,f);
			}
		}
		
	}
	
	public static boolean isHandlerByFileContent(File cur)
	{
		boolean res=false;
		FileUtil fu=new FileUtil();
		fu.openForRead(cur.getPath());
		String tmp="";
		while((tmp=fu.readLine())!=null)
		{
			if(tmp.contains("extends HttpServlet"))
			{
				res=true;
				break;
			}
		}
		fu.closeForRead();
		
		return res;
	}
	public static boolean isHandlerByFileName(File cur)
	{
		boolean res=false;
		if(cur.getName().endsWith("Handler.java"))
		{
			res=true;
		}
		
		return res;
	}

}
