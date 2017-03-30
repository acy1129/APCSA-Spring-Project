package com;

public class NewItem {
	private String itemName;
	private int itemRate;
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getItemRate() {
		return itemRate;
	}
	public void setItemRate(int itemRate) {
		this.itemRate = itemRate;
	}
	
	public String toString(){
//		return "Item Name : "+ itemName + " Item Rate : "+ itemRate + " Item Quantity : "+ itemQty;
		return itemName ;
	}

}
