package com.amrita.menu.service.model;



import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="finalordersmenu")
public class OrderMenuList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemId;

	private String itemName;

	private int itemPrice;

	private int itemQuantity;

	private String itemCategory;

	private String canteenName;
	
	private long totalCost;
	
	@Temporal(TemporalType.DATE)
    private Date creationDateTime;
	
//	@ManyToOne
//	@JoinColumn(name="finalorders_menu_id")
//	private OrderList orderlist;

	public OrderMenuList() {

	}

//	public Long getOrderMenuListid() {
//		return OrderMenuListid;
//	}
//
//	public void setOrderMenuListid(Long orderMenuListid) {
//		OrderMenuListid = orderMenuListid;
//	}

	public OrderMenuList(String itemName, int itemPrice, int itemQuantity, String itemCategory, String canteenName,long totalCost) {
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemQuantity = itemQuantity;
		this.itemCategory = itemCategory;
		this.canteenName = canteenName;
		this.totalCost=totalCost;
	}
	
	
	

	public Date getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}
	
	

	public long getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(long totalCost) {
		this.totalCost = totalCost;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public String getCanteenName() {
		return canteenName;
	}

	public void setCanteenName(String canteenName) {
		this.canteenName = canteenName;
	}

}
