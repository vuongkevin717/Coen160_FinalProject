package kvuong_EcoRe;

import java.awt.Container;
import java.awt.GridLayout;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class SessionSummary extends JFrame{
	private Container container;
	private JLabel sessionTitle, sessItems, sessWeight, sessMoney, sessCoupons, couponValue;
	
	public SessionSummary(int RCMsessItems, double RCMsessWeight, double RCMsessMoney, int RCMsessCoupons, double RCMcouponValue){
		super("Session Summary");
		
		this.container = getContentPane();
		container.setLayout(new GridLayout(6,1));
		
		double temp = RCMsessWeight;
		DecimalFormat df = new DecimalFormat("0.00");
		
		sessionTitle = new JLabel("Your Session Summary");
		sessionTitle.setHorizontalAlignment(JLabel.CENTER);
		sessItems = new JLabel("Items Inserted: " + RCMsessItems);
		sessItems.setHorizontalAlignment(JLabel.CENTER);
		sessWeight = new JLabel("Weight Inserted: "  + df.format(temp) + " lbs");
		sessWeight.setHorizontalAlignment(JLabel.CENTER);
		
		temp = RCMsessMoney;
		sessMoney = new JLabel("Money Returned: $" + df.format(temp));
		sessMoney.setHorizontalAlignment(JLabel.CENTER);
		sessCoupons = new JLabel("Coupons Received: " + RCMsessCoupons);
		sessCoupons.setHorizontalAlignment(JLabel.CENTER);
		
		temp = RCMcouponValue;
		couponValue = new JLabel("Coupons worth: $" + df.format(temp));
		couponValue.setHorizontalAlignment(JLabel.CENTER);
		
		container.add(sessionTitle);
		container.add(sessItems);
		container.add(sessWeight);
		container.add(sessMoney);
		container.add(sessCoupons);
		container.add(couponValue);
		
		this.setSize(300, 200);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
