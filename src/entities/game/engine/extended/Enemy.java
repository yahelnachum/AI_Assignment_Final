package entities.game.engine.extended;

import java.awt.Color;

import entities.game.engine.base.BasicObject;
import entities.game.engine.base.Position;

public class Enemy extends BasicObject{

	public Enemy(Position pos){
		setPosition(pos);
		setName("Enemy");
		setColor(Color.RED);
	}
}
