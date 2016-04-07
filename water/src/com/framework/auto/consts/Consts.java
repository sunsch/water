package com.framework.auto.consts;

import com.framework.auto.util.PropertiesUtil;

public class Consts {
	
	public static final String OS_FILE_ENCODING=System.getProperty("file.encoding");
	public static final String WEB_PAGE_ENCODING="UTF-8";
	public static final String WEB_PAGE_TITLE;
	public static final int ITEMS_PER_PAGE;
	
	static{
		PropertiesUtil r=new PropertiesUtil("com.framework.auto.prop.webpage");
		WEB_PAGE_TITLE=r.getString("webpage.all.title");
		ITEMS_PER_PAGE=Integer.parseInt(r.getString("webpage.list.itermsperpage").trim());
		//System.out.print(WEB_PAGE_TITLE+ITEMS_PER_PAGE);
	}

}
