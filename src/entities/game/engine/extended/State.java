package entities.game.engine.extended;

import java.util.ArrayList;

import entities.game.engine.base.BasicObject;
import entities.game.engine.base.GameManager;
import entities.game.engine.base.Position;

public class State {

	int enemyProximity = 0;
	int goalProximity = 0;
	boolean closerToGoal = false;
	
	public State(Position previous, Position pos){

		if(GameManager.getInstance().isObjectNameInAdjacentSquares(Enemy.ENEMY_TYPE, pos, 2, 1))
			enemyProximity = 1;
		if(GameManager.getInstance().isObjectNameInAdjacentSquares(Goal.GOAL_TYPE, pos, 2, 1))
			goalProximity = 1;
		
		if(GameManager.getInstance().isObjectNameInAdjacentSquares(Enemy.ENEMY_TYPE, pos, 1, 0))
			enemyProximity = 2;
		if(GameManager.getInstance().isObjectNameInAdjacentSquares(Goal.GOAL_TYPE, pos, 1, 0))
			goalProximity = 2;
		
		BasicObject objAtPos = GameManager.getInstance().getObjectAtPosition(pos);
		if(objAtPos != null && 
		   objAtPos.getName().compareTo(Enemy.ENEMY_TYPE) == 0){
			enemyProximity = 3;
		}
		else if(objAtPos != null && 
				   objAtPos.getName().compareTo(Goal.GOAL_TYPE) == 0){
					goalProximity = 3;
		}
		
		BasicObject goal = GameManager.getInstance().getObjectsWithName(Goal.GOAL_TYPE).get(0);
		if(pos.getManhattanDistance(goal.getPosition()) < previous.getManhattanDistance(goal.getPosition()))
			closerToGoal = true;
	}
	
	public State(int enemyProximity, int goalProximity, boolean closerToGoal){
		this.enemyProximity = enemyProximity;
		this.goalProximity = goalProximity;
		this.closerToGoal = closerToGoal;
	}
	
	public boolean equals(Object obj){
		if(!(obj instanceof State))
			return false;
		
		State s = (State) obj;
		if(this.hashCode() == s.hashCode())
			return true;
		return false;
	}
	
	public int hashCode(){
		return 19*enemyProximity + 23*goalProximity + (closerToGoal? 29:41);
	}
	
	
}
