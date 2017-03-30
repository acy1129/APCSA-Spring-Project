package com;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ItemTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Item> items;
	private String[] columns;

	public ItemTableModel(List<Item> itemList,String[] cols) {
		super();
		items = itemList;
		//columns = new String[] { "Item Name", "Item Price", "Item Quantity" };
		columns = cols;
	}

	// Number of column of your table
	public int getColumnCount() {
		return columns.length;
	}

	// Number of row of your table
	/*public int getRowsCount() {
		return items.size();
	}*/

	// The object to render in a cell
	public Object getValueAt(int row, int col) {
		Item item = items.get(row);
	    switch (col) {
	    case 0:
	        return item.getItemName();
	    case 1:
	        return item.getItemRate();
	    case 2:
	        return item.getItemQty();
	    default:
	        return null;
	    }
	}

	// Optional, the name of your column
	public String getColumnName(int col) {
		return columns[col];
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

}