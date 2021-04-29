package edu.episen.si.ing1.pds.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import shared.code.Request;
import shared.code.Response;



public class RequestHandler {
	
	private final static Logger logger=LoggerFactory.getLogger(RequestHandler.class.getName());
	
	public static Response handle(Request request, Connection cnx) throws JsonMappingException, JsonProcessingException, SQLException {
		// TODO Auto-generated method stub
		String requestOrder=request.getRequestOrder();
		String sqlRequest="";
		String responseBody=null;
		String valuesJson="[";
		Statement stmt = cnx.createStatement();
		final ObjectMapper mapper=new ObjectMapper();
	
		
		if(requestOrder.toUpperCase().equals("SELECT")) { //this only works for requests that are like this : 
															//SELECT * FROM TABLE_NAME WHERE column_name='column_value' AND column_name1='column_value1' AND .......;
			sqlRequest="SELECT * FROM "+ request.getRequestTable();
			if(!request.getRequestBody().equals("{}")) {// If there are values in the RequestBody
				Map<String,String> valuesMap=mapper.readValue(request.getRequestBody(), Map.class);
				sqlRequest+=" WHERE ";
				for(String key: valuesMap.keySet()) {
					
					sqlRequest+=key+"='"+valuesMap.get(key)+"' AND ";
	
				}
				sqlRequest+=";";
				sqlRequest=sqlRequest.substring(0, sqlRequest.indexOf("AND ;"));
			}
			ResultSet rs= stmt.executeQuery(sqlRequest);
			ResultSetMetaData rsM=rs.getMetaData();
			while(rs.next()) {
				valuesJson+="{";
				for(int i=1;i<=rsM.getColumnCount();i++) {
					if(!(i==1)) {
						valuesJson+=",";
					}
						Object columnValue=rs.getObject(i);
						valuesJson+="\""+rsM.getColumnLabel(i)+"\": "+"\""+columnValue+"\"";
				}
				if(rs.isLast()) {
					valuesJson+="}]";
				}
				else {
				valuesJson+="},";
				}
			}
			
			responseBody="{ \"message\": "+valuesJson+"}";
			rs.close();
		}
		else if (requestOrder.equals("available_workspace")) {
			System.out.println("Workspace recu");
			String sql = "SELECT * FROM work_space WHERE taken = false";
			ResultSet rs= stmt.executeQuery(sqlRequest);
			ArrayList<Offer> offerList = new ArrayList<>();
			while (rs.next()) {
				offerList.add(new Offer(rs.getInt('space_id'), rs.getString('space_type'), rs.getString('space_name'), 
						rs.getInt('space_floor'), rs.getInt('space_building'), rs.getInt('space_cost'), rs.getInt('space_area'),));
			}
		}

		else if(requestOrder.toUpperCase().equals("INSERT")) {

		
		}
		else if(requestOrder.toUpperCase().equals("DELETE")) {
			
		}	
		else if(requestOrder.toUpperCase().equals("UPDATE")) {
			

		}
		else if(requestOrder.toUpperCase().equals("UPDATE_SPOT")) {
			sqlRequest="UPDATE "+request.getRequestTable()+" SET ";
			Map<String,String> valuesMap=mapper.readValue(request.getRequestBody(), Map.class);
			sqlRequest+="id_equipment="+valuesMap.get("id_equipment")+", taken="+valuesMap.get("taken")+" WHERE id_spot="+valuesMap.get("id_spot");
			stmt.executeUpdate(sqlRequest);
			responseBody="{ \"message\": \"Update is successful\"}";
			
		}
		else if(requestOrder.toUpperCase().equals("UPDATE_MATERIALNEEDS")) {
			sqlRequest="UPDATE "+request.getRequestTable()+" SET ";
			Map<String,String> valuesMap=mapper.readValue(request.getRequestBody(), Map.class);
			sqlRequest+="installed="+valuesMap.get("installed")+", state="+valuesMap.get("state")+" WHERE id_equipment="+valuesMap.get("id_equipment");
			stmt.executeUpdate(sqlRequest);
			responseBody="{ \"message\": \"Update is successful\"}";
			
		}
			stmt.close();
			return new Response(request.getRequestId(),responseBody); 
		}
		

}
