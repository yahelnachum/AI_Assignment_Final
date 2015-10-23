package boundaries.graphics;

public class WindowGameEvent extends WindowEvent{

	public static final String WINDOW_GAME_EVENT = "Window Game Event";
	public static final String RUN_GAME_EVENT = "Run Game Event";
	public static final String STOP_GAME_EVENT = "Stop Game Event";
	public static final String STEP_GAME_EVENT = "Step Game Event";
	
	private String gameEvent = "";
	
	public WindowGameEvent(String gameEvent){
		setWindowEventName(WINDOW_GAME_EVENT);
		this.gameEvent = gameEvent;
	}
	
	public String getGameEvent(){
		return gameEvent;
	}
}
