package model;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Model {
	private final static Logger logger=LoggerFactory.getLogger(Model.class.getName());
	private Company selectedCompany=null;
	private ArrayList<WorkSpace> allWorkSpaces=new ArrayList<WorkSpace>();
	private ArrayList<Company> allCompanies=new ArrayList<Company>();
	private ArrayList<Building> allBuildings=new ArrayList<Building>();
	private ArrayList<Equipment> allEquipments=new ArrayList<Equipment>();
	public Model() {
	
		
	}
	

	public ArrayList<WorkSpace> getAllWorkSpaces() {
		return allWorkSpaces;
	}



	public void setAllWorkSpaces(ArrayList<WorkSpace> allWorkSpaces) {
		int i=0;
		
		if(this.allWorkSpaces.isEmpty()) {
			this.allWorkSpaces.addAll(allWorkSpaces);
		}
		else {
		for(WorkSpace workSpace: allWorkSpaces) {
			if(i<this.allWorkSpaces.size()) {
			this.allWorkSpaces.get(i).update(workSpace);
			}
			else {
				this.allWorkSpaces.add(workSpace);
			}
			i++;
		}
	}
  }


	public ArrayList<Company> getAllCompanies() {
		return allCompanies;
	}



	public void setAllCompanies(ArrayList<Company> allCompanies) {
		int i=0;
		
		if(this.allCompanies.isEmpty()) {
			this.allCompanies.addAll(allCompanies);
		}
		else {
		for(Company company: allCompanies) {
			if(i<this.allCompanies.size()) {
			this.allCompanies.get(i).update(company);
			}
			else {
				this.allCompanies.add(company);
			}
			i++;
		}
	}
	}


	public Company getSelectedCompany() {
		return selectedCompany;
	}


	public void setSelectedCompany(Company selectedCompany) {
		this.selectedCompany = selectedCompany;
	}


	public ArrayList<Building> getAllBuildings() {
		return allBuildings;
	}


	public void setAllBuildings(ArrayList<Building> allBuildings) {
		int i=0;
		
		if(this.allBuildings.isEmpty()) {
			this.allBuildings.addAll(allBuildings);
		}
		else {
		for(Building building: allBuildings) {
			if(i<this.allBuildings.size()) {
			this.allBuildings.get(i).update(building);
			}
			else {
				this.allBuildings.add(building);
			}
			i++;
		}
	}
	}


	public ArrayList<Equipment> getAllEquipments() {
		return allEquipments;
	}


	public void setAllEquipments(ArrayList<Equipment> allEquipments) {
		int i=0;
		
		if(this.allEquipments.isEmpty()) {
			this.allEquipments.addAll(allEquipments);
		}
		else {
		for(Equipment equipment: allEquipments) {
			if(i<this.allEquipments.size()) {
			this.allEquipments.get(i).update(equipment);
			}
			else {
				this.allEquipments.add(equipment);
			}
			i++;
		}
	}
	}


}
