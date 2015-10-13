package entities.game.engine.extended;

import entities.game.engine.base.Position;

public class Move implements Comparable<Move>{

	private Position pos = new Position();
	private int value = 0;
	
	public Move(Position pos, int value){
		this.pos = pos;
		this.value = value;
	}
	
	public Position getPosition(){
		return pos;
	}
	
	public int getValue(){
		return value;
	}

	@Override
	public int compareTo(Move o) {
		if(this.value < o.value){
			return -1;
		}
		else if(this.value > o.value){
			return 1;
		}
		else{
			return 0;
		}
	}
}
