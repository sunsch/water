package com.exam.cache.handler;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.entity.IUserlogDao;
import com.framework.auto.util.SpringBean;

public class InitializeCacheServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	/*
	 * NOTE:
	 * Make sure memcached is only initialized once in a tomcat cluster environment.
	 * set this to true for the first tomcat node deployment
	 * change this value to false for the others druing deployment
	 */
	private static final boolean FIST_NODE_INITIALIZE_OF_CLUSTER=true;

	@Override
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		
		IUserlogDao logdao=(IUserlogDao)SpringBean.getBean(this.getServletConfig(), "UserlogDaoTxProxy");
		
		
	}

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
	}

}