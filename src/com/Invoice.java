package com;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Invoice {
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;
	private List<Item> itemList = null;
	private int listSize = 0;

	public Invoice(List<Item> itemList) {
		prepareGUI();
		this.itemList = itemList;
		if(itemList != null)
			listSize = itemList.size();
		showGridBagLayoutDemo();
	}

	public static void main(String[] args) {
		List<Item> list = new ArrayList<Item>();
		Item item1 = new Item();
		item1.setItemName("Faltu");
		item1.setItemRate(10);
		item1.setItemQty(10);
		list.add(item1);
		Item item2 = new Item();
		item2.setItemName("Faltu2");
		item2.setItemRate(20);
		item2.setItemQty(20);
		list.add(item2);
		new Invoice(list);
		
	}

	private void prepareGUI() {
		mainFrame = new JFrame("Invoice Details");
		mainFrame.setSize(400, 600);//400,400
		mainFrame.setLayout(new GridLayout(listSize, 1));//3
		//mainFrame.setDefaultCloseOperation(3);
		mainFrame.setAlwaysOnTop(true);

		headerLabel = new JLabel("", JLabel.CENTER);
		statusLabel = new JLabel("", JLabel.CENTER);
		//statusLabel.setSize(350, 100);


		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());

		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.add(statusLabel);
		mainFrame.setVisible(true);
	}

	private void showGridBagLayoutDemo() {
		headerLabel.setText("Invoice Details of added Items");

		JPanel panel = new JPanel();
		panel.setBackground(Color.darkGray);
		panel.setSize(300, 500);//300,300
		GridBagLayout layout = new GridBagLayout();

		panel.setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		int total = 0;
		for(int i=0 ; i<itemList.size() ;i++){
			Item item = itemList.get(i);
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridx = 0;
			gbc.gridy = i;//0
			panel.add(new JButton(item.getItemName()), gbc);
			
			gbc.gridx = 1;
			gbc.gridy = i;//0
			panel.add(new JButton(item.getItemRate()*item.getItemQty()+""), gbc);
			
			total += item.getItemRate()*item.getItemQty();
		}

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = listSize;//0
		panel.add(new JButton("Total"), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = listSize;//0
		panel.add(new JButton(total+""), gbc);

		/*gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipady = 20;
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(new JButton("Button 3"), gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		panel.add(new JButton("Button 4"), gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = 2;
		panel.add(new JButton("Button 5"), gbc);*/

		controlPanel.add(panel);
		//controlPanel.add(new JScrollPane(panel), BorderLayout.CENTER);
		mainFrame.setVisible(true);
	}
}
