package com.framework.auto.json;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.hibernate.collection.PersistentSet;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonDateProcessor implements JsonValueProcessor {
	
	private String format = "yyyy-MM-dd";
	private String fullFormat= "yyyy-MM-dd HH:mm:ss";


	public JsonDateProcessor() {
		super();
	}

	public JsonDateProcessor(String format) {
		super();
		this.format = format;
	}

	
	@Override
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		String[] obj = {};
		if (value instanceof Date[]) 
		{
			//System.out.println("-----value="+value.getClass().toString());
			
			SimpleDateFormat sf = new SimpleDateFormat(format);
			Date[] dates = (Date[]) value;
			obj = new String[dates.length];
			for (int i = 0; i < dates.length; i++) 
			{
				obj[i] = sf.format(dates[i]);
			}
		}
		return obj;
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		if(value!=null)
		{
			if ((value instanceof java.util.Date) || (value instanceof java.sql.Timestamp)) 
			{
				//System.out.println("key="+key+"-----value="+value.getClass().toString()+"|value instanceof java.util.Date");
				String str = new SimpleDateFormat(format).format((Date) value);
				return str;
			}else if ((value instanceof java.util.Set)||(value instanceof PersistentSet)) 
			{
				//System.out.println("key="+key+"-----value="+value.getClass().toString()+"|value instanceof org.hibernate.collection.PersistentSet");
				return "";
			}else if ((value instanceof java.util.List)||(value instanceof Vector)) 
			{
				//System.out.println("key="+key+"-----value="+value.getClass().toString()+"|value instanceof org.hibernate.collection.PersistentSet");
				return JSONArray.fromObject(value, jsonConfig);
			}else
			{
				System.out.println("processObjectValue: not all. key="+key+"-----value="+value.getClass().toString()+"|value instanceof nothing");
			}
		}
		
		return value==null?"":value.toString();
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}


}
