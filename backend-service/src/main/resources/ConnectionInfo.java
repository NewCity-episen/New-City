package ressources;
import java.util.Properties;

public class ConnectionInfo {
	
	//this attribute allow us to use properties from app.properties since they're not common java attribute
	private Properties prop = PropertyLoader.load("app.properties");
	private String driverClassName;
	private String url;
	private String username;
	private String password;
	
	public ConenctionInfo() { 
		//prop.getProperty(key) return the attribute named key in the file loaded before
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
