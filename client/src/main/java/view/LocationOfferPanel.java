package view;

import java.awt.*;
import javax.swing.*;

public class LocationOfferPanel {

<<<<<<< HEAD
	private static JLabel searchResults=new JLabel("Ici seront les résulats des recherches");
	private static JPanel LocationOfferPanel=new JPanel();
	
	public LocationOfferPanel(){
		
		LocationOfferPanel.add(searchResults);

	private static JFrame locationOfferPanel=new JFrame();
	private JLabel noResults = new JLabel("Aucune offre ne correspond a votre recherche");
	private String[] columnNames = {"Numero offre", "Batiment", "Etage", "Prix"};
	private String[][] test = {{"arerzer", "erfer", "fsdfsfddf", "jhv"},
								{"arerzer", "erfer", "fsdfsfddf", "jhv"},
								{"arerzer", "erfer", "fsdfsfddf", "jhv"}};
	
	
	public LocationOfferPanel(String[][] resultsTable){
		private JTable researchResults = new JTable(resultsTable, columnNames);
		locationOfferPanel.setSize(150, 250);
		locationOfferPanel.setLocationRelativeTo(null);
		if(researchResults.getColumnCount() > 0) {
			ocationOfferPanel.add(researchResults);
		} else {
			locationOfferPanel.add(noResults);
		}
		
		locationOfferPanel.setVisible(true);

=======
	private static JLabel searchResults =new JLabel("Ici seront les résulats des recherches");
	private static JFrame locationOfferPanel =new JFrame();
	JLabel noResults = new JLabel("Aucune offre ne correspond a votre recherche");

	public LocationOfferPanel() {
		locationOfferPanel.setSize(600, 400);
		locationOfferPanel.setLocationRelativeTo(null);
		locationOfferPanel.add(noResults);
		locationOfferPanel.setVisible(true);
>>>>>>> fa99d5b129ab5a08fc05184c8d415977c48610c1
	}
    public static JFrame getJFrame() {
		 return locationOfferPanel;
	}

}