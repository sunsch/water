package com.exam.action.proxy.account;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exam.constant.Consts;
import com.exam.entity.IUserlogDao;
import com.exam.entity.Users;
import com.framework.auto.util.SessionUtil;
import com.framework.auto.util.SpringBean;

public class LoginProxy 
{
	public static Log log = LogFactory.getLog("Audit"); 

	public boolean isAuthed(Users logonu,HttpServletRequest request)
	{
		if(logonu.getRoleid()!=Consts.ROLE_ACCOUNT_RID)
		{
			log.error("The current user[uid="+logonu.getRoleid()+" username="+logonu.getUsername()+"] goes into "+this.getClass().getName()+", but not a ROLE_ACCOUNT_RID user!");
			return false;
		}
		return true;
	}


	public void doLogin(HttpServletRequest request,HttpServletResponse response,ServletConfig sc,String args) throws IOException 
	{

		IUserlogDao logdao=(IUserlogDao)SpringBean.getBean(sc, "UserlogDaoTxProxy");

		JSONObject argsJson = JSONObject.fromObject( args ); 
		String username=argsJson.getString("username");
		String password=argsJson.getString("password");

		
		//List ol=logdao.query("from Users u where u.username='"+username+"' and u.password='"+password+"'");
		List ol=logdao.exactFind("from Users u where u.username=:username and u.password=:password", new String[]{"username","password"},  new String[]{username,password});
		JSONObject res=new JSONObject();
		if(ol.size()>0)
		{
			res.put("flag", "success");
			Users u=(Users)ol.get(0);
			request.getSession().setAttribute(Consts.SESSION_ATTRIBUTE_LOGON_USER,u);
		}else
		{
			res.put("flag", "failed");
		}
		response.getWriter().write(res.toString());


	}
	public void doLogout(HttpServletRequest request,HttpServletResponse response,ServletConfig sc,String args) throws IOException 
	{

		request.getSession().removeAttribute(Consts.SESSION_ATTRIBUTE_LOGON_USER);

		JSONObject res=new JSONObject();
		res.put("flag", "success");
		response.getWriter().write(res.toString());


	}


	public void testAddUser(HttpServletRequest request,HttpServletResponse response,ServletConfig sc,String args) throws IOException 
	{
		Users logonu=SessionUtil.getLogonUser(request);

		IUserlogDao logdao=(IUserlogDao)SpringBean.getBean(sc, "UserlogDaoTxProxy");

		JSONObject argsJson = JSONObject.fromObject( args ); 
		String username=argsJson.getString("username");
		String password=argsJson.getString("password");
		Users u=new Users();
		u.setUsername(username);
		u.setPassword(password);

		logdao.save( u);


		List ol=logdao.query("from Users ");
		JSONArray jsonObject = JSONArray.fromObject(ol);
		response.getWriter().write(jsonObject.toString());

		/*
		String hql="select count(*) from Users ";
		List l=logdao.query(hql);
		int count=Integer.parseInt(l.get(0).toString());
		String res="{count:"+count+"}";
		JSONObject jo=JSONObject.fromObject(res);
		response.getWriter().write(jo.toString());*/

	}


}
