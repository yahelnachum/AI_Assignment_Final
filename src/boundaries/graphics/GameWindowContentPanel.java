package boundaries.graphics;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;

public class GameWindowContentPanel extends JPanel {

	DrawingPanel panel = DrawingPanel.getInstance();
	private static GameWindowContentPanel gameWindowContentPanel = new GameWindowContentPanel();
	/**
	 * Create the panel.
	 */
	private GameWindowContentPanel() {}
	
	public static GameWindowContentPanel getInstance(){
		return gameWindowContentPanel;
	}
	
	public void startUp(){

		/*GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		*/
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 10.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		RunButtonPanel panel = RunButtonPanel.getInstance();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		
		DrawingPanel panel_1 = DrawingPanel.getInstance();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		add(panel_1, gbc_panel_1);
		
		panel.startUp();	
		panel_1.startUp();
		
		this.getRootPane().addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                // This is only called when the user releases the mouse button.
                System.out.printf("w %d h %d\n", e.getComponent().getWidth(), e.getComponent().getHeight());
                DrawingPanel.getInstance().setWidthAndHeight();
            }
        });
	}
}
