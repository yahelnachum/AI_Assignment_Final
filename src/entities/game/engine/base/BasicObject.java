package entities.game.engine.base;

import java.awt.Color;

import entities.utilities.Utility;

/**
 * @author Yahel
 * Basic object of the game engine
 * Provides basic functions and attributes
 * Should be extended to create more complex game objects
 */
public class BasicObject {

	// basic object attributes
	private Position pos = new Position();
	private Color col = Color.WHITE;
	private String name = "BasObj";
	
	/**
	 * Create a basic object with the default values
	 */
	public BasicObject(){
		GameManager.getInstance().addObjectToList(this);
	}
	
	/**
	 * Create a basic object at a position
	 * @param pos The position the object starts at
	 */
	public BasicObject(Position pos){
		this.pos = pos;
		GameManager.getInstance().addObjectToList(this);
	}
	
	/**
	 * Get the position the object is at
	 * @return
	 */
	public Position getPosition(){
		return pos;
	}
	
	/**
	 * Set the position that the object is at
	 * @param pos
	 */
	public void setPosition(Position pos){
		this.pos = pos;
	}
	
	/**
	 * Set the color that the object is given in the window
	 * @param col
	 */
	public void setColor(Color col){
		this.col = col;
	}
	
	/**
	 * Get the color of the object
	 * @return
	 */
	public Color getColor(){
		return col;
	}
	
	/**
	 * Set the name of the object
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * Get the name of the object
	 * @return
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Perform one step of this objects logic
	 */
	public void step(){
		
	}
	
	public int move(Direction dir){
		Position newPos = Utility.getPositionInDirection(getPosition(), dir);
		if(GameManager.getInstance().objectAtPosition(newPos)){
			BasicObject obj = GameManager.getInstance().getObjectAtPosition(newPos);
			if(GameManager.getInstance().objectInCollidableObjectsList(obj))
				return -1;
		}
		if(newPos.getX() < 0 || 
		   newPos.getY() < 0 ||
		   newPos.getX() > GameManager.getInstance().getWorldWidth() - 1 || 
		   newPos.getY() > GameManager.getInstance().getWorldHeight() - 1){
			return -2;
		}
		setPosition(newPos);
		return 0;
	}
}
