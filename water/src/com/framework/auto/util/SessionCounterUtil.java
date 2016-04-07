package com.framework.auto.util;

import java.util.Vector;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.exam.entity.Users;

public class SessionCounterUtil implements HttpSessionListener{

	//private static int activeSessions = 0; 
	private static Vector<Users> onlines=new Vector<Users>();

	//this method invoked by the first time website visiting
	//this is not used, use session_Created
	public void sessionCreated(HttpSessionEvent se)
	{ 
		//activeSessions++; 
		print("sessionCreated:\n",onlines);
	} 
	
	//this is used when one user login
	//SessionCounter.Destroysession(user);    //注user  为用户实体
	public  static void session_Created(HttpSessionEvent se) 
	{ 
		//activeSessions++; 
		onlines.add((Users)se.getSession().getAttribute("user"));
		print("session_Created:\n",onlines);
	} 
	
	//this is not used, will statistic by sessionDestroyed
	//SessionCounter.Destroysession(user);    //注user  为用户实体
	public  static void session_Destroyed(Users se)
	{ 
		if(onlines.contains(se))
		{
			//activeSessions--; 
			onlines.remove(se);
		}
		print("session_Destroyed:\n",onlines);
	}

	//this is used.
	public void sessionDestroyed(HttpSessionEvent se)
	{
		onlines.remove((Users)se.getSession().getAttribute("user"));
		print("sessionDestroyed:\n",onlines);

	} 

	public static int getOnlineCount()
	{
		return onlines.size();
	}
	public static void print(String debug,Vector<Users> onlines)
	{
		//Log.warning(debug+onlines.size());
		for(Users u:onlines)
		{
			//Log.console(u.getUid()+u.getUsername());
		}
	}
}
