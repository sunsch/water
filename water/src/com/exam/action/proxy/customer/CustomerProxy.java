package com.exam.action.proxy.customer;

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
import com.exam.entity.Customer;
import com.exam.entity.IUserlogDao;
import com.exam.entity.Users;
import com.framework.auto.util.SessionUtil;
import com.framework.auto.util.SpringBean;

public class CustomerProxy 
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


	public void getCustomerList(HttpServletRequest request,HttpServletResponse response,ServletConfig sc,String args) throws IOException 
	{

		IUserlogDao logdao=(IUserlogDao)SpringBean.getBean(sc, "UserlogDaoTxProxy");
		JSONObject argsJson = JSONObject.fromObject( args ); 

		List ol=logdao.query("from Customer ");
		
		JSONArray jsonObject = JSONArray.fromObject(ol);
		response.getWriter().write(jsonObject.toString());


	}
	public void getCommonCustomerList(HttpServletRequest request,HttpServletResponse response,ServletConfig sc,String args) throws IOException 
	{

		IUserlogDao logdao=(IUserlogDao)SpringBean.getBean(sc, "UserlogDaoTxProxy");
		JSONObject argsJson = JSONObject.fromObject( args ); 

		List ol=logdao.query("from Customer c where c.types=0");
		
		JSONArray jsonObject = JSONArray.fromObject(ol);
		response.getWriter().write(jsonObject.toString());


	}
	public void getAgentList(HttpServletRequest request,HttpServletResponse response,ServletConfig sc,String args) throws IOException 
	{

		IUserlogDao logdao=(IUserlogDao)SpringBean.getBean(sc, "UserlogDaoTxProxy");
		JSONObject argsJson = JSONObject.fromObject( args ); 

		List ol=logdao.query("from Customer c where c.types=1");
		
		JSONArray jsonObject = JSONArray.fromObject(ol);
		response.getWriter().write(jsonObject.toString());


	}


	public void addCustomer(HttpServletRequest request,HttpServletResponse response,ServletConfig sc,String args) throws IOException 
	{
		Users logonu=SessionUtil.getLogonUser(request);

		IUserlogDao logdao=(IUserlogDao)SpringBean.getBean(sc, "UserlogDaoTxProxy");

		JSONObject argsJson = JSONObject.fromObject( args ); 
		String types=argsJson.getString("types");
		int agent_customer_cid=argsJson.getInt("agent_customer_cid");
		int sex=argsJson.getInt("sex");
		String customer_name=argsJson.getString("customer_name");
		String phone=argsJson.getString("phone");
		String address=argsJson.getString("address");
		
		Customer p=new Customer();
		p.setTypes(types);
		p.setAgent_customer_cid(agent_customer_cid);
		p.setSex(sex);
		p.setCustomer_name(customer_name);
		p.setPhone(phone);
		p.setAddress(address);
		logdao.commonsave(p);

		JSONObject res=new JSONObject();
		res.put("flag", "success");
		response.getWriter().write(res.toString());


	}

	public void deleteCustomer(HttpServletRequest request,HttpServletResponse response,ServletConfig sc,String args) throws IOException 
	{
		Users logonu=SessionUtil.getLogonUser(request);

		IUserlogDao logdao=(IUserlogDao)SpringBean.getBean(sc, "UserlogDaoTxProxy");

		JSONObject argsJson = JSONObject.fromObject( args ); 
		int cid=argsJson.getInt("cid");
		
		logdao.execute("delete from Customer where cid="+cid);

		JSONObject res=new JSONObject();
		res.put("flag", "success");
		response.getWriter().write(res.toString());


	}

}
