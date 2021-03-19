package edu.episen.si.ing1.pds.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import server.config.ServerConfig;

public class ServerCore {

	private ServerSocket serverSocket;
	private final static Logger logger=LoggerFactory.getLogger(ServerCore.class.getName());
	private ClientRequestManager clientRequestManager;
	
	public ServerCore(final ServerConfig config) throws IOException {
		serverSocket = new ServerSocket(config.getConfig().getListenPort());
		serverSocket.setSoTimeout(config.getConfig().getSoTimeOut());
	}
	
	public void serve() throws IOException, InterruptedException {
		try {
			while(true) {
			logger.debug("Waiting for clients to connect...");
			final Socket socket=serverSocket.accept();
			logger.debug("Ok, got a requester.");
			clientRequestManager=new ClientRequestManager(socket);
			}
		} catch(SocketTimeoutException e) {
			logger.debug("Ok, got a timeout!");
			clientRequestManager.join();
		}
		finally {
			serverSocket.close();	
		}
	}
	
}
