package com.exam.ui.utils;

import com.exam.constant.Consts;

//will return roleid for student, and return txt for other roles
public class MenuTxtUtil {
	
	public static String getUserMenuTxt(int roleid)
	{
		if(roleid==Consts.ROLE_STUDENT_RID)
		{
			return "ROLE_STUDENT_RID";
		}else if(roleid==Consts.ROLE_TEACHER_RID)
		{
			return "ROLE_TEACHER_RID";
		}else if(roleid==Consts.ROLE_SCHOOL_RID)
		{
			return getSchoolMenuTxt();
		}else if(roleid==Consts.ROLE_HEAD_TEACHER_RID)
		{
			return getHeadTeacherMenuTxt();
		}else if(roleid==Consts.ROLE_ACCOUNT_RID)
		{
			return getAccountMenuTxt();
		}else if(roleid==Consts.ROLE_ADMINISTRATOR_RID)
		{
			return getAdministratorMenuTxt();
		}else
		{
			return "USER_ROLEID_INVALID";
		}
	}
	
	//TODO  optimize: use one line instead 
	public static String getHeadTeacherMenuTxt()
	{
		String res=
		"<h3>"+
			"<a href='#'>我的班级</a>"+
		"</h3>"+
		"<div style='padding-top: 5px; padding-left: 10px;'>"+
			"<ul>"+
				"<li onclick='headt_clz_teacher_list_page();'><img style='width: 20px; height: 20px;' src='/exam/image/crud/group.png'></img><a href='javascript:;' >班级教师</a></li>"+
				"<li onclick='headt_clz_student_list_page();'><img style='width: 20px; height: 20px;' src='/exam/image/crud/group.png'></img><a href='javascript:;' >班级学生</a></li>"+
			"</ul>"+
		"</div>"+
		/*"<h3>"+
			"<a href='#'>学习统计</a>"+
		"</h3>"+
		"<div class='myinfoui-myrecord' style='padding-top: 5px; padding-left: 10px;'>" +
			"<ul>" +
				"<li onclick='sch_show_class_statistics_page();'><a style='background-position: 6px 2px;' href='javascript:;'>班级统计</a></li>"+
				"<li><a style='background-position: 6px 2px;' href='javascript:;' onclick='show_exercise_history_page(1);'>定点练习</a></li>"+
				"<li><a style='background-position: 6px -109px;' href='javascript:;' onclick='show_exercise_history_page(2);'>组合练习</a></li>"+
				"<li><a style='background-position: 6px -191px;' href='javascript:;' onclick='show_exercise_history_page(4);'>检索练习</a></li>"+
				"<li><a style='background-position: 6px -219px;' href='javascript:;' onclick='show_exercise_history_page(3);'>考试记录</a></li>"+
			"</ul>"+
		"</div>"+
		"<h3>"+
			"<a href='#'>学习分析</a>"+
		"</h3>"+
		"<div style='padding-top: 5px; padding-left: 10px;'>" +
			"<ul>" +
				"<li><img style='width:20px;height:20px;' src='../image/icons/pie_chart_48.png'></img><a href='javascript:;' onclick='s_show_klg_analysis_page();' >知识点分析</a></li>"+
				"<li><div style='border-bottom: 2px solid #99CC01;margin:35px 0 2px 0;'></div></li>"+
				"<li><img style='width:20px;height:20px;' src='../image/icons/pie_chart_48.png'></img><a href='javascript:;' onclick='s_show_exercise_analysis_page(1);' >定点练习分析</a></li>"+
				"<li><img style='width:20px;height:20px;' src='../image/icons/pie_chart_48.png'></img><a href='javascript:;' onclick='s_show_exercise_analysis_page(2);' >组合练习分析</a></li>"+
				"<li><img style='width:20px;height:20px;' src='../image/icons/pie_chart_48.png'></img><a href='javascript:;' onclick='s_show_exercise_analysis_page(4);' >搜索练习分析</a></li>"+
				"<li><img style='width:20px;height:20px;' src='../image/icons/pie_chart_48.png'></img><a href='javascript:;' onclick='s_show_exercise_analysis_page(3);' >作业测验分析</a></li>"+
				"<li><img style='width:20px;height:20px;' src='../image/icons/pie_chart_48.png'></img><a href='javascript:;' onclick='s_show_exercise_analysis_page(0);' >总体学习分析</a></li>"+
			"</ul>" +
		"</div>"+*/
		"<h3>"+
			"<a href='#'>学习分析</a>"+
		"</h3>"+
		"<div style='padding-top: 5px; padding-left: 10px;'>" +
			"<ul>" +
				"<li><img style='width:21px;height:21px;' src='../image/icons/pie_chart.png'></img><a href='javascript:;' onclick='sch_show_class_statistics_page();'>班级统计</a></li>"+
				"<li><img style='width:21px;height:21px;' src='../image/icons/pie_chart.png'></img><a href='javascript:;' onclick='headt_show_student_study_statistic_page();'>学习分析</a></li>"+
			"</ul>"+
		"</div>"+
		"<h3>"+
			"<a href='#'>模型分析</a>"+
		"</h3>"+
		"<div style='padding-top: 5px; padding-left: 10px;'>" +
			"<ul>" +
				"<li><img style='width:21px;height:21px;' src='../image/icons/pie_chart.png'></img><a href='javascript:;' onclick='headt_show_common_module_page();'>公共模型</a></li>"+
				"<li><img style='width:21px;height:21px;' src='../image/icons/pie_chart.png'></img><a  href='javascript:;' onclick='headt_show_individual_module_page();'>个人模型</a></li>"+
				"<li><img style='width:20px;height:20px;' src='../image/icons/pie_chart_48.png'></img><a href='javascript:;' onclick='headt_show_statistic_by_testpaper_page();'>作业测验</a></li>"+
				"<li><img style='width:20px;height:20px;' src='../image/icons/pie_chart_48.png'></img><a  href='javascript:;' onclick='headt_show_statistic_by_class_page();'>各个班级</a></li>"+
				"<li><img style='width:20px;height:20px;' src='../image/icons/pie_chart_48.png'></img><a  href='javascript:;' onclick='headt_show_statistic_by_all_page();'>整个系统</a></li>"+
			"</ul>"+
		"</div>"+
		"<h3>"+
			"<a href='#'>账户信息</a>"+
		"</h3>"+
		"<div style='padding-top: 5px; padding-left: 10px;'>"+
			"<ul>"+
				"<li onclick='show_myinfo();'><img style='width:16px;height:16px;' src='../image/crud/wrench_orange.png'></img><a href='javascript:;' >个人信息</a></li>"+
			"</ul>"+
		"</div>";
		return res;
	}
	public static String getSchoolMenuTxt()
	{
		String res=
		"<h3>"+
			"<a href='#'>班级管理</a>"+
		"</h3>"+
		"<div style='padding-top: 5px; padding-left: 10px;'>"+
			"<ul>"+
				"<li onclick='sch_show_class_management_page();'><img style='width: 20px; height: 20px;' src='/exam/image/crud/wrench_orange.png'></img><a href='javascript:;' >班级管理</a></li>"+
				"<li onclick='sch_show_class_statistics_page();'><img style='width: 20px; height: 20px;' src='/exam/image/icons/pie_chart_48.png'></img><a href='javascript:;'>班级统计</a></li>"+
			"</ul>"+
		"</div>"+
		"<h3>"+
			"<a href='#'>学生管理</a>"+
		"</h3>"+
		"<div style='padding-top: 5px; padding-left: 10px;'>"+
			"<ul>"+
				"<li onclick='sch_show_student_management_page();'><img style='width: 20px; height: 20px;' src='/exam/image/crud/wrench_orange.png'></img><a href='javascript:;' >学生管理</a></li>"+
			"</ul>"+
		"</div>"+
		"<h3>"+
			"<a href='#'>课程管理</a>"+
		"</h3>"+
		"<div style='padding-top: 5px; padding-left: 10px;'>" +
			"<ul>" +
				"<li onclick='sch_show_school_course_page();'><img style='width: 20px; height: 20px;' src='/exam/image/crud/hammer_screwdriver.png'></img><a href='javascript:;'>集体课程</a></li>"+
				"<li onclick='sch_show_class_course_page();'><img style='width: 20px; height: 20px;' src='/exam/image/crud/hammer_screwdriver.png'></img><a href='javascript:;'>班级课程</a></li>"+
				"<li onclick='sch_show_request_course_page();'><img style='width: 20px; height: 20px;' src='/exam/image/crud/text_lowercase.png'></img><a href='javascript:;'>申请课程</a></li>"+
			"</ul>"+
		"</div>"+
		"<h3>"+
			"<a href='#'>教师管理</a>"+
		"</h3>"+
		"<div style='padding-top: 5px; padding-left: 10px;'>" +
			"<ul>" +
				"<li onclick='sch_show_headteacher_management_page();'><img style='width: 20px; height: 20px;' src='/exam/image/crud/group.png'></img><a href='javascript:;'>班主任管理</a></li>"+
				"<li onclick='sch_show_teacher_management_page();'><img style='width: 20px; height: 20px;' src='/exam/image/crud/group.png'></img><a href='javascript:;'>教师管理</a></li>"+
			"</ul>" +
		"</div>"+
		"<h3>"+
			"<a href='#'>账户信息</a>"+
		"</h3>"+
		"<div style='padding-top: 5px; padding-left: 10px;'>"+
			"<ul>"+
				"<li onclick='show_myinfo();'><img style='width:16px;height:16px;' src='../image/crud/wrench_orange.png'></img><a href='javascript:;' >个人信息</a></li>"+
			"</ul>"+
		"</div>";
		return res;
	}
	public static String getAccountMenuTxt()
	{
		String res=
		"<h3>"+
			"<a href='#'>财务管理</a>"+
		"</h3>"+
		"<div style='padding-top: 5px; padding-left: 10px;'>"+
			"<ul>"+
				"<li onclick='account_class_course_manage_page();'><img style='width: 20px; height: 20px;' src='/exam/image/crud/group.png'></img><a href='javascript:;' >集体课程购买</a></li>"+
				"<li onclick='account_student_course_manage_page();'><img style='width: 20px; height: 20px;' src='/exam/image/crud/group.png'></img><a href='javascript:;' >个人课程购买</a></li>"+
			"</ul>"+
		"</div>"+
		"<h3>"+
			"<a href='#'>账户信息</a>"+
		"</h3>"+
		"<div style='padding-top: 5px; padding-left: 10px;'>"+
			"<ul>"+
				"<li onclick='show_myinfo();'><img style='width:16px;height:16px;' src='../image/crud/wrench_orange.png'></img><a href='javascript:;' >个人信息</a></li>"+
			"</ul>"+
		"</div>";
		return res;
	}
	//TODO  optimize: use one line instead 
	public static String getAdministratorMenuTxt()
	{
		String res=
		"<h3>"+
			"<a href='#'>系统课程</a>"+
		"</h3>"+
		"<div style='padding-top: 5px; padding-left: 10px;'>"+
			"<ul>"+
				"<li onclick='adm_show_course_management_page();'><img style='width: 20px; height: 20px;' src='/exam/image/crud/wrench_orange.png'></img><a href='javascript:;' >系统课程设置</a></li>"+
			"</ul>"+
		"</div>"+
		"<h3>"+
			"<a href='#'>集体管理</a>"+
		"</h3>"+
		"<div style='padding-top: 5px; padding-left: 10px;'>"+
			"<ul>"+
				"<li onclick='adm_show_school_management_page();'><img style='width: 20px; height: 20px;' src='/exam/image/crud/wrench_orange.png'></img><a href='javascript:;' >集体用户</a></li>"+
				"<li onclick='adm_show_school_reg_approve_page();'><img style='width: 20px; height: 20px;' src='/exam/image/crud/wrench_orange.png'></img><a href='javascript:;' >集体注册审批</a></li>"+
				"<li onclick='adm_show_school_course_approve_page();'><img style='width: 20px; height: 20px;' src='/exam/image/crud/wrench_orange.png'></img><a href='javascript:;' >集体课程审批</a></li>"+
			"</ul>"+
		"</div>"+
		"<h3>"+
			"<a href='#'>用户管理</a>"+
		"</h3>"+
		"<div style='padding-top: 5px; padding-left: 10px;'>"+
			"<ul>"+
				"<li onclick='adm_show_user_management_page();'><img style='width: 20px; height: 20px;' src='/exam/image/crud/wrench_orange.png'></img><a href='javascript:;' >在线用户列表</a></li>"+
			"</ul>"+
		"</div>"+
		"<h3>"+
			"<a href='#'>论坛管理</a>"+
		"</h3>"+
		"<div style='padding-top: 5px; padding-left: 10px;'>"+
			"<ul>"+
				"<li onclick='adm_show_forum_management_page();'><img style='width: 20px; height: 20px;' src='/exam/image/crud/wrench_orange.png'></img><a href='javascript:;' >论坛管理员设置</a></li>"+
			"</ul>"+
		"</div>"+
		"<h3>"+
			"<a href='#'>数据更新</a>"+
		"</h3>"+
		"<div style='padding-top: 5px; padding-left: 10px;'>"+
			"<ul>"+
				"<li onclick='adm_show_system_update_page();'><img style='width: 20px; height: 20px;' src='/exam/image/crud/wrench_orange.png'></img><a href='javascript:;' >系统数据更新</a></li>"+
			"</ul>"+
		"</div>"+
		"<h3>"+
			"<a href='#'>创新管理</a>"+
		"</h3>"+
		"<div style='padding-top: 5px; padding-left: 10px;'>"+
			"<ul>"+
				"<li onclick='adm_show_innovation_management_page();'><img style='width: 20px; height: 20px;' src='/exam/image/crud/wrench_orange.png'></img><a href='javascript:;' >创新学习奖励计划</a></li>"+
			"</ul>"+
		"</div>"+
		"<h3>"+
			"<a href='#'>课程开发</a>"+
		"</h3>"+
		"<div style='padding-top: 5px; padding-left: 10px;'>"+
			"<ul>"+
				"<li onclick='adm_show_course_dev_request_approve_page();'><img style='width: 20px; height: 20px;' src='/exam/image/crud/wrench_orange.png'></img><a href='javascript:;' >课程开发申请审批</a></li>"+
				"<li onclick='adm_show_course_dev_klg_approve_page();'><img style='width: 20px; height: 20px;' src='/exam/image/crud/wrench_orange.png'></img><a href='javascript:;' >知识点设计审批</a></li>"+
				"<li onclick='adm_show_course_dev_item_approve_page();'><img style='width: 20px; height: 20px;' src='/exam/image/crud/wrench_orange.png'></img><a href='javascript:;' >题目设计开发审批</a></li>"+
				"<li onclick='adm_show_course_dev_tp_approve_page();'><img style='width: 20px; height: 20px;' src='/exam/image/crud/wrench_orange.png'></img><a href='javascript:;' >试卷设计开发审批</a></li>"+
				"<li onclick='adm_show_course_dev_migration_page();'><img style='width: 20px; height: 20px;' src='/exam/image/crud/wrench_orange.png'></img><a href='javascript:;' >课程复制迁移</a></li>"+
			"</ul>"+
		"</div>"+
		"<h3>"+
			"<a href='#'>通知系统</a>"+
		"</h3>"+
		"<div style='padding-top: 5px; padding-left: 10px;'>"+
			"<ul>"+
				"<li onclick='todo();'><img style='width: 20px; height: 20px;' src='/exam/image/crud/wrench_orange.png'></img><a href='javascript:;' >用户成绩通知</a></li>"+
				"<li onclick='todo();'><img style='width: 20px; height: 20px;' src='/exam/image/crud/wrench_orange.png'></img><a href='javascript:;' >集体用户通知</a></li>"+
				"<li onclick='todo();'><img style='width: 20px; height: 20px;' src='/exam/image/crud/wrench_orange.png'></img><a href='javascript:;' >系统更新通知</a></li>"+
				"<li onclick='todo();'><img style='width: 20px; height: 20px;' src='/exam/image/crud/wrench_orange.png'></img><a href='javascript:;' >其它信息通知</a></li>"+
			"</ul>"+
		"</div>"+
		"<h3>"+
			"<a href='#'>账户信息</a>"+
		"</h3>"+
		"<div style='padding-top: 5px; padding-left: 10px;'>"+
			"<ul>"+
				"<li onclick='show_myinfo();'><img style='width:16px;height:16px;' src='../image/crud/wrench_orange.png'></img><a href='javascript:;' >个人信息</a></li>"+
			"</ul>"+
		"</div>";
		return res;
	}

}
