package entities.game.engine.extended;

import entities.game.engine.base.BasicObject;
import entities.game.engine.base.GameManager;
import entities.game.engine.base.Position;

public class State {

	private boolean enemyAtPosition = false;
	private boolean closerToGoal = false;
	private boolean atGoal = false;
	private boolean objInWay = false;
	int numOfObjectsNearPosition = 0;
	int numOfObjectsInBiggerProximityToPosition = 0;
	
	public State(Position previous, Position pos){
		BasicObject objAtPos = GameManager.getInstance().getObjectAtPosition(pos);
		if(objAtPos != null &&
		   objAtPos.getName().compareTo(Enemy.ENEMY_TYPE) == 0){
			enemyAtPosition = true;
		}
		if(objAtPos != null){
			objInWay = true;
		}
		BasicObject goal = GameManager.getInstance().getObjectsWithName(Goal.GOAL_TYPE).get(0);
		if(pos.getManhattanDistance(goal.getPosition()) < previous.getManhattanDistance(goal.getPosition()))
			closerToGoal = true;
		if(objInWay && objAtPos.getName().compareTo(Goal.GOAL_TYPE) == 0){
			atGoal = true;
			numOfObjectsNearPosition--;
		}
		numOfObjectsNearPosition += GameManager.getInstance().getObjectsInAdjecentSquares(pos).size() - 1;
		
		
	}
	
	public int getValueOfState(){
		 int sum = 0;
		 sum += enemyAtPosition? -50:25;
		 sum += closerToGoal? 1:-1;
		 sum += atGoal? 1000:0;
		 sum += objInWay? -5:0;
		 sum += numOfObjectsNearPosition * -1;
		 return sum;
	}
	
	public boolean isEnemyAtPosition(){
		return enemyAtPosition;
	}
	
	public boolean equals(Object obj){
		if(!(obj instanceof State)){
			return false;
		}
		else{
			State sObj = (State) obj;
			if(sObj.enemyAtPosition = this.enemyAtPosition)
				return true;
			else
				return false;
		}
	}
	
	public int hashCode(){
		int mul = 1;
		mul *= enemyAtPosition? 1231:1237;
		mul *= closerToGoal? 3:5;
		mul *= atGoal? 7:11;
		mul *= objInWay? 13:17;
		mul += numOfObjectsNearPosition * 19;
		return mul;
	}
	
	
}
