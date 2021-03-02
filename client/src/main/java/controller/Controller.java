package controller;
import view.*;
import model.*;
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
	public void buildAndHandleSQLRequest(String query,String clientName) throws SQLException {
		
		Connection cnx=mdl.retrieveConnectionPool();
		
			if(cnx!=null) {
					logger.info("Client {} connected to the database.",clientName);
			}
			else {
				logger.info("Failed to connect the database.");
				mdl.sendConnectionBack(cnx);
				mdl.closeAllConnections();
				return;//Exit from the method due to the failure.
			}
			
		Scanner sc = new Scanner(System.in);
		String input;
		String sqlRequest="";
		if(query.toUpperCase().equals("INSERT")) { 
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
		handleRequest(sqlRequest,query,cnx);
	}
	public void handleRequest(String sqlRequest,String query,Connection cnx) {
					
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
				} catch (SQLException e) {
					logger.info("Error in handleRequest:");
					e.printStackTrace();
				}
				
		      
			
	}
	public void stopConnections() throws SQLException {
		mdl.closeAllConnections();
	}
	
}
