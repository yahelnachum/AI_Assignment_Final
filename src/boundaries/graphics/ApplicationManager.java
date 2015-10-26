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

	
	private int fps = 30;						// times to refresh the screen per second
	private int numOfGameStepsToSkip = 1;		// number of game steps to skip before refreshing the screen
	private int timeToShowResults = 500;		// time to show the results of a run
	private boolean stopRun = false;			// whether or not to stop refreshing the screen at all
	private boolean runGame = false;			// whether or not to run the game
	private boolean stepGame = false;			// whether or not to run one step of the game
	private Clock clock = new Clock();			// used to keep refresh rate constant
		
	private ArrayList<WindowEvent> windowEvents = new ArrayList<WindowEvent>(); // a list of events to complete for the window
	
	private static ApplicationManager applicationManager = new ApplicationManager(); // keep a singlton
	
	/**
	 * Private for singleton
	 * Set up look and feel based on the system information
	 */
	private ApplicationManager(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the single instance of the ApplicationManager
	 * @return ApplicationManager
	 */
	public static ApplicationManager getInstance(){
		return applicationManager;
	}
	
	/**
	 * Set up the GameManager and GameWindow
	 */
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
	
	/**
	 * Run the window
	 * @throws InterruptedException
	 */
	public void run() throws InterruptedException{
		// get instances of objects
		GameManager gm = GameManager.getInstance();
		GameWindow gw = GameWindow.getInstance();
		DrawingPanel dp = DrawingPanel.getInstance();
		
		// run window
		while(!stopRun){
			clock.delta();
			pollForWindowEvents();
			
			// run game step
			if(runGame || stepGame){
				// if game is still running
				if(!gm.getGameOver()){
					gm.step();
					
					// refresh if the number of game steps has already been skiped
					if(gm.getStepCount() % numOfGameStepsToSkip == 0){
						dp.repaint();
					}
				}
				// if game is over, refresh and show results, then reset game
				else{
					dp.repaint();
					Thread.sleep(timeToShowResults);
					gm.resetGame();
					setUpRandomLevel();
				}
				
				// if it performed a single step, then don't step another time
				if(stepGame){
					stepGame = false;
				}
			}
			
			// figure out how long to sleep for to keep refresh rate constant
			long actualTime = clock.split();
			if(actualTime < 1000 / fps){
				Thread.sleep(1000 / fps - actualTime); 
			}
		}
	}
	
	/**
	 * Run through all the window events and resolve them.
	 */
	public void pollForWindowEvents(){
		for(int i = 0; i < windowEvents.size(); i++){
			// if its a game event
			if(windowEvents.get(i).getWindowEventName() == WindowGameEvent.WINDOW_GAME_EVENT){
				WindowGameEvent wge = (WindowGameEvent) windowEvents.get(i);
				
				// run game event
				if(wge.getGameEvent() == WindowGameEvent.RUN_GAME_EVENT){
					runGame = true;
				}
				// stop game event
				else if(wge.getGameEvent() == WindowGameEvent.STOP_GAME_EVENT){
					runGame = false;
					stepGame = false;
				}
				// step game event
				else if(wge.getGameEvent() == WindowGameEvent.STEP_GAME_EVENT){
					stepGame = true;
				}
				
			}
			
			// if its a slider event
			else if(windowEvents.get(i).getWindowEventName() == WindowSliderEvent.WINDOW_SLIDER_EVENT){
				WindowSliderEvent wse = (WindowSliderEvent) windowEvents.get(i);
				
				// fps slider
				if(wse.getSliderEventName() == WindowSliderEvent.FPS_SLIDER_EVENT)
					fps = wse.getSliderValue();
				// game steps to skip slider
				else if(wse.getSliderEventName() == WindowSliderEvent.GAME_STEPS_TO_SKIP_SLIDER_EVENT){
					numOfGameStepsToSkip = wse.getSliderValue();
				}
				// time to show results slider
				else if(wse.getSliderEventName() == WindowSliderEvent.TIME_TO_SHOW_RESULTS_EVENT){
					timeToShowResults = wse.getSliderValue();
				}
			}
		}
		
		// clear all events
		windowEvents.clear();
	}
	
	/**
	 * Add a new window event to the list
	 * @param we WindowEvent
	 */
	public void addWindowEvent(WindowEvent we){
		windowEvents.add(we);
	}
	
	/**
	 * Set up a random level in the game
	 */
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
