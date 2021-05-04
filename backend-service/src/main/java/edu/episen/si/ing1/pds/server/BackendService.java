package edu.episen.si.ing1.pds.server;

import org.apache.commons.cli.Options;

import java.awt.desktop.ScreenSleepEvent;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import connectionPool.DataSource;
import server.config.ServerConfig;



public class BackendService {
private final static Logger logger=LoggerFactory.getLogger(BackendService.class.getName());
 public static ServerConfig serverConfig;
	public static void main(String[] args) {
	  try{
		  
	  final Options options=new Options();
	  final Option testMode = Option.builder().longOpt("testMode").build();
	  final Option serverMode = Option.builder().longOpt("server").build();
	  final Option maxConnection=Option.builder().longOpt("maxConnection").hasArg().argName("maxConnection").build();
	  options.addOption(testMode);
	  options.addOption(maxConnection);
	  options.addOption(serverMode);
	  final CommandLineParser parser=new DefaultParser();
	  final CommandLine commandLine=parser.parse(options, args);
	  boolean inTestMode=false; //default value
	  int maxConnectionValue=10;// default value
	  if(commandLine.hasOption("testMode")) {
		  inTestMode=true;
	  }
	  
	  if(commandLine.hasOption("maxConnection")) {
		  maxConnectionValue= Integer.parseInt(commandLine.getOptionValue("maxConnection"));
		  
	  }
		  logger.info("Backend service is running. (testMode={}), maxConnection={}.",inTestMode,maxConnectionValue);
		  serverConfig= new ServerConfig();
		  new ServerCore(serverConfig, maxConnectionValue).serve();
		  
		  
		  
		  
	      
	  }
	  catch(Exception e) {
		  e.printStackTrace();
	  }
		
	}
}
