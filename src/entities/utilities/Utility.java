package entities.utilities;

import entities.game.engine.base.Direction;
import entities.game.engine.base.Position;

public class Utility {

	public static Position getPositionInDirection(Position pos, Direction dir){
		Position copy = new Position(pos.getX(), pos.getY());
		
		switch(dir){
		case NORTH:
			copy.setY(copy.getY() - 1);
			break;
		case SOUTH:
			copy.setY(copy.getY() + 1);
			break;
		case EAST:
			copy.setX(copy.getX() + 1);
			break;
		case WEST:
			copy.setX(copy.getX() - 1);
			break;
		}
		
		return copy;
	}
}
