package main;

import boundaries.graphics.GameWindow;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GameWindow gw = GameWindow.getInstance();
		gw.startUp(500, 500);
		gw.setVisible(true);
	}
}
