package boundaries.graphics;

import java.awt.Graphics;

import javax.swing.Box.Filler;
import javax.swing.JPanel;

import entities.base.game.engine.GameManager;

public class DrawingPanel extends JPanel {
	
	/**
	 * Create the panel.
	 */
	public DrawingPanel() {

	}
	
	public void paintComponent(Graphics g){
		System.out.println(this.getWidth());
		System.out.println(this.getHeight());
		GameWindow gw = GameWindow.getInstance();
		double margin = 5;
		double num_of_boxes_x = GameManager.getInstance().getWorldWidth();
		double num_of_boxes_y = GameManager.getInstance().getWorldHeight();
		double box_length_x = (this.getWidth() - margin * (num_of_boxes_x + 1)) / num_of_boxes_x;
		double box_length_y = (this.getHeight() - margin * (num_of_boxes_y + 1)) / num_of_boxes_y;
		for(double x = margin; x < this.getWidth() - 1; x+=box_length_x + margin){
			for(double y = margin; y < this.getHeight() - 1; y+=box_length_y + margin){
				g.fillRect((int)x, (int)y, (int)box_length_x, (int)box_length_y);		
			}
		}
	}

}
