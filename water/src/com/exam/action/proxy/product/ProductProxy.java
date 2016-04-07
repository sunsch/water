package com.exam.action.proxy.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exam.constant.Consts;
import com.exam.entity.IUserlogDao;
import com.exam.entity.Product;
import com.exam.entity.Users;
import com.framework.auto.util.SessionUtil;
import com.framework.auto.util.SpringBean;

public class ProductProxy 
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


	public void listProductCount(HttpServletRequest request,HttpServletResponse response,ServletConfig sc,String args) throws IOException 
	{
		Users logonu=SessionUtil.getLogonUser(request);
		IUserlogDao logdao=(IUserlogDao)SpringBean.getBean(sc, "UserlogDaoTxProxy");
		JSONObject argsJson = JSONObject.fromObject( args ); 
		
		String hql="select count(*) from Product ";
		List l=logdao.query(hql);
		int count=Integer.parseInt(l.get(0).toString());
		
		JSONObject jo=new JSONObject();
		jo.put("count", count);
		response.getWriter().write(jo.toString());
	}
	public void listProductOnePage(HttpServletRequest request,HttpServletResponse response,ServletConfig sc,String args) throws IOException 
	{
		IUserlogDao logdao=(IUserlogDao)SpringBean.getBean(sc, "UserlogDaoTxProxy");
		JSONObject argsJson = JSONObject.fromObject( args ); 
		int page_index=argsJson.getInt("page_index");
		String hql="from Product ";
		List ol=logdao.pager(hql,page_index);
		
		JsonConfig jsonConfig = new JsonConfig();
		JSONArray jsonObject = JSONArray.fromObject(ol,jsonConfig);
		response.getWriter().write(jsonObject.toString());
	}
	public void getProductList(HttpServletRequest request,HttpServletResponse response,ServletConfig sc,String args) throws IOException 
	{

		IUserlogDao logdao=(IUserlogDao)SpringBean.getBean(sc, "UserlogDaoTxProxy");
		JSONObject argsJson = JSONObject.fromObject( args ); 

		List ol=logdao.query("from Product ");
		
		JSONArray jsonObject = JSONArray.fromObject(ol);
		response.getWriter().write(jsonObject.toString());


	}
	


	public void addProduct(HttpServletRequest request,HttpServletResponse response,ServletConfig sc,String args) throws IOException 
	{
		Users logonu=SessionUtil.getLogonUser(request);

		IUserlogDao logdao=(IUserlogDao)SpringBean.getBean(sc, "UserlogDaoTxProxy");

		JSONObject argsJson = JSONObject.fromObject( args ); 
		String product_name=argsJson.getString("product_name");
		float volume=new Double(argsJson.getDouble("volume")).floatValue();
		float price=new Double(argsJson.getDouble("price")).floatValue();
		float cash_return=new Double(argsJson.getDouble("cash_return")).floatValue();
		Product p=new Product();
		p.setProduct_name(product_name);
		p.setVolume(volume);
		p.setPrice(price);
		p.setCash_return(cash_return);
		logdao.commonsave(p);

		JSONObject res=new JSONObject();
		res.put("flag", "success");
		response.getWriter().write(res.toString());


	}
	public void deleteProduct(HttpServletRequest request,HttpServletResponse response,ServletConfig sc,String args) throws IOException 
	{
		Users logonu=SessionUtil.getLogonUser(request);

		IUserlogDao logdao=(IUserlogDao)SpringBean.getBean(sc, "UserlogDaoTxProxy");

		JSONObject argsJson = JSONObject.fromObject( args ); 
		int pid=argsJson.getInt("pid");
		
		logdao.execute("delete from Product where pid="+pid);

		JSONObject res=new JSONObject();
		res.put("flag", "success");
		response.getWriter().write(res.toString());


	}

}
