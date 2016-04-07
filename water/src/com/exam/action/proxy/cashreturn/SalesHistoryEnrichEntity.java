package com.exam.action.proxy.cashreturn;

import com.exam.entity.Customer;
import com.exam.entity.Product;
import com.exam.entity.SalesHistory;

public class SalesHistoryEnrichEntity extends SalesHistory{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Customer customer;
	private Product product;
	
	
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
}
