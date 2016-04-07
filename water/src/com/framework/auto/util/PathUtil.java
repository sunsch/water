package com.framework.auto.util;

import java.io.File;

import com.framework.auto.path.Path;

public class PathUtil 
{
	//f://aaa/bbb/src/com/frame/util will return com.frame.util
	public static String calPackage(String path)
	{
		String pac="";
		File f=new File(path);
		int count=0;
		pac=f.getName()+"."+pac;
		//Log.console(f.getName()+f.getAbsolutePath());
		while(!f.getParentFile().getName().equals("src"))
		{
			pac=f.getParentFile().getName()+"."+pac;
			f=f.getParentFile();
			if(count++==100)
			{
				Log.warning(path+" does not contains src! can not calculate the package for DAO!");
				break;
			}
		}
		//Log.console(pac.substring(0,pac.length()-1));
		return pac.substring(0,pac.length()-1);
	}
	//f://aaa/bbb/src/com/frame/util will return com/frame/util
	public static String calDir(String path)
	{
		return calPackage(path).replace('.', '/');
	}
	//com.frame.util will return f://aaa//bbb//src//com//frame//util//
	public static String calAbsolutePath(String packag)
	{
		FileUtil fu=new FileUtil();
		fu.createFullDirIfNotExist(packag);
		
		return Path.PROJECT_PATH+"src//"+packag.replace(".", "//")+"//";
	}
	

}
