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
public class EncodingFilter implements Filter
{
	private FilterConfig config;
	private String targetEncoding;

	public EncodingFilter() 
	{
		config = null;
		targetEncoding = "ASCII";
	}

	public void init(FilterConfig config) throws ServletException 
	{
		this.config = config;
		targetEncoding = config.getInitParameter("encoding");
	}

	public void destroy() 
	{
		config = null;
		targetEncoding = null;
	}

	public void doFilter(ServletRequest srequest, ServletResponse sresponse,FilterChain chain) throws IOException,ServletException 
	{
		HttpServletRequest request = (HttpServletRequest) srequest;
		HttpServletResponse response = (HttpServletResponse) sresponse;

		request.setCharacterEncoding(targetEncoding);
		sresponse.setContentType("text/html; charset=UTF-8");
		sresponse.setCharacterEncoding(targetEncoding);
		chain.doFilter(srequest, sresponse);
	}

}
