package entities.game.engine.base;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class TestBasicObject {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		BasicObject obj1 = new BasicObject();
		BasicObject obj2 = new BasicObject(new Position(10,15));
		
		assertEquals(obj1.getPosition().compareTo(new Position()), 0);
		assertEquals(obj2.getPosition().compareTo(new Position(10,15)), 0);
		assertEquals(obj1.getName(), "BasObj");
		assertEquals(obj1.getColor(), Color.WHITE);
		
		obj1.setPosition(new Position(12,17));
		obj1.setName("Enemy");
		obj1.setColor(Color.RED);
		
		assertEquals(obj1.getPosition().compareTo(new Position(12,17)), 0);
		assertEquals(obj1.getName(), "Enemy");
		assertEquals(obj1.getColor(), Color.RED);
		
		GameManager.getInstance().removeObectFromList(obj1);
		GameManager.getInstance().removeObectFromList(obj2);
	}

}
