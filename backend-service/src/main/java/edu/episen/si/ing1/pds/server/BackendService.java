package edu.episen.si.ing1.pds.server;

import java.text.ParseException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BackendService {
private final static Logger logger=LoggerFactory.getLogger(BackendService.class.getName());
	public static void main(String[] args) throws ParseException {
		Scanner sc=new Scanner(System.in);
		System.out.println("Veuillez saisir l'option: ");
		String option= sc.nextLine();
		boolean inTestMode=false;
		int maxConnectionValue=10;
		  if(option.equals("testMode")) {
			inTestMode=true;
		}
		else if(option.equals("maxConnection")) {
			inTestMode=true;
			maxConnectionValue = sc.nextInt();
		}
		logger.info("Backend service is running. (testMode={"+inTestMode+"}), maxConnection={"+ maxConnectionValue +"}");
		
	}
}
