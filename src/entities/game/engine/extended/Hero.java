package entities.game.engine.extended;

import java.awt.Color;

import entities.game.engine.base.BasicObject;
import entities.game.engine.base.Position;

public class Hero extends BasicObject{

	public Hero(Position pos){
		setPosition(pos);
		setName("Hero");
		setColor(Color.BLUE);
	}
}
