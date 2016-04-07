package com.framework.auto.json;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
public class JsonHelper 
{
	private static String[] configFilter = new String[]{"comments","relDocTrees"};
	
    public static JsonConfig getFilter(final String[] s)
    {
        JsonConfig config = new JsonConfig();
        config.setJsonPropertyFilter(new PropertyFilter()
        {
            public boolean apply(Object source, String name, Object value) 
            {
             if(juge(s,name)) 
             {
            	 return true;
             } else 
             {
            	 return false;
             }
            }
            
            public boolean juge(String[] s,String s2)
            {
                boolean b = false;
                for(String sl : s){
                    if(s2.equals(sl)){
                        b=true;
                    }
                }
                return b;
            }
           });
        return config;
    }
 // 转换的时候调用过滤器
  /**
   * @param entityList
   * @param cfg 自定义过滤条件
   * @return 对象数组
   */
  public static Object getJsonObject(List entityList,String[] cfgFilter){
	   JsonConfig config=new JsonConfig(); 
	   config.setExcludes(cfgFilter);
       JSONArray jSONArray  =JSONArray.fromObject(entityList,config);
       return jSONArray.toArray();
 }
  /**
   * @param entityList
   * @param cfg 自定义过滤条件
   * @return 对象数组
   */
  public static Object getJsonArrayObject(Map entityList,String[] cfgFilter){
	   JsonConfig config=new JsonConfig(); 
	   config.setExcludes(cfgFilter);
       JSONArray jSONArray  =JSONArray.fromObject(entityList,config);
       return jSONArray.toArray();
 }
  /**
   * 
   * @param entityList
   * @return 对象数组
   */
 public static Object getJsonArrayObject(List entityList){
	 JsonConfig config=new JsonConfig(); 
	 config.setExcludes(configFilter);
      JSONArray jSONArray  =JSONArray.fromObject(entityList,config);
      return jSONArray.toArray();
}
public static Object getJsonArrayObject(Map entityList){
	  JsonConfig config=new JsonConfig(); 
	  config.setExcludes(configFilter);
//      JsonConfig config = getFilter(configFilter);//String数组中存储的是要过滤的属性 
      JSONArray jSONArray  =JSONArray.fromObject(entityList,config);
      return jSONArray.toArray();
} 
/**
 *  
 * @param jsonStr json对象
 * @param entityClass 实体类  xxx.class
 * @return  Object 对象
 */
public static Object JsonToEntity(String jsonStr,Class entityClass){  
        JSONObject jsonObject = JSONObject.fromObject( jsonStr );  
        return JSONObject.toBean(jsonObject, entityClass);
    }  
}
