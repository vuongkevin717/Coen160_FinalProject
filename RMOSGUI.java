package kvuong_EcoRe;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

public class RMOSGUI extends JFrame implements ActionListener {

	private RMOS rmos = new RMOS();
	private int currRCMid;

	private JComboBox<String> itemTypes; //need to add
	private JPanel changePricePanel; //need to add
	private JTextField newPrice; //need to add
	private JButton updatePrice; //need to add
	private String currItemType; //need to add
	private JLabel changePriceStatus; //add
	
	private JComboBox<String> itemTypes2; //add
	private JPanel removeItemPanel; //add
	private JButton removeItem; //add
	private JLabel removeStatus; //add
	private Item currItem; //add
	private JComboBox<String> rcmID;
	
	private JLabel booty;
	private JTextField monthText;
	private JPanel sleepDeprived2; //add2
	private JButton enterRCMButton2; //add2
	private JTextField rcmIDText; //add2
	private JLabel booty2; //add2
	
	private JTextField enterModifyRCM;
	private JButton modify;
	
	private JLabel valueLabel1;
	private JLabel valuelLabel2;
	
	private JPanel rcmPanel;
	private JPanel loginPanel;
	private JPanel rcmControllerPanel;
	private JPanel itemDetailsPanel;
	private JPanel optionsPanel;
	private JPanel logoutPanel;
	private JPanel removeAddRCMPanel;
	private JPanel menuPanel;
	private JPanel addItemPanel;
	private JPanel butthole;
	private JPanel sleepdeprived;
	private JPanel monthPanel;

	//RCM labels 
	private JLabel rcmIDLabel;
	private JLabel rcmControllerLabel;

	// For the login screen
	private JPasswordField pw;
	private JLabel pwLabel;
	private JLabel usernameLabel;
	private JTextField usernameText;
	private JButton loginButton;

	// Input for the RCM
	private JTextField rcmLocationText;

	// Input for the Item
	private JTextField itemTypeText;
	private JTextField itemPriceText;
	private JTextField itemWeightText;
	private JLabel itemTypeLabel;
	private JLabel itemPriceLabel;
	private JLabel itemWeightLabel;
	private JPanel monthAllPanel;//GO add
	private JTextField monthTextAll;

	// Text area to display for admin's screen
	static JTextArea outputText;

	// Buttons for the RCM
	private JButton addRCMButton;
	private JButton removeRCMButton;
	private JButton restockMoneyButton;
	private JButton emptyRCMButton;
	private JButton currentMoneyButton;
	private JButton currentCapacityButton;
	private JButton busiestRCMButton;
	private JButton logoutButton;
	private JButton modifyRCMButton;
	private JButton numItemsAllButton;
	private JButton numItemsRCMButton;
	private JButton lastEmptiedButton;
	private JButton cancelButton;
	private JButton enterItemButton;
	private JButton returnRCMControllerButton;
	private JButton convertMetricButton;
	private JButton returnHomeButton;
	private JButton enterRCMButton;
	private JButton monthButton;
	private JButton monthAllButton;

	// Buttons for the Item
	private JButton addItemButton;
	private JButton removeItemButton;
	private JButton changePriceButton;	

	// Button for graph
	private JButton displayGraphButton;
	private JButton usageStatButton;
	private Container container;

