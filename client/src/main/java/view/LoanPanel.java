package view;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoanPanel {
 
	private static JPanel loanPanel=new JPanel();
	public static JPanel getJPanel() {
		// TODO Auto-generated method stub
		JLabel message=new JLabel("Partie réservation!");
		loanPanel.add(message);
		return loanPanel;
	}

}
