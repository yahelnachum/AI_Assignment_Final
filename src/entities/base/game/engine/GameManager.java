package entities.base.game.engine;

import java.util.ArrayList;

public class GameManager {

	private static GameManager gameManager = new GameManager();
	private ArrayList<BasicObject> objectList = new ArrayList<BasicObject>();
	private int world_width = 0;
	private int world_height = 0;
	private boolean gameOver = false;
	
	private GameManager(){}
	
	public static GameManager getInstance(){
		return gameManager;
	}
	
	public void step(){
		updateObjects();
	}
	
	public void updateObjects(){
		
	}
	
	public void drawObjects(){
		
	}
	
	public void addObjectToList(BasicObject obj){
		objectList.add(obj);
	}
	
	public int removeObectFromList(BasicObject obj){
		if(objectList.remove(obj))
			return 0;
		return -1;
	}
	
	public void setWorldWidth(int world_width){
		this.world_width = world_width;
	}
	
	public void setWorldHeight(int world_height){
		this.world_height = world_height;
	}
	
	public int getWorldWidth(){
		return world_width;
	}
	
	public int getWorldHeight(){
		return world_height;
	}
	
	public void setGameOver(boolean gameOver){
		this.gameOver = gameOver;
	}
	public boolean getGameOver(){
		return gameOver;
	}
}
