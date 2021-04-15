package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.View;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class FunctionalitiesBarAndPanel{
	    private JPanel functionsAndBarPanel=new JPanel();
		private static CardLayout myFunctionalities=new CardLayout();
		static JPanel functionalitiesPanel=new JPanel(); // The content of this Panel is going to change depending on the functionality that you clicked on.
		private static JButton homeButton=new JButton("Home");
		private static JButton loanButton=new JButton("Réservation");
	    private static JButton mappingButton=new JButton("Mapping");
	    private static JButton configurateWindowsButton=new JButton("Configurer fenêtres");
	    private static JButton quitButton=new JButton("Quitter");	
	    private LoanPanel loanPanel=new LoanPanel();
	    private MappingPanel mappingPanel=new MappingPanel();
	    private ConfigurateWindowsPanel configurateWindowPanel=new ConfigurateWindowsPanel();
	    private static JLabel information;
    
	  public FunctionalitiesBarAndPanel() {

     /******************JToolBar Part*************************************************/
		JToolBar functionalitiesBar=new JToolBar();
		functionalitiesBar.setBackground(new Color(100, 149, 237));
		functionalitiesBar.setFloatable(false);
		information=new JLabel();
		functionalitiesBar.add(information);
		homeButton.setBackground(new Color(100, 149, 237));
		homeButton.setBorderPainted(false);
		homeButton.setForeground(new Color(255, 255, 255));
		loanButton.setBackground(new Color(100, 149, 237));
		loanButton.setBorderPainted(false);
		loanButton.setForeground(new Color(255, 255, 255));
		mappingButton.setBackground(new Color(100, 149, 237));
		mappingButton.setBorderPainted(false);
		mappingButton.setForeground(new Color(255, 255, 255));
		configurateWindowsButton.setBackground(new Color(100, 149, 237));
		configurateWindowsButton.setBorderPainted(false);
		configurateWindowsButton.setForeground(new Color(255, 255, 255));
		quitButton.setBackground(new Color(100, 149, 237));
		quitButton.setBorderPainted(false);
		quitButton.setForeground(new Color(255, 255, 255));
		functionalitiesBar.addSeparator(new Dimension(25,0));
		functionalitiesBar.add(loanButton);
		functionalitiesBar.add(mappingButton);
		functionalitiesBar.add(configurateWindowsButton);
		functionalitiesBar.addSeparator(new Dimension(400,0));
		functionalitiesBar.add(quitButton);
		functionsAndBarPanel.setLayout(new BorderLayout());
		functionsAndBarPanel.add(functionalitiesBar,BorderLayout.NORTH);
	/*****************End of JToolBar Part***************************************/
		
	/****************JPanel Part that is going to be a CardLayout to switch between each of the functionalities**************/
         functionsAndBarPanel.add(functionalitiesPanel,BorderLayout.CENTER);
		functionalitiesPanel.setBackground(Color.DARK_GRAY);

		functionalitiesPanel.setLayout(myFunctionalities);
		JPanel heyPanel=new JPanel();
		JLabel hey=new JLabel("Heeey! Choose your functionality :) ");
		heyPanel.add(hey);
		functionalitiesPanel.add("hey",heyPanel);
	    functionalitiesPanel.add("Réservation",loanPanel.getJPanel());
	    functionalitiesPanel.add("Mapping",mappingPanel.getJPanel());
	    functionalitiesPanel.add("Configurer fenêtres",configurateWindowPanel.getJPanel());
	    myFunctionalities.show(functionalitiesPanel, "hey");//will delete later
   /**********************End of JPanel Part**************************************/
    }
	  public static JButton getHomeButton() {
			return homeButton;
	}
	public static JButton getLoanButton() {
		return loanButton;
	}
	public static JButton getMappingButton() {
		return mappingButton;
	}
	public static JButton getConfigurateWindowsButton() {
		return configurateWindowsButton;
	}
	public static JButton getQuitButton() {
		return quitButton;
	}
	public static CardLayout getMyFunctionalities() {
		return myFunctionalities;
	}
	public static JPanel getFunctionalitiesPanel() {
		return functionalitiesPanel;
	}
	public static JLabel getInformationLabel() {
		return information;
	}
	public static void setInformationLabel(String companyName, String contact) {
		String selectedCompanyName = (String) Home.companyNameList.getSelectedItem();

		information.setText(companyName+"| Contact:"+ selectedCompanyName);
		information.setFont(new Font("Dialog", Font.BOLD, 15));
		information.setForeground(new Color(255, 255, 255));
	}
	public JPanel getFunctionsAndBarPanel() {
		return functionsAndBarPanel;
	}
	
}
