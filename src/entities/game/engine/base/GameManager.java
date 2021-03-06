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
	private ArrayList<BasicObject> collideableObjects = new ArrayList<BasicObject>();
	
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
	
	public ArrayList<BasicObject> getObjectsWithName(String name){
		ArrayList<BasicObject> objWithName = new ArrayList<BasicObject>();
		for(int i = 0; i < objectList.size(); i++){
			if(objectList.get(i).getName().compareTo(name) == 0)
				objWithName.add(objectList.get(i));
		}
		
		return objWithName;
	}
	
	public ArrayList<BasicObject> getObjectsInAdjecentSquares(Position pos, int outerRadius, int innerRadius){
		ArrayList<BasicObject> objs = new ArrayList<BasicObject>();
		
		for(int i = -1*outerRadius; i <= outerRadius; i++){
			for(int j = -1*outerRadius; j <= outerRadius; j++){
				if(!(-1*innerRadius <= i && i <= innerRadius &&
				     -1*innerRadius <= j && j <= innerRadius)){
					Position checkPos = new Position(pos.getX() + i, pos.getY() + j);
					BasicObject checkObj = getObjectAtPosition(checkPos);
					if(checkObj != null)
						objs.add(checkObj);
				}
			}
		}
		
		return objs;
	}
	
	public boolean isObjectNameInAdjacentSquares(String name, Position pos, int outerRadius, int innerRadius){
		ArrayList<BasicObject> list = getObjectsInAdjecentSquares(pos, outerRadius, innerRadius);
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).getName().compareTo(name) == 0){
				return true;
			}
		}
		return false;
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
		stepCount = 0;
	}
	
	public ArrayList<BasicObject> getCollidableObjectsList(){
		return collideableObjects;
	}
	
	public void addToCollidableObjectsList(BasicObject obj){
		collideableObjects.add(obj);
	}
	
	public boolean objectInCollidableObjectsList(BasicObject obj){
		for(int i = 0; i < collideableObjects.size(); i++){
			if(collideableObjects.get(i).getName().compareTo(obj.getName()) == 0)
				return true;
		}
		return false;
	}
}
