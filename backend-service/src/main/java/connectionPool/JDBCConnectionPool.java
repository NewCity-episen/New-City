package connectionPool;
import java.sql.*;
import java.util.ArrayList;
public class JDBCConnectionPool {
 private ArrayList<Connection> pool=new ArrayList<Connection>();
 
 String url="";
 
 JDBCConnectionPool(){ //Constructor
	 try {
		 Class.forName("org.postgresql.Driver");
		 
	 }
	 catch(Exception e) {}
 }
 
  public void createConnectionsPool(int numberOfConnections) {
	 try {
		 for(int i=0;i<numberOfConnections;i++) {
		 Connection cnx=DriverManager.getConnection(url,"user","password");
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
  
 

