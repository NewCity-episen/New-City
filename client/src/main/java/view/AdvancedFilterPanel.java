package view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import model.Equipment;

public class AdvancedFilterPanel {

	private static JFrame advancedFilterPanel =new JFrame();
	private static JButton okEquipmentButton = new JButton("Valider");
	private JCheckBox boxes[];
	private static HashMap<String, HashMap<Object, Object>> selectedEquipments = new HashMap<>();
	
	public AdvancedFilterPanel(ArrayList<Equipment> list) {
		advancedFilterPanel.setVisible(true);
		advancedFilterPanel.setSize(600, 700);
		advancedFilterPanel.setLocationRelativeTo(null);
		boxes = new JCheckBox[list.size()];
		advancedFilterPanel.setLayout(null);

		for(int i = 0; i < list.size(); i++) {
			boxes[i] = new JCheckBox();
	        boxes[i].setText((String)(list.get(i).getEquipment_name()));
	        boxes[i].setSelected(false);
	        boxes[i].setVisible(true);
	        advancedFilterPanel.add(boxes[i]);
	        boxes[i].setBounds(200, 20*(i+1), 200, 200);
		}
		advancedFilterPanel.add(okEquipmentButton);
		okEquipmentButton.setBounds(300, 550, 130, 40);
	}
	
	public static JButton getOkEquipmentButton() {
		return okEquipmentButton;
	}
	
	public static HashMap<String, HashMap<Object, Object>> getSelectedEquipment() {
		return selectedEquipments;
	}
    public static JFrame getJFrame() {
		return advancedFilterPanel;
	}
}
