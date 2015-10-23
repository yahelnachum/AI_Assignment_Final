package main;

import java.util.Random;

import boundaries.graphics.ApplicationManager;
import boundaries.graphics.GameWindow;
import entities.game.engine.base.GameManager;
import entities.game.engine.base.Position;
import entities.game.engine.extended.Enemy;
import entities.game.engine.extended.Goal;
import entities.game.engine.extended.Hero;
import entities.game.engine.extended.State;
import entities.game.engine.extended.Wall;
import entities.utilities.Clock;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		
		ApplicationManager am = ApplicationManager.getInstance();
		am.startUp();
		am.run();
	}
}