package view;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MappingPanel{
	private JPanel mappingPanel=new JPanel();
	public JPanel getJPanel() {
		// TODO Auto-generated method stub
		JLabel message=new JLabel("Partie mapping!");
		mappingPanel.add(message);
		return mappingPanel;
	}

}
