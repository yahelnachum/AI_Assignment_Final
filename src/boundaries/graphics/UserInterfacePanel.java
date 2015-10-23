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
import java.awt.Component;
import javax.swing.JProgressBar;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.BoxLayout;

public class UserInterfacePanel extends JPanel {

	
	private static UserInterfacePanel runButtonPanel = new UserInterfacePanel();
	
	JButton runButton = new JButton("Run");
	
	JSlider fpsSlider = new JSlider();
	JSlider gameStepsSlider = new JSlider();
	JSlider resultsTimeSlider = new JSlider();
	
	private JLabel fpsLabel = new JLabel(String.valueOf(ApplicationManager.getInstance().getFPS()));
	private JLabel gameStepsLabel = new JLabel(String.valueOf(ApplicationManager.getInstance().getNumberOfGameStepsToSkip()));
	private JLabel resultsTimeLabel = new JLabel(String.valueOf(ApplicationManager.getInstance().getTimeToShowResults()));
	
	JPanel panelForResultsTime = new JPanel();
	JPanel panelForGameSteps = new JPanel();
	JPanel panelForFPSSlider = new JPanel();
	JPanel panelForRunButton = new JPanel();
	
	private JTextArea txtrFramesPerSecond = new JTextArea();
	private JTextArea txtrNumberOfGame = new JTextArea();
	private JTextArea txtrTimeToShow = new JTextArea();
	private JPanel panelForStopButton = new JPanel();
	private JPanel panelForStepButton = new JPanel();
	private JButton stepButton = new JButton("Step");
	private JButton stopButton = new JButton("Stop");
	
