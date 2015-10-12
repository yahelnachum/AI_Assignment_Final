package main;

import boundaries.graphics.GameWindow;
import entities.game.engine.base.GameManager;
import entities.game.engine.base.Position;
import entities.game.engine.extended.Enemy;
import entities.game.engine.extended.Hero;
import entities.game.engine.extended.Wall;
import entities.utilities.Clock;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		// game manager and window instances
		GameManager gm = GameManager.getInstance();
		GameWindow gw = GameWindow.getInstance();
		
		// set up game then set up window
		setUpGame();
		setUpWindow();
		
		// run game loop until game over
		Clock clock = new Clock();
		long fps = 3;
		while(gm.getGameOver() == false){
			gm.step();
			gw.repaint();
			long actualTime = clock.split();
			
			// sleep for some time
			if(actualTime < 1000 / fps){
				Thread.sleep(1000 / fps - actualTime); 
			}
			clock.delta();
		}
	}
	
	/**
	 * Set up the world width and height
	 * Set up the game objects
	 */
	public static void setUpGame(){
		GameManager gm = GameManager.getInstance();
		gm.setWorldWidth(12);
		gm.setWorldHeight(12);
		new Enemy(new Position(3, 6));
		new Hero(new Position(1,4));
		new Wall(new Position(2,5));
	}
	
	/**
	 * Set up the window width and height
	 * Set the window to be visable
	 */
	public static void setUpWindow(){
		GameWindow gw = GameWindow.getInstance();
		gw.startUp(1000, 1000);
		gw.setVisible(true);
	}
}
