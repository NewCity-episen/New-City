package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;

import controller.Controller;
import edu.episen.si.ing1.pds.client.ClientGUI;
import model.Company;
import model.Model;
public class Home{
	private static final String ConfigEnVar="REQUESTS_LOCATION";
	private static String RequestsFileLocation = "";
	private static JButton okButton=new JButton("Se connecter");
	private static JPanel panel=new JPanel();
	//we should use an arrayList or a vector to list the company names
	// it should be updated everytime an insert, update or delete is made on the associated table
	private static JComboBox<Company> companyNameList=new JComboBox<Company>();
	private static JPanel homePanel=new JPanel();
	private static JLabel selectCompany=new JLabel("Veuillez choisir votre entreprise:");
	
	Home(){
		RequestsFileLocation= System.getenv(ConfigEnVar);
		homePanel.setLayout(null);
		homePanel.setBackground(Color.white);
		selectCompany.setBounds(287, 419, 310, 16);
		homePanel.add(selectCompany);
		homePanel.add(companyNameList);
		JLabel gifNewcity = new JLabel("");
		gifNewcity.setIcon(new ImageIcon(RequestsFileLocation+"\\new-city (2).gif"));
		gifNewcity.setBounds(161, 13, 534, 393);
		homePanel.add(gifNewcity);
		companyNameList.setMaximumRowCount(5);
		companyNameList.setBounds(287, 454, 255, 22);
		companyNameList.setUI(new BasicComboBoxUI());
		okButton.setBounds(554, 453, 114, 25);
		homePanel.add(okButton);
	}
	public static JPanel getJPanel() {
		
		return homePanel;
	}
	
	public static JButton getOkButton() {
		return okButton;
	}
	
	public static JComboBox<Company> getCompanyNameList() {
		return companyNameList;
	}

	
}