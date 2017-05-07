package com.techgeekme.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
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
		violation.setTimeOfViolation(new Date(((long) payload.getDouble("timeOfViolation"))*1000));
		Node node = nodeRepository.findByUid(payload.getString("uid"));
		node.getViolations().add(violation);
		violation.setNode(node);
		violationRepository.save(violation);
		nodeRepository.save(node);
		return "OK";
	}
	
	@RequestMapping(value = "/getViolations", method = RequestMethod.GET)
	public List<ViolationResponse> getViolations(){
//		ArrayList<Violation> violations =  (ArrayList<Violation>) violationRepository.findAll();
		ArrayList<ViolationResponse> vResponse = new ArrayList<>();
//		for(Violation v : violations){
//			vResponse.add(new ViolationResponse(v));
//		}
		ArrayList<Node> nodes = (ArrayList<Node>) nodeRepository.findAll();
		for(Node n: nodes){
			for(Violation v: n.getViolations()){
				vResponse.add(new ViolationResponse(v, n.getNodeName()));
			}
		}
		return vResponse;
	}
	@RequestMapping(value="/getGraphData", method = RequestMethod.GET)
	public List<Long> getGraphData()	{
		return violationRepository.countGrounpByViolationTime();
	}
	
//	@RequestMapping(value="/getGraphData2", method = RequestMethod.GET)
//	public Map<String, Long> getGraphData2()	{
//		return violationRepository.countByViolationTime();
//	}
	
	@RequestMapping(value="/getGraphData1", method = RequestMethod.GET)
	public String getGraphData1()	{
		JSONArray ja = new JSONArray();
		List<Object[]> objs = violationRepository.countByViolationTime();
		//HashMap<String, Long> resp = new HashMap<>();
		for(Object[] obj: objs){
			JSONArray in = new JSONArray();
			in.put((String)obj[0]);
			in.put((Long)obj[1]);
			ja.put(in);
		}
		return ja.toString();
	}
}
