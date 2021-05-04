package edu.episen.si.ing1.pds.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import shared.code.Request;
import shared.code.Response;

///import shared.code.Offer;



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
	
		System.out.print("Request order : " + requestOrder);
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
			
			//@Hejer Fessi
			//fix bug 
			//in case of no response, the following error is raised : 
			//com.fasterxml.jackson.databind.JsonMappingException: Unexpected close marker '}': expected ']' (for Array starting at [Source: (String)"{"responseData":null,"request_id":"112","response_body":{ "message": [}}"; line: 1, column: 70])			
			//
			if (!rs.next()) {
				valuesJson+="]";
			} else {
					do{
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
					}while(rs.next()) ;
			
			}
			responseBody="{ \"message\": "+valuesJson+"}";
			rs.close();
		}
		else if (requestOrder.equals("available_workspace")) {
			System.out.println("Workspace recu");
			String sql = "SELECT * FROM work_space INNER JOIN building ON work_space.id_building = building.id_building and taken = false";
			System.out.println("Requete sql");
			ResultSet rs= stmt.executeQuery(sql);
			System.out.println("Requete sql bis");
			ArrayList<HashMap<String, Object>> rowList = new ArrayList<>();
			
			while(rs.next()) {
				HashMap<String, Object> row = new HashMap<>();
				row.put("space_id", rs.getInt("id_work_space"));
				row.put("space_type", rs.getString("space_type"));
				row.put("space_name", rs.getString("space_name"));
				row.put("space_floor", rs.getInt("space_floor"));
				row.put("building_name", rs.getString("building_name"));
				row.put("space_cost", rs.getInt("space_cost"));
				row.put("space_area", rs.getInt("space_area"));

				rowList.add(row);
				System.out.println("Line : " + row);
			}

			System.out.println("Data to sent: " + rowList);
			return new Response(request.getRequestId(), rowList);
		}

		else if(requestOrder.equals("loan_work_space")) {
			System.out.println("Demande de reservation recu");
			Map<String,Object> valuesMap=mapper.readValue(request.getRequestBody(), Map.class);

			String spaceName = (String)valuesMap.get("space_name");
			String companyId = (String)valuesMap.get("id_entreprise");
			
			String sql = "SELECT taken from work_space WHERE space_name = '" + spaceName + "'";
			ResultSet rs= stmt.executeQuery(sql);
	
			if(rs.getBoolean("taken")) {
				return new Response(request.getRequestId(), "" + spaceName + " already taken");
			} else  {			
				String sqlUpdate = "UPDATE work_space SET taken = 'true', id_entreprise = " + companyId + " WHERE space_name = '" + spaceName + "'";
				System.out.println("Requete sql");
				return new Response(request.getRequestId(), "" + spaceName + " reserved with success");
			}
			
			
		}
		
		else if(requestOrder.equals("equipment_list")) {
			System.out.println("Equipment name recu");
			String sql = "SELECT DISTINCT equipment_name, unit_cost, ref FROM equipment";
			System.out.println("Requete sql");
			ResultSet rs= stmt.executeQuery(sql);
			System.out.println("Requete sql bis");
			ArrayList<HashMap<String, Object>> rowList = new ArrayList<>();
			
			while(rs.next()) {
				HashMap<String, Object> row = new HashMap<>();
				row.put("equipment_name", rs.getString("equipment_name"));
				row.put("unit_cost", rs.getInt("unit_cost"));
				row.put("ref", rs.getInt("ref"));
				
				rowList.add(row);
				System.out.println("Line : " + row);
			}
			System.out.println("Data to sent: " + rowList);
			return new Response(request.getRequestId(), rowList);
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
		else if(requestOrder.toUpperCase().equals("UPDATE_WORKSPACE")) {
			sqlRequest="UPDATE "+request.getRequestTable()+" SET ";
			Map<String,String> valuesMap=mapper.readValue(request.getRequestBody(), Map.class);
			sqlRequest+="configurable="+valuesMap.get("configurable")+" WHERE id_work_space="+valuesMap.get("id_work_space");
			stmt.executeUpdate(sqlRequest);
			responseBody="{ \"message\": \"Update is successful\"}";
			
		}
		else if(requestOrder.toUpperCase().equals("UPDATE_EQUIPMENT")) {
			sqlRequest="UPDATE "+request.getRequestTable()+" SET ";
			Map<String,String> valuesMap=mapper.readValue(request.getRequestBody(), Map.class);
			sqlRequest+="id_window="+valuesMap.get("id_window")+" WHERE id_equipment="+valuesMap.get("id_equipment");
			stmt.executeUpdate(sqlRequest);
			responseBody="{ \"message\": \"Update is successful\"}";
			
		}
		else if(requestOrder.toUpperCase().equals("UNMAP_WORKSPACE")){
			Map<String,String> valuesMap=mapper.readValue(request.getRequestBody(), Map.class);
			stmt.executeUpdate("UPDATE spot SET id_equipment=null, taken=false WHERE id_work_space="+valuesMap.get("id_work_space"));
			stmt.executeUpdate("UPDATE material_needs SET installed=false WHERE id_work_space="+valuesMap.get("id_work_space"));
			stmt.executeUpdate("UPDATE work_space SET configurable=false WHERE id_work_space="+valuesMap.get("id_work_space"));
			ResultSet rs= stmt.executeQuery("SELECT id_equipment FROM spot WHERE id_work_space="+valuesMap.get("id_work_space"));
			while(rs.next()) {
				stmt.executeUpdate("UPDATE equipment SET id_window=null WHERE id_equipment="+rs.getInt("id_equipment"));
				
			}
			rs.close();
			responseBody="{ \"message\": \"Update is successful\"}";
		}
		else if(requestOrder.toUpperCase().equals("STATE_EQUIPMENT_FALSE")) {
			Map<String,String> valuesMap=mapper.readValue(request.getRequestBody(), Map.class);
			ResultSet rs= stmt.executeQuery("SELECT id_equipment FROM spot WHERE id_spot="+valuesMap.get("id_spot"));
			stmt.executeUpdate("UPDATE material_needs SET state=false WHERE id_equipment="+rs.getInt("id_equiment"));
			rs.close();
			responseBody="{ \"message\": \"Update is successful\"}";
		}
		
		else if(requestOrder.toUpperCase().equals("UPDATE_SMARTWINDOW")) {
			sqlRequest="UPDATE "+request.getRequestTable()+" SET ";
			Map<String,String> valuesMap=mapper.readValue(request.getRequestBody(), Map.class);			
			sqlRequest+="configured_window="+valuesMap.get("configured_window")+", preferredtem = "+valuesMap.get("preferredtem")+", preferredlum = '"+valuesMap.get("preferredlum")+"' WHERE id_window="+valuesMap.get("id_window");
			stmt.executeUpdate(sqlRequest);
			System.out.print(sqlRequest);
			responseBody="{ \"message\": \"Update is successful\"}";
			
			
		}
			stmt.close();
			return new Response(request.getRequestId(),responseBody); 
		
		
	
}}
