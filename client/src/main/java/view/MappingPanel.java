package view;
import model.Equipment;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;


import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JLabel;
import javax.swing.JPanel;





import javax.swing.UIManager;

import model.WorkSpace;
public class MappingPanel{
	private static JPanel mappingPanel=new JPanel();
	private static CardLayout mappingCard=new CardLayout();
	private static JPanel choiceOfMappingPanel=new JPanel();
	private static JPanel mappingSpotsPanel=new JPanel();
	private static JPanel spotsMap=new JPanel();
	private static JComboBox<Equipment> equipmentsToInstallBox=new JComboBox<Equipment>();
	private static WorkSpace workSpace=null;
	private static JLabel position=new JLabel();
	private static JLabel position2=new JLabel();
	private static JButton mapSensorsBtn = new JButton("Mapper capteurs");
	private static JButton mapEquipmentsBtn = new JButton("Mapper équipements");
	private static JButton okEquipmentButton=new JButton("OK");
	private static JButton returnButton=new JButton("Retour");
	private static JButton cancelButton=new JButton("Annuler");
	private static int currentp=1;
	private static boolean initialized=false;
	public MappingPanel() {
		
		
		mappingPanel.setLayout(mappingCard);
		mappingPanel.add("Choix",choiceOfMappingPanel);
		mappingPanel.add("Emplacement",mappingSpotsPanel);
		choiceOfMappingPanel.setLayout(null);
		choiceOfMappingPanel.add(position);
		choiceOfMappingPanel.setBackground(Color.white);
		position.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		position.setBounds(12, 25, 480, 36);
		mapSensorsBtn.setFocusPainted(false);
		mapSensorsBtn.setBorderPainted(true);
		mapSensorsBtn.setBackground(Color.gray);
		mapSensorsBtn.setForeground(Color.white);
		mapSensorsBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		mapSensorsBtn.setBounds(249, 255, 215, 42);
		mapEquipmentsBtn.setBounds(502, 255, 215, 42);
		mapEquipmentsBtn.setFocusPainted(false);
		mapEquipmentsBtn.setBorderPainted(true);
		mapEquipmentsBtn.setBackground(Color.gray);
		mapEquipmentsBtn.setForeground(Color.white);
		mapEquipmentsBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		choiceOfMappingPanel.add(mapEquipmentsBtn);
		choiceOfMappingPanel.add(mapSensorsBtn);
		mappingSpotsPanel.setLayout(null);
		position2.setBounds(0, 0, 380, 32);
		mappingSpotsPanel.add(position2);
		equipmentsToInstallBox.setBounds(392, 5, 256, 22);
		mappingSpotsPanel.add(equipmentsToInstallBox);
		okEquipmentButton.setBounds(660, 5, 97, 21);
		mappingSpotsPanel.add(okEquipmentButton);
		spotsMap.setBounds(10, 30, 888, 508);
		mappingSpotsPanel.add(spotsMap);
		cancelButton.setBounds(777,5,100,21);
		cancelButton.setEnabled(false);
		mappingSpotsPanel.add(cancelButton);
		returnButton.setBounds(24, 548, 97, 25);
		mappingSpotsPanel.add(returnButton);
		spotsMap.setLayout(null);
		
	}
	public static void showChoiceOfMappingPanel() {
		mappingCard.show(mappingPanel, "Choix");	
		position.setText("WorkSpace "+workSpace.getId_work_space()+", \u00C9tage "+ workSpace.getSpace_floor()+" - B\u00E2timent "+ workSpace.getId_building());
		choiceOfMappingPanel.validate();
	}
	public static void showMappingPanel(int choice) {
		mappingCard.show(mappingPanel, "Emplacement");
		if(choice==1) {// if user clicked on "mapper équipements"
			setCurrentp(2);
			position2.setText("Plan des emplacements des équipements du workspace "+workSpace.getId_work_space()+" a mapper");

		}
		else {
			setCurrentp(3);
			position2.setText("Plan des emplacements des capteurs du workspace "+workSpace.getId_work_space()+" a mapper");
		}
	}
	public static JPanel getJPanel() {
		return mappingPanel;
	}
	public CardLayout getMappingCard() {
		return mappingCard;
	}
	public void setMappingCard(CardLayout mappingCard) {
		this.mappingCard = mappingCard;
	}
	public JPanel getChoiceOfMappingPanel() {
		return choiceOfMappingPanel;
	}
	public void setChoiceOfMappingPanel(JPanel choiceOfMappingPanel) {
		this.choiceOfMappingPanel = choiceOfMappingPanel;
	}
	public static WorkSpace getWorkSpace() {
		return workSpace;
	}
	public static void setWorkSpace(WorkSpace workSpace1) {
		workSpace = workSpace1;
	}
	public static JPanel getMappingPanel() {
		return mappingPanel;
	}
	public static void setMappingPanel(JPanel mappingPanel) {
		MappingPanel.mappingPanel = mappingPanel;
	}
	public static JPanel getMappingSpotsPanel() {
		return mappingSpotsPanel;
	}
	public static void setMappingSpotsPanel(JPanel mappingSpotsPanel) {
		MappingPanel.mappingSpotsPanel = mappingSpotsPanel;
	}
	public static JPanel getSpotsMap() {
		return spotsMap;
	}
	public static void setSpotsMap(JPanel spotsMap) {
		MappingPanel.spotsMap = spotsMap;
	}
	public static JComboBox<Equipment> getEquipmentsToInstallBox() {
		return equipmentsToInstallBox;
	}
	public static void setEquipmentsToInstallBox(JComboBox<Equipment> equipmentsToInstallBox) {
		MappingPanel.equipmentsToInstallBox = equipmentsToInstallBox;
	}
	public static JLabel getPosition() {
		return position;
	}
	public static void setPosition(JLabel position) {
		MappingPanel.position = position;
	}
	public static JLabel getPosition2() {
		return position2;
	}
	public static void setPosition2(JLabel position2) {
		MappingPanel.position2 = position2;
	}
	public static JButton getMapSensorsBtn() {
		return mapSensorsBtn;
	}
	public static void setMapSensorsBtn(JButton mapSensors) {
		MappingPanel.mapSensorsBtn = mapSensors;
	}
	public static JButton getMapEquipmentsBtn() {
		return mapEquipmentsBtn;
	}
	public static void setMapEquipmentsBtn(JButton mapEquipments) {
		MappingPanel.mapEquipmentsBtn = mapEquipments;
	}
	public static JButton getOkEquipmentButton() {
		return okEquipmentButton;
	}
	public static void setOkEquipmentButton(JButton okEquipmentButton) {
		MappingPanel.okEquipmentButton = okEquipmentButton;
	}
	public static JButton getReturnButton() {
		return returnButton;
	}
	public static int getCurrentp() {
		return currentp;
	}
	public static void setCurrentp(int currentp) {
		MappingPanel.currentp = currentp;
	}
	public static JButton getCancelButton() {
		return cancelButton;
	}
	public static boolean isInitialized() {
		return initialized;
	}
	public static void setInitialized(boolean initialized) {
		MappingPanel.initialized = initialized;
	}


	
}
