package entities.game.engine.extended;

import java.awt.Color;

import entities.game.engine.base.BasicObject;
import entities.game.engine.base.Position;

public class Wall extends BasicObject{

	public Wall(Position pos){
		setPosition(pos);
		setName("Wall");
		setColor(Color.GRAY);
	}
}