	public RMOSGUI(RMOS rmos){
		super("RMOS");
		this.rmos = rmos;
		this.container = getContentPane();
		this.container.setLayout(new BorderLayout());
		
		outputText = new JTextArea();
		cancelButton = new JButton("CANCEL");
		cancelButton.addActionListener(this);
		
		returnHomeButton = new JButton("Return to main menu");
		returnHomeButton.addActionListener(this);
		/*
		 * ***************LOGIN PANEL*******************
		 */
		loginPanel = new JPanel();
		loginPanel.setBackground(Color.LIGHT_GRAY);
		loginPanel.setLayout(new FlowLayout());
		
		usernameLabel = new JLabel("Username:");
		loginPanel.add(usernameLabel);
		usernameText = new JTextField(10);
		loginPanel.add(usernameText);

		pwLabel = new JLabel("Password: ");
		loginPanel.add(pwLabel);
		pw = new JPasswordField(10);
		loginPanel.add(pw);
		
		loginButton = new JButton("Login");
		loginButton.addActionListener(this);
		loginPanel.add(loginButton);
		
		container.add(loginPanel, BorderLayout.NORTH);

		/*
		 * ***************OPTIONS PANEL*******************
		 */
		menuPanel = new JPanel();
		menuPanel.setLayout(new FlowLayout());
		
		optionsPanel = new JPanel();
		optionsPanel.setBackground(Color.LIGHT_GRAY);
		
		modifyRCMButton = new JButton("Modify RCM");
		optionsPanel.add(modifyRCMButton);
		modifyRCMButton.addActionListener(this);
		
		busiestRCMButton = new JButton("Busiest RCM");
		optionsPanel.add(busiestRCMButton);
		busiestRCMButton.addActionListener(this);
		
		numItemsAllButton = new JButton("Total Items of ALL RCMs");
		optionsPanel.add(numItemsAllButton);
		numItemsAllButton.addActionListener(this);
		
		usageStatButton = new JButton("Usage Statistics");
		usageStatButton.addActionListener(this);
		
		removeAddRCMPanel = new JPanel();
		removeAddRCMPanel.setBackground(Color.LIGHT_GRAY);
		addRCMButton = new JButton("Add RCM");
		addRCMButton.addActionListener(this);
		removeRCMButton = new JButton("Remove RCM");
		removeRCMButton.addActionListener(this);
		removeAddRCMPanel.add(addRCMButton);
		removeAddRCMPanel.add(removeRCMButton);
		
		logoutButton = new JButton("Logout");
		logoutButton.addActionListener(this);
		logoutPanel = new JPanel();
		logoutPanel.add(logoutButton);
		
		menuPanel.add(optionsPanel, BorderLayout.NORTH);
		menuPanel.add(removeAddRCMPanel, BorderLayout.CENTER);
		menuPanel.add(logoutPanel);
		
		/*
		 * ***************RCM PANEL*******************
		 */
		rcmPanel = new JPanel();
		rcmIDLabel = new JLabel("Enter an RCM ID");
		rcmPanel.add(rcmIDLabel);
		modify = new JButton("Modify");
		modify.addActionListener(this);
		enterModifyRCM = new JTextField(10);
		rcmPanel.add(enterModifyRCM);
		rcmPanel.add(modify);
		/*
		 * ***************RCM CONTROLLER PANEL*******************
		 */
		rcmControllerPanel = new JPanel();
		rcmControllerPanel.setLayout(new GridLayout(3, 3));
		rcmControllerPanel.add(usageStatButton);
		
		//current status controllers
		currentMoneyButton = new JButton("Current Balance");
		currentCapacityButton = new JButton("Current Capacity");
		currentMoneyButton.addActionListener(this);
		currentCapacityButton.addActionListener(this);
		rcmControllerPanel.add(currentMoneyButton);
		rcmControllerPanel.add(currentCapacityButton);
		
		//item controllers
		addItemButton = new JButton("Add Item");
		removeItemButton = new JButton("Remove Item");
		changePriceButton = new JButton("Change Item Price");
		addItemButton.addActionListener(this);
		removeItemButton.addActionListener(this);
		changePriceButton.addActionListener(this);
		rcmControllerPanel.add(addItemButton);
		rcmControllerPanel.add(removeItemButton);
		rcmControllerPanel.add(changePriceButton);
		
		//contents controllers
		numItemsRCMButton = new JButton("Number of Items");
		lastEmptiedButton = new JButton("Last Emptied");
		numItemsRCMButton.addActionListener(this);
		lastEmptiedButton.addActionListener(this);
		rcmControllerPanel.add(numItemsRCMButton);
		rcmControllerPanel.add(lastEmptiedButton);
	
		//cancel
		rcmControllerPanel.add(cancelButton);
		
		returnRCMControllerButton = new JButton("Return to Controller");
		returnRCMControllerButton.addActionListener(this);
		
		/*
		 * ***************ADD ITEM PANEL*******************
		 */
		addItemPanel = new JPanel();
		addItemPanel.setLayout(new FlowLayout());
		
		itemDetailsPanel = new JPanel();
		itemDetailsPanel.setLayout(new GridLayout(4, 2));
		
		itemTypeLabel = new JLabel("Set Item Type: ");
		itemDetailsPanel.add(itemTypeLabel);
		itemTypeText = new JTextField();
		itemDetailsPanel.add(itemTypeText);
		
		itemPriceLabel = new JLabel("Set Item Price: ");
		itemDetailsPanel.add(itemPriceLabel);
		itemPriceText = new JTextField();
		itemDetailsPanel.add(itemPriceText);
		
		itemWeightLabel = new JLabel("Set Item Weight: ");
		itemDetailsPanel.add(itemWeightLabel);
		itemWeightText = new JTextField();
		itemDetailsPanel.add(itemWeightText);
		
		addItemPanel.add(itemDetailsPanel);
		
		enterItemButton = new JButton("Enter");
		enterItemButton.addActionListener(this);
		addItemPanel.add(enterItemButton);
		addItemPanel.add(returnRCMControllerButton);
		
		restockMoneyButton = new JButton("Restock");
		restockMoneyButton.addActionListener(this);
		emptyRCMButton = new JButton("Empty");
		emptyRCMButton.addActionListener(this);
		
		this.setSize(800, 400);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override 
	public void actionPerformed(ActionEvent e){
		// store command from event e 
		Object source = e.getSource();

		/*
		 * ***************OPTIONS PANEL*******************
		 */
		// Add a new RCM
		if(source == returnHomeButton){
			container.removeAll();
			container.repaint();
			container.revalidate();
			container.add(menuPanel);
		}
		else if(source == modifyRCMButton){
			container.removeAll();
			container.repaint();
			container.revalidate();
			container.add(rcmPanel);
			container.add(returnHomeButton, BorderLayout.SOUTH);
		}
		else if(source == busiestRCMButton){
			container.removeAll();
			container.repaint();
			container.revalidate();
			if(rmos.getTotalMachines() == 0){
				outputText.setText("NO RCMs at this time");
				outputText.setFont(new Font("Arial", Font.PLAIN, 50));
			}else{
				outputText.setText("The busiest RCM is RCM " + rmos.getRCMDirectory().get(rmos.busiestRCM()).getrcmID());
				outputText.setFont(new Font("Arial", Font.PLAIN, 50));
			}
			container.add(outputText, BorderLayout.NORTH);
			container.add(returnHomeButton, BorderLayout.SOUTH);
		}
		else if(source == numItemsAllButton){
			container.removeAll();
			container.repaint();
			container.revalidate();
			monthTextAll = new JTextField(10);
			
			monthAllPanel = new JPanel();
			monthAllPanel.add(monthTextAll);
			monthAllButton = new JButton("Enter Month");
			monthAllButton.addActionListener(this);
			monthAllPanel.add(monthAllButton);
			container.add(monthAllPanel);
			container.add(returnHomeButton, BorderLayout.SOUTH);
		}
		else if (source == monthAllButton){
			container.removeAll();
			container.repaint();
			container.revalidate();
			String month = monthTextAll.getText().toUpperCase();
			outputText.setText("Number of items: " + rmos.itemsReturnedMonth(month));
			outputText.setFont(new Font("Arial", Font.PLAIN, 50));
			container.add(outputText, BorderLayout.NORTH);
			container.add(returnHomeButton, BorderLayout.SOUTH);
		}
		else if(source == addRCMButton){
			container.removeAll();
			container.repaint();
			container.revalidate();
			sleepdeprived = new JPanel();
			sleepdeprived.setLayout(new FlowLayout());
			booty = new JLabel("Location for new RCM:");
			rcmLocationText = new JTextField(10);
			sleepdeprived.add(rcmLocationText);
			enterRCMButton = new JButton("Add RCM");
			enterRCMButton.addActionListener(this);
			sleepdeprived.add(enterRCMButton);
			container.add(sleepdeprived);
			container.add(returnHomeButton, BorderLayout.SOUTH);
		}
		else if(source == enterRCMButton){
			container.removeAll();
			container.repaint();
			container.revalidate();
			String rcmLocation = rcmLocationText.getText();
			
			rmos.addRCM(rcmLocation);
			outputText.setText("RCM add success! \n" + rmos.displayRCM());
			outputText.setFont(new Font("Arial", Font.PLAIN, 50));
			container.add(outputText, BorderLayout.NORTH);
			container.add(returnHomeButton, BorderLayout.SOUTH);
		}
		else if(source == removeRCMButton){ //add2
			container.removeAll();
			container.repaint();
			container.revalidate();
			sleepDeprived2 = new JPanel();
			sleepDeprived2.setLayout(new FlowLayout());
			booty2 = new JLabel("ID of RCM to remove");
			sleepDeprived2.add(booty2);
			rcmIDText = new JTextField(10);
			sleepDeprived2.add(rcmIDText);
			enterRCMButton2 = new JButton("Remove RCM");
			enterRCMButton2.addActionListener(this);
			sleepDeprived2.add(enterRCMButton2);
			container.add(sleepDeprived2);
			container.add(returnHomeButton, BorderLayout.SOUTH);
			rmos.removeRCM(currRCMid);
			outputText.setText(rmos.displayRCM());
		}
		else if(source == enterRCMButton2){ //add2
			container.removeAll();
			container.repaint();
			container.revalidate();
			int newID = Integer.parseInt(rcmIDText.getText());
			rmos.removeRCM(newID);
			if(newID <= rmos.getTotalMachines() + 1){
				outputText.setText("RCM remove success! \n" + rmos.displayRCM());
				outputText.setFont(new Font("Arial", Font.PLAIN, 50));
				container.add(outputText, BorderLayout.NORTH);
				container.add(returnHomeButton, BorderLayout.SOUTH);
			}
			else{
				outputText.setText("No RCM by that ID!");
				outputText.setFont(new Font("Arial", Font.PLAIN, 50));
				container.add(outputText, BorderLayout.NORTH);
				container.add(returnHomeButton, BorderLayout.SOUTH);
			}
		}
		else if(source == restockMoneyButton){
			container.removeAll();
			container.repaint();
			container.revalidate();
			Date timeRestocked;
			timeRestocked = new Date();
			rmos.restockMoney(currRCMid);
			outputText.setText("RCM " + currRCMid + " was restocked at " + timeRestocked);
			outputText.setFont(new Font("Arial", Font.PLAIN, 25));
			container.add(outputText, BorderLayout.NORTH);
			container.add(returnRCMControllerButton, BorderLayout.SOUTH);
		}
		else if(source == emptyRCMButton){
			container.removeAll();
			container.repaint();
			container.revalidate();
			Date timeEmptied;
			timeEmptied = new Date();
			rmos.empty(currRCMid);
			outputText.setText("RCM " + currRCMid + " was emptied at " + timeEmptied);
			outputText.setFont(new Font("Arial", Font.PLAIN, 25));
			container.add(outputText, BorderLayout.NORTH);
			container.add(returnRCMControllerButton, BorderLayout.SOUTH);
		}
		else if(source == currentMoneyButton){
			container.removeAll();
			container.repaint();
			container.revalidate();
			outputText.setText("Current Balance: " + rmos.checkMoney(currRCMid));
			outputText.setFont(new Font("Arial", Font.PLAIN, 50));
			container.add(outputText, BorderLayout.NORTH);
			container.add(restockMoneyButton, BorderLayout.CENTER);
			container.add(returnRCMControllerButton, BorderLayout.SOUTH);
		}
		else if(source == currentCapacityButton){
			container.removeAll();
			container.repaint();
			container.revalidate();
			outputText.setText("Current Capacity: " + rmos.checkWeight(currRCMid) + "lbs");
			outputText.setFont(new Font("Arial", Font.PLAIN, 50));
			butthole = new JPanel();
			butthole.add(outputText);
			butthole.add(emptyRCMButton);
			convertMetricButton = new JButton("Convert to kg");
			convertMetricButton.addActionListener(this);
			butthole.add(convertMetricButton);
			container.add(butthole);
			container.add(returnRCMControllerButton, BorderLayout.SOUTH);
		}
		else if(source == convertMetricButton){
			container.removeAll();
			container.repaint();
			container.revalidate();
			outputText.setText("Current Capacity: " + rmos.lbsToKilograms(currRCMid) + "Kg");
			outputText.setFont(new Font("Arial", Font.PLAIN, 50));
			container.add(outputText, BorderLayout.NORTH);
			container.add(returnRCMControllerButton, BorderLayout.SOUTH);
		}
		else if(source == lastEmptiedButton){
			container.removeAll();
			container.repaint();
			container.revalidate();
			outputText.setText("Last emptied on: " + rmos.lastTimeEmptied(currRCMid));
			outputText.setFont(new Font("Arial", Font.PLAIN, 25));
			container.add(outputText, BorderLayout.NORTH);
			container.add(emptyRCMButton, BorderLayout.CENTER);
			container.add(returnRCMControllerButton, BorderLayout.SOUTH);
		}
		//fix this
		else if(source == numItemsRCMButton){
			container.removeAll();
			container.repaint();
			container.revalidate();
			monthText = new JTextField(10);
			monthPanel = new JPanel();
			monthPanel.add(monthText);
			monthButton = new JButton("Enter Month");
			monthButton.addActionListener(this);
			monthPanel.add(monthButton);
			container.add(monthPanel);
			container.add(returnRCMControllerButton, BorderLayout.SOUTH);
		}
		else if(source == monthButton){
			container.removeAll();
			container.repaint();
			container.revalidate();
			String month = monthText.getText().toUpperCase();
			outputText.setText("Number of items: " + rmos.itemsReturnedMonth(currRCMid, month));
			outputText.setFont(new Font("Arial", Font.PLAIN, 50));
			container.add(outputText, BorderLayout.NORTH);
			container.add(returnRCMControllerButton, BorderLayout.SOUTH);
		}
		else if(source == modifyRCMButton){
			container.removeAll();
			container.repaint();
			container.revalidate();
			container.add(rcmPanel);
			container.add(returnRCMControllerButton, BorderLayout.SOUTH);
		}

		/*
		 * ***************ITEM MANIPULATION*******************
		 *
		 *
		 * ***************ADD ITEM*******************
		 */
		else if(source == addItemButton){
			container.remove(rcmControllerPanel);
			container.repaint();
			container.revalidate();
			container.add(addItemPanel);
			container.add(returnRCMControllerButton, BorderLayout.SOUTH);
		}
		else if(source == enterItemButton){
			Item item = new Item(itemTypeText.getText(), Double.parseDouble(itemWeightText.getText()), Double.parseDouble(itemPriceText.getText()));
			rmos.addAcceptedItem(currRCMid, item);
			container.remove(addItemPanel);
			container.repaint();
			container.revalidate();
			
			outputText.setText("Item " + itemTypeText.getText() + " was added!");
			outputText.setFont(new Font("Arial", Font.PLAIN, 50));
			container.add(outputText, BorderLayout.NORTH);
			container.add(returnRCMControllerButton, BorderLayout.SOUTH);
		}
		else if(source == returnRCMControllerButton){
			container.removeAll();
			container.repaint();
			container.revalidate();
			container.add(rcmControllerPanel);
		}
		/*
		 * ***************REMOVE ITEM*******************
		 */
		else if(source == removeItemButton){
			container.removeAll();
			container.repaint();
			container.revalidate();
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
			for(int i = 0; i < rmos.itemTypes(currRCMid).length; i++){
				model.addElement(rmos.itemTypes(currRCMid)[i]);
			}
			itemTypes2 = new JComboBox<String>(model);
			itemTypes2.addActionListener(this);
			removeItemPanel = new JPanel();
			removeItemPanel.setLayout(new GridLayout(4,1));
			removeItemPanel.add(itemTypes2);
			
			removeItem = new JButton("Remove Item");
			removeItem.addActionListener(this);
			removeItemPanel.add(removeItem);
			removeStatus = new JLabel("Remove Successful!");
			removeStatus.setHorizontalAlignment(JLabel.CENTER);
			removeItemPanel.add(returnRCMControllerButton);
			container.add(removeItemPanel);
		}
		else if(source == itemTypes2){	//add*************
			JComboBox cb = (JComboBox) source;
			String itemType = (String) cb.getSelectedItem();
			currItem = rmos.getRCMDirectory().get(rmos.locateRCM(currRCMid)).getItem(rmos.getRCMDirectory().get(rmos.locateRCM(currRCMid)).locateItem(itemType));
		}
		else if(source == removeItem){	//add***********
			boolean status = rmos.removeAcceptedItem(currRCMid, currItem);
			System.out.println(status);
			if(status == true){
				removeItemPanel.add(removeStatus);
				removeItemPanel.repaint();
				removeItemPanel.revalidate();
			}
			else{
				removeItemPanel.remove(removeStatus);
				outputText.setText("Item is not present!");
				outputText.setFont(new Font("Arial", Font.PLAIN, 50));
				outputText.setAlignmentX(CENTER_ALIGNMENT);
				removeItemPanel.add(outputText);
				removeItemPanel.repaint();
				removeItemPanel.revalidate();
			}
		}
		/*
		 * ***************CHANGE PRICE*******************
		 */
		else if(source == changePriceButton){
			container.removeAll();
			container.repaint();
			container.revalidate();
			itemTypes = new JComboBox<String>(rmos.itemTypes(currRCMid));
			itemTypes.addActionListener(this);
			changePricePanel = new JPanel();
			changePricePanel.setLayout(new GridLayout(5,1));
			changePricePanel.add(itemTypes);
			newPrice = new JTextField(10);
			newPrice.setEditable(false);
			changePricePanel.add(newPrice);
			updatePrice = new JButton("Update Price");
			updatePrice.addActionListener(this);
			changePricePanel.add(updatePrice);
			changePricePanel.add(returnRCMControllerButton);
			container.add(changePricePanel);
		}
		else if(source == itemTypes){ //need to add*****
			JComboBox cb = (JComboBox) source;
			String itemType = (String) cb.getSelectedItem();
			this.currItemType = itemType;
			newPrice.setEditable(true);
		}
		else if(source == updatePrice){ //need to add****
			boolean check = rmos.changePrice(currRCMid, currItemType, Double.parseDouble(newPrice.getText()));
			if(check == true){
				changePriceStatus = new JLabel("Update Successful!");
				changePriceStatus.setHorizontalAlignment(JLabel.CENTER);
				changePricePanel.add(changePriceStatus);
				changePricePanel.repaint();
				changePricePanel.revalidate();
			}
		}
		/*
		 * ***********************LOGIN PANEL************************
		 */
		else if(source == loginButton){
			boolean access = false;
			access = rmos.login(usernameText.getText(), String.valueOf(pw.getPassword()));

			if(access){
				container.remove(loginPanel);
				container.repaint();
				container.revalidate();
				container.add(menuPanel);
			}else{
				outputText.setText("Login Failed");
				outputText.setFont(new Font("Arial", Font.PLAIN, 50));
				container.add(outputText);
			}
		}
		else if(source == modify){
			currRCMid = Integer.parseInt(enterModifyRCM.getText());
			container.removeAll();
			container.repaint();
			container.revalidate();
			container.add(rcmControllerPanel);
		}
		/*
		 * ***********************DISPLAY************************
		 */
		else if(source == usageStatButton){
			int index = rmos.locateRCM(currRCMid);
			Font keyFont = new Font("Georgia", Font.BOLD, 25);
			
			JLabel key = new JLabel("Key:");
			key.setForeground(Color.BLACK);
			key.setFont(new Font("Georgia", Font.BOLD, 25));
			
			JPanel legend = new JPanel();
			legend.setLayout(new GridBagLayout());
			GridBagConstraints constr = new GridBagConstraints();
			constr.gridx = 1;
			constr.gridy = 0;
			legend.add(key, constr);
			
			String[] legendLabels = {"Current Weight: " + rmos.getRCMDirectory().get(index).getcurrWeight() + "lbs", "Remaining Capacity " + (rmos.getRCMDirectory().get(index).getCapacity() - rmos.getRCMDirectory().get(index).getcurrWeight() + "lbs")};
			Color[] colorsList = {Color.GREEN, Color.RED};
			constr.anchor = GridBagConstraints.LINE_START;
			
			for (int i = 0; i < 2; i++){
				constr.gridy = (1 + i);
				constr.gridx = 0;
				
				key = new JLabel("* ", JLabel.RIGHT);
				key.setForeground(colorsList[i]);
				key.setFont(new Font("Georgia", Font.BOLD, 40));
				legend.add(key, constr);
				
				constr.gridx = 1;
				key = new JLabel(legendLabels[i]);
				key.setForeground(Color.BLACK);
				key.setFont(keyFont);
				legend.add(key, constr);
				constr.gridx = 2;
				legend.add(new JLabel("     "), constr);
			}
			ArrayList<Double> values = new ArrayList<Double>();
			values.add(rmos.getRCMDirectory().get(index).getcurrWeight());
			values.add(rmos.getRCMDirectory().get(index).getCapacity() - rmos.getRCMDirectory().get(index).getcurrWeight());
			
			ArrayList<Color> colors = new ArrayList<Color>();
			colors.add(Color.GREEN);
			colors.add(Color.RED);
			PieGraph pie = new PieGraph(values,colors);
			JLabel title = new JLabel("RCM " + rmos.getRCMDirectory().get(index).getrcmID() + " Weight of Items", JLabel.CENTER);
			title.setFont(new Font("Georgia", Font.PLAIN, 20));
			title.setForeground(Color.BLACK);
			JFrame frame = new JFrame();
			frame.setLocation(350, 0);
			frame.setLayout(new BorderLayout(25, 25));
			frame.getContentPane().add(title, BorderLayout.NORTH);
			frame.getContentPane().add(pie, BorderLayout.CENTER);
			frame.getContentPane().add(new JLabel("    "), BorderLayout.EAST);
			frame.getContentPane().add(legend, BorderLayout.EAST);
			frame.setSize(900, 900);
			frame.setVisible(true);
			
		}
		else if(source == cancelButton){
			container.removeAll();
			container.repaint();
			container.revalidate();
			container.add(menuPanel);
		}
		else if(source == logoutButton){
			logout();
		}
	}
	
	void logout(){
		container.removeAll();
		container.repaint();
		container.add(loginPanel, BorderLayout.NORTH);
	}

	//test
	public static void main(String[] args){
		RMOS rmos = new RMOS();
		Item glass = new Item("Glass Bottle", 1.0, 101.00);
		Item plastic = new Item("Plastic Cup", 10.5, 2.00);
		Item aluminum = new Item("Aluminum Can", 20.0, 3.00);
		rmos.addRCM("SF");
		rmos.addRCM("Dallas");
		rmos.addAcceptedItem(1, glass);
		rmos.addAcceptedItem(1, plastic);
		rmos.addAcceptedItem(1, aluminum);
		new RMOSGUI(rmos);
	}
}
