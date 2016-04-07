package com.framework.auto.test;

import java.util.ResourceBundle;

import org.apache.tomcat.jni.Local;

import com.framework.auto.util.EncodUtil;
import com.framework.auto.util.Log;

public class TestResourceBundle {

	public static void main(String[] args) 
	{
		
		ResourceBundle r = ResourceBundle.getBundle("com.framework.auto.prop.webpage");  


		for(String s:r.keySet())
		{
			Log.console(EncodUtil.transform("utf-8", "iso8859-1", r.getString(s)) );
		}
	}
}
