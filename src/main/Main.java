package main;

import java.util.Random;

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
		gm.addToCollidableObjectsList(new Wall(new Position(-100,-100)));
		gm.addToCollidableObjectsList(new Enemy(new Position(-100,-100)));
		gm.addToCollidableObjectsList(new Goal(new Position(-100,-100)));
		// set up game then set up window
		setUpRandomLevel();	
		setUpWindow();
		
		Hero.printTable();
		
		/*Hero.lookUpTable.put(new State(0,3,true), 1000.0);
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
		Hero.lookUpTable.put(new State(3,0,true), -1000.0);*/
		
		long fps = 30;
		long numOfGameStepsToSkip = 10000;
		long showResultForMilliseconds = 5;
		Clock clock = new Clock();
		for(int i = 0; i < 100000; i++){
			if(i == 300){
				showResultForMilliseconds = 500;
				numOfGameStepsToSkip = 10;
			}
			System.out.printf("Trial number: %d\n", i);
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
			Hero.printTable();
			System.out.printf("Number of steps made: %d\n\n", ((Hero)gm.getObjectsWithName(Hero.HERO_TYPE).get(0)).getSteps());
			gm.resetGame();

			setUpRandomLevel();		
		}
	}
	
	public static void setUpLevel(int level){
		switch(level){
		case 1:
			setUpLevelOne();
			break;
		case 2:
			setUpLevelTwo();
			break;
		default:
			setUpLevelThree();
			break;
		}
	}
	
	public static void setUpLevelOne(){
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
	
	public static void setUpLevelTwo(){
		
		new Enemy(new Position(5,3));
		new Enemy(new Position(5,8));
		new Enemy(new Position(5,13));
		new Enemy(new Position(15,3));
		new Enemy(new Position(15,8));
		new Enemy(new Position(15,13));
		for(int i = 0; i < 10; i++){
			new Wall(new Position(i, 5));
			new Wall(new Position(i+10, 10));
			new Wall(new Position(i, 15));
		}

		new Goal (new Position(19,19));
		
		new Hero(new Position(0,0));
	}
	
	public static void setUpLevelThree(){
		new Goal (new Position(19,19));
		
		new Hero(new Position(0,0));
	}
	
	public static void setUpRandomLevel(){
		new Goal (new Position(19,19));
		Random randomObj = new Random();
		for(int i = 0; i < 10; i++){
			Position pos = new Position(randomObj.nextInt(16) + 2, randomObj.nextInt(16) + 2);
			if(!GameManager.getInstance().objectAtPosition(pos)){
				new Enemy(pos);
			}
		}
		
		for(int i = 0; i < 10; i++){
			Position pos = new Position(randomObj.nextInt(16) + 2, randomObj.nextInt(16) + 2);
			if(!GameManager.getInstance().objectAtPosition(pos)){
				new Wall(pos);
			}
		}
		new Hero(new Position(0,0));
	}
	
	/**
	 * Set up the window width and height
	 * Set the window to be visible
	 */
	public static void setUpWindow(){
		GameWindow gw = GameWindow.getInstance();
		gw.startUp(1000, 1000);
		gw.setVisible(true);
	}
}
