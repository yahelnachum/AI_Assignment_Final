package entities.game.engine.extended;

import java.awt.Color;

import entities.game.engine.base.BasicObject;
import entities.game.engine.base.Position;

public class Goal extends BasicObject{

	public static final String GOAL_TYPE = "Goal";
	public Goal(Position pos){
		setPosition(pos);
		setName(GOAL_TYPE);
		setColor(Color.YELLOW);
	}

}
