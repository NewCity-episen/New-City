package model;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Model {

private static int numberOfClients=0;//Number of connected clients in the server.

private final static Logger logger=LoggerFactory.getLogger(Model.class.getName());
	public Model() {
		
	}

	public synchronized void addClient() {
	
		numberOfClients++;
		
	}
	public synchronized void removeClient() {
		numberOfClients--;
	}

	public int getNumberClients() {
		// TODO Auto-generated method stub
		return numberOfClients;
	}

}
