package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import controller.Controller;

public class LoanCondition {
	private static int loanBudget;
	private static String spaceBuilding = "Veuillez selectionner un batiment";
	private static String spaceFloor = "Veuillez selectionner un etage";
	private static int spaceArea;
	private static String spaceType = "Veuillez selectionner un type d'espace";	
	private static int equipmentCost = 0;
	private static ArrayList<Equipment> neededEquipments = new ArrayList<>();

	public static int getLoanBudget() {
		return loanBudget;
	}

	public static void setLoanBudget(int l) {
		loanBudget = l;
	}
	
	public static String getSpaceBuilding() {
		return spaceBuilding;
	}
	
	public static void setSpaceBuilding(String s) {
		spaceBuilding = s;
	}

	public static String getSpaceFloor() {
		return spaceFloor;
	}

	public static void setSpaceFloor (String s) {
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
	
	public static ArrayList<Equipment> getNeededEquipments() {
		return neededEquipments;
	}
	
	public static void setNeededEquipments() {
		
		for(int i = 0; i < Controller.getEquipmentToInsert().size() ;i ++) {
			neededEquipments.add((Equipment)(Controller.getEquipmentToInsert().get(i)));
		}
		
		for(int i = 0; i < neededEquipments.size() ;i ++) {
				equipmentCost = equipmentCost + (int)(neededEquipments.get(i).getUnit_cost());
		}
	}
	
	public static ArrayList<Offer> filterLoanOffer(ArrayList<Map> resultList) {
		
		//resultList.get(i).setOfferCost(resultList.get(i).get("space_cost") + getEquipmentCost());

		setNeededEquipments();

		for(Map offer : resultList) {
			offer.replace("to_present", false);

			if ((spaceBuilding.equals("Veuillez selectionner un batiment"))) {
				offer.replace("to_present", true);
				if((spaceType.equals("Veuillez selectionner un type d'espace"))) {
					offer.replace("to_present", true);
				} else if((offer.get("space_type").equals(getSpaceType()))) {
					offer.replace("to_present", true);
				} else {
					offer.replace("to_present", true, false);
				}

			} else if(spaceBuilding.equals(offer.get("building_name"))) {

				if(spaceFloor.equals("Veuillez selectionner un etage")) {
					offer.replace("to_present", true);
					if((spaceType.equals("Veuillez selectionner un type d'espace"))) {
						offer.replace("to_present", true);
					} else if((offer.get("space_type").equals(getSpaceType()))) {
						offer.replace("to_present", true);
					} else {
						offer.replace("to_present", true, false);
					}
					
					if((spaceType.equals("Veuillez selectionner un type d'espace"))) {
						offer.replace("to_present", true);
					} else if((offer.get("space_type").equals(getSpaceType()))) {
						offer.replace("to_present", true);
					} else {
						offer.replace("to_present", true, false);
					}
				} else if ((int)(offer.get("space_floor")) == (Integer.parseInt(getSpaceFloor()))){
					offer.replace("to_present", true);
					if((spaceType.equals("Veuillez selectionner un type d'espace"))) {
						offer.replace("to_present", true);
					} else if((offer.get("space_type").equals(getSpaceType()))) {
						offer.replace("to_present", true);
					} else {
						offer.replace("to_present", true, false);
					}
					
					if((spaceType.equals("Veuillez selectionner un type d'espace"))) {
						offer.replace("to_present", true);
					} else if((offer.get("space_type").equals(getSpaceType()))) {
						offer.replace("to_present", true);
					} else {
						offer.replace("to_present", true, false);
					}
				} else {
					offer.replace("to_present", true, false);
				}
			
				if((spaceType.equals("Veuillez selectionner un type d'espace"))) {
					offer.replace("to_present", true, true);
				} else if((offer.get("space_type").equals(getSpaceType()))) {
					offer.replace("to_present", true, true);
				}
			}
		
			if((int)(offer.get("space_cost")) + getEquipmentCost() > getLoanBudget()) {
				offer.replace("to_present", true, false);
			}
		
		
		
			if((int)(offer.get("space_area")) > getSpaceArea()) {
				offer.replace("to_present", true, false);
			}
			
			for(int i = 0; i < getNeededEquipments().size(); i++) {
				if((getNeededEquipments().get(i).getEquipment_name().equals("fenetre")) && ((int)offer.get("number_of_windows") == 0)) {
					offer.replace("to_present", true, false);
				}
			}
		}
		

		ArrayList<Offer> offerList = new ArrayList<>();
		
		for(int i = 0; i < resultList.size(); i++) {
			if((boolean)resultList.get(i).get("to_present") == true) {
				Offer offerRow = new Offer((int)(resultList.get(i).get("space_id")), (String)(resultList.get(i).get("space_type")), (String)(resultList.get(i).get("space_name")),
						(int)(resultList.get(i).get("space_floor")), (String)(resultList.get(i).get("building_name")),(int)(resultList.get(i).get("space_cost")) +  getEquipmentCost(), 
						(int)(resultList.get(i).get("space_area")), (int)(resultList.get(i).get("number_of_windows")));
				offerList.add(offerRow);
			}
		}
		return offerList;
	}
}