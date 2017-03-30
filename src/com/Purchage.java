package com;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Purchage extends JFrame implements ActionListener,MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String[] cols = { "Item Name", "Item Price ($)", "Item Quantity"};
	ItemTableModel model = null;
	JTable table = null;
	JComboBox<NewItem> cmbItemNameTxt = null;
	JTextField itemPriceTxt = null,itemQtyTxt = null;
	private List<Item> itemList = new ArrayList<Item>();
	Utilities utilities = new Utilities();
	int row = -1;
	Vector<NewItem> vecItemList = new Vector<NewItem>();

	public Purchage() {
		List<Item> itemListNew = utilities.readJson();
		itemList = new ArrayList<Item>();
		model = new ItemTableModel(itemList,cols);
		table = new JTable(model);
		NewItem newItem = new NewItem();
		newItem.setItemName("Select");
		newItem.setItemRate(0);
		vecItemList.addElement(newItem);
		if(itemListNew != null && !itemListNew.isEmpty()){
			for(int i=0; i< itemListNew.size() ; i++){
				Item item = itemListNew.get(i);
				newItem = new NewItem();
				newItem.setItemName(item.getItemName());
				newItem.setItemRate(item.getItemRate());
				vecItemList.addElement(newItem);
			}
		}
		initUI();
	}

	public JPanel initUI() {

		//setTitle("<html><span style='color: black;'>Item Transaction Details</span></html>");
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());
		int ySize = ((int) tk.getScreenSize().getHeight());
		setSize(xSize / 2, ySize / 2);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		JLabel itemNameLbl = new JLabel("Item Name : ");
		cmbItemNameTxt = new JComboBox<NewItem>(vecItemList);
		cmbItemNameTxt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<?> comboBox = (JComboBox<?>)e.getSource();
		        NewItem item = (NewItem)comboBox.getSelectedItem();
		        //System.out.println( item.getItemName() + " : " + item.getItemRate());
		        if(item != null)
		        	setRate(item.getItemRate());
			}
		});
		
		JLabel itemPriceLbl = new JLabel("Item Price ($): ");
		itemPriceTxt = new JTextField(5);
		itemPriceTxt.setEditable(false);
		
		JLabel itemQtyLbl = new JLabel("Item Quentity : ");
		itemQtyTxt = new JTextField(5);
		
		JButton saveBtn =new JButton("Save");
		saveBtn.addActionListener(this);
		
		JButton invoceBtn =new JButton("Generate Invoice");
		invoceBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(itemList != null && !itemList.isEmpty()){//
					new Invoice(itemList);
				}else
					showMsg("No Item available.");
				
			}
		});
		
		JPanel panel = new JPanel();
		panel.add(itemNameLbl);
		panel.add(cmbItemNameTxt);
		panel.add(itemPriceLbl);
		panel.add(itemPriceTxt);
		panel.add(itemQtyLbl);
		panel.add(itemQtyTxt);
		panel.add(saveBtn);
		panel.add(invoceBtn);

		JPanel outerPanel = new JPanel();
		outerPanel.add(panel, BorderLayout.NORTH);
		outerPanel.add(new JScrollPane(table), BorderLayout.CENTER);
		
		table.addMouseListener(this);
		
		return outerPanel; 
	}

	public void clear() {
		cmbItemNameTxt.setSelectedIndex(0);
		itemQtyTxt.setText(null);
		itemPriceTxt.setText(null);
	}
	
	private void showMsg(String msg){
		JOptionPane.showMessageDialog(this, msg);
	}
	
	public void setRate(int rate){
		itemPriceTxt.setText(rate+"");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if(cmbItemNameTxt.getSelectedIndex() > 0 && itemQtyTxt.getText() != null && !"".equals(itemQtyTxt.getText()) && itemPriceTxt.getText() != null && !"".equals(itemPriceTxt.getText())){
			String regex = "[0-9]+";
			if(itemPriceTxt.getText().matches(regex) && itemQtyTxt.getText().matches(regex)){
				String quentity = itemQtyTxt.getText();
				String rate = itemPriceTxt.getText();
				String itemName = cmbItemNameTxt.getSelectedItem()+"";
				Item item = new Item();
				item.setItemName(itemName);
				item.setItemQty(Integer.parseInt(quentity));
				item.setItemRate(Integer.parseInt(rate));
				if(row == -1)
					itemList.add(item);
				else{
					itemList.set(row, item);
					table.clearSelection();
				}
				table.updateUI();
				this.clear();
				//utilities.writeJson(itemList);
			}else
				JOptionPane.showMessageDialog(this, "Please enter valid Item price and Item Quentity.");
		}else
			JOptionPane.showMessageDialog(this, "Item Name, Item price or Item Quentity may not blank.");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(itemList != null && !itemList.isEmpty()){
			row=table.rowAtPoint(e.getPoint());
			if(row != -1){
				Item item = itemList.get(row);
				System.out.println(item.getItemName());
				int i=1;
				for(; i<vecItemList.size(); i++){
					if(vecItemList.elementAt(i) != null && vecItemList.elementAt(i).getItemName() != null && vecItemList.elementAt(i).getItemName().equals(item.getItemName())){
						break;
					}
				}
				cmbItemNameTxt.setSelectedIndex(i);
				cmbItemNameTxt.updateUI();
				itemQtyTxt.setText(item.getItemQty()+"");
				itemPriceTxt.setText(item.getItemRate()+"");
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

