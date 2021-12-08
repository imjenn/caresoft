package com.caresoft.clinicapp.models;
import java.util.ArrayList;
import java.util.Date;

import com.caresoft.clinicapp.interfaces.HIPAACompliantUser;

public class Physician extends User implements HIPAACompliantUser {
    
    // Inside class:    
    private ArrayList<String> patientNotes;
	
    // TO DO: Constructor that takes an ID
    public Physician(Integer id) {
    	super(id);
    }
    
    // TO DO: Implement HIPAACompliantUser!
	@Override
	public boolean assignPin(int pin) {
		int length = String.valueOf(pin).length();
//		System.out.println(length);
		if(length == 4) {
			return true;
		}
		return false;
	}

	@Override
	public boolean accessAuthorized(Integer confirmedAuthID) {
		if(confirmedAuthID == this.id) {
			return true;
		}
		return false;
	}
	
    public void newPatientNotes(String notes, String patientName, Date date) {
        String report = String.format(
            "Datetime Submitted: %s \n", date);
        report += String.format("Reported By ID: %s\n", this.id);
        report += String.format("Patient Name: %s\n", patientName);
        report += String.format("Notes: %s \n", notes);
        this.patientNotes.add(report);
    }

	
    // GETTERS
 	public Integer getId() {
 		return this.id;
 	}
 	

 	// SETTERS
 	public void setId(Integer id) {
 		this.id = id;
 	}
    

}
