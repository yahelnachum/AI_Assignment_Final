package main;

import boundaries.graphics.GameWindow;
import entities.game.engine.base.GameManager;
import entities.game.engine.base.Position;
import entities.game.engine.extended.Enemy;
import entities.game.engine.extended.Goal;
import entities.game.engine.extended.Hero;
import entities.game.engine.extended.State;
import entities.game.engine.extended.Wall;
import entities.utilities.Clock;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		// game manager and window instances
		GameManager gm = GameManager.getInstance();
		GameWindow gw = GameWindow.getInstance();
		gm.setWorldWidth(20);
		gm.setWorldHeight(20);
		// set up game then set up window
		setUpGame();
		setUpWindow();
		
		Hero.lookUpTable.put(new State(0,3,true), 1000.0);
		Hero.lookUpTable.put(new State(1,3,true), 1000.0);
		Hero.lookUpTable.put(new State(2,3,true), 1000.0);
		
		Hero.lookUpTable.put(new State(0,3,false), 1000.0);
		Hero.lookUpTable.put(new State(1,3,false), 1000.0);
		Hero.lookUpTable.put(new State(2,3,false), 1000.0);
		
		Hero.lookUpTable.put(new State(3,3,false), -1000.0);
		Hero.lookUpTable.put(new State(3,2,false), -1000.0);
		Hero.lookUpTable.put(new State(3,1,false), -1000.0);
		Hero.lookUpTable.put(new State(3,0,false), -1000.0);
		
		Hero.lookUpTable.put(new State(3,3,true), -1000.0);
		Hero.lookUpTable.put(new State(3,2,true), -1000.0);
		Hero.lookUpTable.put(new State(3,1,true), -1000.0);
		Hero.lookUpTable.put(new State(3,0,true), -1000.0);
		
		for(int j = 0; j < 4; j++){
			for(int k = 0; k < 4; k++){
				System.out.printf("%5.2f ", Hero.lookUpTable.get(new State(j,k, false)));
				System.out.printf("%5.2f ", Hero.lookUpTable.get(new State(j,k, true)));
			}
			System.out.println();
		}
		System.out.println();
		
		Thread.sleep(500);
		
		long fps = 100;
		long numOfGameStepsToSkip = 1;
		long showResultForMilliseconds = 100;
		Clock clock = new Clock();
		for(int i = 0; i < 100; i++){
			
			clock.delta();
			// run game loop until game over
			gw.repaint();
			while(gm.getGameOver() == false){
				gm.step();
				if(gm.getStepCount() % numOfGameStepsToSkip == 0){
					gw.repaint();
					long actualTime = clock.split();
					
					// sleep for some time
					if(actualTime < 1000 / fps){
						Thread.sleep(1000 / fps - actualTime); 
					}
					clock.delta();
				}
			}
			gw.repaint();
			Thread.sleep(showResultForMilliseconds);
			for(int j = 0; j < 4; j++){
				for(int k = 0; k < 4; k++){
					System.out.printf("%5.2f ", Hero.lookUpTable.get(new State(j,k, false)));
					System.out.printf("%5.2f ", Hero.lookUpTable.get(new State(j,k, true)));
				}
				System.out.println();
			}
			System.out.println();
			System.out.printf("Number of steps made: %d\n", ((Hero)gm.getObjectsWithName(Hero.HERO_TYPE).get(0)).getSteps());
			gm.resetGame();
			setUpGame();
			
			
		}
	}
	
	/**
	 * Set up the world width and height
	 * Set up the game objects
	 */
	public static void setUpGame(){
		Position center = new Position(17,17);
		new Enemy(new Position(center.getX() - 1,center.getY()-1));
		new Enemy(new Position(center.getX(),center.getY()-1));
		new Enemy(new Position(center.getX()+1,center.getY()-1));
		
		new Enemy(new Position(center.getX()-1,center.getY()));
		new Goal (new Position(center.getX(),center.getY()));
		new Enemy(new Position(center.getX()+1,center.getY()));
		
		new Enemy(new Position(center.getX()-1,center.getY()+1));
		new Enemy(new Position(center.getX()+1,center.getY()+1));
		
		for(int i = 0; i < 10; i++){
			new Wall(new Position(5+i, 15-i));
		}
		for(int i = 0; i < 5; i++){
			new Wall(new Position(5-i, 15-i));
		}
		for(int i = 0; i < 5; i++){
			new Wall(new Position(15-i, 5-i));
		}
		new Hero(new Position(0,0));
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
