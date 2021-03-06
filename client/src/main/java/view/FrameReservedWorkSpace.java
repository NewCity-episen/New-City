package view;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class FrameReservedWorkSpace extends JFrame{
	
	private JButton ButtonMapping=new JButton("Mapper l'espace");
	private static boolean opened=false;
	public FrameReservedWorkSpace() {
		setOpened(true);
		View.getappFrame().setEnabled(false); //Must be enabled false to not let user click on the "main" frame but has to be enable TRUE when user finish with the opened frame
		setSize(150, 250);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		addWindowListener((WindowListener) new WindowAdapter() {
			
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				setOpened(false);
				if(!MappingPanel.isOpened()) {
				View.getappFrame().setEnabled(true);
				View.getappFrame().setVisible(true);
					}
				}
			});
		this.add(ButtonMapping);
		this.setVisible(true);
	}
	public JButton getButtonMapping() {
		return ButtonMapping;
	}
	public static boolean isOpened() {
		return opened;
	}
	public static void setOpened(boolean opened1) {
		opened = opened1;
	}
	
}
