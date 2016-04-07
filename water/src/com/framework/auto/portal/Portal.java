package com.framework.auto.portal;


import com.framework.auto.action.*;
import com.framework.auto.conf.*;
import com.framework.auto.dao.*;
import com.framework.auto.hbm.*;
import com.framework.auto.ui.*;
import com.framework.auto.util.*;

public class Portal {
	
	//public static String[]entities={"Groups","GrRelation","Operations","Roles","UgRelation","UrRelation","Users"};//,"Classes","Theachers","ScRelation"
	public static String[]ent=
	{"SchooluserteacherRelation"};
	public static String[]all={
	"ClasscourseRelation",
	"Classes",
	"Composeexercise",
	"Composeexercisedetail",
	"Courses",
	"CourseteacherRelation",
	"Examination",
	"Examinationdetail",
	"Groups",
	"GrRelation",
	"Items",
	"Knowledgeexercise",
	"Knowledgeexercisedetail",
	"Knowledges",
	"Operations",
	"Roles",
	"SchoolusercourseRelation",
	"Studentmodule",
	"Testpaper",
	"Testpaperdetail",
	"UgRelation",
	"UrRelation",
	"Userlog",
	"Users"
	};
	
	public static void main(String[] args) {
		genAll();
	}
	
	
	public static void genAll()
	{
		for(String e:ent)
		{
			if(e.endsWith("Relation"))//not create DAO for ScRelation.hbm.xml etc.
			{
				updateHbm(e);
				updateConf(e);
				genDao(e);
				genAction(e);
				genUI(e);
				
			}else
			{
				updateHbm(e);
				updateConf(e);
				genDao(e);
				genAction(e);
				genUI(e);
				
			}
		}
	}
	
	/*
	 * update hbm.xml file for entity, e.g. students.hbm.xml
	 */
	public static void updateHbm(String e)
	{
		UpdateHbm.update(e);
	}
	/*
	 * generate DAO interface, DAO
	 */
	public static void genDao(String e)
	{
		GenFace.createIDao(e);
		GenDao.createDao(e);
	}
	/*
	 * update configure file: hibernate.cfg.xml, applicationContext.xml, web.xml
	 */
	public static void updateConf(String e)
	{
		UpdateHibernate.update(e);
		UpdateSpring.update(e);
		UpdateWeb.update(e);
	}
	/*
	 * generate ui: add delete edit view
	 */
	public static void genUI(String e)
	{
		GenAddUI.createAddUI(e);
		GenEditUI.createEditUI(e);
		GenViewUI.createViewUI(e);
		
	}
	/*
	 * generate actions: add delete edit view
	 */
	public static void genAction(String e)
	{
		GenAddAction.createAddAction(e);
		GenDeleteAction.createDeleteAction(e);
		GenEditAction.createEditAction(e);
		GenViewAction.createViewAction(e);
	}

}
