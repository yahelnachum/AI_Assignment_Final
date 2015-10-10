package entities.base.game.engine;

public class BasicObject {

	private Position pos = new Position();
	
	public BasicObject(){
		
	}
	
	public BasicObject(Position pos){
		this.pos = pos;
	}
	
	public Position getPosition(){
		return pos;
	}
	
	public void setPosition(Position pos){
		this.pos = pos;
	}
}
