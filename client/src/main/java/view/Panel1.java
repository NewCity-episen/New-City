package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panel1{
	static JButton okButton=new JButton("OK");
	private static JPanel panel=new JPanel();
	public Panel1() {
		
		panel.add(okButton);
	}
	public static JButton getOkButton() {
		return okButton;
	}
	public JPanel getPanel() {
		return panel;
	}
}
