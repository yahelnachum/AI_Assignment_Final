package entities.base.game.engine;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.base.game.engine.Position;

public class TestPosition {

	@Test
	public void test() {
		Position p1 = new Position();
		Position p2 = new Position();
		Position p3 = new Position(1,5);
		Position p4 = new Position(2,5);
		Position p5 = new Position();
		
		assertEquals(p1.getX(), 0);
		assertEquals(p1.getY(), 0);
		
		assertEquals(p3.getX(), 1);
		assertEquals(p3.getY(), 5);
		
		p5.setX(1);
		p5.setY(2);
		assertEquals(p5.getX(), 1);
		assertEquals(p5.getY(), 2);
		
		p5.setXY(3,5);
		assertEquals(p5.getX(), 3);
		assertEquals(p5.getY(), 5);
		
		assertEquals(p1.compareTo(p2), 0);
		assertEquals(p1.compareTo(p3), -1);
		assertEquals(p3.compareTo(p1), 1);
		assertEquals(p3.compareTo(p4), -1);
		assertEquals(p4.compareTo(p3), 1);
	}

}
