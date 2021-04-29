package view;

import java.awt.BorderLayout;
import javax.swing.*;

public class LocationOfferPanel {
	private static JLabel searchResults=new JLabel("Ici seront les résulats des recherches");
	private static JPanel LocationOfferPanel=new JPanel();
	
	public LocationOfferPanel(){
		
		LocationOfferPanel.add(searchResults);
	}
	public static JPanel getJPanel() {
		return LocationOfferPanel;
	}
}
