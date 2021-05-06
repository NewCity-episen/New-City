package edu.episen.si.ing1.pds.client;
import java.sql.SQLException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ClientConfig.ClientConfig;
import controller.Controller;
import model.Model;
import shared.code.Response;
import view.View;



public class Client {
	private final static Logger logger=LoggerFactory.getLogger(Client.class.getName());
	 public static ClientConfig clientConfig;
	public static void main(String[] args) throws InterruptedException {
		  try{
			    final Options options=new Options();
				final Option clientName=Option.builder().longOpt("clientName").hasArg().argName("clientName").build();
				final Option runApp= Option.builder().longOpt("runApp").build();
				final Option request=Option.builder().longOpt("request").hasArg().argName("request").build();
				final Option id_spot=Option.builder().longOpt("idSpot").hasArg().argName("idSpot").build();
				options.addOption(runApp);
				options.addOption(request);
				options.addOption(id_spot);
				options.addOption(clientName);
				final CommandLineParser parser=new DefaultParser();
			    final CommandLine commandLine=parser.parse(options, args);
			    String clientNameDefault="admin";
			    if(commandLine.hasOption("clientName")) {
			    	clientNameDefault= commandLine.getOptionValue("clientName");
				  }
			    logger.info("Client {} is running.",clientNameDefault);
			    if(commandLine.hasOption("runApp")) {
			    	View vw=new View();
					Model mdl=new Model();
					Controller cntrl=new Controller(mdl,vw);
			    	
			    }
			    else if (commandLine.hasOption("request")&&commandLine.hasOption("idSpot")) {
					Response response = Controller.sendRequestToServer(commandLine.getOptionValue("request")+".json",
							"{\"id_spot\": \""+commandLine.getOptionValue("idSpot")+"\"}");
				}
				else {
					logger.info("Missing argument! Try: --runApp to launch the application or other options..");
				}	
		  }
		  catch(Exception e) {
			  e.printStackTrace();
		  }
			
		}
	
}
