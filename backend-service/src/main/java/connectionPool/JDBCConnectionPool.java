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
	
	private final static Logger logger=LoggerFactory.getLogger(JDBCConnectionPool.class.getName());
	private ArrayList<Connection> pool=new ArrayList<Connection>();

 	public void createConnectionsPool(int numberOfConnections) {
 		try {
	 		InfoConnection info = InfoConnection.getINFO();
	 		
			for(int i=0;i<numberOfConnections;i++) {
				Connection cnx =null;
				cnx = DriverManager.getConnection(info.getUrl()), info.getUsername()), info.getPasswor());
				pool.add(cnx);
			}
		}
 		catch(SQLException | IOException | ClassNotFoundException e) {
			logger.info("Failed to get a connection from database.");
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

  public static void main(String[] args) {
	  System.out.print("+++");
  }
  
}
 

