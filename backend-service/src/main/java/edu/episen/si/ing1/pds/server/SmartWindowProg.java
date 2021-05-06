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

import connectionPool.DataSource;
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
			if (allSmartWin.size()!=0) {
			   for(SmartWindow sw : allSmartWin) {
				//Smart Window Program
				
				int tempExt;
				int tempInt;
				int sunAzimuth;
				int outdoorIlluminance;
				int teintOfGlass;
				int levelOfBlind;
				
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
				
				
				if(winEquipments.stream().count()==4 && 
				   winEquipments.stream().filter(eq -> eq.getEquipment_name().equals("Capteur de soleil")).count()==1  &&  
				   winEquipments.stream().filter(eq -> eq.getEquipment_name().equals("Capteur de temperature")).count()==1 && 
				   winEquipments.stream().filter(eq -> eq.getEquipment_name().equals("Capteur de temperature exterieure")).count()==1 && 
				   winEquipments.stream().filter(eq -> eq.getEquipment_name().equals("Capteur d'ensoleillement")).count()==1) { 
					
								Optional<Equipment> matchingSunAzimuthSensor = winEquipments.stream().filter(eq -> eq.getEquipment_name().equals("Capteur de soleil")).findFirst();
								Equipment sunAzimuthSensor=matchingSunAzimuthSensor.get();
								
								
								Optional<Equipment> matchingTempIntSensor = winEquipments.stream().filter(eq -> eq.getEquipment_name().equals("Capteur de temperature")).findFirst();
								Equipment tempIntSensor=matchingTempIntSensor.get();
								
								Optional<Equipment> matchingTempExtSensor = winEquipments.stream().filter(eq -> eq.getEquipment_name().equals("Capteur de temperature exterieure")).findFirst();
								Equipment tempExtSensor=matchingTempExtSensor.get();
								
								
								Optional<Equipment> matchingOutdoorIlluminanceSensor = winEquipments.stream().filter(eq -> eq.getEquipment_name().equals("Capteur d'ensoleillement")).findFirst();
								Equipment outdoorIlluminanceSensor=matchingOutdoorIlluminanceSensor.get();
								
								
								Request rqSunAzimuthValue = new Request();
								rqSunAzimuthValue.setRequestId("1003");
								rqSunAzimuthValue.setRequestOrder("SELECT");
								rqSunAzimuthValue.setRequestTable("readings");
								rqSunAzimuthValue.setRequestContent("{\"id_equipment\": \""+sunAzimuthSensor.getId_equipment()+"\"}");					
								Response rpSunAzimuthValue = RequestHandler.handle(rqSunAzimuthValue,cnx);
								String rpSunAzimuthValueBody=rpSunAzimuthValue.getResponseBody().substring(rpSunAzimuthValue.getResponseBody().indexOf("["),
										rpSunAzimuthValue.getResponseBody().indexOf("]")+1);				
								ObjectMapper sunAzimuthMapper=new ObjectMapper();					
								Readings[] sunAzimuReadings=sunAzimuthMapper.readValue(rpSunAzimuthValueBody,Readings[].class);
					
					
								Request rqTempIntValue = new Request();
								rqTempIntValue.setRequestId("1004");
								rqTempIntValue.setRequestOrder("SELECT");
								rqTempIntValue.setRequestTable("readings");
								rqTempIntValue.setRequestContent("{\"id_equipment\": \""+tempIntSensor.getId_equipment()+"\"}");					
								Response rpTempIntValue = RequestHandler.handle(rqTempIntValue,cnx);
								String rpTempIntValueBody=rpTempIntValue.getResponseBody().substring(rpTempIntValue.getResponseBody().indexOf("["),
										rpTempIntValue.getResponseBody().indexOf("]")+1);
								ObjectMapper tempIntMapper=new ObjectMapper();					
								Readings[] tempIntReadings =tempIntMapper.readValue(rpTempIntValueBody,Readings[].class);
								
								
								
								Request rqTempExtValue = new Request();
								rqTempExtValue.setRequestId("1006");
								rqTempExtValue.setRequestOrder("SELECT");
								rqTempExtValue.setRequestTable("readings");
								rqTempExtValue.setRequestContent("{\"id_equipment\": \""+tempExtSensor.getId_equipment()+"\"}");					
								Response rpTempExtValue = RequestHandler.handle(rqTempExtValue,cnx);
								String rpTempExtValueBody=rpTempExtValue.getResponseBody().substring(rpTempExtValue.getResponseBody().indexOf("["),
										rpTempExtValue.getResponseBody().indexOf("]")+1);
								ObjectMapper tempExtMapper=new ObjectMapper();					
								Readings[] tempExtReadings =tempExtMapper.readValue(rpTempExtValueBody,Readings[].class);
					
					
					
								Request rqOutdoorIlluminanceValue = new Request();
								rqOutdoorIlluminanceValue .setRequestId("1007");
								rqOutdoorIlluminanceValue .setRequestOrder("SELECT");
								rqOutdoorIlluminanceValue .setRequestTable("readings");
								rqOutdoorIlluminanceValue .setRequestContent("{\"id_equipment\": \""+outdoorIlluminanceSensor.getId_equipment()+"\"}");					
								Response rpOutdoorIlluminanceValue = RequestHandler.handle(rqOutdoorIlluminanceValue ,cnx);
								String rpOutdoorIlluminanceValueBody=rpOutdoorIlluminanceValue.getResponseBody().substring(rpOutdoorIlluminanceValue.getResponseBody().indexOf("["),
										rpOutdoorIlluminanceValue.getResponseBody().indexOf("]")+1);
								ObjectMapper outdoorIlluminanceMapper=new ObjectMapper();					
								Readings[] outdoorIlluminanceReadings =outdoorIlluminanceMapper.readValue(rpOutdoorIlluminanceValueBody,Readings[].class);
					
								if ( outdoorIlluminanceReadings.length!=0 &&  
									 tempExtReadings.length!=0 && 
									 tempIntReadings.length!=0 && 
									 sunAzimuReadings.length!=0) {
						
						
													tempExt=tempExtReadings[0].getValue();
													tempInt=tempIntReadings[0].getValue();
													sunAzimuth=sunAzimuReadings[0].getValue();
													outdoorIlluminance=outdoorIlluminanceReadings[0].getValue();

													if(tempInt >= sw.getPreferredtem())	{
														if (tempExt <= sw.getPreferredtem()) {
															if (outdoorIlluminance<35000) {
																sw.setLevel_of_blind(0);
																sw.setTeint_of_glass(0);
															}else {
																if(Math.abs(sw.getWindow_orientation()-sunAzimuth)<=45){
																		if(sw.getPreferredlum().equals("Luminosite intense")) {
																			sw.setLevel_of_blind(0);
																			sw.setTeint_of_glass(25);
																		 }else if(sw.getPreferredlum().equals("Lumineux")) {
																			sw.setLevel_of_blind(0);
																			sw.setTeint_of_glass(50);
																		 }else if(sw.getPreferredlum().equals("Luminosite moyenne")) {
																			sw.setLevel_of_blind(0);
																			sw.setTeint_of_glass(50);
																		 }else if(sw.getPreferredlum().equals("Luminosite faible")) {
																			sw.setLevel_of_blind(0);
																			sw.setTeint_of_glass(100);
																		 }
																
																} else {
																	    if ((sw.getPreferredlum().equals("Luminosite intense"))){
																	    	sw.setLevel_of_blind(0);
																		    sw.setTeint_of_glass(0);																	    	  
																	     }else if ((sw.getPreferredlum().equals("Lumineux"))) {																	    	  
																	    	sw.setLevel_of_blind(0);
																		    sw.setTeint_of_glass(25);																	    	  
																	     }else if ((sw.getPreferredlum().equals("Luminosite moyenne"))) {																	    	  
																	    	sw.setLevel_of_blind(0);
																		    sw.setTeint_of_glass(50);																		      
																	     }else if ((sw.getPreferredlum().equals("Luminosite faible"))) {																	    	  
																	    	sw.setLevel_of_blind(0);
																		    sw.setTeint_of_glass(100);																		      
																	      }
															           }
															       }	
														 }else {
														    if (outdoorIlluminance<10000) {
														    	  sw.setLevel_of_blind(0);
															      sw.setTeint_of_glass(0);	  
														      }else if(outdoorIlluminance<35000 && outdoorIlluminance>=10000) {
														    	  sw.setLevel_of_blind(25);
															      sw.setTeint_of_glass(25);  
														      }else if(outdoorIlluminance>=35000) {
														    	  if(Math.abs(sw.getWindow_orientation()-sunAzimuth)<=45){
																		if(sw.getPreferredlum().equals("Luminosite intense")) {
																			sw.setLevel_of_blind(25);
																			sw.setTeint_of_glass(50);
																		 }else if(sw.getPreferredlum().equals("Lumineux")) {
																			sw.setLevel_of_blind(50);
																			sw.setTeint_of_glass(50);
																		 }else if(sw.getPreferredlum().equals("Luminosite moyenne")) {
																			sw.setLevel_of_blind(50);
																			sw.setTeint_of_glass(50);
																		 }else if(sw.getPreferredlum().equals("Luminosite faible")) {
																			sw.setLevel_of_blind(75);
																			sw.setTeint_of_glass(100);
																		 }
																
																  } else {
																	    if ((sw.getPreferredlum().equals("Luminosite intense"))){
																	    	sw.setLevel_of_blind(25);
																		    sw.setTeint_of_glass(25);																	    	  
																	     }else if ((sw.getPreferredlum().equals("Lumineux"))) {																	    	  
																	    	sw.setLevel_of_blind(25);
																		    sw.setTeint_of_glass(50);																	    	  
																	     }else if ((sw.getPreferredlum().equals("Luminosite moyenne"))) {																	    	  
																	    	sw.setLevel_of_blind(25);
																		    sw.setTeint_of_glass(50);																		      
																	     }else if ((sw.getPreferredlum().equals("Luminosite faible"))) {																	    	  
																	    	sw.setLevel_of_blind(50);
																		    sw.setTeint_of_glass(100);																		      
																	      }
															       }
														      }
														 }			
												     }
													else {
															//Tint<Tpref
														if (outdoorIlluminance<35000) {
															sw.setLevel_of_blind(0);
															sw.setTeint_of_glass(0);
														}else {
															if(Math.abs(sw.getWindow_orientation()-sunAzimuth)<=45){
																	if(sw.getPreferredlum().equals("Luminosite intense")) {
																		sw.setLevel_of_blind(0);
																		sw.setTeint_of_glass(25);
																	 }else if(sw.getPreferredlum().equals("Lumineux")) {
																		sw.setLevel_of_blind(0);
																		sw.setTeint_of_glass(50);
																	 }else if(sw.getPreferredlum().equals("Luminosite moyenne")) {
																		sw.setLevel_of_blind(0);
																		sw.setTeint_of_glass(50);
																	 }else if(sw.getPreferredlum().equals("Luminosite faible")) {
																		sw.setLevel_of_blind(0);
																		sw.setTeint_of_glass(100);
																	 }
															
															} else {
																    if ((sw.getPreferredlum().equals("Luminosite intense"))){
																    	sw.setLevel_of_blind(0);
																	    sw.setTeint_of_glass(0);																	    	  
																     }else if ((sw.getPreferredlum().equals("Lumineux"))) {																	    	  
																    	sw.setLevel_of_blind(0);
																	    sw.setTeint_of_glass(25);																	    	  
																     }else if ((sw.getPreferredlum().equals("Luminosite moyenne"))) {																	    	  
																    	sw.setLevel_of_blind(0);
																	    sw.setTeint_of_glass(50);																		      
																     }else if ((sw.getPreferredlum().equals("Luminosite faible"))) {																	    	  
																    	sw.setLevel_of_blind(0);
																	    sw.setTeint_of_glass(100);																		      
																      }
														           }
														  }
														
						                          }
									//UPDATE DATABASE
													
									Request rqSmartWindowCfg = new Request();
								    rqSmartWindowCfg.setRequestId("2001");
									rqSmartWindowCfg.setRequestOrder("UPDATE_SMARTWINDOW_CFG");
									rqSmartWindowCfg.setRequestTable("smart_window");
									rqSmartWindowCfg.setRequestContent("{\"id_window\": \""+sw.getId_window()+"\", \"level_of_blind\": \""+sw.getLevel_of_blind()+"\", \"teint_of_glass\": \""+sw.getTeint_of_glass()+"\", \"configured_window\": \""+true+"\", \"preferredlum\": \""+sw.getPreferredlum()+ "\", \"preferredtem\": \""+sw.getPreferredtem()+"\"}");
									Response rpSmartWindowCfg = RequestHandler.handle(rqSmartWindowCfg ,cnx);
									logger.debug(" SmartWindow finished the configuration update of the smartwindow id : "+ sw.getId_window());
					
								   }else {									
									logger.debug(" missing Equipments'readings in Database. Check Equipment ids  : "+sunAzimuthSensor.getId_equipment() + " , " +tempExtSensor.getId_equipment() + " , "+tempIntSensor.getId_equipment() + " , "+outdoorIlluminanceSensor.getId_equipment());
									continue;
								     }
					
			        }else {
				        logger.debug(" SmartWindow Program Failed to find the right sensors of the window id : "+ sw.getId_window());
					    continue;
				   }
			
				
				
				
			  }
			
		      } else {
		    	  logger.debug(" no smartwindow to configure");
		      }
				
		   } catch (SQLException | JsonProcessingException e) {
			logger.debug(" SmartWindow Program Failed to parse response from database.");
			e.printStackTrace();
		} finally {
			try {
				cnx.close();
			} catch (SQLException e) {
				logger.debug(" SmartWindow Program failed to close connection to the database");
				e.printStackTrace();
			}	
		}
	}
}
