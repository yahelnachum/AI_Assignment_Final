package boundaries.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import entities.game.engine.base.BasicObject;
import entities.game.engine.base.GameManager;
import entities.game.engine.base.Position;

/**
 * @author Yahel
 * JPanel to draw the game world objects on
 */
public class DrawingPanel extends JPanel {
	
	// attributes of panel
	private int panelWidth = 0;
	private int panelHeight = 0;
	
	// variables to calculate drawing positions, lengths, and text size
	private double margin = 5.0;
	private double num_of_boxes_x = 1;
	private double num_of_boxes_y = 1;
	private double box_length_x = -1.0;
	private double box_length_y = -1.0;
	private int heightFontSize = 1;
	
	// for initializing the font
	private boolean fontInitialized = false;
	
	// single instance of a drawing panel
	private static DrawingPanel drawingPanel = new DrawingPanel();
	
	/**
	 * Used to only create one instance of a drawing panel 
	 */
	private DrawingPanel() {}
	
	/**
	 * Get the single instance of the drawing panel
	 * @return The instance of the drawing panel
	 */
	public static DrawingPanel getInstance(){
		return drawingPanel;
	}
	
	/**
	 * Initialize all the variables for calculations 
	 * based on the game world size and game window size
	 */
	public void startUp(){
		// get instances of game manager and window
		GameWindow gw = GameWindow.getInstance();
		GameManager gm = GameManager.getInstance();
		
		// initialize variables for game world positions for drawing
		num_of_boxes_x = gm.getWorldWidth();
		num_of_boxes_y = gm.getWorldHeight();
		panelWidth = gw.getWindowWidth() - 16;
		panelHeight = gw.getWindowHeight() - 16;
		box_length_x = (panelWidth - margin * (num_of_boxes_x + 1)) / num_of_boxes_x;
		box_length_y = (panelHeight - margin * (num_of_boxes_y + 1)) / num_of_boxes_y;
		
		// calculate font
		// get the longest name from the list of game objects
		String longestString = "";
		ArrayList<BasicObject> objectList = GameManager.getInstance().getObjectsList();
		for (int i = 0; i < objectList.size(); i++){
			if(objectList.get(i).getName().length() > longestString.length())
				longestString = objectList.get(i).getName(); 
		}
		
		// find the biggest size font that can print the name of the longest named object
		for(int i = 8; i < 100; i++){
			if(getFontMetrics(new Font("Consolas", Font.PLAIN, i)).stringWidth(longestString) > box_length_x){
				heightFontSize = i-1;
				i = 100;
			}
		}
		
		//this.getGraphics().setFont(new Font("Consolas", 0, heightFontSize));
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 * Print out the game board and the objects on the board
	 */
	public void paintComponent(Graphics g){
		// initialize the font
		if(!fontInitialized){
			g.setFont(new Font("Consolas", Font.PLAIN, heightFontSize));
		}
		
		// draw empty rectangles to represent the game world
		for(double x = margin; x < panelWidth - 1; x+=box_length_x + margin){
			for(double y = margin; y < panelHeight - 1; y+=box_length_y + margin){
				g.drawRect((int)x, (int)y, (int)box_length_x, (int)box_length_y);
			}
		}
		
		// get a list of all the objects in the world
		// draw them in their correct positions, with their color, and their names
		ArrayList<BasicObject> objectList = GameManager.getInstance().getObjectsList();
		for (int i = 0; i < objectList.size(); i++){
			// get position and calculate panel position
			Position pos = objectList.get(i).getPosition();
			double x = (pos.getX()+1) * (box_length_x + margin) - box_length_x + 1;
			double y = (pos.getY()+1) * (box_length_y + margin) - box_length_y + 1;
			
			// fill in a rectangle at the objects position
			g.setColor(objectList.get(i).getColor());
			g.fillRect((int)x, (int)y, (int)(box_length_x-1), (int)(box_length_y-1));
			
			// write the name over the rectangle drawn
			g.setColor(Color.BLACK);
			g.drawString(objectList.get(i).getName(), (int)x, (int)(y + box_length_y - 2));
		}
	}

}
