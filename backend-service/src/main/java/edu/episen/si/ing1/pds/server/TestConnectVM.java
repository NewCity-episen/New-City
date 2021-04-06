package edu.episen.si.ing1.pds.server;
import java.sql.Connection;
import connectionPool.*;
public class TestConnectVM {

	public static void main(String[] args) {
		try {
			DataSource.loadPool(5);
			Connection cnx=DataSource.getConnectionFromPool();
			if(cnx!=null) {
				System.out.println("Connected to the database");
				DataSource.returnConnection(cnx);
			}
			DataSource.closePool();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
