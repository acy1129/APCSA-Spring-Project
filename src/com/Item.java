package com;

public class Item {
	private String itemName;
	private int itemRate;
	private int itemQty;
	
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
	public int getItemQty() {
		return itemQty;
	}
	public void setItemQty(int itemQty) {
		this.itemQty = itemQty;
	}
	
	public String toString(){
		return "Item Name : "+ itemName + " Item Rate : "+ itemRate + " Item Quantity : "+ itemQty;
	}

}
