package view;

import java.awt.BorderLayout;
import javax.swing.*;

public class Home{
	
	static JButton okButton=new JButton("OK");
	private static JPanel panel=new JPanel();
	static String[] companyNames = new String[] {"Wayne industries", "LexCorp", "Stark industries"};
	static JComboBox<String> companyNameList= new JComboBox<String>(companyNames);

	private static JPanel homePanel=new JPanel();
	
	public static JPanel getJPanel() {
		homePanel.add(companyNameList);
		homePanel.add(okButton);
		return homePanel;
	}
	
	public static JButton getOkButton() {
		return okButton;
	}

	static String selectedCompany = (String)companyNameList.getSelectedItem();
}