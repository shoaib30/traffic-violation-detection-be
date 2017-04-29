package com.techgeekme.boot;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Violation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Autowired
	long id;
	
	@Autowired
	String numberPlate;
	
	@Autowired
	Date timeOfViolation;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "node_id")
	@Autowired
    private Node node;
}