	/**
	 * Create the panel.
	 */
	private UserInterfacePanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{23, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 10.0, 2.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		GridBagConstraints gbc_panelForFPSSlider = new GridBagConstraints();
		gbc_panelForFPSSlider.insets = new Insets(0, 0, 5, 5);
		gbc_panelForFPSSlider.fill = GridBagConstraints.BOTH;
		gbc_panelForFPSSlider.gridx = 2;
		gbc_panelForFPSSlider.gridy = 0;
		add(panelForFPSSlider, gbc_panelForFPSSlider);
		panelForFPSSlider.setLayout(new BorderLayout(0, 0));
		
		panelForFPSSlider.add(fpsSlider, BorderLayout.CENTER);
		fpsSlider.setMaximum(500);
		fpsSlider.setValue(30);
		fpsSlider.setPreferredSize(new Dimension(500, 26));
		fpsSlider.setMinimum(1);
		
		fpsSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){
				int jSliderValue = ((JSlider)e.getSource()).getValue();
				ApplicationManager.getInstance().addWindowEvent(new WindowSliderEvent(jSliderValue, WindowSliderEvent.FPS_SLIDER_EVENT));
				UserInterfacePanel.getInstance().getFPSLabel().setText(String.valueOf(jSliderValue));
			}
		});
		
		GridBagConstraints gbc_txtrFramesPerSecond = new GridBagConstraints();
		gbc_txtrFramesPerSecond.insets = new Insets(0, 0, 5, 5);
		gbc_txtrFramesPerSecond.fill = GridBagConstraints.BOTH;
		gbc_txtrFramesPerSecond.gridx = 3;
		gbc_txtrFramesPerSecond.gridy = 0;
		txtrFramesPerSecond.setFont(UIManager.getFont("Label.font"));
		txtrFramesPerSecond.setEditable(false);
		txtrFramesPerSecond.setVerifyInputWhenFocusTarget(false);
		txtrFramesPerSecond.setBackground(UIManager.getColor("Panel.background"));
		txtrFramesPerSecond.setText("Frames \r\nPer \r\nSecond");
		add(txtrFramesPerSecond, gbc_txtrFramesPerSecond);
		
		GridBagConstraints gbc_fpsLabel = new GridBagConstraints();
		gbc_fpsLabel.anchor = GridBagConstraints.WEST;
		gbc_fpsLabel.insets = new Insets(0, 0, 5, 0);
		gbc_fpsLabel.gridx = 4;
		gbc_fpsLabel.gridy = 0;
		add(fpsLabel, gbc_fpsLabel);
		
		GridBagConstraints gbc_panelForRunButton = new GridBagConstraints();
		gbc_panelForRunButton.gridwidth = 2;
		gbc_panelForRunButton.gridheight = 2;
		gbc_panelForRunButton.insets = new Insets(0, 0, 5, 5);
		gbc_panelForRunButton.fill = GridBagConstraints.BOTH;
		gbc_panelForRunButton.gridx = 0;
		gbc_panelForRunButton.gridy = 0;
		add(panelForRunButton, gbc_panelForRunButton);
		panelForRunButton.setLayout(new BorderLayout(0, 0));
		
		panelForRunButton.add(runButton, BorderLayout.CENTER);
		runButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // This is only called when the user releases the mouse button.
                
				ApplicationManager.getInstance().addWindowEvent(new WindowGameEvent(WindowGameEvent.RUN_GAME_EVENT));
            }
        });
		
		stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // This is only called when the user releases the mouse button.
                
				ApplicationManager.getInstance().addWindowEvent(new WindowGameEvent(WindowGameEvent.STOP_GAME_EVENT));
            }
        });
		
		stepButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // This is only called when the user releases the mouse button.
                
				ApplicationManager.getInstance().addWindowEvent(new WindowGameEvent(WindowGameEvent.STEP_GAME_EVENT));
            }
        });
		
		GridBagConstraints gbc_panelForGameSteps = new GridBagConstraints();
		gbc_panelForGameSteps.insets = new Insets(0, 0, 5, 5);
		gbc_panelForGameSteps.fill = GridBagConstraints.BOTH;
		gbc_panelForGameSteps.gridx = 2;
		gbc_panelForGameSteps.gridy = 1;
		add(panelForGameSteps, gbc_panelForGameSteps);
		panelForGameSteps.setLayout(new BorderLayout(0, 0));
		
		panelForGameSteps.add(gameStepsSlider, BorderLayout.CENTER);
		gameStepsSlider.setValue(1);
		gameStepsSlider.setPreferredSize(new Dimension(500, 26));
		gameStepsSlider.setMinimum(1);
		
		gameStepsSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){
				int jSliderValue = ((JSlider)e.getSource()).getValue();
				ApplicationManager.getInstance().addWindowEvent(new WindowSliderEvent(jSliderValue, WindowSliderEvent.GAME_STEPS_TO_SKIP_SLIDER_EVENT));
				UserInterfacePanel.getInstance().getGameStepsToSkipLabel().setText(String.valueOf(jSliderValue));
			}
		});
		
		GridBagConstraints gbc_txtrNumberOfGame = new GridBagConstraints();
		gbc_txtrNumberOfGame.insets = new Insets(0, 0, 5, 5);
		gbc_txtrNumberOfGame.fill = GridBagConstraints.BOTH;
		gbc_txtrNumberOfGame.gridx = 3;
		gbc_txtrNumberOfGame.gridy = 1;
		txtrNumberOfGame.setVerifyInputWhenFocusTarget(false);
		txtrNumberOfGame.setText("Number\r\nOf Game\r\nSteps To\r\nSkip");
		txtrNumberOfGame.setFont(UIManager.getFont("Label.font"));
		txtrNumberOfGame.setEditable(false);
		txtrNumberOfGame.setBackground(SystemColor.menu);
		add(txtrNumberOfGame, gbc_txtrNumberOfGame);
		
		GridBagConstraints gbc_gameStepsLabel = new GridBagConstraints();
		gbc_gameStepsLabel.anchor = GridBagConstraints.WEST;
		gbc_gameStepsLabel.insets = new Insets(0, 0, 5, 0);
		gbc_gameStepsLabel.gridx = 4;
		gbc_gameStepsLabel.gridy = 1;
		add(gameStepsLabel, gbc_gameStepsLabel);
		
		GridBagConstraints gbc_panelForStopButton = new GridBagConstraints();
		gbc_panelForStopButton.insets = new Insets(0, 0, 0, 5);
		gbc_panelForStopButton.fill = GridBagConstraints.BOTH;
		gbc_panelForStopButton.gridx = 0;
		gbc_panelForStopButton.gridy = 2;
		add(panelForStopButton, gbc_panelForStopButton);
		panelForStopButton.setLayout(new BorderLayout(0, 0));
		
		panelForStopButton.add(stopButton);
		
		GridBagConstraints gbc_panelForStepButton = new GridBagConstraints();
		gbc_panelForStepButton.insets = new Insets(0, 0, 0, 5);
		gbc_panelForStepButton.fill = GridBagConstraints.BOTH;
		gbc_panelForStepButton.gridx = 1;
		gbc_panelForStepButton.gridy = 2;
		add(panelForStepButton, gbc_panelForStepButton);
		panelForStepButton.setLayout(new BorderLayout(0, 0));
		
		panelForStepButton.add(stepButton, BorderLayout.CENTER);
		
		GridBagConstraints gbc_panelForResultsTime = new GridBagConstraints();
		gbc_panelForResultsTime.insets = new Insets(0, 0, 0, 5);
		gbc_panelForResultsTime.fill = GridBagConstraints.BOTH;
		gbc_panelForResultsTime.gridx = 2;
		gbc_panelForResultsTime.gridy = 2;
		add(panelForResultsTime, gbc_panelForResultsTime);
		panelForResultsTime.setLayout(new BorderLayout(0, 0));
		
		panelForResultsTime.add(resultsTimeSlider);
		resultsTimeSlider.setMinimum(1);
		resultsTimeSlider.setMaximum(1000);
		resultsTimeSlider.setValue(500);
		
		resultsTimeSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){
				int jSliderValue = ((JSlider)e.getSource()).getValue();
				ApplicationManager.getInstance().addWindowEvent(new WindowSliderEvent(jSliderValue, WindowSliderEvent.TIME_TO_SHOW_RESULTS_EVENT));
				UserInterfacePanel.getInstance().getTimeToShowResultsLabel().setText(String.valueOf(jSliderValue));
			}
		});
		
		GridBagConstraints gbc_txtrTimeToShow = new GridBagConstraints();
		gbc_txtrTimeToShow.insets = new Insets(0, 0, 0, 5);
		gbc_txtrTimeToShow.fill = GridBagConstraints.BOTH;
		gbc_txtrTimeToShow.gridx = 3;
		gbc_txtrTimeToShow.gridy = 2;
		txtrTimeToShow.setVerifyInputWhenFocusTarget(false);
		txtrTimeToShow.setText("Time To\r\nShow Result\r\n(msec)");
		txtrTimeToShow.setFont(UIManager.getFont("Label.font"));
		txtrTimeToShow.setEditable(false);
		txtrTimeToShow.setBackground(SystemColor.menu);
		add(txtrTimeToShow, gbc_txtrTimeToShow);
		
		GridBagConstraints gbc_resultsTimeLabel = new GridBagConstraints();
		gbc_resultsTimeLabel.anchor = GridBagConstraints.WEST;
		gbc_resultsTimeLabel.gridx = 4;
		gbc_resultsTimeLabel.gridy = 2;
		add(resultsTimeLabel, gbc_resultsTimeLabel);
	}
	
	public static UserInterfacePanel getInstance(){
		return runButtonPanel;
	}
	
	public void startUp(){
		
	}
	
	public JLabel getFPSLabel(){
		return fpsLabel;
	}

	public JLabel getGameStepsToSkipLabel(){
		return gameStepsLabel;
	}
	
	public JLabel getTimeToShowResultsLabel(){
		return resultsTimeLabel;
	}
}
