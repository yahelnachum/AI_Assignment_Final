package base.game.engine;

public class Position implements Comparable<Position>{
	
	private int x = 0;
	private int y = 0;
	
	public Position(){
		
	}
	
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public int getY(){
		return y;
	}
	
	public void setXY(int x, int y){
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Position pos) {

		if(y < pos.getY())
			return -1;
		else if(y > pos.getY())
			return 1;
		else if(x < pos.getX())
			return -1;
		else if(x > pos.getX())
			return 1;
		else
			return 0;
	}
}
