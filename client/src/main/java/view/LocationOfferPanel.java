package view;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import model.Offer;


public class LocationOfferPanel {

	private static JFrame locationOfferPanel =new JFrame();
	private JLabel noResults = new JLabel("Aucune offre ne correspond a votre recherche");
	private String[] columnNames = {"Numero offre", "nom salle"};

	public LocationOfferPanel(ArrayList<Offer> list) {
		locationOfferPanel.setVisible(true);
		locationOfferPanel.setSize(600, 400);
		locationOfferPanel.setLocationRelativeTo(null);

		if(list.isEmpty()) {
			locationOfferPanel.add(noResults);
		} else {
			Object resultTable[][] = new Object[list.size()][columnNames.length];

			for(int i = 0; i < list.size(); i++) {
				resultTable[i][0] = list.get(i).getOfferId();
				resultTable[i][1] = list.get(i).getOfferName();
			}
			
			JTable researchResults = new JTable(resultTable, columnNames);
			locationOfferPanel.add(researchResults);
		}
	}

    public static JFrame getJFrame() {
		return locationOfferPanel;
	}

}