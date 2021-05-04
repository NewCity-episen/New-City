package edu.episen.si.ing1.pds.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.sql.SQLException;
import java.util.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import connectionPool.DataSource;
import server.config.ServerConfig;

public class ServerCore {

	private ServerSocket serverSocket;
	private final static Logger logger=LoggerFactory.getLogger(ServerCore.class.getName());
	private ClientRequestManager clientRequestManager;
	
	public ServerCore(final ServerConfig config, int maxConnectionValue) throws IOException {
		serverSocket = new ServerSocket(config.getConfig().getListenPort());
		serverSocket.setSoTimeout(config.getConfig().getSoTimeOut());
		DataSource.loadPool(maxConnectionValue);
	}
	
	public void serve() throws IOException, InterruptedException, SQLException {
		try {
			
			Timer timer = new Timer();
			  SmartWindowProg tS=new SmartWindowProg();
			  logger.debug("time to run SW Prog");
			  timer.scheduleAtFixedRate(tS, 0, 60000);
			while(true) {
			logger.debug("Waiting for clients to connect...");
			final Socket socket=serverSocket.accept();
			logger.debug("Ok, got a requester {}",socket.hashCode());
			clientRequestManager=new ClientRequestManager(socket,DataSource.getConnectionFromPool());
			Thread.sleep(10);
			}
		} catch(SocketTimeoutException e) {
			logger.debug("Ok, got a timeout!");
			clientRequestManager.join();
		}
		finally {
			serverSocket.close();	
			DataSource.closePool();
		}
	}
	
}
