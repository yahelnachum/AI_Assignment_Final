package entities.game.engine.extended;

import entities.game.engine.base.Position;

public class Move implements Comparable<Move>{

	private State s = new State(new Position(), new Position());
	private Position pos = new Position();
	private double value = 0;
	private int delta = 0;
	
	public Move(State s, Position pos, double value, int delta){
		this.s = s;
		this.pos = pos;
		this.value = value;
		this.delta = delta;
	}
	
	public State getState(){
		return s;
	}
	
	public Position getPosition(){
		return pos;
	}
	
	public double getValue(){
		return value;// - delta;
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
