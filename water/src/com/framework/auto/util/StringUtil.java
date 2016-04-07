package com.framework.auto.util;

public class StringUtil 
{
	public static String changeFirstToUpper(String str)
	{
		String fir=str.substring(0,1).toUpperCase();
		return fir+str.substring(1,str.length());
	}
}
