package view;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Offer;


public class LocationOfferPanel {

	private static JFrame locationOfferPanel =new JFrame();
	private JLabel noResults = new JLabel("Aucune offre ne correspond a votre recherche");
	private String[] columnNames = {"Numero offre", "Type de salle", "nom", "batiment", "etage", "Prix", "Surface" };

	public LocationOfferPanel(ArrayList<Offer> list) {
		locationOfferPanel.setVisible(true);
		locationOfferPanel.setSize(600, 400);
		locationOfferPanel.setLocationRelativeTo(null);

		if(list.isEmpty()) {
			locationOfferPanel.add(noResults);
		} else {
			Object resultTable[][] = new Object[list.size() + 1][columnNames.length];

			resultTable[0][0] = "Numero offre";
			resultTable[0][1] = "Type de salle";
			resultTable[0][2] = "nom";
			resultTable[0][3] = "batiment"; 
			resultTable[0][4] = "etage";
			resultTable[0][5] = "Prix";
			resultTable[0][6] = "Surface";
			
			for(int i = 0; i < list.size(); i++) {
				resultTable[i + 1][0] = i + 1;
				resultTable[i + 1][1] = list.get(i).getOfferType();
				resultTable[i + 1][2] = list.get(i).getOfferName();
				resultTable[i + 1][3] = list.get(i).getOfferBuilding();
				resultTable[i + 1][4] = list.get(i).getOfferFloor();
				resultTable[i + 1][5] = "" + list.get(i).getOfferCost() + " euros";
				resultTable[i + 1][6] = list.get(i).getOfferArea();
			}

			JTable researchResults = new JTable(resultTable, columnNames);
			locationOfferPanel.add(new JScrollPane(researchResults));
			locationOfferPanel.add(researchResults);
		}
	}

    public static JFrame getJFrame() {
		return locationOfferPanel;
	}

}