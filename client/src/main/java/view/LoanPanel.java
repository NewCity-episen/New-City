package view;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;

import model.Building;

import java.awt.*;

public class LoanPanel {
    private static JPanel panelAbsolute=new JPanel();
	private static JPanel loanPanel=new JPanel();
	
	private static String[] buildingNames = new String[] {"Stark Tower", "Watchtower", "Batcave"};
	private static JComboBox<String> buildingNameList= new JComboBox<String>(buildingNames);
	private static String[] floorNumber = new String[] {"1", "2", "3"};
	private static JComboBox<String> floorNumberList= new JComboBox<String>(floorNumber);
	private static String selectedBuilding = (String)buildingNameList.getSelectedItem();
	private static String selectedFloor = (String)floorNumberList.getSelectedItem();
	private static JSlider budget = new JSlider(0, 20000);
	private static JSlider area = new JSlider(0, 200);
	private static JButton filterButton=new JButton("Filtrer");
	private static JLabel buildingMessage=new JLabel("Selectionnez un batiment");
	private static JLabel floorMessage=new JLabel("Selectionnez un etage");
	private static JLabel priceMessage=new JLabel("Prix");
	private static JLabel areaMessage=new JLabel("Surface");
	private static JComboBox<Building> buildingBox =new JComboBox<Building>();
	private static JComboBox floorBox=new JComboBox();
	private static JComboBox<Building> buildingBoxFilter =new JComboBox<Building>();
	private static JComboBox floorBoxFilter =new JComboBox();
	private static JButton btnOkFloorBuilding=new JButton("OK");
	private static JButton btnAdvancedFilter=new JButton(" espaces reserves"); 
	static JPanel panelMap = new JPanel();
	private int currentFloor=1;//initially first floor
	private int currentBuilding=1;


	public LoanPanel(){

		panelAbsolute.setLayout(null);
		panelAbsolute.setBackground(new Color(245, 245, 220));
		loanPanel.setBounds(0,0,1000,105);
		loanPanel.setBackground(new Color(245, 245, 220));
		panelAbsolute.add(loanPanel);
		JPanel panelButtonsMeaning = new JPanel();
		panelButtonsMeaning.setBackground(new Color(245, 245, 220));
		panelButtonsMeaning.setBounds(771, 104, 223, 511);
		panelAbsolute.add(panelButtonsMeaning);
		panelButtonsMeaning.setLayout(null);
		JButton grayButton = new JButton("");
		grayButton.setBackground(new Color(169, 169, 169));
		grayButton.setBounds(12, 119, 82, 32);
		
		panelButtonsMeaning.add(grayButton);
		grayButton.setEnabled(false);
		JButton greenButton = new JButton("");
		greenButton.setBackground(new Color(143, 188, 143));
		greenButton.setBounds(12, 181, 82, 32);
		panelButtonsMeaning.add(greenButton);
		greenButton.setEnabled(false);
		JButton redButton = new JButton("");
		redButton.setBackground(new Color(165, 42, 42));
		redButton.setBounds(12, 248, 82, 32);
		redButton.setEnabled(false);
		panelButtonsMeaning.add(redButton);
		JLabel reserveLabel = new JLabel("Espace réservable");
		reserveLabel.setBounds(106, 130, 109, 16);
		panelButtonsMeaning.add(reserveLabel);
		
		reserveLabel = new JLabel("Espace réservé");
		reserveLabel.setBounds(106, 190, 109, 16);
		panelButtonsMeaning.add(reserveLabel);
		
		reserveLabel = new JLabel("Espace réservé");
		reserveLabel.setBounds(102, 248, 109, 16);
		panelButtonsMeaning.add(reserveLabel);
		
		reserveLabel = new JLabel("par d'autres\r\n");
		reserveLabel.setBounds(102, 259, 109, 16);
		panelButtonsMeaning.add(reserveLabel);
		
		reserveLabel = new JLabel("entreprises\r\n");
		reserveLabel.setBounds(102, 272, 109, 16);
		panelButtonsMeaning.add(reserveLabel);
		
		panelMap.setBackground(new Color(245, 245, 220));
		panelMap.setBounds(37, 120, 680, 400);
		panelMap.setLayout(new FlowLayout(FlowLayout.CENTER,15,15));
		panelAbsolute.add(panelMap);
		buildingBox.setBounds(210, 540, 108, 22);
		buildingBox.setUI(new BasicComboBoxUI());
		panelAbsolute.add(buildingBox);
		floorBox.setBounds(330, 540, 108, 22);
		floorBox.setUI(new BasicComboBoxUI());
		panelAbsolute.add(floorBox);
		btnOkFloorBuilding.setBounds(450,540,130,22);
		panelAbsolute.add(btnOkFloorBuilding);
		btnAdvancedFilter.setBounds(590,540,130,22);
		panelAbsolute.add(btnAdvancedFilter);//HF

	}
	public void showFloorMap() {
		
	}
	public static JPanel getJPanel() {
		// find a way to show two times the same JComboBox
		loanPanel.add(buildingMessage);
		loanPanel.add(buildingBoxFilter);
		
		loanPanel.add(floorMessage);
		loanPanel.add(floorBoxFilter);

		loanPanel.add(priceMessage);
		loanPanel.add(budget);

		loanPanel.add(areaMessage);
		loanPanel.add(area);

		loanPanel.add(filterButton);
		//loanPanel.add(floorMap);
		//floorMap map = new floorMap(4,3);
		//loanPanel.add(map);
		return panelAbsolute;
	}
	
	public static JButton getFilterButton() {
		return filterButton;
	}
	public static JPanel getPanelAbsolute() {
		return panelAbsolute;
	}
	public static void setPanelAbsolute(JPanel panelAbsolute) {
		LoanPanel.panelAbsolute = panelAbsolute;
	}
	public static JPanel getLoanPanel() {
		return loanPanel;
	}
	public static void setLoanPanel(JPanel loanPanel) {
		LoanPanel.loanPanel = loanPanel;
	}
	public static JComboBox<Building> getBuildingBox() {
		return buildingBox;
	}
	public static void setBuildingBox(JComboBox<Building> buildingBox) {
		LoanPanel.buildingBox = buildingBox;
	}
	
	public static JComboBox<Building> getBuildingBoxFilter() {
		return buildingBoxFilter;
	}
	public static void setBuildingBoxFilter(JComboBox<Building> buildingBoxFilter) {
		LoanPanel.buildingBoxFilter = buildingBoxFilter;
	}
	
	public static JComboBox getFloorBox() {
		return floorBox;
	}
	public static void setFloorBox(JComboBox floorBox) {
		LoanPanel.floorBox = floorBox;
	}
	
	public static JComboBox getFloorBoxFilter() {
		return floorBoxFilter;
	}
	public static void setFloorBoxFilter(JComboBox floorBoxFilter) {
		LoanPanel.floorBoxFilter = floorBoxFilter;
	}
	
	public static JButton getBtnOkFloorBuilding() {
		return btnOkFloorBuilding;
	}
	public static void setBtnOkFloorBuilding(JButton btnOkFloorBuilding) {
		LoanPanel.btnOkFloorBuilding = btnOkFloorBuilding;
	}
	
	
	public static JButton getBtnAdvancedFilter() {
		return btnAdvancedFilter;
	}
	public static void setBtnAdvancedFiltre(JButton btnAdvancedFilter) {
		LoanPanel.btnAdvancedFilter = btnAdvancedFilter;
	}
	public static JPanel getPanelMap() {
		return panelMap;
	}
	public static void setPanelMap(JPanel panelMap) {
		LoanPanel.panelMap = panelMap;
	}

}
