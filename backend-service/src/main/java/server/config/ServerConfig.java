package server.config;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;


public class ServerConfig {
	private final static Logger logger=LoggerFactory.getLogger(ServerConfig.class.getName());
	private static final String episenServerConfigEnVar="EPISEN_SRV_CONFIG";
	private final String episenServerConfigFileLocation;
	private ServerCoreConfig config;
	
	public ServerConfig() throws JsonParseException, JsonMappingException, IOException {
		episenServerConfigFileLocation= System.getenv(episenServerConfigEnVar);
		logger.debug("Config file ={}",episenServerConfigFileLocation);
		
		final ObjectMapper mapper=new ObjectMapper(new YAMLFactory());
		config= mapper.readValue(new File(episenServerConfigFileLocation), ServerCoreConfig.class);
		logger.debug("Config ={}",config.toString());
	}
	public ServerCoreConfig getConfig() {
		return config;
	}
	
	
}
