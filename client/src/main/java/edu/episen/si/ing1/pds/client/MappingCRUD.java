package edu.episen.si.ing1.pds.client;

import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import ClientConfig.ClientConfig;
import controller.Controller;

public class MappingCRUD {
	private final static Logger logger=LoggerFactory.getLogger(Client.class.getName());
	 public static ClientConfig clientConfig;
	public static void main(String[] args) throws ParseException, JsonParseException, JsonMappingException, InterruptedException, IOException {
		
		/* REQUEST_NAME: UNMAP_WORKSPACE  ( THE USER HAS TO GIVE : --id_work_space= ??  )
		 * REQUESTS TO DO IN THE SERVER : 
		 * 
		//update spot set id_equipment=null, taken=false where id_work_space=?
		//update material_needs set installed=false where id_work_space=?
		//update equipment set id_window=null where id_equipment= ? (a loop with select id_equipment from spot where id_work_space=??)
		// update work_space set configurable=false where id_work_space=??
		 * 
		 * */
		/*REQUEST_NAME: STATE_EQUIPMENT_FALSE*  ( THE USER HAS TO GIVE : --id_spot= ??)
		 * 
		 * REQUESTS TO DO IN THE SERVER : 
		 *
		 * update material_needs set state=false where id_equipment= ?? ( this will be done with select id_equipment from spot where id_spot=??)
		 * 
		 * */
		final Options options=new Options();
		final Option request=Option.builder().longOpt("request").hasArg().argName("request").build();
		final Option id_spot=Option.builder().longOpt("idSpot").hasArg().argName("idSpot").build();
		final Option id_work_space=Option.builder().longOpt("idWorkSpace").hasArg().argName("idWorkSpace").build();
		options.addOption(request);
		options.addOption(id_work_space);
		options.addOption(id_spot);
		final CommandLineParser parser=new DefaultParser();
	    final CommandLine commandLine=parser.parse(options, args);
		logger.info("Script for mapping is running..");
		if(commandLine.hasOption("request")&&commandLine.hasOption("idWorkSpace")) {
			Controller.sendRequestToServer(commandLine.getOptionValue("request")+".json",
					"{\"id_work_space\": \""+commandLine.getOptionValue("idWorkSpace")+"\"}");
			
		}
		else if (commandLine.hasOption("request")&&commandLine.hasOption("idSpot")) {
			Controller.sendRequestToServer(commandLine.getOptionValue("request")+".json",
					"{\"id_spot\": \""+commandLine.getOptionValue("idSpot")+"\"}");
		}
		else {
			logger.info("Missing argument! Please try again.");
		}
		
		
	}
}
