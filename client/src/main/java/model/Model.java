package model;
import java.sql.Connection;
import java.sql.SQLException;
import connectionPool.*;

public class Model {
private int maxConnection=5;// Default maximum value of connected clients.
private int numberOfClients=0;//Number of connected clients in the server.
	public Model() {
		DataSource.loadPool(maxConnection);
	}
	
	public void addClient() {
		numberOfClients++;
	}
	public void removeClient() {
		numberOfClients--;
	}
	
	public int getmaxConnection() {
		return maxConnection;
	}
	
	public void setmaxConnection(int n) {
		this.maxConnection=n;
	}
	
	public Connection retrieveConnectionPool() {
		return DataSource.getConnectionFromPool();
	}
	
	public void sendConnectionBack(Connection cnx) {
		DataSource.returnConnection(cnx);
	}
	
	public void closeAllConnections() throws SQLException {
		if(numberOfClients==0) { 
	 // We have to make sure that the pool is "full" and that there is no client left before closing the connections.
		DataSource.closePool();
		}
	}
}
