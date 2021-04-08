package controller;
import view.*;
import model.*;
import shared.code.Request;
import shared.code.Response;
import shared.code.StudentConfig;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import ClientConfig.ClientConfig;





public class Controller {
	private static final String ConfigEnVar="REQUESTS_LOCATION";
	private final String RequestsFileLocation;
	private final static Logger logger=LoggerFactory.getLogger(Controller.class.getName());
	private Model mdl;
	private View vw;
	public static ClientConfig clientconfig;
	
	public Controller(Model mdl,View vw) throws JsonParseException, JsonMappingException, IOException {
		this.mdl=mdl;
		this.vw=vw;
		RequestsFileLocation= System.getenv(ConfigEnVar);
		
	}
	
	
	public Socket connectToServer()  {
		
		
		try {
			
			clientconfig= new ClientConfig();
			/*InetAddress ip=InetAddress.getByName("172.31.254.95");
			logger.info("Trying to connect to IP:{}",ip.getHostAddress());*/
			InetAddress ip=InetAddress.getByName("localhost");
			return new Socket(ip , 4666);//Connect to the server
		} catch (UnknownHostException e) {
			logger.info("Unknown host:");
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("No I/O");
		}
		return null;
		
	}
	public void sendRequestToServer(String query) throws InterruptedException, JsonParseException, JsonMappingException, IOException {
		
		Socket socketClient=connectToServer();
		if(socketClient==null) {
			logger.info("Client not connected to server.");
		}
		else {
			try {
                final ObjectMapper jsonMapper= new ObjectMapper();
                final ObjectMapper yamlMapper= new ObjectMapper(new YAMLFactory());
                Request rq = null;
        
                if(query.equals("select")) {
                	rq=jsonMapper.readValue(new File(RequestsFileLocation+"/select-request.json")
							, Request.class);
                }
                else if(query.equals("insert")) {
                	rq=jsonMapper.readValue(new File(RequestsFileLocation+"/insert-request.json")
							, Request.class);
                	StudentConfig students=yamlMapper.readValue(new File(RequestsFileLocation+"/students-to-be-inserted.yaml")
                											,StudentConfig.class);
                	rq.setRequestContent(jsonMapper.writeValueAsString(students));
                	
                }
                else if(query.equals("delete")) {
                	rq=jsonMapper.readValue(new File(RequestsFileLocation+"/delete-request.json"), Request.class);
                	StudentConfig students=yamlMapper.readValue(new File(RequestsFileLocation+"/students-to-be-deleted.yaml")
							,StudentConfig.class);
                    rq.setRequestContent(jsonMapper.writeValueAsString(students));

             
                }
                else if(query.equals("update")) {
                	
                }
                
                logger.info("Request from client : {}",jsonMapper.writeValueAsString(rq));
                DataOutputStream out= new DataOutputStream(socketClient.getOutputStream());
                out.write(jsonMapper.writeValueAsBytes(rq),0,jsonMapper.writeValueAsBytes(rq).length);//Sending the json file converted as bytes to the server
                InputStream inputStream=socketClient.getInputStream();
                while(inputStream.available()==0) {
					Thread.sleep(0); /* The thread sleeps until there is data in the inputStream
											sent by the server*/						
				}
                byte [] inputData=new byte[inputStream.available()];
                inputStream.read(inputData);
                Response response= jsonMapper.readValue(new String(inputData), Response.class);
                logger.debug("Data received {} bytes from server, content={}",inputData.length,jsonMapper.writeValueAsString(response));
                
				out.close();
				inputStream.close();
				socketClient.close();
			} catch (IOException e) {
				logger.info("No I/O");
				e.printStackTrace();
			}
		}
	}

}
