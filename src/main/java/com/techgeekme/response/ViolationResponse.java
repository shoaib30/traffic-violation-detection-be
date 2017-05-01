package com.techgeekme.response;

import java.util.Date;

import com.techgeekme.boot.Violation;

public class ViolationResponse {
	

	private String numberPlate;
	private String timeOfViolation;
	private String location;

	ViolationResponse(){
	
	}
	
	public ViolationResponse(Violation v){
		this.setNumberPlate(v.getNumberPlate());
		this.setTimeOfViolation(v.getTimeOfViolation().toString());
		this.setLocation(" ");
	}
	
	public ViolationResponse(Violation v, String location){
		this.setNumberPlate(v.getNumberPlate());
		this.setTimeOfViolation(v.getTimeOfViolation().toString());
		this.setLocation(location);
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
