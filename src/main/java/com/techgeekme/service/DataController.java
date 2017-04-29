package com.techgeekme.service;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techgeekme.boot.Node;
import com.techgeekme.boot.NodeRepository;
import com.techgeekme.boot.Violation;
import com.techgeekme.boot.ViolationRepository;

@RestController
@RequestMapping(value="/data")
public class DataController {
	
	@Autowired
	NodeRepository nodeRepository;
	
	@Autowired
	ViolationRepository violationRepository;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index()	{
		return "Data Cotroller";
	}
	
	@RequestMapping(value="/setNode", method = RequestMethod.GET)
	public String setNode(@RequestParam(value="lat") String lat, @RequestParam(value="lon") String lon, @RequestParam(value="nodeName") String nodeName){
		
		Node node = new Node();
		
		node.setLat(lat);
		node.setLon(lon);
		node.setNodeName(nodeName);
		
		nodeRepository.save(node);
		
		return "OK";
	}
	@RequestMapping(value="/getNodes", method = RequestMethod.GET)
	public List<Node> getNodes(){
		return nodeRepository.findAll();
	}
	
	@RequestMapping(value="/setViolation", method = RequestMethod.GET)
	public String setViolation(@RequestBody String body){
		JSONObject bodyJson = new JSONObject(body);
		JSONObject payload = bodyJson.getJSONObject("payload");
		Violation violation = new Violation();
		violation.setNumberPlate(payload.getString("numberPlate"));
		violation.setTimeOfViolation(new Date(Long.parseLong(payload.getString("timeOfViolation"))));
		Node node = nodeRepository.findOne(payload.getLong("node"));
		node.getViolations().add(violation);
		violation.setNode(node);
		violationRepository.save(violation);
		nodeRepository.save(node);
		return "OK";
	}
}
