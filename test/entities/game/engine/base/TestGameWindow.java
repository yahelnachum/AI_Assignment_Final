package entities.game.engine.base;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import boundaries.graphics.GameWindow;

public class TestGameWindow {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws InterruptedException {
		GameManager gm = GameManager.getInstance();
		gm.setWorldWidth(10);
		gm.setWorldHeight(12);
		BasicObject obj1 = new BasicObject();
		
		GameWindow gw = GameWindow.getInstance();
		gw.startUp(500, 600);
		gw.setVisible(true);
		
		Thread.sleep(500);
		
		gm.removeObectFromList(obj1);
	}

}
