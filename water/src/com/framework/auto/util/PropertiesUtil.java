package com.framework.auto.util;

import java.util.ResourceBundle;

public class PropertiesUtil
{
	private ResourceBundle r;
	
	@SuppressWarnings("unused")
	private PropertiesUtil()
	{
		
	}
	public PropertiesUtil(String propertiesFileName)
	{
		r = ResourceBundle.getBundle(propertiesFileName); 
		
	}
	
	public String getString(String key)
	{
		return EncodUtil.transform("utf-8", "iso8859-1", r.getString(key));
	}

}
