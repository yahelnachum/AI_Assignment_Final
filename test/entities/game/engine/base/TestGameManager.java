package entities.game.engine.base;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import entities.game.engine.extended.Goal;
import entities.game.engine.extended.Hero;

public class TestGameManager {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testBasic() {
		GameManager inst1 = GameManager.getInstance();
		GameManager inst2 = GameManager.getInstance();
		
		assertEquals(inst1, inst2);
		
		inst1.setWorldWidth(10);
		inst1.setWorldHeight(15);
		
		assertEquals(inst1.getWorldWidth(), 10);
		assertEquals(inst1.getWorldHeight(), 15);
		assertEquals(inst1.getGameOver(), false);
		assertEquals(inst1.getObjectsList().size(), 0);
		
		inst1.setGameOver(true);
		
		assertEquals(inst1.getGameOver(), true);
		
		BasicObject obj1 = new BasicObject();
		
		assertEquals(inst1.getObjectsList().size(), 1);
		assertEquals(inst1.getObjectsList().get(0).getPosition().compareTo(new Position()), 0);
		assertEquals(inst1.getObjectsList().get(0).getColor(), Color.WHITE);
		assertEquals(inst1.getObjectsList().get(0).getName(), "BasObj");
		
		assertEquals(inst1.removeObectFromList(obj1), 0);
		assertEquals(inst1.removeObectFromList(obj1), -1);
		
	}
	
	@Test
	public void testAdjacency() {
		GameManager inst1 = GameManager.getInstance();
		
		new BasicObject(new Position(3,3)).setName("blue");
		new BasicObject(new Position(4,4)).setName("red");
		new BasicObject(new Position(5,5)).setName("green");
		
		assertEquals(inst1.isObjectNameInAdjacentSquares("red", new Position(5,5), 1, 0), true);
		assertEquals(inst1.isObjectNameInAdjacentSquares("blue", new Position(5,5), 2, 1), true);
		assertEquals(inst1.isObjectNameInAdjacentSquares("red", new Position(5,5), 2, 0), true);
		assertEquals(inst1.isObjectNameInAdjacentSquares("blue", new Position(5,5), 2, 0), true);
		
	}
	
	@Test
	public void testGetObjects() {
		GameManager inst = GameManager.getInstance();
		
		new Goal(new Position(1,1));
		new Hero(new Position(0,0));
		
		ArrayList<BasicObject> list = inst.getObjectsWithName(Hero.HERO_TYPE);
		
		assertEquals(list.size(), 1);
		
		inst.resetGame();
	}

}
