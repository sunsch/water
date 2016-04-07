package com.framework.auto.util;

import java.util.HashSet;

import org.hibernate.collection.PersistentSet;

import com.framework.auto.json.JsonDateProcessor;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

public class JsonDateUtil {
	
	
	public static void main(String[] args) {
		PersistentSet ps=new org.hibernate.collection.PersistentSet();
		
		java.util.HashSet h=new java.util.HashSet();

		System.out.println(h instanceof java.util.HashSet);
		System.out.println(ps instanceof java.util.Set);
	}
	public static void main2(String[] args) {
		
		//Students s=new Students();
		//注册date类型的转化方式
		 JsonConfig jsonConfig = new JsonConfig();
		 jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JsonDateProcessor());
		 
		JSONObject jsonFromBean = JSONObject.fromObject("",jsonConfig);
		System.out.println(jsonFromBean);
		
		//prints {"birthday":"2009-10-26","id":"id","name":"name"}
		
		String[] dateFormats = new String[] {"yyyy/MM/dd","yyyy-MM-dd"}; 
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats)); 
		//Students jsonToBean = (Students)JSONObject.toBean(jsonFromBean,Students.class);
		//System.out.println(jsonToBean);
		//prints TestBean@1126b07[id=id,name=name,birthday=Mon Oct 26 00:00:00 CST 2009]
	}

}
