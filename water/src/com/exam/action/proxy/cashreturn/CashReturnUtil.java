package com.exam.action.proxy.cashreturn;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.exam.entity.Customer;
import com.exam.entity.Product;
import com.exam.entity.SalesHistory;

public class CashReturnUtil {
	
	
	public static Vector<CashReturn> getCashReturnStatistics(List ol)
	{
		Vector<CashReturn> res=agregateCashReturn(ol);
		calAllCashReturn(res);
		return res;
	}
	public static Vector<CashReturn> agregateCashReturn(List ol)
	{
		HashMap<Integer,CashReturn> hm=new HashMap<Integer,CashReturn>();//agent_id,CashReturn
		Vector<CashReturn> res =new Vector<CashReturn> ();
		for(Object o:ol)
		{
			Object[] os=(Object[] )o;
			SalesHistory sale=(SalesHistory)os[0]; 
			Product product=(Product)os[1]; 
			Customer agent=(Customer)os[2]; 
			Customer customer=(Customer)os[3]; 
			
			if(hm.containsKey(agent.getCid()))
			{
				CashReturn cash=hm.get(agent.getCid());
				SalesHistoryEnrichEntity salerich=new SalesHistoryEnrichEntity();
				salerich.setCustomer(customer);
				salerich.setProduct(product);
				salerich.setQuantity(sale.getQuantity());
				salerich.setSale_time(sale.getSale_time());
				cash.getSalesHistory().add(salerich);
			}else
			{
				CashReturn cash=new CashReturn();
				cash.setAgent(agent);
				SalesHistoryEnrichEntity salerich=new SalesHistoryEnrichEntity();
				salerich.setCustomer(customer);
				salerich.setProduct(product);
				salerich.setQuantity(sale.getQuantity());
				salerich.setSale_time(sale.getSale_time());
				cash.getSalesHistory().add(salerich);
				hm.put(agent.getCid(), cash);
			}
		}
		
		for(CashReturn cr:hm.values())
		{
			res.add(cr);
		}
		return res;
	}
	
	public static void calAllCashReturn(Vector<CashReturn> cs)
	{
		for(CashReturn c:cs)
		{
			calCashReturn(c);
		}
		
	}
	public static void calCashReturn(CashReturn c)
	{
		c.setTotalCashReturn(0);
		for(SalesHistoryEnrichEntity s:c.getSalesHistory())
		{
			c.setTotalCashReturn(c.getTotalCashReturn()+s.getProduct().getCash_return()*s.getQuantity());
		}
	}

}
