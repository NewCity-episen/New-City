package connectionPool;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
public class JDBCConnectionPool {
 private ArrayList<Connection> pool=new ArrayList<Connection>();
 protected Properties props;

 public Properties getProps() {
		return this.props;
	}
 
  public void createConnectionsPool(int numberOfConnections) {
	 try {
		 props=new Properties();
		 InputStream inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("app.properties");
		 props.load(inStream);
		 Class.forName(props.getProperty("database.driverClassName"));
		 
		 for(int i=0;i<numberOfConnections;i++) {
			 Connection cnx =null;
			 cnx=DriverManager.getConnection(props.getProperty("database.url"), props.getProperty("database.userName"), props.getProperty("database.password"));
		     pool.add(cnx);

	   }
	 }
	 catch(Exception e) {}
 }
  
  
  public Connection getConnectionPool() {
	  Connection cnx= pool.get(0);
	  pool.remove(0);
	  return cnx;
  }
  
  
  public void setConnectionPool(Connection cnx) {
	  pool.add(cnx);
	  
  }
  
  public void closeAllConnections() throws SQLException {
	  
	  for(Connection cnx: pool) {
		  cnx.close();
	  }
   }
	 
 }
  
 

