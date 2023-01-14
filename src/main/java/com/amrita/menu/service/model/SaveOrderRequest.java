package com.amrita.menu.service.model;

import java.util.List;

public class SaveOrderRequest {
	
	private String rollNo;
	
	private List<OrderMenuList> orderList;

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public List<OrderMenuList> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderMenuList> orderList) {
		this.orderList = orderList;
	}
	

}
