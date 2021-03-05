package connectionPool;
import java.sql.*;
public class DataSource {
	
  static JDBCConnectionPool jdbcConnectionPool=new JDBCConnectionPool();
  
 public static void loadPool(int numberOfConnections){
	 synchronized(jdbcConnectionPool) {
	 jdbcConnectionPool.createConnectionsPool(numberOfConnections);//Load the driverManager and initialize number of connections in the pool
  }
}
 
 public static Connection getConnectionFromPool() {
	Connection cnx=null;
	 synchronized(jdbcConnectionPool) { 
		 cnx= jdbcConnectionPool.getConnectionPool(); 
	   }
	 return cnx;
  }
 
 public static void returnConnection(Connection cnx) {
	
	 synchronized(jdbcConnectionPool) { 
		 jdbcConnectionPool.setConnectionPool(cnx);	 
	   }	 
 }
 
 public static void closePool() throws SQLException {
	 synchronized(jdbcConnectionPool) {
		 jdbcConnectionPool.closeAllConnections();
	   }
	 
  }

}
