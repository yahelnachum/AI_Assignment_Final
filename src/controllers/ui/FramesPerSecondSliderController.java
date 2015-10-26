package controllers.ui;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import boundaries.graphics.ApplicationManager;
import boundaries.graphics.UserInterfacePanel;
import boundaries.graphics.WindowSliderEvent;

public class FramesPerSecondSliderController implements ChangeListener{

	public void stateChanged(ChangeEvent e){
		int jSliderValue = ((JSlider)e.getSource()).getValue();

		if(jSliderValue == 0){
			jSliderValue++;
		}
	
		ApplicationManager.getInstance().addWindowEvent(new WindowSliderEvent(jSliderValue, WindowSliderEvent.FPS_SLIDER_EVENT));
		UserInterfacePanel.getInstance().getFPSLabel().setText(String.valueOf(jSliderValue));
	}	
}
