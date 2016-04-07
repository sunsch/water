package com.framework.util;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringBean {
	
	public static Object getBean(ServletConfig config,String beanName)
	{
		ServletContext context = config.getServletContext();
        WebApplicationContext webContxt = WebApplicationContextUtils.getWebApplicationContext(context);
        return webContxt.getBean(beanName);
	}

}
