package com.exam.action.proxy.cashreturn;

import java.util.Vector;

import com.exam.entity.Customer;

public class CashReturn {
	
	private Customer agent;
	private Vector<SalesHistoryEnrichEntity> salesHistory=new Vector<SalesHistoryEnrichEntity> ();
	private float totalCashReturn;
	
	public Customer getAgent() {
		return agent;
	}
	public void setAgent(Customer agent) {
		this.agent = agent;
	}
	public Vector<SalesHistoryEnrichEntity> getSalesHistory() {
		return salesHistory;
	}
	public void setSalesHistory(Vector<SalesHistoryEnrichEntity> salesHistory) {
		this.salesHistory = salesHistory;
	}
	public float getTotalCashReturn() {
		return totalCashReturn;
	}
	public void setTotalCashReturn(float totalCashReturn) {
		this.totalCashReturn = totalCashReturn;
	}

}
