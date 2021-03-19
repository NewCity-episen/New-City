package edu.episen.si.ing1.pds.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ClientRequestManager implements Runnable {
	private final static Logger logger=LoggerFactory.getLogger(ClientRequestManager.class.getName());
	private final InputStream inputStream;
	private final OutputStream outputStream;
	private Thread self;
	private final static String name="client-thread-manager";
	
	public ClientRequestManager(final Socket socket) throws IOException {
		// TODO Auto-generated constructor stub
		inputStream=socket.getInputStream();
		outputStream=socket.getOutputStream();
		self=new Thread(this, name);
		self.start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		byte [] inputData;
		try {
			inputData=new byte[inputStream.available()];
			inputStream.read(inputData);
			logger.debug("Data received {} bytes, content={}",inputData.length,new String(inputData));
			PrintWriter out = new PrintWriter(outputStream, true);
			out.println("Well received.");
			/*WILL REDEFINE LATER
			final ObjectMapper mapper= new ObjectMapper();
			final Map<String,String> result=new HashMap();
			result.put("result", "success");
			outputStream.write(mapper.writeValueAsBytes(result));*/
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void join() throws InterruptedException {
		// TODO Auto-generated method stub
		self.join();
	}

}
