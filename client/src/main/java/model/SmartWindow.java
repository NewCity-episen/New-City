package model;

import java.util.HashMap;

public class SmartWindow {
	private int level_of_blind;
	private int teint_of_glass;
	private boolean configured;
	private int id_window;
	private int window_orientation;
	private int id_work_space;
	
	
	
	/*****GETTERS AND SETTERS******/
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
	public boolean isConfigured() {
		return configured;
	}
	public void setConfigured(boolean configured) {
		this.configured = configured;
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
	this.configured=w.isConfigured();
	this.id_work_space=w.getWindow_orientation();
	this.window_orientation=w.getWindow_orientation();
}
public String toString() {
	return ("espace "+ id_work_space +" : Fenetre "+configured);
}	

}