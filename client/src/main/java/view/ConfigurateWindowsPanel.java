package view;

import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import model.Building;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;


public class ConfigurateWindowsPanel{
	
private static JPanel configurateWindowJPanel =new JPanel();
private static CardLayout configurateWindowsCard=new CardLayout();	
private static JPanel configuratePanel =new JPanel();	
	
	
	public ConfigurateWindowsPanel() {
		// TODO Auto-generated constructor stub
		
		
		
		configurateWindowJPanel.setLayout(configurateWindowsCard);
		configuratePanel.setLayout(null);	
		configurateWindowJPanel.add("Configure",configuratePanel);
		
		JScrollPane configuredWinJScrollPane = new JScrollPane();
		configuredWinJScrollPane.setBounds(350, 50, 300, 191);
		configuratePanel.add(configuredWinJScrollPane);
		
		JList configuredWinList = new JList();
		configuredWinJScrollPane.setViewportView(configuredWinList);
		
		JLabel lblNewLabel = new JLabel("Les fenêtres configurées");
		lblNewLabel.setBounds(417, 16, 164, 16);
		configuratePanel.add(lblNewLabel);
		
		JButton retourBtn = new JButton("retour");
		retourBtn.setBounds(375, 525, 115, 29);
		configuratePanel.add(retourBtn);
		
		JButton configureBtn = new JButton("configurer");
		configureBtn.setBounds(510, 525, 115, 29);
		configuratePanel.add(configureBtn);
		
		JButton statusBtn = new JButton("statut");
		statusBtn.setBounds(445, 253, 117, 29);
		configuratePanel.add(statusBtn);
		
		JScrollPane winToCnfgJScrollPane = new JScrollPane();
		winToCnfgJScrollPane.setBounds(90, 310, 300, 190);
		configuratePanel.add(winToCnfgJScrollPane);
		
		JList winToCnfgList = new JList();
		winToCnfgJScrollPane.setViewportView(winToCnfgList);
		
		JScrollPane winToCnfgSelJScrollPane = new JScrollPane();
		winToCnfgSelJScrollPane.setBounds(610, 310, 300, 190);
		configuratePanel.add(winToCnfgSelJScrollPane);
		
		JList winToCnfgSelList = new JList();
		winToCnfgSelJScrollPane.setViewportView(winToCnfgSelList);
		
		JLabel lblNewLabel_1 = new JLabel("Les fenêtres à configurer");
		lblNewLabel_1.setBounds(153, 282, 169, 16);
		configuratePanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Selection");
		lblNewLabel_2.setBounds(720, 282, 84, 16);
		configuratePanel.add(lblNewLabel_2);
		
		JButton winAddBtn = new JButton(">>>");
		winAddBtn.setBounds(458, 351, 80, 30);
		configuratePanel.add(winAddBtn);
		
		JButton winRmvBtn = new JButton("<<<");
		winRmvBtn.setBounds(458, 427, 80, 30);
		configuratePanel.add(winRmvBtn);
		
	}
		
	public static void show() {
		configurateWindowsCard.show(configurateWindowJPanel, "ConfigurateWindows");
		
	}	
	public static JPanel getJPanel() {
		// TODO Auto-generated method stub
		return configurateWindowJPanel;
	}	
}
	
	
	
	

