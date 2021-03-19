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

import controller.Controller;
import model.Model;
import view.View;



public class Client {
	private final static Logger logger=LoggerFactory.getLogger(Client.class.getName());
	public static void main(String[] args) throws InterruptedException {
		  try{
			    final Options options=new Options();
				final Option clientName=Option.builder().longOpt("clientName").hasArg().argName("clientName").build();
				final Option command=Option.builder().longOpt("command").hasArg().argName("command").build();
				final Option numberClient=Option.builder().longOpt("numberClient").hasArg().argName("numberClient").build();
				final Option connect=Option.builder().longOpt("connect").build();
				options.addOption(command);
				options.addOption(numberClient);
				options.addOption(clientName);
				options.addOption(connect);
				final CommandLineParser parser=new DefaultParser();
			    final CommandLine commandLine=parser.parse(options, args);
			    String clientNameDefault="admin";
			    
			    if(commandLine.hasOption("clientName")) {
			    	clientNameDefault= commandLine.getOptionValue("clientName");
				  }
			    logger.info("Client {} is running.",clientNameDefault);
			    View vw=new View();
				Model mdl=new Model();
				Controller cntrl=new Controller(mdl,vw);
				if(commandLine.hasOption("command")) {
					 cntrl.buildAndHandleSQLRequest(commandLine.getOptionValue("command"),0);
				    }
				if(commandLine.hasOption("numberClient")) {
				ThreadClient client = null;
				for(int i=1;i<=Integer.valueOf(commandLine.getOptionValue("numberClient"));i++) {
					client=new ThreadClient(i,cntrl);
					client.start();
				}
			      client.join();
				}
				if(commandLine.hasOption("connect")) {
			    	cntrl.connectToServer();
			    	cntrl.sendRequestToServer();
			    	
			    }	
				
				
		  }
		  catch(ParseException | SQLException e) {
			  logger.info("Problems with parsing: Missing argument for option clientName");
		  }
			
		}
	
}
