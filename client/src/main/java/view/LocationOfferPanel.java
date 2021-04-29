package view;

import java.awt.*;
import javax.swing.*;

public class LocationOfferPanel {

	private static JLabel searchResults =new JLabel("Ici seront les résulats des recherches");
	private static JFrame locationOfferPanel =new JFrame();
	JLabel noResults = new JLabel("Aucune offre ne correspond a votre recherche");

	public LocationOfferPanel() {
		locationOfferPanel.setSize(600, 400);
		locationOfferPanel.setLocationRelativeTo(null);
		locationOfferPanel.add(noResults);
		locationOfferPanel.setVisible(true);
	}
	public static JFrame getJFrame() {
		return locationOfferPanel;
	}

}