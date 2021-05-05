package model;

import com.fasterxml.jackson.annotation.JsonIgnore;



public class SmartWindow {
	private int level_of_blind;
	private int teint_of_glass;
	private boolean configured_window;
	private int id_window;
	private int window_orientation;
	private int id_work_space;
	private String preferredlum="";
	private int preferredtem;
	
	
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
	public String getPreferredlum() {
		return preferredlum;
	}

	public void setPreferredlum(String preferredLum) {
		this.preferredlum = preferredLum;
	}

	public int getPreferredtem() {
		return preferredtem;
	}

	public void setPreferredtem(int preferredTem) {
		this.preferredtem = preferredTem;
	}
	

public void updateWindow(SmartWindow w) {
	this.level_of_blind=w.getLevel_of_blind();
	this.teint_of_glass=w.getTeint_of_glass();
	this.id_window=w.getId_window();
	this.configured_window=w.getConfigured_window();
	this.id_work_space=w.getWindow_orientation();
	this.window_orientation=w.getWindow_orientation();
	this.msgtoString=w.msgtoString;
	this.preferredlum=w.preferredlum;
	this.preferredtem=w.preferredtem;
}
public String toString() {
	if (this.msgtoString=="")return ("WorkSpace id "+ id_work_space +", Fenetre id : "+id_window+this.getOrientationText());
	else return this.msgtoString+getOrientationText();
}	
public String getOrientationText() {
	String orientation="";
	if(this.window_orientation>23 && this.window_orientation<=68) {
		orientation=" (Orientation Nord-Est)";
	}else if (this.window_orientation>68 && this.window_orientation<=113) {
		orientation=" (Orientation Est)";
	}else if (this.window_orientation>113 && this.window_orientation<=158) {
		orientation=" (Orientation Sud-Est)";
	}else if (this.window_orientation>158 && this.window_orientation<=203) {
		orientation=" (Orientation Sud)";
	}else if (this.window_orientation>203 && this.window_orientation<=248) {
		orientation=" (Orientation Sud-Ouest)";
	}else if (this.window_orientation>248 && this.window_orientation<=294) {
		orientation=" (Orientation Ouest)";
	}else if (this.window_orientation>294 && this.window_orientation<=339) {
		orientation=" (Orientation Nord-Ouest)";
	}else orientation=" (Orientation Nord)";
	
	return orientation;
}

}
