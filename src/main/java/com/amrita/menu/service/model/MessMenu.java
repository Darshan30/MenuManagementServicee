package com.amrita.menu.service.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "messmenu")
public class MessMenu {
	
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	
	  private String itemName;
	  
	  private String dayOfWeek;
 

	public MessMenu() {

	}
	
	
	

	public MessMenu(String itemName, String dayOfWeek) {
		this.itemName = itemName;
		this.dayOfWeek=dayOfWeek;
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




	public String getDayOfWeek() {
		return dayOfWeek;
	}




	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	
	  
	  
	  
	

}
	