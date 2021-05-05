package model;

import java.util.ArrayList;
import java.util.HashMap;

public class LoanCondition {
	private static int loanBudget;
	private static String spaceBuilding = "Veuillez selectionner un batiment";
	private static String spaceFloor = "Veuillez selectionner un etage";
	private static int spaceArea;
	private static String spaceType = "Veuillez selectionner un type d'espace";	
	private static int equipmentCost = 0;
	private static HashMap<String, HashMap<Object, Object>> neededEquipments = new HashMap<>();

	public static int getLoanBudget() {
		return loanBudget;
	}

	public static void setLoanBudget(int l) {
		loanBudget = l;
	}
	
	public static String getSpaceBuilding() {
		return spaceBuilding;
	}
	
	private static void setSpaceBuilding(String s) {
		spaceBuilding = s;
	}

	private static String getSpaceFloor() {
		return spaceFloor;
	}

	private static void setSpaceFloor (String s) {
		spaceFloor = s;
	}

	public static int getSpaceArea() {
		return spaceArea;
	}

	public static void setSpaceArea(int s) {
		spaceArea = s;
	}

	public static String getSpaceType() {
		return spaceType;
	}
	
	public static void setSpaceType(String s) {
		spaceType = s;
	}
	
	public static int getEquipmentCost() {
		return equipmentCost;
	}
	
	public static HashMap<String, HashMap<Object, Object>> getNeededEquipments() {
		return neededEquipments;
	}
	
	public static void setNeededEquipments(HashMap<String, HashMap<Object, Object>> n) {
		neededEquipments = n;
		equipmentCost = 0;
		
		for(int i = 0; i < n.size() ;i ++) {
			for(int j = 0; j < n.get(i).size(); j ++) {
				equipmentCost = equipmentCost + (int)(n.get(i).get(j));
			}
		}
		
	}
	
	public static ArrayList<Offer> filterLoanOffer(ArrayList<Offer> list) {
		//the method get an ArrayList of offer wich will be filtered with the known filter
		for(int i = 0; i < list.size(); i ++) {
			list.get(i).setOfferCost(list.get(i).getOfferCost() + getEquipmentCost());
			
			if(list.get(i).getOfferCost() > getLoanBudget()) {
				list.remove(i);
			}
			
			if((list.get(i).getOfferBuilding() != getSpaceBuilding()) && (spaceBuilding != "Veuillez selectionner un batiment")) {
				list.remove(i);
			}
			
			if((String.valueOf(list.get(i).getOfferFloor()) != getSpaceFloor()) && (spaceFloor != "Veuillez selectionner un etage")) {
				list.remove(i);
			}
			
			if((list.get(i).getOfferType() != getSpaceType()) && (spaceType != "Veuillez selectionner un type d'espace")) {
				list.remove(i);
			}
			
			if(list.get(i).getOfferArea() != getSpaceArea()) {
				list.remove(i);
			}
			
			if(getNeededEquipments().containsKey("fenetre") && (list.get(i).getNumber_of_windows() == 0)) {
				list.remove(i);
			}
		}
		return list;
	}
}


























