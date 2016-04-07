package com.framework.auto.opt;

import java.util.Vector;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.framework.auto.basicentity.Operations;
import com.framework.auto.util.Log;


public class DBOpts
{
	public static void reset(Vector<String> handlers)
	{
		SessionFactory sf = new Configuration().configure("com/spring/config/hibernate.cfg.xml").buildSessionFactory();
		Session s =sf.openSession();
		Transaction t=s.beginTransaction();
		
		clearAllOpts(s);
		addAllOpts(handlers,s);
		
		t.commit();
		s.close();
	}
	public static void clearAllOpts(Session s)
	{
		
		Query q1=s.createQuery("delete from Operations q where q.optname like '%Handler'");
		int us=q1.executeUpdate();
		Log.console("delete from Operations q where q.optname like '%Handler' ----count:"+us);	
		
	
	}
	public static void addAllOpts(Vector<String> handlers,Session s)
	{
		for(String h:handlers)
		{
			Operations o=new Operations();
			o.setOptname(h);
			s.save(o);
			Log.console("save handlers...."+h);
		}
		
	}

}
