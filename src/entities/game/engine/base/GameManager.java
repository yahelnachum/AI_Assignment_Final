package entities.game.engine.base;

import java.util.ArrayList;

/**
 * @author Yahel
 * GameManager manages the game world and objects.
 */
public class GameManager {

	// single instance of the game manager
	private static GameManager gameManager = new GameManager();
	
	// the objects in the game world
	private ArrayList<BasicObject> objectList = new ArrayList<BasicObject>();
	
	// game world lengths
	private int world_width = 0;
	private int world_height = 0;
	
	// if the game is over
	private boolean gameOver = false;
	private static int stepCount = 0;
	
	
	/**
	 * Provides one instance of the game manager
	 */
	private GameManager(){}
	
	public static GameManager getInstance(){
		return gameManager;
	}
	
	/**
	 * Updates all the objects in the world
	 */
	public void step(){
		for(int i = 0; i < objectList.size(); i++){
			objectList.get(i).step();
		}
		stepCount++;
	}

	/**
	 * Add object to the world
	 * @param obj BasicObject to add to the world
	 */
	public void addObjectToList(BasicObject obj){
		objectList.add(obj);
	}
	
	/**
	 * Remove object from the world
	 * @param obj BasicObject to remove from the world
	 * @return returns 0 if it removed object, else -1 if it could not find object
	 */
	public int removeObectFromList(BasicObject obj){
		if(objectList.remove(obj))
			return 0;
		return -1;
	}
	
	/**
	 * Returns the array list of game objects
	 * @return
	 */
	public ArrayList<BasicObject> getObjectsList(){
		return objectList;
	}
	
	/**
	 * Sets the game world's width
	 * @param world_width
	 */
	public void setWorldWidth(int world_width){
		this.world_width = world_width;
	}
	
	/**
	 * Sets the game world's height
	 * @param world_height
	 */
	public void setWorldHeight(int world_height){
		this.world_height = world_height;
	}
	
	/**
	 * Gets the game world's width
	 * @return
	 */
	public int getWorldWidth(){
		return world_width;
	}
	
	/**
	 * Gets the game world's height
	 * @return
	 */
	public int getWorldHeight(){
		return world_height;
	}
	
	/**
	 * Sets the game over boolean
	 * @param gameOver
	 */
	public void setGameOver(boolean gameOver){
		this.gameOver = gameOver;
	}
	
	/**
	 * Gets the game over boolean
	 * @return
	 */
	public boolean getGameOver(){
		return gameOver;
	}
	
	public boolean objectAtPosition(Position pos){
		for(int i = 0; i < objectList.size(); i++){
			if(objectList.get(i).getPosition().compareTo(pos) == 0)
				return true;
		}
		return false;
	}
	
	public BasicObject getObjectAtPosition(Position pos){
		for(int i = 0; i < objectList.size(); i++){
			if(objectList.get(i).getPosition().compareTo(pos) == 0)
				return objectList.get(i);
		}
		return null;
	}
	
	public int getStepCount(){
		return stepCount;
	}
	
	/**
	 * Reset the game by removing all the objects
	 */
	public void resetGame(){
		objectList.clear();
		gameOver = false;
	}
}
