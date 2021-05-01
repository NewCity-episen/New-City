package edu.episen.si.ing1.pds.client;



import javax.swing.UIManager;

import ClientConfig.ClientConfig;
import controller.Controller;
import model.Model;
import view.View;

public class ClientGUI {
	private static final String ConfigEnVar="REQUESTS_LOCATION";
	private static String RequestsFileLocation = "";
	public static void main(String[] args) {
		setRequestsFileLocation(System.getenv(ConfigEnVar));
		View view=new View();
		Model mdl=new Model();
		
		try {
			Controller cntrl=new Controller(mdl,view);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String getRequestsFileLocation() {
		return RequestsFileLocation;
	}
	public static void setRequestsFileLocation(String requestsFileLocation) {
		RequestsFileLocation = requestsFileLocation;
	}
}
