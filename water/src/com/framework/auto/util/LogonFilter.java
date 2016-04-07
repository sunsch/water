package com.framework.auto.util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
public class LogonFilter implements Filter 
{
	public LogonFilter() 
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
		String req=request.getRequestURI();
		//System.out.println("REQUEST:"+req);
		if(req.endsWith(".js"))
		{
			response.setDateHeader("Expires", ( Long.MAX_VALUE)); 
		}
		
		boolean pageReq=req.indexOf(".jsp")!=-1||req.indexOf(".html")!=-1||req.indexOf(".htm")!=-1;//||req.indexOf("Handler")!=-1
		if(pageReq)//only check for jsp handler html htm pages, others ignored
		{
			//System.out.println("jsp or Hander request....("+request.getRequestURI().toString()+")");
			boolean loginPage=(request.getRequestURI().indexOf("/login") != -1);
			boolean loginHandler=(request.getRequestURI().indexOf("LoginHandler") != -1);
			boolean logoutHandler=(request.getRequestURI().indexOf("LogoutHandler") != -1);
			boolean regPage=(request.getRequestURI().indexOf("/register") != -1);
			boolean regHandler=(request.getRequestURI().indexOf("RegisterHandler") != -1);
			boolean document=(request.getRequestURI().indexOf("/Document") != -1);
			boolean template=(request.getRequestURI().indexOf("/template") != -1);
			boolean NewsProceedings=(request.getRequestURI().indexOf("/NewsProceedings") != -1);
			boolean home=(request.getRequestURI().indexOf("/water.html") != -1);
			boolean initialRequest=template||NewsProceedings||loginPage||loginHandler||logoutHandler||regPage||regHandler||document||home;
			if(!initialRequest)//is not initialRequest, Logon check
			{
				//System.out.println("is not initialRequest, Logon check....("+request.getRequestURI().toString()+")");
				if(request.getSession().getAttribute("user")==null)
				{
					//System.out.println("is not initialRequest, user is not logon....("+request.getRequestURI().toString()+")");
					
					response.sendRedirect(request.getContextPath()+"/login/login.jsp");
					return;
				}else//continue other filters
				{
					chain.doFilter(srequest, sresponse);
				}
			}else//login page, break other filters
			{
				chain.doFilter(srequest, sresponse);
			}
		}else//other request, such as css image.., break other filters
		{
			chain.doFilter(srequest, sresponse);
		}
		
		
		
	}

}
