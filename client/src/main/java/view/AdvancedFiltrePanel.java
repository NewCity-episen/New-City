package view;


import java.awt.CardLayout;
import java.awt.Color;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.Building;


import javax.swing.JButton;


public class AdvancedFiltrePanel {
	private static JPanel advancedFiltrePanel=new JPanel();
	private static CardLayout advancedFiltrePanelCard=new CardLayout();
	private static JPanel choiceAdvancedPanel =new JPanel();
	private static JLabel titleLabel =new JLabel();
	private static JLabel msgLabel=new JLabel();
	private static JButton btnReturn = new JButton("retour");
	private static JRadioButton btnFilterWindow = new JRadioButton ("Éspaces mappés et possédant des fenetres",false);
	private static JButton btnOk =new JButton ("Filtrer");
	private static int floorNum=1;
	private static Building building=null;
	public AdvancedFiltrePanel() {
		
		advancedFiltrePanel.setLayout(advancedFiltrePanelCard);
		advancedFiltrePanel.add("Filter",choiceAdvancedPanel);
		
		msgLabel.setText("Veuillez choisir un filtre");
		choiceAdvancedPanel.setLayout(null);	
		choiceAdvancedPanel.add(titleLabel);
		choiceAdvancedPanel.add(msgLabel);
		choiceAdvancedPanel.add(btnFilterWindow);
		choiceAdvancedPanel.setBackground(Color.white);
		titleLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		titleLabel.setBounds(12, 5, 480, 36);
		msgLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		msgLabel.setBounds(12, 25, 480, 36);
		btnFilterWindow.setBounds(25, 101, 377, 23);
		
		btnReturn.setBounds(390, 535, 100, 22);
		choiceAdvancedPanel.add(btnReturn);
		
		btnOk.setBounds(510, 535, 100, 22);
		choiceAdvancedPanel.add(btnOk);
		
		
			
		
	}
		public static int getFloorNum() {
		return floorNum;
	}
	public static void setFloorNum(int floorNum) {
		AdvancedFiltrePanel.floorNum = floorNum;
	}
	public static Building getBuilding() {
		return building;
	}
	public static void setBuilding(Building building) {
		AdvancedFiltrePanel.building = building;
	}
		public static JRadioButton getBtnFilterWindow() {
		return btnFilterWindow;
	}
	public static void setBtnFilterWindow(JRadioButton btnFilterWindow) {
		AdvancedFiltrePanel.btnFilterWindow = btnFilterWindow;
	}
		public static JButton getBtnReturn() {
		return btnReturn;
	}
	public static void setBtnReturn(JButton btnReturn) {
		AdvancedFiltrePanel.btnReturn = btnReturn;
	}
		public static JPanel getChoiceAdvancedPanel() {
		return choiceAdvancedPanel;
	}
	public static void setChoiceAdvancedPanel(JPanel choiceAdvancedPanel) {
		AdvancedFiltrePanel.choiceAdvancedPanel = choiceAdvancedPanel;
	}
		public static void show() {
			advancedFiltrePanelCard.show(advancedFiltrePanel, "Filter");
			titleLabel.setText("Bâtiment  "+ building.getBuilding_name() +", étage "+ floorNum);
			
		}
	public static JPanel getJPanel() {
		
		return advancedFiltrePanel;
	}
	public static JButton getBtnOk() {
		return btnOk;
	}
	public static void setBtnOk(JButton btnOk) {
		AdvancedFiltrePanel.btnOk = btnOk;
	}
	public static JPanel getAdvancedFiltrePanel() {
		return advancedFiltrePanel;
	}
	public static void setAdvancedFiltrePanel(JPanel advancedFiltrePanel) {
		AdvancedFiltrePanel.advancedFiltrePanel = advancedFiltrePanel;
	}
	public static CardLayout getAdvancedFiltrePanelCard() {
		return advancedFiltrePanelCard;
	}
	public static void setAdvancedFiltrePanelCard(CardLayout advancedFiltrePanelCard) {
		AdvancedFiltrePanel.advancedFiltrePanelCard = advancedFiltrePanelCard;
	}
}
