package controller;
import view.*;
import model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Controller {
	private final static Logger logger=LoggerFactory.getLogger(Controller.class.getName());
	private Model mdl;
	private View vw;
	public Controller(Model mdl,View vw) {
		this.mdl=mdl;
		this.vw=vw;
	}
	public void buildAndHandleSQLRequest(String query,int clientID) throws SQLException {
		
		Connection cnx=mdl.retrieveConnectionPool();
		    
			if(cnx!=null) {
					mdl.addClient();
					logger.info("Client {} is using a connectionpool(ref:{})",clientID,cnx.hashCode());	
						
			}
			else {
				logger.info("Client {} failed to connect the database.No connections left.",clientID);

				return;//Exit from the method due to the failure.
			}
			
		Scanner sc = new Scanner(System.in);
		String input;
		String sqlRequest="";
		if(query.toUpperCase().equals("THREAD")){
			sqlRequest="SELECT * from persons where id="+clientID;
			query="SELECT";
		}
		else if(query.toUpperCase().equals("INSERT")) { 
		  logger.info("Type name: ");
		  input=sc.next();
		  sqlRequest="INSERT INTO persons(name,lastname) VALUES('"+input+"','";
		  logger.info("Type last name: ");
		  input=sc.next();
		  sqlRequest+=input+"')";
		}
		else if(query.toUpperCase().equals("SELECT")) {
			logger.info("Type id: ");
			input=sc.next();
			sqlRequest="SELECT * FROM persons WHERE id='"+input+"'";
		}
		else if(query.toUpperCase().equals("DELETE")) { 
			logger.info("Type id: ");
			input=sc.next();
			sqlRequest="DELETE FROM persons WHERE id='"+input+"'";
		}
		else if(query.toUpperCase().equals("UPDATE")) { 
			
			sqlRequest="UPDATE persons SET name='";
			logger.info("Type name: ");
			input=sc.next();
			sqlRequest+=input+"', lastname='";
			logger.info("Type lastname: ");
			input=sc.next();
			sqlRequest+=input+"' WHERE id=";
			logger.info("Type id: ");
			input=sc.next();
			sqlRequest+=input;
		}
		sc.close();
		handleRequest(sqlRequest,query,cnx,clientID);
	}
	public void handleRequest(String sqlRequest,String query,Connection cnx,int clientId) {
					
				try {
					String response="";//default value if not exists
					Statement stmt = cnx.createStatement();
					if(query.toUpperCase().equals("SELECT")) {
					ResultSet rs= stmt.executeQuery(sqlRequest);
					rs.next();
					int id= rs.getInt("id");
					String name=rs.getString("name");
					String lastname = rs.getString("lastname");
					response=id+"|"+name+"|"+lastname;	
					rs.close();
					}
					else { // else if insert/delete/update
						response=String.valueOf(stmt.executeUpdate(sqlRequest))+" modified lines";
					}
					stmt.close();
					mdl.sendConnectionBack(cnx);
					vw.showResponseToClient(response);
					mdl.removeClient();
					logger.info("Client {} is done with using the connectionpool(ref:{})",clientId,cnx.hashCode());
				} catch (SQLException e) {
					logger.info("Error in handleRequest:");
					e.printStackTrace();
				}
				
		      
			
	}
	public void connectToServer() {
		try {
			InetAddress ip=InetAddress.getByName("172.31.254.95");
			logger.info("Trying to connect to IP:{}",ip.getHostAddress());
			mdl.setSocket(new Socket(ip,4666));//Connect to the server
		} catch (UnknownHostException e) {
			logger.info("Unknown host:");
		} catch (IOException e) {
			logger.info("No I/O");
		}
	}
	public void sendRequestToServer() {
		
		Socket socketClient=mdl.getSocket();
		if(socketClient==null) {
			logger.info("Client not connected to server.");
		}
		else {
			try {
				PrintWriter out = new PrintWriter(socketClient.getOutputStream(), true);
				BufferedReader in=new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
                out.println("Episen");
				logger.info("Response from server:{}",in.readLine());
				out.close();
				in.close();
				socketClient.close();
				mdl.setSocket(null);
			} catch (IOException e) {
				logger.info("No I/O");
			}
		}
	}
	public void stopConnections() throws SQLException {
		mdl.closeAllConnections();
	}
	
	
	
}
