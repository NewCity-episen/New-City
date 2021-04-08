package edu.episen.si.ing1.pds.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import connectionPool.DataSource;
import shared.code.Request;
import shared.code.Response;

public class ClientRequestManager implements Runnable {
	private final static Logger logger=LoggerFactory.getLogger(ClientRequestManager.class.getName());
	private final InputStream inputStream;
	private final OutputStream outputStream;
	private String name;
	private Thread self;
	private Connection cnx;
	
	public ClientRequestManager(final Socket socket, Connection cnx) throws IOException {
		// TODO Auto-generated constructor stub
		inputStream=socket.getInputStream();
		outputStream=socket.getOutputStream();
		this.cnx=cnx;
		name="Ref"+socket.hashCode();
		self=new Thread(this, name);
		self.start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {	
			DataOutputStream out= new DataOutputStream(outputStream);
			Response response=null;
			final ObjectMapper mapper= new ObjectMapper();
			if(cnx==null) {
				response=new Response("-1","\"message\": \"No connections left: Please try again later!\" ");
				out.write(mapper.writeValueAsBytes(response),0,mapper.writeValueAsBytes(response).length);
			   }
			else {
				while(inputStream.available()==0) {
					Thread.sleep(0); /* The thread sleeps until there is data in the inputStream
											sent by the client*/						
				}
			byte [] inputData=new byte[inputStream.available()];
			inputStream.read(inputData);
			String rq=new String(inputData);
			logger.debug("Data received {} bytes from client{}, content={}",inputData.length,self.getName(),rq);
			Request clientRequest= mapper.readValue(rq, Request.class);
			response=RequestHandler.handle(clientRequest,cnx);
			DataSource.returnConnection(cnx);
			out.write(mapper.writeValueAsBytes(response),0,mapper.writeValueAsBytes(response).length);
			logger.debug("Connection terminated with client{}",name);
		 }
			out.close();
			inputStream.close();
			outputStream.close();
		}catch(IOException | InterruptedException | SQLException e) {
			e.printStackTrace();
		}
	
}
	public void join() throws InterruptedException {
		// TODO Auto-generated method stub
		self.join();
	}

}
