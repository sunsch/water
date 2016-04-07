package com.framework.auto.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil 
{

	// give a string return a formated date
	// format can be (yyyy-MM-dd HH:mm:ss)
	public static Date parse(String format,String date)
	{
		SimpleDateFormat df=new SimpleDateFormat(format);
		try {
			return df.parse(date);
			
		} catch (ParseException e) 
		{
			Log.console("can not convert "+date+" to Date, return 1900-00-00.");
			e.printStackTrace();
			return null;
		}
	}
	public static Date yyyyMMdd(String ds)
	{
		Date res=null;
		try{
			//res=java.sql.Date.valueOf(ds);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
			res=sdf.parse(ds);
		}catch(Exception ie)
		{
			//res=java.sql.Date.valueOf("1900-00-00");
			res=new Date();
			Log.console("can not convert "+ds+" to Date, return 1900-00-00.");
		} 
		return res;
	}
	public static Date yyyy_MM_dd(String ds)
	{
		Date res=null;
		try{
			//res=java.sql.Date.valueOf(ds);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
			res=sdf.parse(ds);
		}catch(Exception ie)
		{
			//res=java.sql.Date.valueOf("1900-00-00");
			res=new Date();
			Log.console("can not convert "+ds+" to Date, return 1900-00-00.");
		} 
		return res;
	}
	public static Date yyyyLineMMLinedd(String ds)
	{
		Date res=null;
		try{
			//res=java.sql.Date.valueOf(ds);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd"); 
			res=sdf.parse(ds);
		}catch(Exception ie)
		{
			//res=java.sql.Date.valueOf("1900-00-00");
			res=new Date();
			Log.console("can not convert "+ds+" to Date, return 1900-00-00.");
		} 
		return res;
	}
	//convert the string to date, if can not convert, return default 1900-00-00
	public static Date yyyyMMddHHmmss(String ds)
	{
		Date res=null;
		try{
			//res=java.sql.Date.valueOf(ds);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			res=sdf.parse(ds);
		}catch(Exception ie)
		{
			//res=java.sql.Date.valueOf("1900-00-00");
			res=new Date();
			Log.console("can not convert "+ds+" to Date, return 1900-00-00.");
		} 
		return res;
	}
	public static Date yyyy_MM_ddSpaceHHColonmmColonss(String ds)
	{
		Date res=null;
		try{
			//res=java.sql.Date.valueOf(ds);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			res=sdf.parse(ds);
		}catch(Exception ie)
		{
			//res=java.sql.Date.valueOf("1900-00-00");
			res=new Date();
			Log.console("can not convert "+ds+" to Date, return 1900-00-00.");
		} 
		return res;
	}
	//convert the string to date, if can not convert, return null
	public static Date convertFormal(String ds)
	{
		Date res=null;
		try{
			//res=java.sql.Date.valueOf(ds);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			res=sdf.parse(ds);
		}catch(Exception ie)
		{
			//res=java.sql.Date.valueOf("1900-00-00");
			res=new Date();
			Log.console("can not convert "+ds+" to Date, return 1900-00-00.");
		} 
		return res;
	}
	
	//convert the string to date, if can not convert, return default 1900-00-00
	public static Date convertDefault(String ds)
	{
		Date res=null;
		try{
			//res=java.sql.Date.valueOf(ds);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			res=sdf.parse(ds);
		}catch(Exception ie)
		{
			//res=java.sql.Date.valueOf("1900-00-00");
			res=new Date();
			Log.console("can not convert "+ds+" to Date, return 1900-00-00.");
		} 
		return res;
	}
}
