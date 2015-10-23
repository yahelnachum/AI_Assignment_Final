package boundaries.graphics;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import entities.game.engine.base.GameManager;
import entities.game.engine.base.Position;
import entities.game.engine.extended.BreadCrumb;
import entities.game.engine.extended.Enemy;
import entities.game.engine.extended.Goal;
import entities.game.engine.extended.Hero;
import entities.game.engine.extended.Wall;
import entities.utilities.Clock;

public class ApplicationManager {

	private int fps = 30;
	private int numOfGameStepsToSkip = 1;
	private int timeToShowResults = 500;
	private boolean stopRun = false;
	private boolean runGame = false;
	private boolean stepGame = false;
	Clock clock = new Clock();
	
	ArrayList<WindowEvent> windowEvents = new ArrayList<WindowEvent>();
	
	private static ApplicationManager applicationManager = new ApplicationManager();
	
	private ApplicationManager(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ApplicationManager getInstance(){
		return applicationManager;
	}
	
	public void startUp(){
		GameManager gm = GameManager.getInstance();
		GameWindow gw = GameWindow.getInstance();
		
		gm.setWorldWidth(20);
		gm.setWorldHeight(20);
		gm.addToCollidableObjectsList(new Wall(new Position(-100,-100)));
		gm.addToCollidableObjectsList(new Enemy(new Position(-100,-100)));
		gm.addToCollidableObjectsList(new Goal(new Position(-100,-100)));
		
		gw.startUp();
		
		setUpRandomLevel();
	}
	
	public void loadWorld(String fileName){
		
	}
	
	public void run() throws InterruptedException{
		GameManager gm = GameManager.getInstance();
		GameWindow gw = GameWindow.getInstance();
		DrawingPanel dp = DrawingPanel.getInstance();
		
		while(!stopRun){
			clock.delta();
			pollForWindowEvents();
			
			if(runGame || stepGame){
				if(!gm.getGameOver()){
					gm.step();
					
					if(gm.getStepCount() % numOfGameStepsToSkip == 0){
						dp.repaint();
					}
				}
				else{
					dp.repaint();
					Thread.sleep(timeToShowResults);
					gm.resetGame();
					setUpRandomLevel();
				}
				
				if(stepGame){
					stepGame = false;
				}
			}
			
			long actualTime = clock.split();
			if(actualTime < 1000 / fps){
				Thread.sleep(1000 / fps - actualTime); 
			}
		}
	}
	
	public void pollForWindowEvents(){
		for(int i = 0; i < windowEvents.size(); i++){
			if(windowEvents.get(i).getWindowEventName() == WindowGameEvent.WINDOW_GAME_EVENT){
				WindowGameEvent wge = (WindowGameEvent) windowEvents.get(i);
				
				if(wge.getGameEvent() == WindowGameEvent.RUN_GAME_EVENT){
					runGame = true;
				}
				else if(wge.getGameEvent() == WindowGameEvent.STOP_GAME_EVENT){
					runGame = false;
					stepGame = false;
				}
				else if(wge.getGameEvent() == WindowGameEvent.STEP_GAME_EVENT){
					stepGame = true;
				}
				
			}
			else if(windowEvents.get(i).getWindowEventName() == WindowSliderEvent.WINDOW_SLIDER_EVENT){
				WindowSliderEvent wse = (WindowSliderEvent) windowEvents.get(i);
				
				if(wse.getSliderEventName() == WindowSliderEvent.FPS_SLIDER_EVENT)
					fps = wse.getSliderValue();
				else if(wse.getSliderEventName() == WindowSliderEvent.GAME_STEPS_TO_SKIP_SLIDER_EVENT){
					numOfGameStepsToSkip = wse.getSliderValue();
				}
				else if(wse.getSliderEventName() == WindowSliderEvent.TIME_TO_SHOW_RESULTS_EVENT){
					timeToShowResults = wse.getSliderValue();
				}
			}
		}
		
		windowEvents.clear();
	}
	
	public void addWindowEvent(WindowEvent we){
		windowEvents.add(we);
	}
	
	public void setUpRandomLevel(){
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
		new BreadCrumb(new Position(0,0), 0);
	}
	
	public int getFPS(){
		return fps;
	}
	public int getNumberOfGameStepsToSkip(){
		return numOfGameStepsToSkip;
	}
	public int getTimeToShowResults(){
		return timeToShowResults;
	}
}
