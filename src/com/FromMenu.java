package com;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class FromMenu extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static String fromName;
	int xSize = 200;
	int ySize = 200;
	public FromMenu() {

		initUI();
	}

	private void initUI() {

		createMenuBar();
//setting the title to pop up when first run and adjusting its size
		setTitle("Welcome to our Application");
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());
		int ySize = ((int) tk.getScreenSize().getHeight());
		setSize(xSize/2, ySize/2);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}

	private void createMenuBar() {
//assigning the menu for the application (exit)
		JMenuBar menubar = new JMenuBar();
		ImageIcon icon = new ImageIcon("exit.png");
//assigning the menu for the application (file)
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		//assigning the menu for the application (create item)
		JMenuItem itemMenu = new JMenuItem("Create Item");
		itemMenu.setMnemonic(KeyEvent.VK_E);
		itemMenu.setToolTipText("Create Item");
		itemMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//setPanel(new JPanel());
				getContentPane().removeAll();
				getContentPane().repaint();
				CreateItemList ex = new CreateItemList();
				setPanel(ex.initUI());
				//fromName = CreateItemList;
			}
		});
		//assigning the menu for the application (create transaction)
		JMenuItem itemRateMenu = new JMenuItem("Create Transaction");
		itemRateMenu.setMnemonic(KeyEvent.VK_E);
		itemRateMenu.setToolTipText("Create Transaction");
		itemRateMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//setPanel(new JPanel());
				getContentPane().removeAll();
				getContentPane().repaint();
				Purchage ex = new Purchage();
				setPanel(ex.initUI());
			}
		});
		//assigning the menu for the application (exit)
		JMenuItem eMenuItem = new JMenuItem("Exit", icon);
		eMenuItem.setMnemonic(KeyEvent.VK_E);
		eMenuItem.setToolTipText("Exit application");
		eMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		
		file.add(itemMenu);
		file.add(itemRateMenu);
		file.add(eMenuItem);

		menubar.add(file);

		setJMenuBar(menubar);
	}
	
	private void setPanel(JPanel panel){
		this.add(panel);
		panel.updateUI();
		panel.repaint();
		this.setVisible(true);
	}

	public static void main(String[] args) {

		FromMenu ex = new FromMenu();
		ex.setVisible(true);

	}
}
