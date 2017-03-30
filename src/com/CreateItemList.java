package com;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class CreateItemList extends JFrame implements ActionListener,MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String[] cols = { "Item Name", "Item Price ($)" };
	ItemTableModel model = null;
	JTable table = null;
	JTextField itemNameTxt = null;
	JTextField itemPriceTxt = null;
	private List<Item> itemList = new ArrayList<Item>();
	Utilities utilities = new Utilities();
	int row = -1;

	public CreateItemList() {
		itemList = utilities.readJson();
		model = new ItemTableModel(itemList,cols);
		table = new JTable(model);
		initUI();
	}

	public JPanel initUI() {

		//setTitle("<html><span style='color: black;'>Item Rate Details</span></html>");
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());
		int ySize = ((int) tk.getScreenSize().getHeight());
		setSize(xSize / 2, ySize / 2);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		JLabel itemNameLbl = new JLabel("Item Name : ");
		itemNameTxt = new JTextField(20);
		
		JLabel itemPriceLbl = new JLabel("Item Price ($): ");
		itemPriceTxt = new JTextField(5);
		
		JButton saveBtn =new JButton("Save");
		saveBtn.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.add(itemNameLbl);
		panel.add(itemNameTxt);
		panel.add(itemPriceLbl);
		panel.add(itemPriceTxt);
		panel.add(saveBtn);

		JPanel outerPanel = new JPanel();
		outerPanel.add(panel, BorderLayout.NORTH);
		outerPanel.add(new JScrollPane(table), BorderLayout.CENTER);
		
		table.addMouseListener(this);
		
		return outerPanel; 
	}

	public void clear() {
		itemNameTxt.setText(null);
		itemPriceTxt.setText(null);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if(itemNameTxt.getText() != null && !"".equals(itemNameTxt.getText()) && itemPriceTxt.getText() != null && !"".equals(itemPriceTxt.getText())){
			String regex = "[0-9]+";
			if(itemPriceTxt.getText().matches(regex)){
				String name = itemNameTxt.getText();
				String rate = itemPriceTxt.getText();
				Item item = new Item();
				item.setItemName(name);
				item.setItemRate(Integer.parseInt(rate));
				if(row == -1)
					itemList.add(item);
				else{
					itemList.set(row, item);
					table.clearSelection();
				}
				table.updateUI();
				this.clear();
				utilities.writeJson(itemList);
			}else
				JOptionPane.showMessageDialog(this, "Please enter valid Item price.");
		}else
			JOptionPane.showMessageDialog(this, "Item Name or Item price may not blank.");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(itemList != null && !itemList.isEmpty()){
			row=table.rowAtPoint(e.getPoint());
			if(row != -1){
				Item item = itemList.get(row);
				itemNameTxt.setText(item.getItemName());
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
