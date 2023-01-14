package com.amrita.menu.service.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "mbamenu")
public class MbaCanteenMenu {
	
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	
	  private String itemName;
	  
	  private int itemPrice;
	  
	  private int itemQuantity;
	  
	  private String itemCategory;
	   
	  private String canteenName;
	  

	public MbaCanteenMenu() {

	}
	
	
	

	public MbaCanteenMenu(String itemName, int itemPrice, int itemQuantity,String itemCategory,String canteenName) {
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemQuantity = itemQuantity;
		this.itemCategory=itemCategory;
		this.canteenName=canteenName;
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




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	  
	  
	  
	

}
	