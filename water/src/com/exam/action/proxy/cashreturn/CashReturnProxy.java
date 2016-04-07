package com.exam.action.proxy.cashreturn;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exam.constant.Consts;
import com.exam.entity.IUserlogDao;
import com.exam.entity.Product;
import com.exam.entity.Users;
import com.framework.auto.util.SessionUtil;
import com.framework.auto.util.SpringBean;

public class CashReturnProxy 
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


	public void getCashReturnList(HttpServletRequest request,HttpServletResponse response,ServletConfig sc,String args) throws IOException 
	{

		IUserlogDao logdao=(IUserlogDao)SpringBean.getBean(sc, "UserlogDaoTxProxy");
		JSONObject argsJson = JSONObject.fromObject( args );

		List ol=logdao.query("from SalesHistory sale, Product product, Customer agent, Customer customer where sale.sale_time>='2010-01-01' and sale.product_id=product.pid and sale.customer_id=customer.cid and agent.cid=customer.agent_customer_cid");
		
		Vector<CashReturn> res=CashReturnUtil.getCashReturnStatistics(ol);
		
		JSONArray jsonObject = JSONArray.fromObject(res);
		response.getWriter().write(jsonObject.toString());


	}
	




}
