package com.techgeekme.service;

import java.util.ArrayList;
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
import com.techgeekme.response.NodeResponse;
import com.techgeekme.response.ViolationResponse;

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
	public String setNode(@RequestParam(value="lat") String lat, @RequestParam(value="lon") String lon, @RequestParam(value="nodeName") String nodeName, @RequestParam(value="uid") String uid){
		
		Node node = new Node();
		
		node.setLat(lat);
		node.setLon(lon);
		node.setNodeName(nodeName);
		node.setUid(uid);
		
		nodeRepository.save(node);
		
		return "OK";
	}
	@RequestMapping(value="/getNodes", method = RequestMethod.GET)
	public List<NodeResponse> getNodes(){
		ArrayList<Node> nodes = (ArrayList<Node>) nodeRepository.findAll();
		ArrayList<NodeResponse> nResponse = new ArrayList<>();
		for(Node n: nodes){
			nResponse.add(new NodeResponse(n));
		}
		return nResponse;
	}
	
	@RequestMapping(value="/setViolation", method = RequestMethod.POST)
	public String setViolation(@RequestBody String body){
		JSONObject bodyJson = new JSONObject(body);
		JSONObject payload = bodyJson.getJSONObject("payload");
		Violation violation = new Violation();
		violation.setNumberPlate(payload.getString("numberPlate"));
		violation.setTimeOfViolation(new Date(Long.parseLong(payload.getString("timeOfViolation")) * 1000));
		Node node = nodeRepository.findByUid(payload.getString("uid"));
		node.getViolations().add(violation);
		violation.setNode(node);
		violationRepository.save(violation);
		nodeRepository.save(node);
		return "OK";
	}
	
	@RequestMapping(value = "/getViolations", method = RequestMethod.GET)
	public List<ViolationResponse> getViolations(){
		ArrayList<Violation> violations =  (ArrayList<Violation>) violationRepository.findAll();
		ArrayList<ViolationResponse> vResponse = new ArrayList<>();
		for(Violation v : violations){
			vResponse.add(new ViolationResponse(v));
		}
		return vResponse;
	}
}
