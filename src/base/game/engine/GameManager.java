package base.game.engine;

import java.util.ArrayList;

public class GameManager {

	private static GameManager gameManager = new GameManager();
	private ArrayList<BasicObject> objectList = new ArrayList<BasicObject>();
	
	private GameManager(){}
	
	public static GameManager getInstance(){
		return gameManager;
	}
	
	public void run(){
		
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
}
