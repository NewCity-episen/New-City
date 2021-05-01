package model;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Spot {

	private int id_spot;
	private String spot_description;
	private String spot_type;
	private int id_work_space;
	private boolean taken;
	private int position_x;
	private int position_y;
	private int id_equipment;
	@JsonIgnore
	private JLabel labelSpot=new JLabel();
	@JsonIgnore
	private JPopupMenu popUpMenu=new JPopupMenu();
	@JsonIgnore
	private boolean state;
	@JsonIgnore
	private JMenuItem placeBtnItem=placeBtnItem=new JMenuItem("Placer");
	
	@JsonIgnore
	private JMenuItem removeBtnItem=new JMenuItem("Retirer");
	@JsonIgnore
	private String color;
	@JsonIgnore
	private Equipment equipmentInstalled;
	
	public int getId_spot() {
		return id_spot;
	}
	
	public Equipment getEquipmentInstalled() {
		return equipmentInstalled;
	}

	public void setEquipmentInstalled(Equipment equipmentInstalled) {
		this.equipmentInstalled = equipmentInstalled;
	}

	public JPopupMenu getPopUpMenu() {
		return popUpMenu;
	}

	public void setPopUpMenu(JPopupMenu popUpMenu) {
		this.popUpMenu = popUpMenu;
	}

	public void setId_spot(int id_spot) {
		this.id_spot = id_spot;
	}
	public String getSpot_description() {
		return spot_description;
	}
	public void setSpot_description(String spot_description) {
		this.spot_description = spot_description;
	}
	public String getSpot_type() {
		return spot_type;
	}
	public void setSpot_type(String spot_type) {
		this.spot_type = spot_type;
	}
	public int getId_work_space() {
		return id_work_space;
	}
	public void setId_work_space(int id_work_space) {
		this.id_work_space = id_work_space;
	}
	public boolean isTaken() {
		return taken;
	}
	public void setTaken(boolean taken) {
		this.taken = taken;
	}
	public int getPosition_x() {
		return position_x;
	}
	public void setPosition_x(int position_x) {
		this.position_x = position_x;
	}
	public int getPosition_y() {
		return position_y;
	}
	public void setPosition_y(int position_y) {
		this.position_y = position_y;
	}
	public JLabel getLabelSpot() {
		return labelSpot;
	}
	public void setLabelSpot(JLabel labelSpot) {
		this.labelSpot = labelSpot;
	}
	public int getId_equipment() {
		return id_equipment;
	}
	public void setId_equipment(int id_equipment) {
		this.id_equipment = id_equipment;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}

	public JMenuItem getPlaceBtnItem() {
		return placeBtnItem;
	}

	public void setPlaceBtnItem(JMenuItem placeBtnItem) {
		this.placeBtnItem = placeBtnItem;
	}

	public JMenuItem getRemoveBtnItem() {
		return removeBtnItem;
	}

	public void setRemoveBtnItem(JMenuItem removeBtnItem) {
		this.removeBtnItem = removeBtnItem;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public void update(Spot s) {
		this.id_spot=s.getId_spot();
		this.id_equipment=s.getId_equipment();
		this.id_work_space=s.getId_work_space();
		this.position_x=s.getPosition_x();
		this.position_y=s.getPosition_y();
		this.spot_type=s.getSpot_type();
		this.taken=s.isTaken();
		
	}
	
}
