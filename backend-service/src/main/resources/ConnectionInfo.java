package ressources;
import java.util.Properties;

public class ConnectionInfo {
	
	private Properties prop = PropertyLoader.load("app.properties");
	private String driverClassName;
	private String url;
	private String username;
	private String password;
	
	public ConenctionInfo() { 
		driverClassNamer = prop.getProperty(database.driverClassName);
		url = prop.getProperty(database.url);
		username = prop.getProperty(database.postgres);
		password = prop.getProperty(database.password);
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

	public string toString() {
		String s = "" + driverClassName + url + username + password;
		return s
	}
}
