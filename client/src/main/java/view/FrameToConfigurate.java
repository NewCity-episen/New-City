package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JRadioButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class FrameToConfigurate extends JFrame {
	private static JPanel framePanel;
	private final int MinTem=20;
	private final int MaxTem=28;
	private final int DefaultTem=24;

	
	

	public FrameToConfigurate() {
		View.getappFrame().setEnabled(false);
		framePanel = new JPanel();
		framePanel.setLayout(null);
		setSize(400, 400);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		JLabel lblNewLabel = new JLabel("Luminosite souhaitee :");
		lblNewLabel.setBounds(38, 36, 196, 16);
		framePanel.add(lblNewLabel);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Luminosite faible");
		rdbtnNewRadioButton.setBounds(189, 32, 141, 23);
		framePanel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Luminosite moyenne");
		rdbtnNewRadioButton_1.setBounds(189, 68, 183, 23);
		framePanel.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Lumineux");
		rdbtnNewRadioButton_2.setBounds(189, 103, 141, 23);
		framePanel.add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("Luminosite intense");
		rdbtnNewRadioButton_3.setBounds(189, 138, 183, 23);
		framePanel.add(rdbtnNewRadioButton_3);
		
		JLabel lblNewLabel_1 = new JLabel("Température souhaitee :");
		lblNewLabel_1.setBounds(38, 205, 183, 16);
		framePanel.add(lblNewLabel_1);
		
		ButtonGroup bg=new ButtonGroup();
		bg.add(rdbtnNewRadioButton);
		bg.add(rdbtnNewRadioButton_1);
		bg.add(rdbtnNewRadioButton_2);
		bg.add(rdbtnNewRadioButton_3);
		
		
		JSlider slider = new JSlider(MinTem,MaxTem,DefaultTem);
		slider.setBounds(138, 233, 190, 63);
		framePanel.add(slider);
		slider.setPaintTicks(true);
		slider.setMinorTickSpacing(1);
		slider.setPaintLabels(true);
		// Add positions label in the slider
		Hashtable position = new Hashtable();
		position.put(20, new JLabel("20"));
		position.put(22, new JLabel("22"));
		position.put(24, new JLabel("24"));
		position.put(26, new JLabel("26"));
		position.put(28, new JLabel("28"));
		         
		// Set the label to be drawn
		slider.setLabelTable(position); 
		
		
		JButton btnNewButton = new JButton("annuler");
		btnNewButton.setBounds(73, 330, 117, 29);
		framePanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("valider");
		btnNewButton_1.setBounds(195, 330, 117, 29);
		framePanel.add(btnNewButton_1);
		
		this.setVisible(true);
		getContentPane().add(framePanel);	
		
            addWindowListener((WindowListener) new WindowAdapter() {
			
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

				View.getappFrame().setEnabled(true);
				View.getappFrame().setVisible(true);

				}
			});
		
	}
	
	
}
