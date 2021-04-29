package view;

import java.awt.BorderLayout;
import javax.swing.*;

import model.Company;
import model.Model;
public class Home{
	
	private static JButton okButton=new JButton("OK");
	private static JPanel panel=new JPanel();
	//we should use an arrayList or a vector to list the company names
	// it should be updated everytime an insert, update or delete is made on the associated table
	private static JComboBox<Company> companyNameList=new JComboBox<Company>();
	private static JPanel homePanel=new JPanel();
	
	Home(){
		
		
		
		homePanel.add(companyNameList);
		companyNameList.setMaximumRowCount(5);
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