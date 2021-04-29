package model;

import java.security.Timestamp;


public class Readings {
private int id_readings;
private int id_equipement;
private Timestamp time;
private float value;
public int getId_readings() {
	return id_readings;
}
public void setId_readings(int id_readings) {
	this.id_readings = id_readings;
}
public int getId_equipement() {
	return id_equipement;
}
public void setId_equipement(int id_equipement) {
	this.id_equipement = id_equipement;
}
public Timestamp getTime() {
	return time;
}
public void setTime(Timestamp time) {
	this.time = time;
}
public float getValue() {
	return value;
}
public void setValue(float value) {
	this.value = value;
}
public void update(Readings r) {
	
	
	
	
}
}
