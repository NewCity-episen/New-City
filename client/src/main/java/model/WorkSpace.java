package model;

import java.util.ArrayList;

import javax.swing.JButton;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class WorkSpace {
	private int id_work_space;
	private int id_building;
	private String space_name;
	private String space_type;
	private int nb_room;
	private int space_cost;
	private float temperature;
	private int number_of_windows;
	private int id_city_hall;
	private int space_floor;
	private boolean taken;
	private boolean configurable;
	private int id_entreprise;
	@JsonIgnore
	private JButton workSpaceButton=new JButton();
	@JsonIgnore
	private int position_X;
	@JsonIgnore
	private int position_Y;
	@JsonIgnore
	private ArrayList<Equipment> equipmentsToInstall=new ArrayList<Equipment>();
	
	
	
	/*****GETTERS AND SETTERS******/
	public int getId_work_space() {
		return id_work_space;
	}
	public void setId_work_space(int id_work_space) {
		this.id_work_space = id_work_space;
	}
	public int getId_building() {
		return id_building;
	}
	public void setId_building(int id_building) {
		this.id_building = id_building;
	}
	public String getSpace_name() {
		return space_name;
	}
	public void setSpace_name(String space_name) {
		this.space_name = space_name;
	}
	public String getSpace_type() {
		return space_type;
	}
	public void setSpace_type(String space_type) {
		this.space_type = space_type;
	}
	public int getSpace_floor() {
		return space_floor;
	}
	public void setSpace_floor(int space_floor) {
		this.space_floor = space_floor;
	}
	public boolean isTaken() {
		return taken;
	}
	public void setTaken(boolean taken) {
		this.taken = taken;
	}
	public boolean isConfigurable() {
		return configurable;
	}
	public void setConfigurable(boolean configurable) {
		this.configurable = configurable;
	}
	public int getId_entreprise() {
		return id_entreprise;
	}
	public void setId_entreprise(int id_entreprise) {
		this.id_entreprise = id_entreprise;
	}
	public int getNb_room() {
		return nb_room;
	}
	public void setNb_room(int nb_room) {
		this.nb_room = nb_room;
	}
	public int getSpace_cost() {
		return space_cost;
	}
	public void setSpace_cost(int space_cost) {
		this.space_cost = space_cost;
	}
	public float getTemperature() {
		return temperature;
	}
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	public int getNumber_of_windows() {
		return number_of_windows;
	}
	public void setNumber_of_windows(int number_of_windows) {
		this.number_of_windows = number_of_windows;
	}
	public int getId_city_hall() {
		return id_city_hall;
	}
	public void setId_city_hall(int id_city_hall) {
		this.id_city_hall = id_city_hall;
	}
	public JButton getWorkSpaceButton() {
		return workSpaceButton;
	}
	public void setWorkSpaceButton(JButton workSpaceButton) {
		this.workSpaceButton = workSpaceButton;
	}
	public int getPosition_X() {
		return position_X;
	}
	public void setPosition_X(int position_X) {
		this.position_X = position_X;
	}
	public int getPosition_Y() {
		return position_Y;
	}
	public void setPosition_Y(int position_Y) {
		this.position_Y = position_Y;
	}
	public ArrayList<Equipment> getEquipmentsToInstall() {
		return equipmentsToInstall;
	}
	public void setEquipmentsToInstall(ArrayList<Equipment> equipmentsToInstall) {
		this.equipmentsToInstall=equipmentsToInstall;
	}
	public void update(WorkSpace wk) {
		this.id_work_space=wk.getId_work_space();
		this.id_building=wk.getId_building();
		this.id_city_hall=wk.getId_city_hall();
		this.id_entreprise=wk.getId_entreprise();
		this.configurable=wk.isConfigurable();
		this.nb_room=wk.getNb_room();
		this.number_of_windows=wk.getNb_room();
		this.space_cost=wk.getSpace_cost();
		this.space_floor=wk.getSpace_floor();
		this.space_name=wk.getSpace_name();
		this.space_type=wk.getSpace_type();
		this.temperature=wk.getTemperature();
		this.taken=wk.isTaken();
	}
		

}
