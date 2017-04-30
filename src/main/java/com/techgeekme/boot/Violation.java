package com.techgeekme.boot;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Violation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String numberPlate;

	
	private Date timeOfViolation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "node_id")
	private Node node;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumberPlate() {
		return numberPlate;
	}

	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}

	public Date getTimeOfViolation() {
		return timeOfViolation;
	}

	public void setTimeOfViolation(Date timeOfViolation) {
		this.timeOfViolation = timeOfViolation;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}
}
