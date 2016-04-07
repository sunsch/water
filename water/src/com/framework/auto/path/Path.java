package com.framework.auto.path;

import com.framework.auto.util.FileUtil;

public class Path {

	/*
	 * Absolute path specified for IDAO, DAO, UI
	 */
	public static final String WORKSPACE_PATH="F:\\workspace\\";
	public static final String PROJECT_NAME="exam";
	public static final String PROJECT_PATH=WORKSPACE_PATH+PROJECT_NAME+"\\";
	public static final String SRC_PATH=PROJECT_PATH+"src\\";
	
	//ENTITY_PATH will be used for find the entity dir, if can not find the entity in this dir, ignored
	public static final String ENTITY_PATH=PROJECT_PATH+"src\\com\\exam\\entity\\";
	
	//public static final String UI_PATH=PROJECT_PATH+"WebContent\\stcui\\";
	public static final String CONFIG_PATH=PROJECT_PATH+"src\\com\\framework\\config\\";
	
	public static final String WEB_CONTENT_PATH=PROJECT_PATH+"WebContent\\";
	public static final String WEB_CONFIG_PATH=WEB_CONTENT_PATH+"WEB-INF\\";
	
	public static final String HIBERNATE_CONFIG_FILE=CONFIG_PATH+"hibernate.cfg.xml";
	public static final String SPRING_CONFIG_FILE=CONFIG_PATH+"applicationContext.xml";
	public static final String WEB_CONFIG_FILE=WEB_CONFIG_PATH+"web.xml";
	
	public static final String ACTION_HANDLER_PACKAGE="com.exam.action";
	
	//used for framework to generate add, edit, view pages
	public static final String UI_TEMPLATE_ADD=WEB_CONTENT_PATH+"admin\\template\\addGroups.jsp";
	public static final String UI_TEMPLATE_EDIT=WEB_CONTENT_PATH+"admin\\template\\editGroups.jsp";
	public static final String UI_TEMPLATE_VIEW=WEB_CONTENT_PATH+"admin\\template\\viewGroups.jsp";
	/*
	public static final String UI_TEMPLATE_ADD=WEB_CONTENT_PATH+"jquery\\addtemplate.html";
	public static final String UI_TEMPLATE_EDIT=WEB_CONTENT_PATH+"jquery\\edittemplate.html";
	public static final String UI_TEMPLATE_VIEW=WEB_CONTENT_PATH+"jquery\\viewtemplate.html";
	*/
	
	
		
	
}
