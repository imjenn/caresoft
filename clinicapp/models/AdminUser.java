package com.caresoft.clinicapp.models;

import java.util.ArrayList;
import java.util.Date;

import com.caresoft.clinicapp.interfaces.HIPAACompliantAdmin;
import com.caresoft.clinicapp.interfaces.HIPAACompliantUser;

public class AdminUser extends User implements HIPAACompliantUser, HIPAACompliantAdmin {
	private Integer employeeID;
	private String role;
	private ArrayList<String> securityIncidents = new ArrayList<String>();
	
	// CONSTRUCTOR
	public AdminUser(Integer employeeID, String role) {
		super(employeeID);
		this.role = role;
	}
	
	@Override
	public ArrayList<String> reportSecurityIncidents() {
		return securityIncidents;
	}
	
	@Override
	public boolean assignPin(int pin) {
		int length = String.valueOf(pin).length();
		if(length == 6) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean accessAuthorized(Integer confirmedAuthID) {
		if(confirmedAuthID.equals(this.id)) {
			System.out.println(confirmedAuthID);
			return true;
		}
//		newIncident("AUTHORIZATION ATTEMPT FAILED FOR THIS USER");
		authIncident();
		return false;
	}
	
	public void newIncident(String notes) {
		String report = String.format(
			"Datetime Submitted: %s \n, Reported by ID: %s\n Notes: %s \n",
			new Date(), this.id, notes
		);
		securityIncidents.add(report);
	}
	
	public void authIncident() {
		String report = String.format(
			"Datetime Submitted: %s \n, ID: %s\n Notes: %s \n",
			new Date(), this.id, "AUTHORIZATION ATTEMPT FAILED FOR THIS USER"
		);
		securityIncidents.add(report);
	}
	

	// GETTERS
	public Integer getEmployeeID() {
		return this.employeeID;
	}
	
	public String getRole() {
		return this.role;
	}

	// SETTERS
	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	public void setRole(String role) {
		this.role = role;
	}


		
}
