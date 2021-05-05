package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	
	public static ArrayList<Offer> filterLoanOffer(ArrayList<Map> resultList) {
		
		/*setSpaceBuilding(LoanPanel.getSelectedBuilding());
		System.out.println(LoanCondition.getSpaceBuilding()+"\\");
		setSpaceFloor(LoanPanel.getSelectedFloor());
		System.out.println(LoanCondition.getSpaceFloor()+"\\");*/

		/*for(int i = 0; i < resultList.size(); i ++) {
			resultList.get(i).setOfferCost(resultList.get(i).get("space_cost") + getEquipmentCost());
		}*/
		
		for(Map offer : resultList) {
			System.out.println(offer.get("building_name")+"\\");	
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
			/*if(((int)resultList.get(i).get("space_cost") + getEquipmentCost()) > getLoanBudget()) {
			resultList.remove(i);
		}
		
		
		
		if((int)resultList.get(i).get("space_area") < getSpaceArea()) {
			resultList.remove(i);
		}
		
		if(getNeededEquipments().containsKey("fenetre") && ((int)offer.get("number_of_windows") == 0)) {
			offer.replace("to_present", true, false);
		}*/
		}
		

		ArrayList<Offer> offerList = new ArrayList<>();
		
		for(int i = 0; i < resultList.size(); i++) {
			if((boolean)resultList.get(i).get("to_present") == true) {
				Offer offerRow = new Offer((int)(resultList.get(i).get("space_id")), (String)(resultList.get(i).get("space_type")), (String)(resultList.get(i).get("space_name")),
						(int)(resultList.get(i).get("space_floor")), (String)(resultList.get(i).get("building_name")),(int)(resultList.get(i).get("space_cost")), 
						(int)(resultList.get(i).get("space_area")), (int)(resultList.get(i).get("number_of_windows")));
				offerList.add(offerRow);
			}
		}
		return offerList;
	}
}