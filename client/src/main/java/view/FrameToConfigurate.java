package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JSlider;

public class FrameToConfigurate extends JFrame {
private JSlider slider_temp = new JSlider();
private JSlider slider_light = new JSlider();	
	
public FrameToConfigurate() {
	
	
	setSize(400, 400);
	setVisible(true);
	setLocationRelativeTo(null);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	this.add(slider_light);
	this.setVisible(true);
	addWindowListener((WindowListener) new WindowAdapter() {
		
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			View.getappFrame().setEnabled(true);
			View.getappFrame().setVisible(true);
			}
		});
	
	
	
}
}
