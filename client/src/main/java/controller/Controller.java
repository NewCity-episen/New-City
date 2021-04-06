package controller;
import view.*;
import model.*;
import shared.code.Request;
import shared.code.StudentConfig;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;



public class Controller {
	private final static Logger logger=LoggerFactory.getLogger(Controller.class.getName());
	private Model mdl;
	private View vw;
	public Controller(Model mdl,View vw) {
		this.mdl=mdl;
		this.vw=vw;
	}
	public Socket connectToServer() {
		try {
			/*InetAddress ip=InetAddress.getByName("172.31.254.95");
			logger.info("Trying to connect to IP:{}",ip.getHostAddress());*/
			InetAddress ip=InetAddress.getByName("localhost");
			return new Socket(ip,4666);//Connect to the server
		} catch (UnknownHostException e) {
			logger.info("Unknown host:");
		} catch (IOException e) {
			logger.info("No I/O");
		}
		return null;
		
	}
	public void sendRequestToServer(String query) throws InterruptedException {
		
		Socket socketClient=connectToServer();
		if(socketClient==null) {
			logger.info("Client not connected to server.");
		}
		else {
			try {
			
				BufferedReader in=new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
                final ObjectMapper jsonMapper= new ObjectMapper();

                final ObjectMapper yamlMapper= new ObjectMapper(new YAMLFactory());
                Request rq = null;
        
                if(query.equals("select")) {
                	rq=jsonMapper.readValue(new File("C:\\Users\\user16\\new-city\\client\\src\\main\\resources\\select-request.json")
							, Request.class);
                }
                else if(query.equals("insert")) {
                	rq=jsonMapper.readValue(new File("C:\\Users\\user16\\new-city\\client\\src\\main\\resources\\insert-request.json")
							, Request.class);
                	logger.info(query);
                	StudentConfig students=yamlMapper.readValue(new File("C:\\Users\\user16\\new-city\\client\\src\\main\\resources\\students-to-be-inserted.yaml")
                											,StudentConfig.class);
                	logger.info(query);
                	rq.setRequestContent(jsonMapper.writeValueAsString(students));
                }
                else if(query.equals("delete")) {
                	
                }
                else if(query.equals("update")) {
                	
                }
                
                logger.info("Request from client : {}",jsonMapper.writeValueAsString(rq));
                DataOutputStream out= new DataOutputStream(socketClient.getOutputStream());
                out.write(jsonMapper.writeValueAsBytes(rq),0,jsonMapper.writeValueAsBytes(rq).length);//Sending the json file converted as a String to the server
                InputStream inputStream=socketClient.getInputStream();
                while(inputStream.available()==0) {
					Thread.sleep(0); /* The thread sleeps until there is data in the inputStream
											sent by the client*/						
				}
                byte [] inputData=new byte[inputStream.available()];
                inputStream.read(inputData);
                String response=new String(inputData);
                logger.debug("Data received {} bytes from server, content={}",inputData.length,response);
				out.close();
				in.close();
				socketClient.close();
			} catch (IOException e) {
				logger.info("No I/O");
				e.printStackTrace();
			}
		}
	}

}
