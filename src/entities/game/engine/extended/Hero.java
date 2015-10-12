package entities.game.engine.extended;

import java.awt.Color;
import java.util.Random;

import entities.game.engine.base.BasicObject;
import entities.game.engine.base.Direction;
import entities.game.engine.base.GameManager;
import entities.game.engine.base.Position;
import entities.utilities.Utility;

public class Hero extends BasicObject{

	Random randomObj = new Random();
	public Hero(Position pos){
		setPosition(pos);
		setName("Hero");
		setColor(Color.BLUE);
	}
	
	public void step(){
		int choice = randomObj.nextInt(4);
		int returnNum = 0;
		Direction dir = Direction.NORTH;
		if(choice % 4 == 0){
			dir = Direction.NORTH;
			returnNum = move(Direction.NORTH);
		}
		else if(choice % 4 == 1){
			dir = Direction.EAST;
			returnNum = move(Direction.EAST);
		}
		else if(choice % 4 == 2){
			dir = Direction.SOUTH;
			returnNum = move(Direction.SOUTH);
		}
		else{
			dir = Direction.WEST;
			returnNum = move(Direction.WEST);
		}
		
		if(returnNum == -1){
			BasicObject obj = GameManager.getInstance().getObjectAtPosition(Utility.getPositionInDirection(getPosition(), dir));
			if(obj.getName().compareTo("Enemy") == 0){
				GameManager.getInstance().setGameOver(true);
			}
		}
	}
}
