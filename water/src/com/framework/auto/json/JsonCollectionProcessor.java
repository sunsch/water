package com.framework.auto.json;


import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonCollectionProcessor implements JsonValueProcessor {
	

	public JsonCollectionProcessor() {
		super();
	}


	@Override
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		String[] obj = {};
		if (value instanceof Date[]) 
		{
			
		}
		return obj;
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		if (value instanceof java.util.Date) 
		{
		}
		return value==null?"NULL_OBJECT":value.toString();
	}


}
