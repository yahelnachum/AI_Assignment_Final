package entities.game.engine.extended;

import java.awt.Color;

import entities.game.engine.base.BasicObject;
import entities.game.engine.base.Position;

public class Enemy extends BasicObject{

	public static final String ENEMY_TYPE = "Enemy";
	public Enemy(Position pos){
		setPosition(pos);
		setName(ENEMY_TYPE);
		setColor(Color.RED);
	}
}
