package entities.game.engine.extended;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import entities.game.engine.base.BasicObject;
import entities.game.engine.base.Direction;
import entities.game.engine.base.GameManager;
import entities.game.engine.base.Position;
import entities.utilities.Utility;

public class Hero extends BasicObject{

	private static Random randomObj = new Random();
	private static HashMap<State, Integer> lookUpTable = new HashMap<State, Integer>();
	
	public Hero(Position pos){
		setPosition(pos);
		setName("Hero");
		setColor(Color.BLUE);
	}
	
	public void step(){
		Position up = Utility.getPositionInDirection(getPosition(), Direction.NORTH);
		Position down = Utility.getPositionInDirection(getPosition(), Direction.SOUTH);
		Position left = Utility.getPositionInDirection(getPosition(), Direction.WEST);
		Position right = Utility.getPositionInDirection(getPosition(), Direction.EAST);
		
		State upState = new State(getPosition(), up);
		State downState = new State(getPosition(), down);
		State leftState = new State(getPosition(), left);
		State rightState = new State(getPosition(), right);
		
		ArrayList<Move> moves = new ArrayList<Move>(); 
		
		if(lookUpTable.get(upState) == null){
			lookUpTable.put(upState, upState.getValueOfState());
			moves.add(new Move(up, upState.getValueOfState()));
		}
		else{
			moves.add(new Move(up, upState.getValueOfState()));
		}
		if(lookUpTable.get(downState) == null){
			lookUpTable.put(downState, downState.getValueOfState());
			moves.add(new Move(down, downState.getValueOfState()));
		}
		else{
			moves.add(new Move(down, downState.getValueOfState()));
		}
		if(lookUpTable.get(leftState) == null){
			lookUpTable.put(leftState, leftState.getValueOfState());
			moves.add(new Move(left, leftState.getValueOfState()));
		}
		else{
			moves.add(new Move(left, leftState.getValueOfState()));
		}
		if(lookUpTable.get(rightState) == null){
			lookUpTable.put(rightState, rightState.getValueOfState());
			moves.add(new Move(right, rightState.getValueOfState()));
		}
		else{
			moves.add(new Move(right, rightState.getValueOfState()));
		}

		int returnNum = 0;
		Direction dir = Direction.NORTH;
		
		Collections.sort(moves);
		Collections.reverse(moves);
		int pickOutOfBestMoves = randomObj.nextInt(10);
		if(pickOutOfBestMoves > 5){
			ArrayList<Move> listOfBestMoves = getListOfBestMoves(moves);
			int choice = randomObj.nextInt(listOfBestMoves.size());
			dir = getDirectionFromAdjacentPosition(listOfBestMoves.get(choice).getPosition());
			returnNum = move(dir);
		}
		else{
			int choice = (int)(Math.pow(randomObj.nextInt((int)(Math.pow(4*100, 1/1.3))), 1.3) / 100);
			dir = getDirectionFromAdjacentPosition(moves.get(choice).getPosition());
			returnNum = move(dir);
		}
		
		/*int choice = randomObj.nextInt(4);
		int returnNum = 0;
		Direction dir = Direction.NORTH;
		if(choice % 4 == 0){
			dir = Direction.NORTH;
			returnNum = move(Direction.NORTH);
		}
		else if(choice % 4 == 1){
			dir = Direction.EAST;
			returnNum = move(Direction.EAST);
		}
		else if(choice % 4 == 2){
			dir = Direction.SOUTH;
			returnNum = move(Direction.SOUTH);
		}
		else{
			dir = Direction.WEST;
			returnNum = move(Direction.WEST);
		}*/
		
		if(returnNum == -1){
			BasicObject obj = GameManager.getInstance().getObjectAtPosition(Utility.getPositionInDirection(getPosition(), dir));
			if(obj.getName().compareTo(Enemy.ENEMY_TYPE) == 0){
				setColor(Color.GRAY);
				GameManager.getInstance().setGameOver(true);
			}
			if(obj.getName().compareTo(Goal.GOAL_TYPE) == 0){
				setColor(Color.GREEN);
				GameManager.getInstance().setGameOver(true);
			}
		}
	}
	
	public ArrayList<Move> getListOfBestMoves(ArrayList<Move> moves){
		ArrayList<Move> possibleMoves = new ArrayList<Move>();
		
		int maximum = moves.get(0).getValue();
		int index = 0;
		while(index < moves.size() && maximum == moves.get(index).getValue()){
			index++;
		}
		
		for(int i = 0; i < index; i++){
			possibleMoves.add(moves.get(i));
		}
		
		return possibleMoves;
		
	}
	
	public Position getPositionFromStateValueIndex(int index){
		Position pos = new Position(getPosition().getX(), getPosition().getY());
		switch(index){
		case 0:
			pos.setY(pos.getY() - 1);			
			break;
		case 1:
			pos.setY(pos.getY() + 1);			
			break;
		case 2:
			pos.setX(pos.getX() - 1);			
			break;
		case 3:
			pos.setX(pos.getX() + 1);			
			break;
		}
		return pos;
	}
	
	public Direction getDirectionFromAdjacentPosition(Position adjacentPosition){
		if(getPosition().getY() - 1 == adjacentPosition.getY())
			return Direction.NORTH;
		if(getPosition().getY() + 1 == adjacentPosition.getY())
			return Direction.SOUTH;
		if(getPosition().getX() - 1 == adjacentPosition.getX())
			return Direction.WEST;
		if(getPosition().getX() + 1 == adjacentPosition.getX())
			return Direction.EAST;
		return Direction.NORTH;
	}
}
