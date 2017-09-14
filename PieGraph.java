package kvuong_EcoRe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JComponent;

public class PieGraph extends JComponent {
	private ArrayList<Double> values;
	private ArrayList<Color> colors; 
	
	enum Type {
		STANDARD, SIMPLE_INDICATOR, GRADED_INDICATOR
	}
	
	private Type type = null;
	
	public PieGraph(){}
	
	//Constructor that initializes the pieces of the graph
	public PieGraph(ArrayList<Double> values, ArrayList<Color> colors){
		type = Type.STANDARD;
		
		this.values = values;
		this.colors = colors;
	}
	
	protected void paintComponent(Graphics g){
		int width = getSize().width;
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		int lastPoint = -270;
		
		for(int i = 0; i < values.size(); i++){
			g2d.setColor(colors.get(i));
			
			Double val = values.get(i);
			Double angle = (val / 100) * 360;

			g2d.fillArc(0, 0, width, width, lastPoint, -angle.intValue());
			System.out.println("fill arc " + lastPoint + " "
					+ -angle.intValue());

			lastPoint = lastPoint + -angle.intValue();
		}
	}

}

