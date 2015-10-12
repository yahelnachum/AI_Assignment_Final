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

/**
 * @author Yahel
 *
 */
public class GameWindow extends JFrame {
	
	// game window attributes
	private final int WINDOW_BAR_HEIGHT = 23;
	private int window_width = 0;
	private int window_height = 0;
	private int window_position_x = 0;
	private int window_position_y = 0;
	
	// single instance of the drawing panel as the content panel of the game window
	private DrawingPanel contentPane = DrawingPanel.getInstance();
	
	// single instance of the game window
	private static GameWindow gameWindow = new GameWindow();

	/**
	 * Can only create a single instance of the game window
	 */
	private GameWindow() {}
	
	/**
	 * Get the single instance of the game window
	 * @return Returns the GameWindow instance
	 */
	public static GameWindow getInstance(){
		return gameWindow;
	}
	
	/**
	 * Starts up the window with the given width and height
	 * @param width The width of the window wanted
	 * @param height The height of the window wanted
	 */
	public void startUp(int width, int height){
		// get screen dimension
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		// set the window height and width, center the window on the primary screen
		window_width = width;
		window_height = height;
		window_position_x = (int) (dim.getWidth() / 2 - 7 - window_width / 2);
		window_position_y = (int) (dim.getHeight() / 2 - 7 - window_height / 2);
		
		// set attributes of the window and content panel
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(window_position_x, window_position_y, window_width, window_height + WINDOW_BAR_HEIGHT);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.startUp();
	}
	
	/**
	 * Returns the width of the window
	 * @return
	 */
	public int getWindowWidth(){
		return window_width;
	}
	
	/**
	 * Returns the height of the window
	 * @return
	 */
	public int getWindowHeight(){
		return window_height;
	}
	
	/**
	 * Returns the x position of the window
	 * @return
	 */
	public int getWindowPositionX(){
		return window_position_x;
	}
	
	/**
	 * Returns the y position of the window
	 * @return
	 */
	public int getWindowPositionY(){
		return window_position_y;
	}
}
