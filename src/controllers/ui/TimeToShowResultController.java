package controllers.ui;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import boundaries.graphics.ApplicationManager;
import boundaries.graphics.UserInterfacePanel;
import boundaries.graphics.WindowSliderEvent;

public class TimeToShowResultController implements ChangeListener{

	public void stateChanged(ChangeEvent e){
		int jSliderValue = ((JSlider)e.getSource()).getValue();
		ApplicationManager.getInstance().addWindowEvent(new WindowSliderEvent(jSliderValue, WindowSliderEvent.TIME_TO_SHOW_RESULTS_EVENT));
		UserInterfacePanel.getInstance().getTimeToShowResultsLabel().setText(String.valueOf(jSliderValue));
	}
}
