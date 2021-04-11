package view;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ConfigurateWindowsPanel{
	private JPanel confWindowPanel=new JPanel();
	public JPanel getJPanel() {
		// TODO Auto-generated method stub
		JLabel message=new JLabel("Partie configuration des fenêtres!");
		confWindowPanel.add(message);
		return confWindowPanel;
	}

}
