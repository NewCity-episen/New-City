package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class SmartWindowStatusFrame extends JFrame {
	private static JPanel framePanel = new JPanel();
	private static JPanel winPanel =new JPanel();
	private static CardLayout winCard=new CardLayout();
	private static JTextArea swID= new JTextArea("");;
	private static JTextArea workSpaceName=new JTextArea("");;
	private static JTextArea levelOfTeint=new JTextArea("");;
	private static JTextArea outdoorilluminance=new JTextArea("");;
	private static JTextArea levelOfBlind=new JTextArea("");;
	private static JTextArea sunAzimuth=new JTextArea("");;
	private static JTextArea swOrientation=new JTextArea("");;
	

	




	public SmartWindowStatusFrame() {
		View.getappFrame().setEnabled(false);
		View.getappFrame();
		winPanel.setLayout(winCard);
		framePanel.setLayout(null);	
		winPanel.add("configure",framePanel);
		
		
		framePanel.setBackground(new Color(245, 245, 220));
		framePanel.setOpaque(true);
		
		JLabel lblNewLabel = new JLabel("Etat actuel de la fenetre :");
		lblNewLabel.setBounds(24, 15, 169, 26);
		framePanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("fenetre id");
		lblNewLabel_1.setBounds(89, 45, 104, 26);
		framePanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_7 = new JLabel("WorkSpace ");
		lblNewLabel_7.setBounds(89, 75, 117, 26);
		framePanel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_2 = new JLabel("teinte du vitre");
		lblNewLabel_2.setBounds(89, 105, 134, 26);
		framePanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("niveau de fermeture du store");
		lblNewLabel_3.setBounds(89, 135, 229, 26);
		framePanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("niveau d'ensoleillement");
		lblNewLabel_4.setBounds(89, 175, 183, 26);
		framePanel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("l'azimut du soleil");
		lblNewLabel_5.setBounds(89, 205, 169, 26);
		framePanel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("position de la fenêtre");
		lblNewLabel_6.setBounds(89, 235, 183, 26);
		framePanel.add(lblNewLabel_6);
		

		swID.setBounds(301, 45, 130, 26);
		framePanel.add(swID);
		swID.setColumns(10);
		
	
		workSpaceName.setBounds(301, 75, 130, 26);
		framePanel.add(workSpaceName);
		workSpaceName.setColumns(10);
		
		
		levelOfTeint.setBounds(301, 105, 130, 26);
		framePanel.add(levelOfTeint);
		levelOfTeint.setColumns(10);
		
	
		levelOfBlind.setBounds(301, 135, 130, 26);
		framePanel.add(levelOfBlind);
		levelOfBlind.setColumns(10);
		
		
		outdoorilluminance.setBounds(301, 175, 130, 26);
		framePanel.add(outdoorilluminance);
		outdoorilluminance.setColumns(10);
		
		
		 sunAzimuth.setBounds(301, 205, 130, 26);
		framePanel.add( sunAzimuth);
		 sunAzimuth.setColumns(10);
		
		
		
	
		swOrientation.setBounds(301, 235, 130, 26);
		framePanel.add(swOrientation);
		swOrientation.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("°");
		lblNewLabel_8.setBounds(443, 210, 61, 16);
		framePanel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Lux");
		lblNewLabel_9.setBounds(443, 180, 61, 16);
		framePanel.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("%");
		lblNewLabel_10.setBounds(443, 140, 61, 16);
		framePanel.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("%");
		lblNewLabel_11.setBounds(443, 110, 61, 16);
		framePanel.add(lblNewLabel_11);
		

		this.setSize(600, 400);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		getContentPane().add(winPanel);
		getContentPane().setBackground(new Color(245, 245, 220));
		
		
		
            addWindowListener((WindowListener) new WindowAdapter() {
			
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

				View.getappFrame().setEnabled(true);
				View.getappFrame().setVisible(true);
				dispose();

				}
			});
		
	}







	public static JTextArea getSwID() {
		return swID;
	}







	public void setSwID(JTextArea swID) {
		this.swID = swID;
	}







	public static JTextArea getWorkSpaceName() {
		return workSpaceName;
	}







	public void setWorkSpaceName(JTextArea workSpaceName) {
		this.workSpaceName = workSpaceName;
	}







	public static JTextArea getLevelOfTeint() {
		return levelOfTeint;
	}







	public void setLevelOfTeint(JTextArea levelOfTeint) {
		this.levelOfTeint = levelOfTeint;
	}







	public static JTextArea getOutdoorilluminance() {
		return outdoorilluminance;
	}







	public void setOutdoorilluminance(JTextArea outdoorilluminance) {
		this.outdoorilluminance = outdoorilluminance;
	}







	public static JTextArea getLevelOfBlind() {
		return levelOfBlind;
	}







	public void setLevelOfBlind(JTextArea levelOfBlind) {
		this.levelOfBlind = levelOfBlind;
	}







	public static JTextArea getSunAzimuth() {
		return sunAzimuth;
	}







	public void setSunAzimuth(JTextArea sunAzimuth) {
		this.sunAzimuth = sunAzimuth;
	}







	public static JTextArea getSwOrientation() {
		return swOrientation;
	}







	public void setSwOrientation(JTextArea swOrientation) {
		this.swOrientation = swOrientation;
	}
}
