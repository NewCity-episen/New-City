package view;

import java.awt.BorderLayout;
import javax.swing.*;

public class Home{
	
	static JButton okButton=new JButton("OK");
	private static JPanel panel=new JPanel();
	//we should use an arrayList or a vector to list the company names
	// it should be updated everytime an insert, update or delete is made on the associated table
	static String[] companyNames = new String[] {"Wayne entrepries", "LexCorp", "Stark industries", 
												 "The Daily Panet", "Daily bugle", "Pyramid Transnational",
												 "Planet express", "Big Kahuna Burger", "Red apple"};
	static JComboBox<String> companyNameList= new JComboBox<String>(companyNames);
	static String selectedCompany = (String)companyNameList.getSelectedItem();
	private static JPanel homePanel=new JPanel();
	
	public static JPanel getJPanel() {
		homePanel.add(companyNameList);
		companyNameList.setMaximumRowCount(5);
		homePanel.add(okButton);
		return homePanel;
	}
	
	public static JButton getOkButton() {
		return okButton;
	}

	
}