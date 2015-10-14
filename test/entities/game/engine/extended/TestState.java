package entities.game.engine.extended;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

import entities.game.engine.base.GameManager;
import entities.game.engine.base.Position;

public class TestState {

	@Test
	public void test() {
		GameManager inst = GameManager.getInstance();
		new Goal(new Position(3,1));
		new Enemy(new Position(2,1));
		State s1 = new State(new Position(1,1), new Position(2,1));
		State s2 = new State(new Position(1,1), new Position(4,1));
		State s3 = new State(new Position(1,1), new Position(5,3));
		
		assertEquals(s1.hashCode(), 19*3+23*2);
		assertEquals(s2.hashCode(), 19*1+23*2);
		
		HashMap<State, Integer> map = new HashMap<State, Integer>();
		
		map.put(s1, 5);
		map.put(s2, 10);
		
		assertEquals(map.get(s1).intValue(), 5);
		assertEquals(map.get(s2).intValue(), 10);
		assertEquals(map.get(s3), null);
	}

}
