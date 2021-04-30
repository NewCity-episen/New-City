package view;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.*;

public class LocationOfferPanel {



	private static JLabel searchResults=new JLabel("Ici seront les résulats des recherches");
	private static JPanel LocationOfferPanel=new JPanel();
	
	//public LocationOfferPanel(){
		
		//LocationOfferPanel.add(searchResults);

	//private static JFrame locationOfferPanel=new JFrame();
	//private JLabel noResults = new JLabel("Aucune offre ne correspond a votre recherche");
	//private String[] columnNames = {"Numero offre", "Batiment", "Etage", "Prix"};

	private static JFrame locationOfferPanel =new JFrame();
	private JLabel noResults = new JLabel("Aucune offre ne correspond a votre recherche");
	private String[] columnNames = {"Numero offre", "Batiment", "Etage", "Prix"};

	//private String[][] test = {{"arerzer", "erfer", "fsdfsfddf", "jhv"},
								//{"arerzer", "erfer", "fsdfsfddf", "jhv"},
								//{"arerzer", "erfer", "fsdfsfddf", "jhv"}};
	
	/*public LocationOfferPanel(String[][] resultsTable){
		
		if(researchResults.getColumnCount() > 0) {
			//ocationOfferPanel.add(researchResults);
		} else {
			//locationOfferPanel.add(noResults);
		}*/

	//private static JLabel searchResults =new JLabel("Ici seront les résulats des recherches");
	//private static JFrame locationOfferPanel =new JFrame();
	//JLabel noResults = new JLabel("Aucune offre ne correspond a votre recherche");

     //public LocationOfferPanel1() {
		//locationOfferPanel.setSize(600, 400);
		//locationOfferPanel.setLocationRelativeTo(null);
		//locationOfferPanel.add(noResults);
		//locationOfferPanel.setVisible(true);

	// }
      //public static JFrame getJFrame() {
		// return locationOfferPanel;
	//}

	public LocationOfferPanel(ArrayList<Map> list) {
		
		locationOfferPanel.setSize(600, 400);
		locationOfferPanel.setLocationRelativeTo(null);
		int resultTable[] = new int[list.size()];

		if(list.isEmpty()) {
			locationOfferPanel.add(noResults);
		} else {
			for(int i = 0; i < list.size(); i++) {
				//resultTable[i] = list.get(i).get("space_id");
			}
			
		//JTable researchResults = new JTable(resultsTable, columnNames);
		}
		locationOfferPanel.setVisible(true);

	}
    public static JFrame getJFrame() {
		return locationOfferPanel;
	}


}