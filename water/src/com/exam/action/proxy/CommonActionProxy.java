package com.exam.action.proxy;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CommonActionProxy extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static Log log = LogFactory.getLog("Audit"); 
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		process(request,response);

	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		process(request,response);
	}
	
	protected void process(HttpServletRequest request,HttpServletResponse response) 
	{
		String clazz=request.getParameter("clazz");
		String service=request.getParameter("service");
		String args=request.getParameter("args");
		
		try {
			Class c = Class.forName(clazz);
			Object instance=c.newInstance();
			Method m=c.getMethod(service, HttpServletRequest.class,HttpServletResponse.class,ServletConfig.class,String.class);
			m.invoke(instance,request,response,this.getServletConfig(), args);
			
			
		}catch (Exception e) 
		{
			//log.error("[ERROR [CommonActionProxy] ERROR] clazz="+clazz+"  service="+service+" args="+args+" msg="+e.getCause().toString());
			e.printStackTrace();
		} 

	}

}


