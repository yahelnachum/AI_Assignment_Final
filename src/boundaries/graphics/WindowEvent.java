package boundaries.graphics;

public class WindowEvent {

	public static final String WINDOW_EVENT_NAME = "Basic Window Event";
	private String name = "";
	
	public WindowEvent(){
		name = WINDOW_EVENT_NAME;
	}
	
	public void setWindowEventName(String name){
		this.name = name;
	}
	
	public String getWindowEventName(){
		return name;
	}
}
