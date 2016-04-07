package com.exam.utils.json;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class JSONTool 
{
	public static String toJSON(String[]keys,String[]values)
	{
		String res="{";
		for(int i=0;i<keys.length;i++)
		{
			res+="\""+keys[i]+"\":\""+values[i]+"\",";
		}
		res=res.substring(0,res.length()-1);
		res+="}";
		JSONObject jo=JSONObject.fromObject(res);
		return jo.toString();
	}
	public static String toString(Object o)
	{
		JsonConfig jsonConfig = new JsonConfig();
		//jsonConfig.setJsonPropertyFilter(new JsonIncluder(new String[]{"userid","userName","realName","roleid"}));
		JSONObject jsonObject = JSONObject.fromObject(o,jsonConfig);
		return jsonObject.toString();
	}
	public static void test()
	{
		JSONTool.toJSON(new String[]{""}, new String[]{""});
	}
	

}
