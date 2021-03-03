package connectionPool;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;


public class InfoConnection {
	
	private final static org.slf4j.Logger logger=LoggerFactory.getLogger(InfoConnection.class.getName());
	InputStream inStream;
	
	private String driverClassName;
	private String url;
	private String username;
	private String password;
	protected Properties props = new Properties();
	private String propFileName = "app.properties";
	
	public Properties getProps() {
		return props;
	}
	
	
	public InfoConnection() throws IOException { 

		try {
			inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("app.properties");
			props.load(inStream);
			
			driverClassName = props.getProperty("database.driverClassName");
			url = props.getProperty("database.url");
			username = props.getProperty("database.username");
			password = props.getProperty("database.password");
	
		}
		catch(Exception e) {
			logger.info("Failed to get a information to connect to the database.");
			e.printStackTrace();
		}
	}
	

	public String getDriverClassName() {
		return driverClassName;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getUsername() {
		return username;
	}
	
	
	public String getPassword() {
		return password;
	}
}