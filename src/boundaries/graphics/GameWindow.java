package boundaries.graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;

public class GameWindow extends JFrame {
	
	private final int WINDOW_BAR_HEIGHT = 23;
	private int window_width = 0;
	private int window_height = 0;
	private int window_position_x = 0;
	private int window_position_y = 0;
	
	private DrawingPanel contentPane = DrawingPanel.getInstance();
	
	private static GameWindow gameWindow = new GameWindow();

	/**
	 * Create the frame.
	 */
	private GameWindow() {
		
		
	}
	
	public static GameWindow getInstance(){
		return gameWindow;
	}
	
	public void startUp(int width, int height){
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		window_width = width;
		window_height = height;
		window_position_x = (int) (dim.getWidth() / 2 - 7 - window_width / 2);
		window_position_y = (int) (dim.getHeight() / 2 - 7 - window_height / 2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(window_position_x, window_position_y, window_width, window_height + WINDOW_BAR_HEIGHT);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.startUp();
	}
	
	public int getWindowWidth(){
		return window_width;
	}
	
	public int getWindowHeight(){
		return window_height;
	}
	
	public int getWindowPositionX(){
		return window_position_x;
	}
	
	public int getWindowPositionY(){
		return window_position_y;
	}
	
	private class KeyboardInput extends KeyAdapter {
		
	}
}
