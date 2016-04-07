package com.framework.util;

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
public class EncodingFilter
	implements Filter {
	public EncodingFilter() {
		config = null;
		targetEncoding = "ASCII";
	}

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		targetEncoding = config.getInitParameter("encoding");
	}

	public void destroy() {
		config = null;
		targetEncoding = null;
	}

	public void doFilter(ServletRequest srequest, ServletResponse sresponse,
						 FilterChain chain) throws IOException,
						 ServletException 
 {
		HttpServletRequest request = (HttpServletRequest) srequest;
		HttpServletResponse response = (HttpServletResponse) sresponse;
		boolean doFilter = false;
		//System.out.println(request.getRequestURI()+"   "+request.getRequestURL());
		//System.out.println(request.getRequestURI());
		/*
		 if (!request.getRequestURI().endsWith("SecurityCheck.do")
		 && !request.getRequestURI().endsWith(".gif")
		 && ! (request.getRequestURI().endsWith(".jpg"))
		 && ! (request.getRequestURI().endsWith(".swf"))
		 && ! (request.getRequestURI().endsWith(".html"))
		 && ! (request.getRequestURI().endsWith(".htm"))
		 && ! (request.getRequestURI().endsWith(".xml"))
		 && ! (request.getRequestURI().endsWith(".css"))
		 && ! (request.getRequestURI().endsWith("index.jsp"))
		 && (request.getRequestURI().indexOf("ebuy") == -1)
		 && (request.getRequestURI().indexOf("supply") == -1)
		 && ! (request.getRequestURI().endsWith("sendInterface.jsp"))) {
		 HttpServletResponse response = (HttpServletResponse) sresponse;
		 String corpCode = CommUtil.getCorpCode(request);
		 String userId = CommUtil.getUserId(request);
		 if (corpCode.equals("") || userId.equals("")) {
		  doFilter = true;
		  response.sendRedirect("/index.html");
		 }

		 }*/
		if (!doFilter) 
		{
			request.setCharacterEncoding(targetEncoding);
			sresponse.setContentType("text/html; charset=UTF-8");
			sresponse.setCharacterEncoding(targetEncoding);
			chain.doFilter(srequest, sresponse);
		}
	}

	private FilterConfig config;
	private String targetEncoding;


}
