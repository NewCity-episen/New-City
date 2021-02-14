package edu.episen.si.ing1.pds.server;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class BackendService {
private final static Logger logger=LoggerFactory.getLogger(BackendService.class.getName());
	public static void main(String[] args) {
	  try{final Options options=new Options();
	  final Option testMode = Option.builder().longOpt("testMode").build();
	  final Option maxConnection=Option.builder().longOpt("maxConnection").hasArg().argName("maxConnection").build();
	  options.addOption(testMode);
	  options.addOption(maxConnection);
	  final CommandLineParser parser=new DefaultParser();
	  final CommandLine commandLine=parser.parse(options, args);
	  boolean inTestMode=false;
	  int maxConnectionValue=10;// default value
	  if(commandLine.hasOption("testMode")) {
		  inTestMode=true;
	  }
	  if(commandLine.hasOption("maxConnection")) {
		  inTestMode=true;
		  maxConnectionValue= Integer.parseInt(commandLine.getOptionValue("maxConnection"));
		  
	  }
	 
		logger.info("Backend service is running. (testMode={}), maxConnection={}.",inTestMode,maxConnectionValue);
	  }
	  catch(Exception e) {
		  logger.info("Problems with parsing: Missing argument for option maxConnection");
	  }
		
	}
}
