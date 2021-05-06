package view;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JRadioButton;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class FrameToConfigurate extends JFrame implements ChangeListener {
	private static JPanel framePanel = new JPanel();
	private static JPanel winPanel =new JPanel();
	private static CardLayout winCard=new CardLayout();
	private final int MinTem=20;
	private final int MaxTem=28;
	private final int DefaultTem=24;
	private static JButton validerbtnFTC =new  JButton("Valider");
	private static JSlider slider;
	private static int selectedTem=24;
	private static ButtonGroup bg=new ButtonGroup();;

	




	public FrameToConfigurate() {
		View.getappFrame().setEnabled(false);
		
		winPanel.setLayout(winCard);
		framePanel.setLayout(null);	
		winPanel.add("Configure",framePanel);
		

		setSize(400, 400);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		JLabel lblNewLabel = new JLabel("Luminosite souhaitee :");
		lblNewLabel.setBounds(38, 36, 196, 16);
		framePanel.add(lblNewLabel);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Luminosite faible");
		rdbtnNewRadioButton.setActionCommand("Luminosite faible");
		rdbtnNewRadioButton.setBounds(189, 32, 141, 23);
		framePanel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Luminosite moyenne",true);
		rdbtnNewRadioButton_1.setActionCommand("Luminosite moyenne");
		rdbtnNewRadioButton_1.setBounds(189, 68, 183, 23);
		framePanel.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Lumineux");
		rdbtnNewRadioButton_2.setActionCommand("Lumineux");
		rdbtnNewRadioButton_2.setBounds(189, 103, 141, 23);
		framePanel.add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("Luminosite intense");
		rdbtnNewRadioButton_3.setActionCommand("Luminosite intense");
		rdbtnNewRadioButton_3.setBounds(189, 138, 183, 23);
		framePanel.add(rdbtnNewRadioButton_3);
		
		JLabel lblNewLabel_1 = new JLabel("Température souhaitee :");
		lblNewLabel_1.setBounds(38, 205, 183, 16);
		framePanel.add(lblNewLabel_1);
		
		bg.add(rdbtnNewRadioButton);
		bg.add(rdbtnNewRadioButton_1);
		bg.add(rdbtnNewRadioButton_2);
		bg.add(rdbtnNewRadioButton_3);
		
		
		slider = new JSlider(MinTem,MaxTem);
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
		
		slider.addChangeListener(this);
		
	
		
		
		validerbtnFTC.setBounds(164, 332, 117, 29);
		framePanel.add(validerbtnFTC);
		
		this.setVisible(true);
		getContentPane().add(winPanel);	
		
            addWindowListener((WindowListener) new WindowAdapter() {
			
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

				View.getappFrame().setEnabled(true);
				View.getappFrame().setVisible(true);

				}
			});
		
	}




	




	public static ButtonGroup getBg() {
		return bg;
	}




	public static void setBg(ButtonGroup bg) {
		FrameToConfigurate.bg = bg;
	}




	public static JButton getValiderbtnFTC() {
		return validerbtnFTC;
	}

	public static void setValiderbtnFTC(JButton validerbtnFTC) {
		FrameToConfigurate.validerbtnFTC = validerbtnFTC;
	}
	
	public static JSlider getSlider() {
		return slider;
	}

	public static void setSlider(JSlider slider) {
		FrameToConfigurate.slider = slider;
	}
	
	public static JPanel getJPanel() {
		// TODO Auto-generated method stub
		return winPanel;
	}
	
	
	public static int getSelectedTem() {
		return selectedTem;
	}

	public static void setSelectedTem(int selectedTem) {
		FrameToConfigurate.selectedTem = selectedTem;
	}
   


	
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
		
		JSlider source = (JSlider)e.getSource();
	    if (!source.getValueIsAdjusting()) {
	    	selectedTem = (int)source.getValue();
	        
	    }

		
	}
	
	
	
	

	
	
}
