package ClientConfig;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;


public class ClientConfig {
	private final static Logger logger=LoggerFactory.getLogger(ClientConfig.class.getName());
	private static final String episenClientConfigEnVar="EPISEN_CLIENT_CONFIG";
	private final String ClientConfigFileLocation;
	private ClientCoreConfig config;
	
	public ClientConfig() throws JsonParseException, JsonMappingException, IOException {
		ClientConfigFileLocation= System.getenv(episenClientConfigEnVar);
		logger.debug("Config file ={}",episenClientConfigEnVar);	
		final ObjectMapper mapper=new ObjectMapper(new YAMLFactory());	
		config= mapper.readValue(new File(ClientConfigFileLocation), ClientCoreConfig.class);
		logger.debug("Config ={}",config.toString());
	}
	public ClientCoreConfig getConfig() {
		
		return config;
	}
	

}
