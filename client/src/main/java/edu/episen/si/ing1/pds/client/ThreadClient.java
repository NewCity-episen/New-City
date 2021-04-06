package edu.episen.si.ing1.pds.client;
import java.sql.SQLException;

import controller.Controller;
public class ThreadClient extends Thread{
   private int clientID;
   Controller cntrl;
   private String query;
	ThreadClient(int id,Controller cntrl, String query){
		clientID=id;
		this.cntrl=cntrl;
		this.query=query;
	}
	
	public void run() {
		try {
			cntrl.sendRequestToServer(query);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
