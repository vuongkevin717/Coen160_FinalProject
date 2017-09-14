package kvuong_EcoRe;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainMenu extends JFrame implements ActionListener{
	JButton RMOS, RCM;
	JTextField enterRCMID;
	JPanel leftPanel, filler, showRCM;
	JLabel enterRCM;
	Container container;
	private static RMOS rmos = new RMOS();
	
	public MainMenu(){
		super("EcoRe Simulator");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.container = getContentPane();
		this.container.setLayout(new GridLayout(1,2));
		
		RMOS = new JButton("RMOS");
		RCM = new JButton("Show RCM");
		enterRCMID = new JTextField(10);
		enterRCM = new JLabel("rcmID");
		enterRCM.setHorizontalAlignment(JLabel.CENTER);
		leftPanel = new JPanel();
		filler = new JPanel();
		showRCM = new JPanel();
		showRCM.setLayout(new FlowLayout());
		leftPanel.setLayout(new GridLayout(3,1));
		leftPanel.add(filler);
		showRCM.add(enterRCM);
		showRCM.add(enterRCMID);
		showRCM.add(RCM);
		leftPanel.add(showRCM);
		
		RMOS.addActionListener(this);
		RCM.addActionListener(this);
		RMOS.setActionCommand("RMOS");
		RCM.setActionCommand("RCM");
		
		container.add(leftPanel);
		container.add(RMOS);
		
		this.setSize(400,400);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand() == "RMOS"){
			new RMOSGUI(rmos);
		}
		else if(e.getActionCommand() == "RCM"){
			int rcmID;
			if(enterRCMID.getText() != ""){
				rcmID = Integer.parseInt(enterRCMID.getText());
				if(rcmID <= rmos.getTotalMachines())
				new RCMGUI(rmos.getRCMDirectory().get(rmos.locateRCM(rcmID)));	
			}
		}
	}
	
	//run this main for the whole simulation. Already pre-inserted 2 RCMs and a few Items
	public static void main(String[] args){
		Item glass = new Item("Glass Bottle", 1.0, 101.00);
		Item plastic = new Item("Plastic Cup", 10.5, 2.00);
		Item aluminum = new Item("Aluminum Can", 20.0, 3.00);
		Item paper = new Item("Newspaper", 0.5, 1.00);
		rmos.addRCM("SF");
		rmos.addRCM("Dallas");
		rmos.addAcceptedItem(1, glass);
		rmos.addAcceptedItem(1, plastic);
		rmos.addAcceptedItem(1, aluminum);
		rmos.addAcceptedItem(1, paper);
		rmos.addAcceptedItem(2, glass);
		rmos.addAcceptedItem(2, plastic);
		rmos.addAcceptedItem(2, aluminum);
		rmos.addAcceptedItem(2, paper);
		rmos.getRCMDirectory().get(0).insertItem(glass);
		rmos.getRCMDirectory().get(0).insertItem(plastic);
		rmos.getRCMDirectory().get(0).insertItem(plastic);
		rmos.getRCMDirectory().get(0).insertItem(aluminum);
		rmos.getRCMDirectory().get(1).insertItem(plastic);
		rmos.getRCMDirectory().get(1).insertItem(paper);
		new MainMenu();
	}
}
