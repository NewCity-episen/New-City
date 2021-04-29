package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Equipment {

	private int id_equipment;
	private String equipment_name;
	private float unit_cost;
	private String equipment_description;
	private String equipment_type;
	private String Equipment_spot_type;
	private int id_window;

	private int ref;

	@JsonIgnore
	private boolean installed;
	@JsonIgnore
	private boolean state;
	public Equipment() {
		equipment_name="Choisir �quipement/capteur � installer..";
	}
	public int getId_equipment() {
		return id_equipment;
	}
	public void setId_equipment(int id_equipment) {
		this.id_equipment = id_equipment;
	}
	public String getEquipment_name() {
		return equipment_name;
	}
	public void setEquipment_name(String equipment_name) {
		this.equipment_name = equipment_name;
	}
	public float getUnit_cost() {
		return unit_cost;
	}
	public void setUnit_cost(float unit_cost) {
		this.unit_cost = unit_cost;
	}
	public String getEquipment_description() {
		return equipment_description;
	}
	public void setEquipment_description(String equipment_description) {
		this.equipment_description = equipment_description;
	}
	public String getEquipment_type() {
		return equipment_type;
	}
	public void setEquipment_type(String equipment_type) {
		this.equipment_type = equipment_type;
	}
	public String getEquipment_spot_type() {
		return Equipment_spot_type;
	}
	public void setEquipment_spot_type(String equipment_spot_type) {
		Equipment_spot_type = equipment_spot_type;
	}
	public int getId_window() {
		return id_window;
	}
	public void setId_window(int id_window) {
		this.id_window = id_window;
	}

	public boolean isInstalled() {
		return installed;
	}
	public void setInstalled(boolean installed) {
		this.installed = installed;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String toString() {
		return equipment_name;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public void update(Equipment eq) {
		this.id_equipment=eq.getId_equipment();
		this.equipment_description=eq.getEquipment_description();
		this.equipment_name=eq.getEquipment_name();
		this.Equipment_spot_type=eq.getEquipment_spot_type();
		this.equipment_type=eq.getEquipment_type();
		this.id_window=eq.getId_window();
		this.unit_cost=eq.getUnit_cost();


		this.ref=eq.getRef();


		this.ref=eq.getRef();

	}
	
}
