package model;

import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Model {
	private String companyName="CompanyName";//Will change depending on the user's company
	private String contact="Name";   //Same thing
	
	private final static Logger logger=LoggerFactory.getLogger(Model.class.getName());
	public Model() {
		
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}


}
