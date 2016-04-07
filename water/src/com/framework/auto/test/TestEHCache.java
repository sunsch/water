package com.framework.auto.test;

import java.util.Vector;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class TestEHCache {

	
	public static void main(String[] args) {
		CacheManager cm = new CacheManager();  
		Cache cache = cm.getCache("exam.cache");  
		
		for(int i=0;i<4000;i++)
		{
			Vector<String> vs=new Vector<String>(1000);
			for(int j=0;j<5000;j++)
			{
				vs.add("i="+i+",j="+j+",value=tttttttttttttttttttttttttt");
			}
			cache.put(new Element("key="+i, vs)); 
			if(i%100==0)
			{
				System.out.println("puting i="+i);
			}
		}
		
		for(int i=100;i<101;i++)
		{
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//System.out.println(cache.get("key="+i).getValue());
			Vector<String> vs=(Vector<String>) cache.get("key="+i).getValue();
			for(String s:vs)
			{
				System.out.println(s);
			}
			
		}
		
	}
}
