package kvuong_EcoRe;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;

public class RCMGUI extends JFrame implements ActionListener{
	private Container container;
	private JButton startSession, endSession, insert;
	private JComboBox<String> itemType;
	private JLabel itemWeight, itemValue, totalWeight, totalMoney, location, rcmID, dropDownLabel;
	private JPanel topPanel, sessionPanel, bottomPanel;
	private RCM rcm;
	private String itemToInsert = null;
	private int sessItems = 0;
	private double couponValue = 0.0;
	
	public RCMGUI(RCM rcm){
		super("RCM " + rcm.getrcmID());
		this.rcm = rcm;
		
		this.container = getContentPane();
		this.container.setLayout(new BorderLayout());
		
		//initialize the Buttons
		startSession = new JButton("Start Session");
		startSession.setAlignmentX(Component.CENTER_ALIGNMENT);
		startSession.setActionCommand("start");
		
		endSession = new JButton("End Session");
		endSession.setActionCommand("end");
		
		insert = new JButton("Insert Item");
		insert.setActionCommand("insert");
		
		//Add ActionListeners to the buttons
		startSession.addActionListener(this);
		endSession.addActionListener(this);
		insert.addActionListener(this);
		
		//initialize the JPanels
		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		
		sessionPanel = new JPanel();
		sessionPanel.setLayout(new GridLayout(6,1));
		
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		
		//initialize the Labels
		itemWeight = new JLabel("Item Weight: ");
		itemValue = new JLabel("Item Value: ");
		DecimalFormat df = new DecimalFormat("0.00");
		double temp = rcm.getcurrWeight();
		totalWeight = new JLabel("Total Weight: " + df.format(temp) + " lbs / 100.0 lbs    ");
		temp = rcm.getTotalMoney();
		totalMoney = new JLabel("Total Money: $" + df.format(temp));
		location = new JLabel("Location: " + rcm.getLocation());
		rcmID = new JLabel("ID: " + rcm.getrcmID() + "        ");
		dropDownLabel = new JLabel("Please Select Your Item:");
		
		//initialize the ComboBox and add action listener
		itemType = new JComboBox<String>(rcm.itemTypesAccepted());
		itemType.addActionListener(this);
		itemType.setActionCommand("selectItem");
		
		//Add the buttons to their respective panels
		topPanel.add(rcmID);
		topPanel.add(location);
		
		sessionPanel.add(startSession);
		
		bottomPanel.add(totalWeight);
		bottomPanel.add(totalMoney);
		
		//Add the panels to the container
		container.add(topPanel, BorderLayout.NORTH);
		container.add(sessionPanel, BorderLayout.CENTER);
		container.add(bottomPanel, BorderLayout.SOUTH);
		
		this.setSize(400, 300);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("start")){
			sessionPanel.remove(startSession);
			sessionPanel.revalidate();
			sessionPanel.repaint();
			sessionPanel.add(dropDownLabel);
			sessionPanel.add(itemType);
			sessionPanel.add(itemWeight);
			sessionPanel.add(itemValue);
			sessionPanel.add(insert);
			sessionPanel.add(endSession);
		}
		else if(e.getActionCommand().equals("selectItem")){
			JComboBox cb = (JComboBox) e.getSource();
			String itemType = (String) cb.getSelectedItem();
			itemToInsert = itemType;
			double temp = rcm.getItem(rcm.locateItem(itemType)).getItemWeight();
			DecimalFormat df = new DecimalFormat("0.00");
			itemWeight.setText("Item Weight: " + df.format(temp) + " lbs");
			temp = rcm.getItem(rcm.locateItem(itemType)).getPrice();
			itemValue.setText("Item Value: $" + df.format(temp));
		}
		else if(e.getActionCommand().equals("insert")){
			if(itemToInsert != null){
				boolean moneyTest = (rcm.getTotalMoney() - rcm.getItem(rcm.locateItem(itemToInsert)).getPrice() >= 0);
				rcm.insertItem(rcm.getItem(rcm.locateItem(itemToInsert)));
				double temp = rcm.getcurrWeight();
				DecimalFormat df = new DecimalFormat("0.00");
				totalWeight.setText("Total Weight: " + df.format(temp) + " lbs / 100.0 lbs    ");
				temp = rcm.getTotalMoney();
				totalMoney.setText("Total Money: $" + df.format(temp));
				itemWeight.setText("Item Weight: ");
				itemValue.setText("Item Value: ");
				if(moneyTest == false){ 
					new Coupon();
					rcm.incrementCoupon();
					couponValue += rcm.getItem(rcm.locateItem(itemToInsert)).getPrice();
				}
				if(rcm.getcurrWeight() + rcm.getItem(rcm.locateItem(itemToInsert)).getItemWeight() <= 100){
					sessItems++;
				}
				itemToInsert = null;
			}
		}
		else if(e.getActionCommand().equals("end")){
			sessionPanel.removeAll();
			sessionPanel.repaint();
			sessionPanel.add(startSession);
			new SessionSummary(sessItems, rcm.getSessWeight(), rcm.getSessMoney(), rcm.getSessCoupons(), couponValue);
			rcm.resetSession();
			sessItems = 0;
			couponValue = 0.0;
		}
	}
	
	//test
	public static void main(String[] args){
		Item glass = new Item("Glass Bottle", 1.0, 101.00);
		Item plastic = new Item("Plastic Cup", 10.5, 2.00);
		Item aluminum = new Item("Aluminum Can", 20.0, 3.00);
		Item paper = new Item("Newspaper", 0.5, 1.00);
		RCM rcm1 = new RCM(1, "Dallas");
		rcm1.addItem(glass);
		rcm1.addItem(plastic);
		rcm1.addItem(aluminum);
		rcm1.addItem(paper);
		new RCMGUI(rcm1);
	}
}
