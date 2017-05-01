package com.techgeekme.response;

import java.util.ArrayList;
import java.util.List;

import com.techgeekme.boot.Node;
import com.techgeekme.boot.Violation;

public class NodeResponse {

	private String nodeName;
	private String lat;
	private String lon;
	private String uid;
	
	private List<ViolationResponse> violations;
	
	public NodeResponse()	{
		
	}
	
	public NodeResponse(Node n){
		this.setLat(n.getLat());
		this.setLon(n.getLon());
		this.setNodeName(n.getNodeName());
		this.setUid(n.getUid());
		this.setViolations(new ArrayList<>());
		for(Violation v: n.getViolations()){
			this.getViolations().add(new ViolationResponse(v, n.getNodeName()));
		}
	}
	/**
	 * @return the lat
	 */
	public String getLat() {
		return lat;
	}
	/**
	 * @param lat the lat to set
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}
	/**
	 * @return the lon
	 */
	public String getLon() {
		return lon;
	}
	/**
	 * @param lon the lon to set
	 */
	public void setLon(String lon) {
		this.lon = lon;
	}
	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public List<ViolationResponse> getViolations() {
		return violations;
	}

	public void setViolations(List<ViolationResponse> violations) {
		this.violations = violations;
	}
}
