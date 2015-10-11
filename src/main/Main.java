package main;

import java.awt.Color;

import entities.base.game.engine.BasicObject;
import entities.base.game.engine.GameManager;
import entities.base.game.engine.Position;
import boundaries.graphics.GameWindow;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		GameManager gm = GameManager.getInstance();
		gm.setWorldWidth(10);
		gm.setWorldHeight(10);
		BasicObject obj1 = new BasicObject(new Position(3, 6));
		obj1.setColor(Color.GREEN);
		obj1.setName("Enemy");
		gm.addObjectToList(obj1);
		BasicObject obj2 = new BasicObject(new Position(2, 3));
		obj2.setColor(Color.cyan);
		obj2.setName("Hero");
		gm.addObjectToList(obj2);
		BasicObject obj3 = new BasicObject(new Position(3, 7));
		obj3.setColor(Color.RED);
		obj3.setName("Wall");
		gm.addObjectToList(obj3);
		GameWindow gw = GameWindow.getInstance();
		gw.startUp(500, 500);
		gw.setVisible(true);
		
		while(gm.getGameOver() == false){
			Thread.sleep(1000);
			gm.step();
			gw.repaint();
		}
	}
}
