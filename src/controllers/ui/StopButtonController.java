package controllers.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import boundaries.graphics.ApplicationManager;
import boundaries.graphics.WindowGameEvent;

public class StopButtonController implements ActionListener{

	public void actionPerformed(ActionEvent e) {
        // This is only called when the user releases the mouse button.
        
		ApplicationManager.getInstance().addWindowEvent(new WindowGameEvent(WindowGameEvent.STOP_GAME_EVENT));
    }
}
