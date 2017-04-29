package com.techgeekme.boot;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Node {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	
	private
	String lat;
	
	private String lon;
	
	@OneToMany(mappedBy = "node", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<Violation> violations;
	
	public Node(){
		violations = new ArrayList<>();
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}
}
