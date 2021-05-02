package edu.episen.si.ing1.pds.server;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.sql.Connection;  




import connectionPool.InfoConnection;
import shared.code.Request;
import shared.code.Response;





public class SmartWindowProg extends TimerTask {
	
	private final static Logger logger=LoggerFactory.getLogger(SmartWindowProg.class.getName());

	@Override
	public void run() {
		InfoConnection info = null;
		Connection cnx = null;
		try {
			info = InfoConnection.getInfo();
		} catch (IOException e) {
			logger.info(" SmartWindow Program Failed to get a connection info.");
			e.printStackTrace();
		}
		try {
			cnx = DriverManager.getConnection(info.getUrl(), info.getUsername(), info.getPassword());
		} catch (SQLException e) {
			logger.info(" SmartWindow Program Failed to connect to database.");
			e.printStackTrace();
		}
		
		Request rqAllConfiguredWin = new Request();
		rqAllConfiguredWin.setRequestId("1001");
		rqAllConfiguredWin.setRequestOrder("SELECT");
		rqAllConfiguredWin.setRequestTable("smart_window");
		rqAllConfiguredWin.setRequestContent("{\"configured\": \""+true+"\"}");
		
		try {
			Response rpAllConfiguredWin = RequestHandler.handle(rqAllConfiguredWin,cnx);
			String rpAllConfiguredWinBody=rpAllConfiguredWin.getResponseBody().substring(rpAllConfiguredWin.getResponseBody().indexOf("["),
					rpAllConfiguredWin.getResponseBody().indexOf("]")+1);
			//ObjectMapper allWinMapper=new ObjectMapper();
			//ArrayList<SmartWindow> allSmartWin=allWinMapper.readValue(rpAllConfiguredWinBody,new TypeReference<ArrayList<SmartWindow>>(){});
			//System.out.println("all windows are loaded");
			
			//program code to add
			
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	


}
