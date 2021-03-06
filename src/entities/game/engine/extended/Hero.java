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

	public static String HERO_TYPE = "Hero";
	private static Random randomObj = new Random();
	public static HashMap<State, Double> lookUpTable = new HashMap<State, Double>();
	private State previousState;
	private static int lives = 0;
	private static boolean initializeTable = false;
	private int steps = 0;
	
	public Hero(Position pos){
		setPosition(pos);
		setName(HERO_TYPE);
		setColor(new Color(0x00ffff));
		previousState = new State(pos, pos);
		lives++;
		
		if(!initializeTable){
			initializeTable = true;
			initializeTable();
		}
	}
	
	private void initializeTable(){
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				double value = 0.0;
				
				if( j == 3 & i != 3){
					value = 1000.0;
				}
				if(i == 3){
					value = -1000.0;
				}
				
				lookUpTable.put(new State(i,j,true), value);
				lookUpTable.put(new State(i,j,false), value);
			}
		}
	}
	
	public static void printTable(){
		for(int i = 0; i < 2; i++){
			System.out.printf("Closer To Goal = %s\n", (i == 0)? "True":"False");
			for(int j = 0; j < 4; j++){
				for(int k = 0; k < 4; k++){
					if(j == 0 && k == 0){
						System.out.printf("Goal Proximity\n");
						System.out.printf("%8d %8d %8d %8d Enemy Proximity\n", 0,1,2,3);
					}
					if(i == 0){
						System.out.printf("%8.2f ", lookUpTable.get(new State(j,k,true)));
					}
					else{
						System.out.printf("%8.2f ", lookUpTable.get(new State(j,k,false)));
					}
					
					if(k == 3){
						System.out.printf("%d", j);
					}
				}
				System.out.println();
			}
			System.out.println();
		}
	}
	
	public int getSteps(){
		return steps;
	}
	
	public void step(){
		Position previousPos = getPosition();

		// increment steps
		steps++;
		
		// get the possible positions agent can move
		Position up = Utility.getPositionInDirection(getPosition(), Direction.NORTH);
		Position down = Utility.getPositionInDirection(getPosition(), Direction.SOUTH);
		Position left = Utility.getPositionInDirection(getPosition(), Direction.WEST);
		Position right = Utility.getPositionInDirection(getPosition(), Direction.EAST);
		
		// get states based on positions
		State upState = new State(getPosition(), up);
		State downState = new State(getPosition(), down);
		State leftState = new State(getPosition(), left);
		State rightState = new State(getPosition(), right);
		
		int delta[] = new int[4];
		if(GameManager.getInstance().getObjectAtPosition(up) != null && GameManager.getInstance().getObjectAtPosition(up).getName().compareTo(BreadCrumb.BREADCRUMB_TYPE) == 0){
			BreadCrumb bc = (BreadCrumb) GameManager.getInstance().getObjectAtPosition(up);
			delta[0] = bc.getStepMade();
		}
		if(GameManager.getInstance().getObjectAtPosition(down) != null && GameManager.getInstance().getObjectAtPosition(down).getName().compareTo(BreadCrumb.BREADCRUMB_TYPE) == 0){
			BreadCrumb bc = (BreadCrumb) GameManager.getInstance().getObjectAtPosition(down);
			delta[1] = bc.getStepMade();
		}
		if(GameManager.getInstance().getObjectAtPosition(left) != null && GameManager.getInstance().getObjectAtPosition(left).getName().compareTo(BreadCrumb.BREADCRUMB_TYPE) == 0){
			BreadCrumb bc = (BreadCrumb) GameManager.getInstance().getObjectAtPosition(left);
			delta[2] = bc.getStepMade();
		}
		if(GameManager.getInstance().getObjectAtPosition(right) != null && GameManager.getInstance().getObjectAtPosition(right).getName().compareTo(BreadCrumb.BREADCRUMB_TYPE) == 0){
			BreadCrumb bc = (BreadCrumb) GameManager.getInstance().getObjectAtPosition(right);
			delta[3] = bc.getStepMade();
		}
	
		// create a list of possible moves
		ArrayList<Move> moves = new ArrayList<Move>(); 
		moves.add(new Move(upState, up, lookUpTable.get(upState), delta[0]));
		moves.add(new Move(downState, down, lookUpTable.get(downState), delta[1]));
		moves.add(new Move(leftState, left, lookUpTable.get(leftState), delta[2]));
		moves.add(new Move(rightState, right, lookUpTable.get(rightState), delta[3]));
	

		// get information from best choice and random choice
		int returnNum = 0;
		Direction dir = Direction.NORTH;
		State current = new State(0,0, false);
		
		// sort moves based on value (highest to lowest)
		Collections.sort(moves);
		Collections.reverse(moves);
		
		// Epsilon Random with TD RL or Utiltiy function
		// pick whether to pick out of the best moves
		// or pick a "random" choice
		int pickOutOfBestMoves = randomObj.nextInt(10);
		if(pickOutOfBestMoves > 2 + 300/lives){
		//if(pickOutOfBestMoves > 4){ // for half rand, half utility
			// get list of best moves if some maximum values are equal
			// choose a move randomly out of the best moves list
			// get information on move
			ArrayList<Move> listOfBestMoves = getListOfBestMoves(moves);
			int choice = randomObj.nextInt(listOfBestMoves.size());
			dir = getDirectionFromAdjacentPosition(listOfBestMoves.get(choice).getPosition());
			current = listOfBestMoves.get(choice).getState();
		}
		else{
			// pick a move "randomly"
			// get information on move
			int choice = randomObj.nextInt(4);//(int)(Math.pow(randomObj.nextInt((int)(Math.pow(4*100, 1/1.3))), 1.3) / 100);
			dir = getDirectionFromAdjacentPosition(moves.get(choice).getPosition());
			current = moves.get(choice).getState();
		}
		
		/*// completely random movement
		int choice = randomObj.nextInt(4);
		if(choice % 4 == 0){
			dir = Direction.NORTH;
		}
		else if(choice % 4 == 1){
			dir = Direction.SOUTH;
		}
		else if(choice % 4 == 2){
			dir = Direction.EAST;
		}
		else if(choice % 4 == 3){
			dir = Direction.WEST;
		}*/
			
		// do move
		returnNum = move(dir);
		
		double reward = 0.0;//-1.0;
		
		// check if the move created a collision
		if(returnNum == -1){
			// if the collision was with an enemy
			// set color to grey and end game
			BasicObject obj = GameManager.getInstance().getObjectAtPosition(Utility.getPositionInDirection(getPosition(), dir));
			if(obj.getName().compareTo(Enemy.ENEMY_TYPE) == 0){
				setColor(Color.GRAY);
				GameManager.getInstance().setGameOver(true);
			}
			// if collision was with goal
			// set color to green and end game
			if(obj.getName().compareTo(Goal.GOAL_TYPE) == 0){
				setColor(Color.GREEN);
				GameManager.getInstance().setGameOver(true);
			}
		}
		if(returnNum == 0){
			BasicObject obj = GameManager.getInstance().getObjectAtPosition(previousPos);
			if(obj != null){
				GameManager.getInstance().removeObectFromList(obj);
			}
			
			new BreadCrumb(previousPos, GameManager.getInstance().getStepCount());
			
		}
		
		// calculate the newPreviousState value based on TD-learning
		double newPreviousStateValue = lookUpTable.get(previousState) + 0.01*(reward + (lookUpTable.get(current) - lookUpTable.get(previousState)));
		lookUpTable.put(previousState, newPreviousStateValue);
		previousState = current;
		
	}
	
	public ArrayList<Move> getListOfBestMoves(ArrayList<Move> moves){
		ArrayList<Move> possibleMoves = new ArrayList<Move>();
		
		double maximum = moves.get(0).getValue();
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
