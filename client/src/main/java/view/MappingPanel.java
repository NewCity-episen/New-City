package view;
import model.Equipment;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;





import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicComboBoxUI;

import ch.qos.logback.classic.Logger;
import edu.episen.si.ing1.pds.client.ClientGUI;
import model.WorkSpace;
public class MappingPanel extends JFrame{
	private static final String ConfigEnVar="REQUESTS_LOCATION";
	private static String RequestsFileLocation = "";
	private static  JPanel mappingPanel=new JPanel();
	private static  CardLayout mappingCard=new CardLayout();
	private static  JPanel choiceOfMappingPanel=new JPanel();
	private  JPanel mappingSpotsPanel=new JPanel();
	private  SpotsMapBackground spotsMap;
	private static  JComboBox<Equipment> equipmentsToInstallBox=new JComboBox<Equipment>();
	private static  WorkSpace workSpace=null;
	private static JLabel position=new JLabel();
	private static  JLabel position2=new JLabel();
	private static JButton mapSensorsBtn = new JButton("Mapper capteurs");
	private static JButton mapEquipmentsBtn = new JButton("Mapper équipements");
	private static JButton okEquipmentButton=new JButton("OK");
	private static JButton returnButton=new JButton("Retour");
	private static JButton cancelButton=new JButton("Annuler");
	private static JButton closeButton=new JButton("Retour");
	private static JButton refreshButton=new JButton("Actualiser");
	private static int currentp=1;
	private static boolean opened=false;
	public MappingPanel() {
		setOpened(true);
		RequestsFileLocation=System.getenv(ConfigEnVar);
		this.setSize(View.getWIDTH(),View.getHEIGHT());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.getContentPane().add(mappingPanel);
		addWindowListener((WindowListener) new WindowAdapter() {
			
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				setOpened(false);
				View.getappFrame().setEnabled(true);
				View.getappFrame().setVisible(true);
				}
			});
		mappingPanel.setBackground(new Color(245, 245, 220));
		mappingPanel.setLayout(mappingCard);
		mappingPanel.add("Choix",choiceOfMappingPanel);
		mappingPanel.add("Emplacement",mappingSpotsPanel);
		choiceOfMappingPanel.setLayout(null);
		choiceOfMappingPanel.add(position);
		choiceOfMappingPanel.setBackground(new Color(245, 245, 220));
		position.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		position.setBounds(12, 25, 480, 36);
		mapSensorsBtn.setFocusPainted(false);
		mapSensorsBtn.setOpaque(true);
		mapSensorsBtn.setBorderPainted(false);
		mapSensorsBtn.setBackground(Color.gray);
		returnButton.setBackground(Color.gray);
		returnButton.setOpaque(true);
		returnButton.setForeground(Color.white);
		returnButton.setBorderPainted(false);
		closeButton.setBackground(Color.gray);
		closeButton.setOpaque(true);
		closeButton.setBorderPainted(false);
		closeButton.setForeground(Color.white);
		closeButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		returnButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		okEquipmentButton.setBackground(Color.gray);
		okEquipmentButton.setForeground(Color.white);
		okEquipmentButton.setBorderPainted(false);
		okEquipmentButton.setOpaque(true);
		okEquipmentButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		cancelButton.setBackground(Color.gray);
		cancelButton.setOpaque(true);
		cancelButton.setForeground(Color.white);
		cancelButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		mapSensorsBtn.setForeground(Color.white);
		mapSensorsBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		mapSensorsBtn.setBounds(249, 255, 215, 42);
		mapSensorsBtn.setOpaque(true);
		mapEquipmentsBtn.setBounds(502, 255, 215, 42);
		mapEquipmentsBtn.setFocusPainted(false);
		mapEquipmentsBtn.setBackground(Color.gray);
		mapEquipmentsBtn.setOpaque(true);
		mapEquipmentsBtn.setForeground(Color.white);
		mapEquipmentsBtn.setBorderPainted(false);
		mapEquipmentsBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		choiceOfMappingPanel.add(mapEquipmentsBtn);
		choiceOfMappingPanel.add(mapSensorsBtn);
		closeButton.setBounds(24, 548, 97, 25);
		choiceOfMappingPanel.add(closeButton);
		mappingSpotsPanel.setLayout(null);
		position2.setBounds(0, 0, 380, 32);
		mappingSpotsPanel.add(position2);
		mappingSpotsPanel.setBackground(new Color(245, 245, 220));
		equipmentsToInstallBox.setBounds(392, 5, 256, 22);
		equipmentsToInstallBox.setUI(new BasicComboBoxUI());
		mappingSpotsPanel.add(equipmentsToInstallBox);
		okEquipmentButton.setBounds(660, 5, 97, 21);
		mappingSpotsPanel.add(okEquipmentButton);
		cancelButton.setBounds(777,5,100,21);
		refreshButton.setBounds(890,5,100,21);
		refreshButton.setBackground(Color.gray);
		refreshButton.setForeground(Color.white);
		refreshButton.setBorderPainted(false);
		refreshButton.setOpaque(true);
		refreshButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		cancelButton.setEnabled(false);
		cancelButton.setBorderPainted(false);
		mappingSpotsPanel.add(cancelButton);
		mappingSpotsPanel.add(refreshButton);
		returnButton.setBounds(24, 548, 97, 25);
		returnButton.setBorderPainted(false);
		mappingSpotsPanel.add(returnButton);
		JLabel window1_1 = new JLabel("");
		window1_1.setIcon(new ImageIcon(RequestsFileLocation+"/pin-blue.png"));
		window1_1.setBounds(823, 550, 32, 41);
		mappingSpotsPanel.add(window1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Emplacement libre");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(800, 587, 153, 16);
		mappingSpotsPanel.add(lblNewLabel_2);
		
		JLabel window1_1_1 = new JLabel("");
		window1_1_1.setIcon(new ImageIcon(RequestsFileLocation+"/pin-red.png"));
		window1_1_1.setBounds(678, 550, 32, 41);
		mappingSpotsPanel.add(window1_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Emplacement occup\u00E9(hors service)");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2_1.setBounds(615, 587, 173, 16);
		mappingSpotsPanel.add(lblNewLabel_2_1);
		
		JLabel window1_1_1_1 = new JLabel("");
		window1_1_1_1.setIcon(new ImageIcon(RequestsFileLocation+"/pin-green.png"));
		window1_1_1_1.setBounds(531, 550, 32, 41);
		mappingSpotsPanel.add(window1_1_1_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Emplacement occup\u00E9");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2_1_1.setBounds(493, 586, 115, 16);
		mappingSpotsPanel.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Emplacement compatible");
		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2_1_1_1.setBounds(353, 586, 128, 16);
		mappingSpotsPanel.add(lblNewLabel_2_1_1_1);
		
		JLabel window1_1_1_1_1 = new JLabel("");
		window1_1_1_1_1.setIcon(new ImageIcon(RequestsFileLocation+"/pin-orange.gif"));
		window1_1_1_1_1.setBounds(392, 550, 32, 41);
		mappingSpotsPanel.add(window1_1_1_1_1);
		View.getappFrame().setVisible(false);
		this.setVisible(true);
		position2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
	}
	public static  void showChoiceOfMappingPanel() {
		mappingCard.show(mappingPanel, "Choix");	
		position.setText("WorkSpace "+workSpace.getSpace_name()+", \u00C9tage "+ workSpace.getSpace_floor()+" - B\u00E2timent "+ workSpace.getId_building());
		choiceOfMappingPanel.validate();
	}
	public static void showMappingPanel(int choice) {
		mappingCard.show(mappingPanel, "Emplacement");
		if(choice==1) {// if user clicked on "mapper équipements"
			currentp=2;
			position2.setText("Plan des emplacements des équipements du workspace "+workSpace.getSpace_name()+" a mapper");

		}
		else {
			currentp=3;
			position2.setText("Plan des emplacements des capteurs du workspace "+workSpace.getSpace_name()+" a mapper");
		}
		

	}
	public JPanel getJPanel() {
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
	public  void setWorkSpace(WorkSpace workSpace1) {
		workSpace = workSpace1;
	}
	public  JPanel getMappingPanel() {
		return mappingPanel;
	}
	public  void setMappingPanel(JPanel mappingPanel) {
		this.mappingPanel = mappingPanel;
	}
	public  JPanel getMappingSpotsPanel() {
		return mappingSpotsPanel;
	}
	public void setMappingSpotsPanel(JPanel mappingSpotsPanel) {
		this.mappingSpotsPanel = mappingSpotsPanel;
	}
	public JPanel getSpotsMap() {
		return spotsMap;
	}
	public void setSpotsMap(SpotsMapBackground spotsMap) {
		this.spotsMap = spotsMap;
	}
	
	public static boolean isOpened() {
		return opened;
	}
	public static void setOpened(boolean opened) {
		MappingPanel.opened = opened;
	}
	public static JComboBox<Equipment> getEquipmentsToInstallBox() {
		return equipmentsToInstallBox;
	}
	public void setEquipmentsToInstallBox(JComboBox<Equipment> equipmentsToInstallBox) {
		this.equipmentsToInstallBox = equipmentsToInstallBox;
	}
	public JLabel getPosition() {
		return position;
	}
	public void setPosition(JLabel position) {
		this.position = position;
	}
	public JLabel getPosition2() {
		return position2;
	}
	public void setPosition2(JLabel position2) {
		this.position2 = position2;
	}
	public static JButton getMapSensorsBtn() {
		return mapSensorsBtn;
	}
	public void setMapSensorsBtn(JButton mapSensorsBtn) {
		this.mapSensorsBtn = mapSensorsBtn;
	}
	public static JButton getMapEquipmentsBtn() {
		return mapEquipmentsBtn;
	}
	public void setMapEquipmentsBtn(JButton mapEquipmentsBtn) {
		this.mapEquipmentsBtn = mapEquipmentsBtn;
	}
	public static JButton getOkEquipmentButton() {
		return okEquipmentButton;
	}
	public void setOkEquipmentButton(JButton okEquipmentButton) {
		this.okEquipmentButton = okEquipmentButton;
	}
	public static JButton getReturnButton() {
		return returnButton;
	}
	public void setReturnButton(JButton returnButton) {
		this.returnButton = returnButton;
	}
	public static JButton getCancelButton() {
		return cancelButton;
	}
	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}
	public static int getCurrentp() {
		return currentp;
	}
	public static void setCurrentp(int currentp) {
		MappingPanel.currentp = currentp;
	}
	public static JButton getCloseButton() {
		return closeButton;
	}
	public static void setCloseButton(JButton closeButton) {
		MappingPanel.closeButton = closeButton;
	}
	public static JButton getRefreshButton() {
		return refreshButton;
	}
	public static void setRefreshButton(JButton refreshButton) {
		MappingPanel.refreshButton = refreshButton;
	}
	


	
}
