package edu.episen.si.ing1.pds.client;
import java.io.IOException;
import java.sql.SQLException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

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
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
