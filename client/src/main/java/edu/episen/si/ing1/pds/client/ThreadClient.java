package edu.episen.si.ing1.pds.client;
import java.sql.SQLException;

import controller.Controller;
public class ThreadClient extends Thread{
   private int clientID;
   Controller cntrl;
	ThreadClient(int id,Controller cntrl){
		clientID=id;
		this.cntrl=cntrl;
	}
	
	public void run() {
		try {
			cntrl.buildAndHandleSQLRequest("THREAD", clientID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
