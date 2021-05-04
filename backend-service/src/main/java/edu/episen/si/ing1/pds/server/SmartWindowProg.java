package edu.episen.si.ing1.pds.server;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.sql.Connection;  




import connectionPool.InfoConnection;
import shared.code.Request;
import shared.code.Response;
import model.*;




public class SmartWindowProg extends TimerTask {
	
	private final static Logger logger=LoggerFactory.getLogger(SmartWindowProg.class.getName());

	@Override
	public void run() {
		logger.debug("Start Prog");
		InfoConnection info = null;
		Connection cnx = null;
		try {
			info = InfoConnection.getInfo();
		} catch (IOException e) {
			logger.debug(" SmartWindow Program Failed to get connection info.");
			e.printStackTrace();
		}
		try {
			cnx = DriverManager.getConnection(info.getUrl(), info.getUsername(), info.getPassword());
		} catch (SQLException e) {
			logger.debug(" SmartWindow Program Failed to connect to database.");
			e.printStackTrace();
		}
		
		Request rqAllConfiguredWin = new Request();
		rqAllConfiguredWin.setRequestId("1001");
		rqAllConfiguredWin.setRequestOrder("SELECT");
		rqAllConfiguredWin.setRequestTable("smart_window");
		rqAllConfiguredWin.setRequestContent("{\"configured_window\": \""+true+"\"}");
		
		try {
			Response rpAllConfiguredWin = RequestHandler.handle(rqAllConfiguredWin,cnx);
			String rpAllConfiguredWinBody=rpAllConfiguredWin.getResponseBody().substring(rpAllConfiguredWin.getResponseBody().indexOf("["),
					rpAllConfiguredWin.getResponseBody().indexOf("]")+1);
			ObjectMapper allWinMapper=new ObjectMapper();
			ArrayList<SmartWindow> allSmartWin=allWinMapper.readValue(rpAllConfiguredWinBody,new TypeReference<ArrayList<SmartWindow>>(){});	
			for(SmartWindow sw : allSmartWin) {
				//Smart Window Program
				
				int tempExt;
				int tempInt;
				int sunAzimuth;
				int outdoorIlluminance;
				
				Request rqWinEqpments = new Request();
				rqWinEqpments.setRequestId("1002");
				rqWinEqpments.setRequestOrder("SELECT");
				rqWinEqpments.setRequestTable("equipment");
				rqWinEqpments.setRequestContent("{\"id_window\": \""+sw.getId_window()+"\"}");
				
				Response rpWinEqpments = RequestHandler.handle(rqWinEqpments,cnx);
				String rpWinEqpmentsBody=rpWinEqpments.getResponseBody().substring(rpWinEqpments.getResponseBody().indexOf("["),
						rpWinEqpments.getResponseBody().indexOf("]")+1);
				
				ObjectMapper winEqpmetsMapper=new ObjectMapper();
				ArrayList<Equipment> winEquipments=winEqpmetsMapper.readValue(rpWinEqpmentsBody,new TypeReference<ArrayList<Equipment>>(){});
				
				
				if(winEquipments.stream().count()==5 )
						//&& 
						//winEquipments.stream().filter(eq -> eq.getEquipment_name()=="Capteur de soleil").count()==1  && 
						//winEquipments.stream().filter(eq -> eq.getEquipment_name()=="Capteur de luminosite").count()==1 && 
						//winEquipments.stream().filter(eq -> eq.getEquipment_name()=="Capteur de temperature").count()==1 && 
						//winEquipments.stream().filter(eq -> eq.getEquipment_name()=="Capteur de temperature exterieure").count()==1 && 
						//winEquipments.stream().filter(eq -> eq.getEquipment_name()=="Capteur d'ensoleillement").count()==1) 
				{
					
					Optional<Equipment> matchingCapteurDeSoleil = winEquipments.stream().filter(eq -> eq.getEquipment_name().equals("Capteur de soleil")).findFirst();
					Equipment capteurDeSoleil=matchingCapteurDeSoleil.get();
					
					Optional<Equipment> matchingCapteurDeLuminosite = winEquipments.stream().filter(eq -> eq.getEquipment_name().equals("Capteur de luminosite")).findFirst();
					Equipment capteurDeLuminosite=matchingCapteurDeLuminosite.get();
					
					Optional<Equipment> matchingCapteurDeTempInt = winEquipments.stream().filter(eq -> eq.getEquipment_name().equals("Capteur de temperature")).findFirst();
					Equipment capteurDeTemperatureInt=matchingCapteurDeTempInt.get();
					
					Optional<Equipment> matchingCapteurDeTempExt = winEquipments.stream().filter(eq -> eq.getEquipment_name().equals("Capteur de temperature exterieure")).findFirst();
					Equipment capteurDeTempExt=matchingCapteurDeTempExt.get();
					
					
					Optional<Equipment> matchingCapteurEnsoleillement = winEquipments.stream().filter(eq -> eq.getEquipment_name().equals("Capteur d'ensoleillement")).findFirst();
					Equipment capteurEnsoleillement=matchingCapteurEnsoleillement.get();
					
					logger.debug(" Capteur de soleil : "+capteurDeSoleil.getId_equipment() +" de la SmartWindow id :"+sw.getId_window() );
					logger.debug(" Capteur de luminosite : "+capteurDeLuminosite.getId_equipment() +" de la SmartWindow id :"+sw.getId_window() );
					logger.info(" Capteur de temperature : "+capteurDeTemperatureInt.getId_equipment() +" de la SmartWindow id :"+sw.getId_window() );
					logger.info(" Capteur de temperature exterieure : "+ capteurDeTempExt.getId_equipment() +" de la SmartWindow id :"+sw.getId_window() );
					logger.info(" Capteur d'ensoleillement : "+ capteurEnsoleillement.getId_equipment() +" de la SmartWindow id :"+sw.getId_window() );
					
					
				} else {
					
					logger.debug(" SmartWindow Program Failed to find the right sensors of the window id : "+ sw.getId_window());
					continue;
				}
				
			}
			
		} catch (SQLException | JsonProcessingException e) {
			logger.debug(" SmartWindow Program Failed to parse response from database.");
			e.printStackTrace();
		} 
		
		
		
		
	}
	
	


}
