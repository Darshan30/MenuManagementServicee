package com.amrita.menu.service.model;

import java.util.List;

public class CanteenAllMenu {
	
	List<MainCanteenMenu> mainCanteenMenuList;
	List<MbaCanteenMenu> mbaCanteenMenuList;
	List<ITCanteenMenu> itCanteenMenuList;
	List<MessMenu> messMenuList;
	
	
	public List<MainCanteenMenu> getMainCanteenMenuList() {
		return mainCanteenMenuList;
	}
	public void setMainCanteenMenuList(List<MainCanteenMenu> mainCanteenMenuList) {
		this.mainCanteenMenuList = mainCanteenMenuList;
	}
	public List<MbaCanteenMenu> getMbaCanteenMenuList() {
		return mbaCanteenMenuList;
	}
	public void setMbaCanteenMenuList(List<MbaCanteenMenu> mbaCanteenMenuList) {
		this.mbaCanteenMenuList = mbaCanteenMenuList;
	}
	public List<ITCanteenMenu> getItCanteenMenuList() {
		return itCanteenMenuList;
	}
	public void setItCanteenMenuList(List<ITCanteenMenu> itCanteenMenuList) {
		this.itCanteenMenuList = itCanteenMenuList;
	}
	public List<MessMenu> getMessMenuList() {
		return messMenuList;
	}
	public void setMessMenuList(List<MessMenu> messMenuList) {
		this.messMenuList = messMenuList;
	}
	
	

}
