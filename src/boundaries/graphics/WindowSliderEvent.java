package boundaries.graphics;

public class WindowSliderEvent extends WindowEvent {

	public static final String WINDOW_SLIDER_EVENT = "Window Slider Event";
	
	public static final String FPS_SLIDER_EVENT = "FPS Slider Event";
	public static final String GAME_STEPS_TO_SKIP_SLIDER_EVENT = "Game Steps To Skip Slider Event";
	public static final String TIME_TO_SHOW_RESULTS_EVENT = "Time To Show Results Event";
	
	private int sliderValue;
	private String slider_event_name;
	
	public WindowSliderEvent(int sliderValue, String slider_event_name){
		setWindowEventName(WINDOW_SLIDER_EVENT);
		this.sliderValue = sliderValue;
		this.slider_event_name = slider_event_name;
	}
	
	public void setSliderValue(int sliderValue){
		this.sliderValue = sliderValue;
	}
	
	public int getSliderValue(){
		return sliderValue;
	}
	
	public String getSliderEventName(){
		return slider_event_name;
	}
	
	
}
