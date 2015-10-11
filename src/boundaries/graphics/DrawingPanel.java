package boundaries.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import entities.base.game.engine.BasicObject;
import entities.base.game.engine.GameManager;
import entities.base.game.engine.Position;

public class DrawingPanel extends JPanel {
	
	private int panelWidth = 0;
	private int panelHeight = 0;
	private double margin = 5.0;
	private double num_of_boxes_x = 1;
	private double num_of_boxes_y = 1;
	private double box_length_x = -1.0;
	private double box_length_y = -1.0;
	private int heightFontSize = 1;
	private boolean initialized = false;
	
	private static DrawingPanel drawingPanel = new DrawingPanel();
	
	/**
	 * Create the panel.
	 */
	private DrawingPanel() {
		
	}
	
	public static DrawingPanel getInstance(){
		return drawingPanel;
	}
	
	public void startUp(){
		GameWindow gw = GameWindow.getInstance();
		GameManager gm = GameManager.getInstance();
		
		num_of_boxes_x = gm.getWorldWidth();
		num_of_boxes_y = gm.getWorldHeight();
		panelWidth = gw.getWindowWidth() - 16;
		panelHeight = gw.getWindowHeight() - 16;
		box_length_x = (panelWidth - margin * (num_of_boxes_x + 1)) / num_of_boxes_x;
		box_length_y = (panelHeight - margin * (num_of_boxes_y + 1)) / num_of_boxes_y;
		
		for(int i = 8; i < 100; i++){
			
			if(getFontMetrics(new Font("Consolas", Font.PLAIN, i)).getHeight() > box_length_y){
				heightFontSize = i-1;
				i = 100;
			}
		}
		
		//this.getGraphics().setFont(new Font("Consolas", 0, heightFontSize));
	}
	
	public void paintComponent(Graphics g){
		if(!initialized){
			g.setFont(new Font("Consolas", Font.PLAIN, heightFontSize));
		}
		for(double x = margin; x < panelWidth - 1; x+=box_length_x + margin){
			for(double y = margin; y < panelHeight - 1; y+=box_length_y + margin){
				g.drawRect((int)x, (int)y, (int)box_length_x, (int)box_length_y);
				//System.out.println((int)((x + box_length_x) / (box_length_x + margin) - 0.9));
			}
		}
		
		//g.setFont(new Font("Consolas", 0, 32));
		ArrayList<BasicObject> objectList = GameManager.getInstance().getObjectsList();
		for (int i = 0; i < objectList.size(); i++){
			Position pos = objectList.get(i).getPosition();
			double x = pos.getX() * (box_length_x + margin) - box_length_x + 1;
			double y = pos.getY() * (box_length_y + margin) - box_length_y + 1;
			g.setColor(objectList.get(i).getColor());
			g.fillRect((int)x, (int)y, (int)(box_length_x-1), (int)(box_length_y-1));
			g.setColor(Color.BLACK);
			g.drawString(objectList.get(i).getName(), (int)x, (int)(y + box_length_y - 2));
		}
	}

}
