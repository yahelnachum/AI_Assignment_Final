package entities.game.engine.extended;

import java.awt.Color;

import entities.game.engine.base.BasicObject;
import entities.game.engine.base.GameManager;
import entities.game.engine.base.Position;

public class BreadCrumb extends BasicObject{
	public static final String BREADCRUMB_TYPE = "BrCr";
	int slowdown = 20;
	int countDown = slowdown;
	int stepMade = 0;
	static Color original = new Color(0xffffaa);
	
	public BreadCrumb(Position pos, int stepMade){
		setPosition(pos);
		setName(BREADCRUMB_TYPE);
		setColor(original);
		this.stepMade = stepMade;
	}
	
	public void step(){
	
		if(countDown < 0){
			int r = original.getRed();
			int g = original.getGreen();
			int b = original.getBlue();
			
			r = (int)((double) r * (double)stepMade / (double) GameManager.getInstance().getStepCount());
			g = (int)((double) g * (double)stepMade / (double) GameManager.getInstance().getStepCount());
			b = (int)((double) b * (double)stepMade / (double) GameManager.getInstance().getStepCount());
			setColor(new Color(r, g, b));
			countDown = slowdown;
		}
		else{
			countDown--;
		}

		
		
	}
	
	public int getStepMade(){
		return stepMade;
	}
}
