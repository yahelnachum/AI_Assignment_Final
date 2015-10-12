package entities.game.engine.extended;

import java.awt.Color;

import entities.game.engine.base.BasicObject;
import entities.game.engine.base.Direction;
import entities.game.engine.base.GameManager;
import entities.game.engine.base.Position;

public class Hero extends BasicObject{

	public Hero(Position pos){
		setPosition(pos);
		setName("Hero");
		setColor(Color.BLUE);
	}
	
	public void step(){
		if(GameManager.getInstance().getStepCount() % 4 == 0){
			move(Direction.NORTH);
		}
		else if(GameManager.getInstance().getStepCount() % 4 == 1){
			move(Direction.EAST);
		}
		else if(GameManager.getInstance().getStepCount() % 4 == 2){
			move(Direction.SOUTH);
		}
		else{
			move(Direction.WEST);
		}
	}
}
