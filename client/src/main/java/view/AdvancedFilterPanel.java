package view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import model.Equipment;
import controller.Controller;
import model.LoanCondition;

public class AdvancedFilterPanel {

	private static JFrame advancedFilterPanel =new JFrame();
	private static JButton okEquipmentButton = new JButton("Valider");
	private static JCheckBox boxes[];
	private static HashMap<JCheckBox, Equipment> equipmentsMap = new HashMap<>();
	public static ArrayList<Object> equipmentToInsert = new ArrayList<>();
	
	public AdvancedFilterPanel(ArrayList<Equipment> list) {
		
		
		advancedFilterPanel.setVisible(true);
		advancedFilterPanel.setSize(600, 700);
		advancedFilterPanel.setLocationRelativeTo(null);
		boxes = new JCheckBox[list.size()];
		advancedFilterPanel.setLayout(null);

		LoanCondition.setEquipmentCost(0);

		for(int i = 0; i < list.size(); i++) {
			String text = (String)(list.get(i).getEquipment_name());
			boxes[i] = new JCheckBox(text);
	        boxes[i].setSelected(false);
	        boxes[i].setVisible(true);
	        advancedFilterPanel.add(boxes[i]);
	        boxes[i].setBounds(200, 40*(i+1), 200, 40);
	        equipmentsMap.put(boxes[i], list.get(i));
		}
		advancedFilterPanel.add(okEquipmentButton);
		okEquipmentButton.setBounds(300, 550, 130, 60);
	}
	
	public static JButton getOkEquipmentButton() {
		return okEquipmentButton;
	}
	
	public static HashMap<JCheckBox, Equipment> getEquipmentsMap() {
		return equipmentsMap;
	}
	public static JCheckBox[] getBoxes() {
		return boxes;
	}
	
	public static ArrayList<Object> getEquipmentToInsert() {
		return equipmentToInsert;
	}
	
	public static void setEquipmentToInsert() {
		for(int i = 0; i < getBoxes().length; i++) {
			if(getBoxes()[i].isSelected()) {
				equipmentToInsert.add(getEquipmentsMap().get(getBoxes()[i]));
			}
		}
	}
	
    public static JFrame getJFrame() {
		return advancedFilterPanel;
	}
}
