package edu.episen.si.ing1.pds.client;



import controller.Controller;
import model.Model;
import view.View;

public class ClientGUI {
    
	public static void main(String[] args) {
		View view=new View();
		Model mdl=new Model();
		
		try {
			Controller cntrl=new Controller(mdl,view);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
