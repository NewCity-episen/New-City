package view;

import java.awt.CardLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JList;
import javax.swing.JScrollPane;

import model.SmartWindow;

import javax.swing.DefaultListModel;
import javax.swing.JButton;


public class ConfigurateWindowsPanel{
	
private static JPanel configurateWindowJPanel =new JPanel();
private static CardLayout configurateWindowsCard=new CardLayout();	
private static JPanel configuratePanel =new JPanel();	
private static JButton retourBtn=new JButton("retour");	
private static JButton configureBtn=new JButton ("configurer");	
private static JButton winAddBtn=new JButton (">>>");
private static JButton winRmvBtn = new JButton("<<<");
private static JButton statusBtn = new JButton("statut");
private static JButton resetBtn = new JButton("reset");
private static DefaultListModel<SmartWindow> configuredWinmodel= new DefaultListModel<SmartWindow>();
private static DefaultListModel<SmartWindow> winToCnfgmodel= new DefaultListModel<SmartWindow>();
private static DefaultListModel<SmartWindow> winToCnfgSelmodel= new DefaultListModel<SmartWindow>();
private static JList<SmartWindow>  configuredWinList;
private static JList<SmartWindow> winToCnfgList ;
private static JList<SmartWindow> winToCnfgSelList;





	public ConfigurateWindowsPanel() {
		// TODO Auto-generated constructor stub
		
		
		
		configurateWindowJPanel.setLayout(configurateWindowsCard);
		configuratePanel.setLayout(null);	
		configurateWindowJPanel.add("Configure",configuratePanel);		
		
		JLabel lblNewLabel = new JLabel("Les fenêtres configurées");
		lblNewLabel.setBounds(417, 16, 164, 16);
		configuratePanel.add(lblNewLabel);
		
		configuredWinList = new JList<SmartWindow>(configuredWinmodel);
		JScrollPane configuredWinJScrollPane = new JScrollPane(configuredWinList);
		configuredWinJScrollPane.setBounds(350, 50, 300, 191);
		configuratePanel.add(configuredWinJScrollPane);		
		configuredWinJScrollPane.setViewportView(configuredWinList);
				
		
		statusBtn.setBounds(510, 253, 115, 29);
		configuratePanel.add(statusBtn);
		
		resetBtn.setBounds(375, 253, 115, 29);
		configuratePanel.add(resetBtn);
		
		JLabel lblNewLabel_1 = new JLabel("Les fenêtres à configurer");
		lblNewLabel_1.setBounds(153, 282, 169, 16);
		configuratePanel.add(lblNewLabel_1);
		
		winToCnfgList = new JList<SmartWindow>(winToCnfgmodel);
		JScrollPane winToCnfgJScrollPane = new JScrollPane(winToCnfgList);
		winToCnfgJScrollPane.setBounds(90, 310, 300, 190);
		configuratePanel.add(winToCnfgJScrollPane);		
		winToCnfgJScrollPane.setViewportView(winToCnfgList);
		
		winToCnfgSelList= new JList<SmartWindow>(winToCnfgSelmodel);
		JScrollPane winToCnfgSelJScrollPane = new JScrollPane(winToCnfgSelList);
		winToCnfgSelJScrollPane.setBounds(610, 310, 300, 190);
		configuratePanel.add(winToCnfgSelJScrollPane);
		winToCnfgSelJScrollPane.setViewportView(winToCnfgSelList);
				
		JLabel lblNewLabel_2 = new JLabel("Selection");
		lblNewLabel_2.setBounds(720, 282, 84, 16);
		configuratePanel.add(lblNewLabel_2);
		

		winAddBtn.setBounds(458, 351, 80, 30);
		configuratePanel.add(winAddBtn);
		
		
		winRmvBtn.setBounds(458, 427, 80, 30);
		configuratePanel.add(winRmvBtn);
		
		retourBtn.setBounds(375, 525, 115, 29);
		configuratePanel.add(retourBtn);
		
		
		configureBtn.setBounds(510, 525, 115, 29);
		configuratePanel.add(configureBtn);
		
	}
		
	public static JButton getConfigureBtn() {
		return configureBtn;
	}

	public static void setConfigureBtn(JButton configureBtn) {
		ConfigurateWindowsPanel.configureBtn = configureBtn;
	}

	public static void show() {
		configurateWindowsCard.show(configurateWindowJPanel, "Configurate");
		
	}	
	public static JPanel getJPanel() {
		// TODO Auto-generated method stub
		return configurateWindowJPanel;
	}
	

	public static DefaultListModel<SmartWindow> getWinToCnfgmodel() {
		return winToCnfgmodel;
	}

	public static void setWinToCnfgmodel(DefaultListModel<SmartWindow> winToCnfgmodel) {
		ConfigurateWindowsPanel.winToCnfgmodel = winToCnfgmodel;
	}
	public static DefaultListModel<SmartWindow> getWinToCnfgSelmodel() {
		return winToCnfgSelmodel;
	}

	public static void setWinToCnfgSelmodel(DefaultListModel<SmartWindow> winToCnfgSelmodel) {
		ConfigurateWindowsPanel.winToCnfgSelmodel = winToCnfgSelmodel;
	}

	public static JButton getRetourBtn() {
		return retourBtn;
	}

	public static JButton getWinRmvBtn() {
		return winRmvBtn;
	}

	public static void setWinRmvBtn(JButton winRmvBtn) {
		ConfigurateWindowsPanel.winRmvBtn = winRmvBtn;
	}

	public static DefaultListModel<SmartWindow> getConfiguredWinmodel() {
		return configuredWinmodel;
	}

	public static void setConfiguredWinmodel(DefaultListModel<SmartWindow> configuredWinmodel) {
		ConfigurateWindowsPanel.configuredWinmodel = configuredWinmodel;
	}

	public static void setRetourBtn(JButton retourBtn) {
		ConfigurateWindowsPanel.retourBtn = retourBtn;
	}
	public static JButton getWinAddBtn() {
		return winAddBtn;
	}

	public static void setWinAddBtn(JButton winAddBtn) {
		ConfigurateWindowsPanel.winAddBtn = winAddBtn;
	}
	public static JList<SmartWindow> getConfiguredWinList() {
		return configuredWinList;
	}

	public static void setConfiguredWinList(JList<SmartWindow> configuredWinList) {
		ConfigurateWindowsPanel.configuredWinList = configuredWinList;
	}

	public static JList<SmartWindow> getWinToCnfgList() {
		return winToCnfgList;
	}

	public static void setWinToCnfgList(JList<SmartWindow> winToCnfgList) {
		ConfigurateWindowsPanel.winToCnfgList = winToCnfgList;
	}

	public static JList<SmartWindow> getWinToCnfgSelList() {
		return winToCnfgSelList;
	}

	public static void setWinToCnfgSelList(JList<SmartWindow> winToCnfgSelList) {
		ConfigurateWindowsPanel.winToCnfgSelList = winToCnfgSelList;
	}
	public static JButton getStatusBtn() {
		return statusBtn;
	}

	public static void setStatusBtn(JButton statusBtn) {
		ConfigurateWindowsPanel.statusBtn = statusBtn;
	}

	public static JButton getResetBtn() {
		return resetBtn;
	}

	public static void setResetBtn(JButton resetBtn) {
		ConfigurateWindowsPanel.resetBtn = resetBtn;
	}


}
	
	
	
	

