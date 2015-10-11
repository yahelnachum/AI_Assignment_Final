package main;

import entities.base.game.engine.GameManager;
import boundaries.graphics.GameWindow;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GameManager gm = GameManager.getInstance();
		gm.setWorldWidth(10);
		gm.setWorldHeight(10);
		GameWindow gw = GameWindow.getInstance();
		gw.startUp(500, 500);
		gw.setVisible(true);
		
		while(gm.getGameOver() == false){
			gm.step();
			gw.repaint();
		}
	}
}
