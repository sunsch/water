package com.exam.action.proxy.saleshistory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.exam.entity.SalesHistory;
import com.exam.entity.Users;
import com.framework.auto.util.SessionUtil;
import com.framework.auto.util.SpringBean;

public class SalesHistoryProxy 
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


	public void getSalesHistoryList(HttpServletRequest request,HttpServletResponse response,ServletConfig sc,String args) throws IOException 
	{

		IUserlogDao logdao=(IUserlogDao)SpringBean.getBean(sc, "UserlogDaoTxProxy");
		JSONObject argsJson = JSONObject.fromObject( args ); 

		List ol=logdao.query("from SalesHistory ");
		
		JSONArray jsonObject = JSONArray.fromObject(ol);
		response.getWriter().write(jsonObject.toString());


	}
	


	public void addSalesHistory(HttpServletRequest request,HttpServletResponse response,ServletConfig sc,String args) throws IOException, ParseException 
	{
		Users logonu=SessionUtil.getLogonUser(request);

		IUserlogDao logdao=(IUserlogDao)SpringBean.getBean(sc, "UserlogDaoTxProxy");

		JSONObject argsJson = JSONObject.fromObject( args ); 
		int customer_id=argsJson.getInt("customer_id");
		int product_id=argsJson.getInt("product_id");
		int quantity=argsJson.getInt("quantity");
		String sale_time=argsJson.getString("sale_time");
		
		//SimpleDateFormat sale_date=new SimpleDateFormat("yyyy-MM-dd"); 
		//java.util.Date date=sale_date.parse(sale_time);  
		
		SalesHistory p=new SalesHistory();
		p.setCustomer_id(customer_id);
		p.setProduct_id(product_id);
		p.setQuantity(quantity);
		p.setSale_time(sale_time);
		logdao.commonsave(p);

		JSONObject res=new JSONObject();
		res.put("flag", "success");
		response.getWriter().write(res.toString());


	}
	public void deleteSalesHistory(HttpServletRequest request,HttpServletResponse response,ServletConfig sc,String args) throws IOException 
	{
		Users logonu=SessionUtil.getLogonUser(request);

		IUserlogDao logdao=(IUserlogDao)SpringBean.getBean(sc, "UserlogDaoTxProxy");

		JSONObject argsJson = JSONObject.fromObject( args ); 
		int sid=argsJson.getInt("sid");
		
		logdao.execute("delete from SalesHistory where sid="+sid);

		JSONObject res=new JSONObject();
		res.put("flag", "success");
		response.getWriter().write(res.toString());


	}

}
