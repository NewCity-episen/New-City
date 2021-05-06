package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

import controller.Controller;
import model.Offer;
import controller.Controller;
import shared.code.Request;
import shared.code.Response;


public class LoanOfferPanel extends JFrame{
	
	String columnHeader[] = {"Numero offre", "Type de salle", "nom", "batiment", "etage", "Prix", "Surface", "    "};
	private static JFrame loanOfferPanel = new JFrame();
			
	public LoanOfferPanel(ArrayList<Offer> list) {
		setSize(600,400);
		setVisible(true);
		setLocationRelativeTo(null);

		JLabel noResults = new JLabel("Aucun offre ne correspond a votre recherche");
		Object resultTable[][] = new Object[list.size()][columnHeader.length];
		
		if(list.isEmpty()) {
			getContentPane().add(noResults);
			return;
		} else {
			for(int i = 0; i < list.size(); i++) {
				
				resultTable[i][0] = i + 1;
				resultTable[i][1] = list.get(i).getOfferType();
				resultTable[i][2] = list.get(i).getOfferName();
				resultTable[i][3] = list.get(i).getOfferBuilding();
				resultTable[i][4] = list.get(i).getOfferFloor();
				resultTable[i][5] = "" + list.get(i).getOfferCost() + " euros";
				resultTable[i][6] = "" + list.get(i).getOfferArea() + "m^2";
				resultTable[i][7] = "reserver salle " +  list.get(i).getOfferName();
			}
			
			JTable table = new JTable(resultTable, columnHeader);
			table.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());
			table.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor(new JTextField()));
			JScrollPane pane = new JScrollPane(table);
			getContentPane().add(pane);
		}	
	}

	public static JFrame getLoanOfferPanel() {
		return loanOfferPanel;
	}

}

	

//Button renderer class
class ButtonRenderer extends JButton implements TableCellRenderer {

	public ButtonRenderer(){
		//Set button properties
		setOpaque(true);
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object obj, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		setText((obj == null) ? "":obj.toString());
		return this;
	}
}


//Button editor class

class ButtonEditor extends DefaultCellEditor {
	
	protected JButton btn;
	private String lbl;
	private Boolean clicked;
	//private String companyName = lbl.substring(15);

	public ButtonEditor(JTextField txt) {
		super(txt);
		btn = new JButton();
		btn.setOpaque(true);
		
		//Action listener
		btn.addActionListener(event -> Controller.loanButtonLoad(lbl.substring(15)));
	}
	
	@Override
	public Component getTableCellEditorComponent(JTable table, Object obj, boolean isSelected, int row, int column) {
		lbl = (obj == null) ? "":obj.toString();
		btn.setText(lbl);
		clicked = true;
		return btn;
	}
	
	@Override
	public Object getCellEditorValue() {
		if(clicked) {
			JOptionPane.showMessageDialog(btn, lbl.substring(15));
		}
		clicked = false;
		return new String(lbl);
	}
	
	@Override
	public boolean stopCellEditing() {
		clicked = false;
		return super.stopCellEditing();
	}
	
	@Override
	protected void fireEditingStopped() {
		super.fireEditingStopped();
	}
	
	public String getLabel(JButton btn) {
		return btn.getLabel();
	}
}