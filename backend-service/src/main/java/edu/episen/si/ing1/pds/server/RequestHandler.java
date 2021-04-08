package edu.episen.si.ing1.pds.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import shared.code.Request;
import shared.code.Response;
import shared.code.Student;
import shared.code.StudentConfig;



public class RequestHandler {
	private final static Logger logger=LoggerFactory.getLogger(RequestHandler.class.getName());
	public static Response handle(Request request, Connection cnx) throws JsonMappingException, JsonProcessingException, SQLException {
		// TODO Auto-generated method stub
		String requestOrder=request.getRequestOrder();
	    ArrayList<Student> studentsList=extractValuesFromRequestBody(request.getRequestBody());
		String sqlRequest="";
		String responseBody=null;
		Statement stmt = cnx.createStatement();
		final ObjectMapper mapper=new ObjectMapper();
		if(requestOrder.toUpperCase().equals("SELECT")) {
			sqlRequest="SELECT * FROM "+ request.getRequestTable();
			if(studentsList!=null) {
				sqlRequest+=" WHERE ";
			for(int i=0;i<studentsList.size();i++) {
				sqlRequest+="id="+studentsList.get(i).getId();
				if(!(i==(studentsList.size()-1))) {
					sqlRequest+=" OR ";
				}
			  }
			}
			ResultSet rs= stmt.executeQuery(sqlRequest);
			Student student;
			studentsList=new ArrayList<Student>();
			while(rs.next()) {
			int id= rs.getInt("id");
			String name=rs.getString("name");
			String lastname = rs.getString("lastname");
			student=new Student(name,lastname,id);
			studentsList.add(student);
			}
			responseBody=mapper.writeValueAsString(studentsList);
			rs.close();
		}
		else if(requestOrder.toUpperCase().equals("INSERT")) {
			for(int i=0;i<studentsList.size();i++) {
				sqlRequest="INSERT INTO "+ request.getRequestTable()+" (name,lastname) VALUES ('"+studentsList.get(i).getName()+"','"+
			    studentsList.get(i).getLastname()+"')";
				stmt.executeUpdate(sqlRequest);	
				responseBody="\"message\": \"Successfully inserted!\" "; 
			}
		
		}
		else if(requestOrder.toUpperCase().equals("DELETE")) {
			
				for(int i=0;i<studentsList.size();i++) {
			    sqlRequest="DELETE FROM "+ request.getRequestTable();
			    sqlRequest+=" WHERE "+ "name = '"+  studentsList.get(i).getName()+ "' and lastname = '"+studentsList.get(i).getLastname()+"'";
			    stmt.executeUpdate(sqlRequest);	
				responseBody="\"message\": \"Successfully deleted!\" "; 
				
			
				}
				
			  }
			
		
	
			
		else if(requestOrder.toUpperCase().equals("UPDATE")) {
			
		}
		stmt.close();
		return new Response(request.getRequestId(),responseBody); 
	}
	public static ArrayList<Student> extractValuesFromRequestBody(String requestBody) throws JsonMappingException, JsonProcessingException {
		final ObjectMapper mapper=new ObjectMapper();
		StudentConfig students=mapper.readValue(requestBody, StudentConfig.class);
		return students.getStudents();
	}

}
