package com.framework.auto.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.constant.Consts;
import com.exam.entity.Users;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class AuthenticationFilter implements Filter 
{
	public AuthenticationFilter() 
	{

	}
	public void init(FilterConfig config) throws ServletException 
	{

	}
	public void destroy() 
	{

	}
	public void doFilter(ServletRequest srequest, ServletResponse sresponse,FilterChain chain) throws IOException,ServletException 
	{
		HttpServletRequest request = (HttpServletRequest) srequest;
		HttpServletResponse response = (HttpServletResponse) sresponse;
		Users u=SessionUtil.getLogonUser(request);
		System.out.println("in AuthenticationFilter...("+request.getRequestURI()+"----"+request.getContextPath()+")");
		if(u!=null)//仅对登录的用户判断权限，如果设置了不需登录，那么不判断权限
		{
			//登录后，仅对下面的4个目录作保护，如果还有其它的，那么可以增加
			boolean studentPage=(request.getRequestURI().indexOf(request.getContextPath()+"/student/") != -1);
			boolean teacherPage=(request.getRequestURI().indexOf(request.getContextPath()+"/teacher/") != -1);
			boolean schoolPage=(request.getRequestURI().indexOf(request.getContextPath()+"/school/") != -1);
			boolean adminPage=(request.getRequestURI().indexOf(request.getContextPath()+"/admin/") != -1);
			boolean courseAdminPage=(request.getRequestURI().indexOf(request.getContextPath()+"/course-admin/") != -1);
			boolean accountPage=(request.getRequestURI().indexOf(request.getContextPath()+"/account/") != -1);
			
			if(studentPage)
			{
				if(u.getRoleid()!=Consts.ROLE_STUDENT_RID&&u.getRoleid()!=Consts.ROLE_TEACHER_RID)
				{
					System.out.println("you are not authed to access the page: "+request.getRequestURI().toString());
					response.sendRedirect(request.getContextPath()+"/login/login.jsp");
					return;
				}else
				{
				}
				
				
			}else if(teacherPage)
			{
				if(u.getRoleid()!=Consts.ROLE_TEACHER_RID)
				{
					System.out.println("you are not authed to access the page: "+request.getRequestURI().toString());
					response.sendRedirect(request.getContextPath()+"/login/login.jsp");
					return;
				}else
				{
				}
				
			}else if(schoolPage)
			{
				if(u.getRoleid()!=Consts.ROLE_SCHOOL_RID)
				{
					System.out.println("you are not authed to access the page: "+request.getRequestURI().toString());
					response.sendRedirect(request.getContextPath()+"/login/login.jsp");
					return;
				}else
				{
				}
				
			}else if(adminPage)
			{
				if(u.getRoleid()!=Consts.ROLE_ADMINISTRATOR_RID)
				{
					System.out.println("you are not authed to access the page: "+request.getRequestURI().toString());
					response.sendRedirect(request.getContextPath()+"/login/login.jsp");
					return;
				}else
				{
				}
			}else if(courseAdminPage)
			{
				if(u.getRoleid()!=Consts.ROLE_COURSE_ADMIN_RID)
				{
					System.out.println("you are not authed to access the page: "+request.getRequestURI().toString());
					response.sendRedirect(request.getContextPath()+"/login/login.jsp");
					return;
				}else
				{
				}
			}else if(accountPage)
			{
				if(u.getRoleid()!=Consts.ROLE_ACCOUNT_RID)
				{
					System.out.println("you are not authed to access the page: "+request.getRequestURI().toString());
					response.sendRedirect(request.getContextPath()+"/login/login.jsp");
					return;
				}else
				{
				}
			}
			
		}//end first if
		
		chain.doFilter(srequest, sresponse);
		
	}

}
