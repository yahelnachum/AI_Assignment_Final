package entities.game.engine.extended;

import java.awt.Color;

import entities.game.engine.base.BasicObject;
import entities.game.engine.base.Position;

public class BreadCrumb extends BasicObject{
	public static final String BREADCRUMB_TYPE = "BreadCrumb";
	int slowdown = 10;
	public BreadCrumb(Position pos){
		setPosition(pos);
		setName(BREADCRUMB_TYPE);
		setColor(Color.WHITE);
	}
	
	public void step(){
		if(slowdown < 0){
			setColor(new Color(getColor().getRGB() - 0x010101));
			slowdown = 10;
		}
		else{
			slowdown--;
		}
		
	}
}
