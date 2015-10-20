package boundaries.graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class RunButtonPanel extends JPanel {

	
	private static RunButtonPanel runButtonPanel = new RunButtonPanel();
	
	/**
	 * Create the panel.
	 */
	private RunButtonPanel() {}
	
	public static RunButtonPanel getInstance(){
		return runButtonPanel;
	}
	
	public void startUp(){
		JButton btnNewButton = new JButton("New button");
		add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // This is only called when the user releases the mouse button.
                
				ApplicationManager.getInstance().addWindowEvent(new WindowRunGameEvent());;
            }
        });
	}

}
