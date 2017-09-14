package kvuong_EcoRe;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Coupon extends JFrame{
	private Container container;
	private JLabel coupMessage;
	private BufferedImage coupon;
	
	public Coupon(){
		super("Coupon");
		
		this.container = getContentPane();
		this.container.setLayout(new BorderLayout());
		
		coupMessage = new JLabel("Machine out of money. A coupon will be returned instead.");
		coupMessage.setHorizontalAlignment(JLabel.CENTER);
		container.add(coupMessage, BorderLayout.NORTH);
		container.add(coupMessage, BorderLayout.NORTH);
		this.setSize(400, 75);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
