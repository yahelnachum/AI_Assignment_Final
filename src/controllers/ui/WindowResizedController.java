package controllers.ui;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import boundaries.graphics.DrawingPanel;

public class WindowResizedController extends ComponentAdapter{

	public void componentResized(ComponentEvent e) {
        // This is only called when the user releases the mouse button.
        DrawingPanel.getInstance().setWidthAndHeight();
    }
}
