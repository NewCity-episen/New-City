package connectionPool;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class JDBCConnectionPool {
 private ArrayList<Connection> pool=new ArrayList<Connection>();
 protected Properties props;
 private final static Logger logger=LoggerFactory.getLogger(JDBCConnectionPool.class.getName());
 public Properties getProps() {
		return props;
	}
 
  public void createConnectionsPool(int numberOfConnections) {
	 try {
		 
		 props=new Properties();
		 InputStream inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("app.properties");
		 props.load(inStream);
		 Class.forName(props.getProperty("database.driverClassName"));
	
		 for(int i=0;i<numberOfConnections;i++) {
			 Connection cnx =null;
			 cnx=DriverManager.getConnection(props.getProperty("database.url"),
					 props.getProperty("database.username"), props.getProperty("database.password"));
			 pool.add(cnx);
	   }
	 }
	 catch(SQLException | IOException | ClassNotFoundException e) {
		 
		 e.printStackTrace();
	 }
 }
 
  public Connection getConnectionPool() {
	  if(!pool.isEmpty()) {
		  Connection cnx= pool.get(0);
		  pool.remove(0);
		  return cnx;
	  }
	  return null;
  }
 
  public void setConnectionPool(Connection cnx) {
	 	  pool.add(cnx);
  }
  
  public void closeAllConnections()  {
	 try { 
	  
		   Iterator<Connection> iteratorPool=pool.iterator();
		   while(iteratorPool.hasNext()) {
			   Connection cnx=iteratorPool.next();
			   iteratorPool.remove();
			   cnx.close();
		   }

   }
	catch(SQLException e) {
		logger.info("Error closing connections in JDBCConnectionPool");
		e.printStackTrace();
	}	 
 }
  
  
}
 

