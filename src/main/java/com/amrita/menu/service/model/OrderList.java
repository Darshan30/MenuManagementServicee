package com.amrita.menu.service.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="finalorders")
public class OrderList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long menuId;

	@OneToMany(cascade = CascadeType.ALL)
	private List<OrderMenuList> saveOrderList=new ArrayList<>();

	private String rollNo;

	public OrderList() {

	}

	public OrderList(String rollNo) {
		this.rollNo = rollNo;
	}
	

	

	

	

//	public Long getOrderListId() {
//		return orderListId;
//	}
//
//	public void setOrderListId(Long orderListId) {
//		this.orderListId = orderListId;
//	}

	public List<OrderMenuList> getSaveOrderList() {
		return saveOrderList;
	}

	public void setSaveOrderList(List<OrderMenuList> saveOrderList) {
		this.saveOrderList = saveOrderList;
	}

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

}
