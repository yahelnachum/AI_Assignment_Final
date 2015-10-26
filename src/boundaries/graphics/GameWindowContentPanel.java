package boundaries.graphics;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;

import controllers.ui.WindowResizedController;

public class GameWindowContentPanel extends JPanel {

	private DrawingPanel drawingPanel = DrawingPanel.getInstance();
	private UserInterfacePanel userInterfacePanel = UserInterfacePanel.getInstance();
	private static GameWindowContentPanel gameWindowContentPanel = new GameWindowContentPanel();
	/**
	 * Create the panel.
	 */
	private GameWindowContentPanel() {}
	
	public static GameWindowContentPanel getInstance(){
		return gameWindowContentPanel;
	}
	
	public void startUp(){

		/* General gridbaglayout */
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 10.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		/* User interface panel */ 
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(userInterfacePanel, gbc_panel);
		
		/* Drawing panel */
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		add(drawingPanel, gbc_panel_1);
		
		// start up panels
		userInterfacePanel.startUp();	
		drawingPanel.startUp();
		
		// add resizing listener to window
		drawingPanel.addComponentListener(new WindowResizedController());
	}
}
