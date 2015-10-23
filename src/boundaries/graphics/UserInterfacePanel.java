package boundaries.graphics;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;

public class UserInterfacePanel extends JPanel {

	
	private static UserInterfacePanel runButtonPanel = new UserInterfacePanel();
	
	private JLabel lblNewLabel = new JLabel("New label");
	private JLabel lblNewLabel_1 = new JLabel("New label");
	/**
	 * Create the panel.
	 */
	private UserInterfacePanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{89, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{23, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 10.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		JButton btnNewButton = new JButton("New button");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		add(btnNewButton, gbc_btnNewButton);
		
		JSlider slider = new JSlider();
		slider.setMaximum(500);
		slider.setValue(30);
		slider.setPreferredSize(new Dimension(500, 26));
		slider.setMinimum(1);
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.insets = new Insets(0, 0, 5, 5);
		gbc_slider.gridx = 1;
		gbc_slider.gridy = 0;
		add(slider, gbc_slider);
		
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JSlider slider_1 = new JSlider();
		slider_1.setValue(1);
		slider_1.setMinimum(1);
		GridBagConstraints gbc_slider_1 = new GridBagConstraints();
		gbc_slider_1.insets = new Insets(0, 0, 0, 5);
		gbc_slider_1.gridx = 1;
		gbc_slider_1.gridy = 1;
		add(slider_1, gbc_slider_1);
		
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 1;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // This is only called when the user releases the mouse button.
                
				ApplicationManager.getInstance().addWindowEvent(new WindowRunGameEvent());
            }
        });
		
		slider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){
				int jSliderValue = ((JSlider)e.getSource()).getValue();
				ApplicationManager.getInstance().addWindowEvent(new WindowSliderEvent(jSliderValue, WindowSliderEvent.FPS_SLIDER_EVENT));
				UserInterfacePanel.getInstance().getFPSLabel().setText(String.valueOf(jSliderValue));
			}
		});
		
		slider_1.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){
				int jSliderValue = ((JSlider)e.getSource()).getValue();
				ApplicationManager.getInstance().addWindowEvent(new WindowSliderEvent(jSliderValue, WindowSliderEvent.GAME_STEPS_TO_SKIP_SLIDER_EVENT));
				UserInterfacePanel.getInstance().getGameStepsToSkipLabel().setText(String.valueOf(jSliderValue));
			}
		});
	}
	
	public static UserInterfacePanel getInstance(){
		return runButtonPanel;
	}
	
	public void startUp(){
		
	}
	
	public JLabel getFPSLabel(){
		return lblNewLabel;
	}

	public JLabel getGameStepsToSkipLabel(){
		return lblNewLabel_1;
	}
}
