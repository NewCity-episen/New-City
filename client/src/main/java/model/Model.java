package model;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import connectionPool.*;
import controller.Controller;

public class Model {

private static int numberOfClients=0;//Number of connected clients in the server.
private Socket socketClient=null;
private final static Logger logger=LoggerFactory.getLogger(Model.class.getName());
	public Model() {
		
	}
	
	public Socket getSocket() {
		return socketClient;
	}
	public void setSocket(Socket socket) {
		socketClient=socket;
	}
	public synchronized void addClient() {
	
		numberOfClients++;
		
	}
	public synchronized void removeClient() {
		numberOfClients--;
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

	public int getNumberClients() {
		// TODO Auto-generated method stub
		return numberOfClients;
	}

}
