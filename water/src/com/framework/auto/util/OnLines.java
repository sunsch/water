package com.framework.auto.util;

import java.util.Vector;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.exam.entity.Users;

public class OnLines  {

	public static Vector<Users> onlines=new Vector<Users>();
	public static int totalLogonCount=-1;

	public static void login(Users u)
	{
		onlines.add(u);
		//print("login:");
	}
	public static void logout(Users u)
	{
		onlines.remove(u);
		//print("logout:");
	}
	
	
	public static int getOnlineCount()
	{
		return onlines.size();
		//+new java.util.Random().nextInt(15)+3;
	}
	
	
	

	
}
