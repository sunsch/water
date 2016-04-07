package com.exam.constant;


public interface Consts {

	public final String ITEM_FILE_DIR = "c:\\temp\\";
	public final String SYSTEM_OUT_LOG_FILE = "SystemOut.log";
	public final String ITEM_CORRENTRWONG_ANALYSIS_LOG_FILE = "ItemCorrectwrongAnalysis.log";
	public final String ITEM_NUMBER_SPLITOR = "#";
	
	
	public final int TABLE_USERS_SPLIT_NUMBER = 10000;//DO NOT MODIFY IT

	
	public final String SESSION_ATTRIBUTE_LOGON_USER = "user";
	public final String SESSION_ATTRIBUTE_COURSE = "course";
	public final String SESSION_ATTRIBUTE_FINISHED_ITEMS = "finisheditems";// List<Exercisedetail> fe, load all into session, should improve
	public final String SESSION_ATTRIBUTE_STUDENT_MODULE = "studentmodule";
	
	public final String SESSION_ATTRIBUTE_STUDENT_CORRECT_ITEMS = "correctlist";//List<Integer> iid
	public final String SESSION_ATTRIBUTE_STUDENT_WRONG_ITEMS = "wronglist";//List<Integer> iid
	public final String SESSION_ATTRIBUTE_STUDENT_DONE_ITEMS = "donelist";//List<Integer> iid
	
	public final String SESSION_ATTRIBUTE_EXTERNAL_USER_TARGET_PAGE_NAME="SESSION_ATTRIBUTE_EXTERNAL_USER_TARGET_PAGE_NAME";


	
	public final int ROLE_STUDENT_RID = 3;
	public final int ROLE_TEACHER_RID = 4;
	public final int ROLE_SCHOOL_RID = 5;
	public final int ROLE_ADMINISTRATOR_RID = 6;
	public final int ROLE_ACCOUNT_RID = 7;
	public final int ROLE_FORUM_ADMIN_RID = 8;
	public final int ROLE_COURSE_ADMIN_RID = 9;
	public final int ROLE_HEAD_TEACHER_RID = 10;
	
	
	public final String DEFAULT_CLASS_NUMBER = "default_class";
	public final String CURRENT_SCHOOL_USERNAME = "2013_supercc";//2012_cc,2013_cc...


	
	public final int KLG_COMPOSE_DEFAULT_COMMITMENT = 80;// default 达标阈值
	public final int SEARCH_ITEM_CONTROL_COUNT = 30;//题目检索限制
	

}
