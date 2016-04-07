package com.framework.util;

public class StringUtil 
{
	public static String isNULL(String checked)
	{
		if(checked==null)
		{
			return "";
		}else
		{
			return checked.trim();
		}
	}
	
	public static boolean nullParameter(String param)
	{
		if(param!=null&&!param.equals("null")&&!param.trim().equals(""))
		{
			return false;
		}else
		{
			return true;
		}
	}


}
