package view;

import javax.swing.*;
import java.awt.*;

public class LoanPanel {
 
	private static JPanel loanPanel=new JPanel();
	/*static JButton buildingButton=new JButton("Batiment : n");
	static JButton floorButton=new JButton("Etage : n");
	static JButton budgetButton=new JButton("Budget max");
	static JButton areaButton=new JButton("Surface minimale");*/
	
	private static String[] buildingNames = new String[] {"Stark Tower", "Watchtower", "Batcave"};
	private static JComboBox<String> buildingNameList= new JComboBox<String>(buildingNames);

	private static String[] floorNumber = new String[] {"1", "2", "3"};
	private static JComboBox<String> floorNumberList= new JComboBox<String>(floorNumber);

	private static String selectedBuilding = (String)buildingNameList.getSelectedItem();
	private static String selectedFloor = (String)floorNumberList.getSelectedItem();

	private static JSlider budget = new JSlider(0, 20000);
	private static JSlider area = new JSlider(0, 200);
	//static GridLayout floorMap = new GridLayout(4, 3);

	private static JButton filterButton=new JButton("Filtrer");

	private static JLabel buildingMessage=new JLabel("Selectionnez un batiment");
	private static JLabel floorMessage=new JLabel("Selectionnez un etage");
	private static JLabel priceMessage=new JLabel("Prix");
	private static JLabel areaMessage=new JLabel("Surface");

	public static JPanel getJPanel() {

		loanPanel.add(buildingMessage);
		loanPanel.add(buildingNameList);

		loanPanel.add(floorMessage);
		loanPanel.add(floorNumberList);

		loanPanel.add(priceMessage);
		loanPanel.add(budget);

		loanPanel.add(areaMessage);
		loanPanel.add(area);

		loanPanel.add(filterButton);
		//loanPanel.add(floorMap);
		//floorMap map = new floorMap(4,3);
		//loanPanel.add(map);
		return loanPanel;
	}

}
