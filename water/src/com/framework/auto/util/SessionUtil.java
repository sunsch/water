package com.framework.auto.util;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.exam.constant.Consts;
import com.exam.entity.Users;

public class SessionUtil 
{
	public static Users getLogonUser(HttpServletRequest request)
	{
		return (Users)request.getSession().getAttribute(Consts.SESSION_ATTRIBUTE_LOGON_USER);
		
	}
	public static Users getLogonUser(HttpSession session)
	{
		return (Users)session.getAttribute(Consts.SESSION_ATTRIBUTE_LOGON_USER);
		
	}
	public static int getLogonUID(HttpServletRequest request)
	{
		return ((Users)request.getSession().getAttribute(Consts.SESSION_ATTRIBUTE_LOGON_USER)).getUserid();
		
	}
	

}
