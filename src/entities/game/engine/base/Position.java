package entities.game.engine.base;

/**
 * @author Yahel
 * Position class to keep track of x, y locations
 */
public class Position implements Comparable<Position>{
	
	// x and y
	private int x = 0;
	private int y = 0;
	
	/**
	 * Construct a position of x=0 and y=0
	 */
	public Position(){
		
	}
	
	/**
	 * Construct a position with the given x, y
	 * @param x
	 * @param y
	 */
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Get the x value
	 * @return
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Set the x value
	 * @param x
	 */
	public void setX(int x){
		this.x = x;
	}
	
	/**
	 * Set the y value
	 * @param y
	 */
	public void setY(int y){
		this.y = y;
	}
	
	/**
	 * Get the y value
	 * @return
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Set the x and y value
	 * @param x
	 * @param y
	 */
	public void setXY(int x, int y){
		this.x = x;
		this.y = y;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * Compare two positions with each other
	 */
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
