package model;

import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Building {
	private int id_building;
	private String building_name;
	private int nb_of_floor;
	private String id_address;
	@JsonIgnore
	private HashMap<Integer,WorkSpace [][]> mapBuilding= new HashMap<Integer,WorkSpace [][]>();//HashMap<key: Number of the floor, value: The array of workspace associated to the given floor(key)>
	public int getId_building() {
		return id_building;
	}
	public void setId_building(int id_building) {
		this.id_building = id_building;
	}
	public String getBuilding_name() {
		return building_name;
	}
	public void setBuilding_name(String building_name) {
		this.building_name = building_name;
	}
	public int getNb_of_floor() {
		return nb_of_floor;
	}
	public void setNb_of_floor(int nb_of_floor) {
		this.nb_of_floor = nb_of_floor;
	}
	public String getId_address() {
		return id_address;
	}
	public void setId_address(String id_address) {
		this.id_address = id_address;
	}
	public HashMap<Integer, WorkSpace[][]> getMapBuilding() {
		return mapBuilding;
	}
	public void setMapBuilding(HashMap<Integer, WorkSpace[][]> mapBuilding) {
		this.mapBuilding = mapBuilding;
	}
	public String toString() {
		return building_name;
		
	}
	public void update(Building bd) {
		this.building_name=bd.getBuilding_name();
		this.id_address=bd.getId_address();
		this.nb_of_floor=bd.getNb_of_floor();
		this.id_building=bd.getId_building();
	}
	

}
