package view;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class AdvancedFilterPanel {

	private static JFrame advancedFilterPanel =new JFrame();
	private JButton ok = new JButton("ok");
	private String test[] = {"chekcbox 1"};
	private JCheckBox boxes[];
	
	public AdvancedFilterPanel(ArrayList<String> list) {
		advancedFilterPanel.setVisible(true);
		advancedFilterPanel.setSize(600, 500);
		advancedFilterPanel.setLayout(new BorderLayout());
		advancedFilterPanel.setLocationRelativeTo(null);
		
		boxes = new JCheckBox[list.size()];
		
		for(int i = 0; i < list.size(); i++) {
			boxes[i] = new JCheckBox();
	        boxes[i].setText(list.get(i));
	        boxes[i].setSelected(false);
	        boxes[i].setVisible(true);
	        advancedFilterPanel.add(boxes[i]);
		}
		
		/*for(int i = 0; i < list.size(); i++) {
			advancedFilterPanel.add(new JCheckBox(list.get(i)));
		}*/
		/*advancedFilterPanel.add(new JCheckBox(list.get(0)));
		advancedFilterPanel.add(new JCheckBox(list.get(1)));
		advancedFilterPanel.add(new JCheckBox(list.get(2)));*/
		
		advancedFilterPanel.add("South", ok);
	}
	
    public static JFrame getJFrame() {
		return advancedFilterPanel;
	}
}
