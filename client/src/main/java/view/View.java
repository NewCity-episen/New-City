package view;


import java.awt.CardLayout;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class View{


	private final static Logger logger=LoggerFactory.getLogger(View.class.getName());
	private static int WIDTH=1000;
	private static int HEIGHT=650;
	private Home home=new Home();
	//private AdvancedFiltrePanel advancedFiltrePanel=new AdvancedFiltrePanel();
	private static JFrame appFrame=new JFrame("Interface");
	final JLayeredPane layeredPane=new JLayeredPane();
	FunctionalitiesBarAndPanel funcBarAndPanelFrame=new FunctionalitiesBarAndPanel();
	final CardLayout myCardPanels=new CardLayout(0,0);

	public View() {
	
		
		appFrame.setResizable(false);
		appFrame.setVisible(true);
		appFrame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		appFrame.pack();
		appFrame.setLocationRelativeTo(null);
		appFrame.getContentPane().setLayout(null);
		layeredPane.setBounds(0,0,WIDTH,HEIGHT);
		appFrame.getContentPane().add(layeredPane);
		layeredPane.setLayout(myCardPanels);
		layeredPane.add("functions",funcBarAndPanelFrame.getFunctionsAndBarPanel());
		layeredPane.add("Home",home.getJPanel());//Just an example: will be modified later
		myCardPanels.show(layeredPane, "Home");
		
	}
	public static JFrame getappFrame() {
		return appFrame;
	}
	public static int getWIDTH() {
		return WIDTH;
	}
	public static int getHEIGHT() {
		return HEIGHT;
	}
	public JLayeredPane getLayeredPanel() {
		return layeredPane;
	}
	public CardLayout getMyCardPanels() {
		return myCardPanels;
	}
}
