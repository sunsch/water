package com.framework.auto.util;

import org.hibernate.cfg.DefaultNamingStrategy;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.cfg.NamingStrategy;

public class NamingStrategyUtil extends DefaultNamingStrategy {

	private static final long serialVersionUID = 1L;

	@Override
	public String classToTableName(String className) 
	{
		Log.warning(className+" in class NamingStrategyUtil");
		//need to get the className only
		int dotPos = className.lastIndexOf(".");
		String classNameWithoutPackageName= className.substring(dotPos+1,className.length()).toLowerCase();
		if(classNameWithoutPackageName.equals("knowledgeexercisedetail"))
		{
			//classNameWithoutPackageName =classNameWithoutPackageName + "101";
		}
		return className;
	}
	
	@Override
	public String propertyToColumnName(String propertyName) 
	{
		return propertyName;
	}
	
	




}
