package connectionPool;
import java.sql.*;
public class DataSource {
 static JDBCConnectionPool jdbcConnectionPool;
 
 
 DataSource(){ //Constructor
	 jdbcConnectionPool=new JDBCConnectionPool();//Charge the driveManager
	 jdbcConnectionPool.createConnectionsPool(5);//initialize number of connections in the pool
 }
 
 static Connection getConnectionFromPool() {
	 return jdbcConnectionPool.getConnectionPool();
  }
 
 static void returnConnection(Connection cnx) {
	 jdbcConnectionPool.setConnectionPool(cnx);
 }
 
 static void closePool() throws SQLException {
	 jdbcConnectionPool.closeAllConnections();
	 
  }

}
