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
		gm.setWorldWidth(7);
		gm.setWorldHeight(7);
		// set up game then set up window
		setUpGame();
		setUpWindow();
		
		for(int i = 0; i < 10; i++){

			
			// run game loop until game over
			Clock clock = new Clock();
			long fps = 30;
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
			
			gm.resetGame();
			setUpGame();
		}
	}
	
	/**
	 * Set up the world width and height
	 * Set up the game objects
	 */
	public static void setUpGame(){
		new Enemy(new Position(6, 6));
		new Hero(new Position(0,0));
		new Wall(new Position(3,3));
	}
	
	/**
	 * Set up the window width and height
	 * Set the window to be visible
	 */
	public static void setUpWindow(){
		GameWindow gw = GameWindow.getInstance();
		gw.startUp(500, 500);
		gw.setVisible(true);
	}
}
