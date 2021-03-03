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

	
	public InfoConnection() throws IOException { 

		try {
			// send a request to get properties from file app.properties
			inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(propFileName);
			props.load(inStream);
			
			// the methode getProperty(String key) return the property associated to this key in the file app.properties
			//props can call this method because of the props.load(inStream) at line 27
			
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
	
	public static void main(String[] args) throws IOException {
		
		InfoConnection test = new InfoConnection();
		
		System.out.print(test.getDriverClassName() + "          " + test.getPassword() + "          " + test.getUrl() + "          "+ "          "+ test.getUsername());
	}
}