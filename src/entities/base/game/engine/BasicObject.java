package entities.base.game.engine;

import java.awt.Color;

public class BasicObject {

	private Position pos = new Position();
	private Color col = new Color(0);
	private String name = "";
	
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
	
	public void setColor(Color col){
		this.col = col;
	}
	
	public Color getColor(){
		return col;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void step(){
		pos.setX(pos.getX() + 1);
	}
}
