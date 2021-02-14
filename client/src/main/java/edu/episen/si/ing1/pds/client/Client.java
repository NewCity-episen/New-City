package edu.episen.si.ing1.pds.client;


import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Client {
	private final static Logger logger=LoggerFactory.getLogger(Client.class.getName());
	public static void main(String[] args) {
		  try{
		  final Options options=new Options();
		  
		  final Option clientName=Option.builder().longOpt("clientName").hasArg().argName("clientName").build();
		  options.addOption(clientName);
		  final CommandLineParser parser=new DefaultParser();
		  final CommandLine commandLine=parser.parse(options, args);
		  
		  String clientNameDefault="admin";
		  if(commandLine.hasOption("clientName")) {
			 
			  clientNameDefault= commandLine.getOptionValue("clientName");
			  
		  }
		  /*****To get the classpath.******/
		/* String s= System.getProperty("java.class.path");
		 System.out.println(s);*/ 
			logger.info("Client {} is running.",clientNameDefault);
		  }
		  catch(ParseException e) {
			  logger.info("Problems with parsing: Missing argument for option clientName");
		  }
			
		}
}
