package com.techgeekme.response;

import java.util.Date;

import com.techgeekme.boot.Violation;

public class ViolationResponse {
	

	private String numberPlate;
	private String timeOfViolation;

	ViolationResponse(){
	
	}
	
	public ViolationResponse(Violation v){
		this.setNumberPlate(v.getNumberPlate());
		this.setTimeOfViolation(v.getTimeOfViolation().toString());
	}
	
	public String getNumberPlate() {
		return numberPlate;
	}

	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}

	public String getTimeOfViolation() {
		return timeOfViolation;
	}

	public void setTimeOfViolation(String timeOfViolation) {
		this.timeOfViolation = timeOfViolation;
	}
}
