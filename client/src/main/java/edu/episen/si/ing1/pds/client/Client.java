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
	public static void main(String[] args) {
		  try{
			    final Options options=new Options();
				final Option clientName=Option.builder().longOpt("clientName").hasArg().argName("clientName").build();
				final Option command=Option.builder().longOpt("command").hasArg().argName("command").build();
				options.addOption(command);
				options.addOption(clientName);
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
					 cntrl.buildAndHandleSQLRequest(commandLine.getOptionValue("command"),clientNameDefault);
				    }
					
				cntrl.stopConnections();
		  }
		  catch(ParseException | SQLException e) {
			  logger.info("Problems with parsing: Missing argument for option clientName");
		  }
			
		}
	
}
