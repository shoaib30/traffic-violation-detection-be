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

@Entity
public class Node {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	
	private
	String lat;
	
	private String lon;
	
	private String nodeName;
	
	private String uid;
	
	@OneToMany(mappedBy = "node", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private
	List<Violation> violations;
	
	public Node(){
		setViolations(new ArrayList<>());
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

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public List<Violation> getViolations() {
		return violations;
	}

	public void setViolations(List<Violation> violations) {
		this.violations = violations;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
}
