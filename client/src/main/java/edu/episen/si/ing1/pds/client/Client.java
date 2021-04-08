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
				final Option numberClient=Option.builder().longOpt("numberClient").hasArg().argName("numberClient").build();
				final Option insert=Option.builder().longOpt("insert").build();
				final Option select=Option.builder().longOpt("select").build();
				final Option delete =Option.builder().longOpt("delete").build();
				//final Option delete=Option.builder().longOpt("delete").build();
				String query=null;
				options.addOption(numberClient);
				options.addOption(clientName);
				options.addOption(insert);
			    options.addOption(select);
			    options.addOption(delete);
				final CommandLineParser parser=new DefaultParser();
			    final CommandLine commandLine=parser.parse(options, args);
			    String clientNameDefault="admin";
			    
			    if(commandLine.hasOption("clientName")) {
			    	clientNameDefault= commandLine.getOptionValue("clientName");
				  }
			    if(commandLine.hasOption("insert")) {
			    	query="insert";
			    }
			    if(commandLine.hasOption("select")) {
			    	query="select";
			    }
			    if(commandLine.hasOption("delete")) {
			    	query="delete";
			    }
			    if(commandLine.hasOption("update")) {
			    	query="update";
			    }
			    logger.info("Client {} is running.",clientNameDefault);
			    View vw=new View();
				Model mdl=new Model();
				Controller cntrl=new Controller(mdl,vw);
				
				 if(commandLine.hasOption("numberClient")) {
					 ThreadClient client = null;
						for(int i=1;i<=Integer.valueOf(commandLine.getOptionValue("numberClient"));i++) {
							client=new ThreadClient(i,cntrl,query);
							client.start();
						}
				 }

				
				
		  }
		  catch(ParseException e) {
			  logger.info("Problems with parsing: Missing argument for option clientName");
		  }
			
		}
	
}
