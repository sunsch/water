package com.framework.auto.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.*;

public class JSONUtil {
	
	
	/**
     * 将json转换为java集合对象
     */
	public static <T> List<T> getJavaCollection(T clazz, String jsons) 
	{
        List<T> objs=null;
        JSONArray jsonArray=(JSONArray)JSONSerializer.toJSON(jsons);
        if(jsonArray!=null){
            objs=new ArrayList<T>();
            List list=(List)JSONSerializer.toJava(jsonArray);
            for(Object o:list){
                JSONObject jsonObject=JSONObject.fromObject(o);
                T obj=(T)JSONObject.toBean(jsonObject, clazz.getClass());
                objs.add(obj);
            }
        }
        return objs;
    }

}
