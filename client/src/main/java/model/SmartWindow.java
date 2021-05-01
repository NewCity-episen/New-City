package model;

import com.fasterxml.jackson.annotation.JsonIgnore;



public class SmartWindow {
	private int level_of_blind;
	private int teint_of_glass;
	private boolean configured_window;
	private int id_window;
	private int window_orientation;
	private int id_work_space;	
	@JsonIgnore
	private String msgtoString ="";
	
	
	
	/*****GETTERS AND SETTERS******/
	public String getMsgtoString() {
		return msgtoString;
	}

	public void setMsgtoString(String msgtoString) {
		this.msgtoString = msgtoString;
	}

	
	public int getLevel_of_blind() {
		return level_of_blind;
	}
	
	public void setLevel_of_blind(int level_of_blind) {
		this.level_of_blind = level_of_blind;
	}
	public int getTeint_of_glass() {
		return teint_of_glass;
	}
	public void setTeint_of_glass(int teint_of_glass) {
		this.teint_of_glass = teint_of_glass;
	}
	public boolean getConfigured_window() {
		return configured_window;
	}
	public void setConfigured_window(boolean configured_window) {
		this.configured_window = configured_window;
	}
	public int getId_window() {
		return id_window;
	}
	public void setId_window(int id_window) {
		this.id_window = id_window;
	}
	public int getWindow_orientation() {
		return window_orientation;
	}
	public void setWindow_orientation(int window_orientation) {
		this.window_orientation = window_orientation;
	}
	public int getId_work_space() {
		return id_work_space;
	}
	public void setId_work_space(int id_work_space) {
		this.id_work_space = id_work_space;
	}
	
public void updateWindow(SmartWindow w) {
	this.level_of_blind=w.getLevel_of_blind();
	this.teint_of_glass=w.getTeint_of_glass();
	this.id_window=w.getId_window();
	this.configured_window=w.getConfigured_window();
	this.id_work_space=w.getWindow_orientation();
	this.window_orientation=w.getWindow_orientation();
	this.msgtoString=w.msgtoString;
}
public String toString() {
	if (this.msgtoString=="")return ("WorkSpace id "+ id_work_space +", Fenetre id : "+id_window);
	else return this.msgtoString;
}	

}
