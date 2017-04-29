package com.techgeekme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techgeekme.boot.Node;
import com.techgeekme.boot.NodeRepository;
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
	public String setNode(@RequestParam(value="lat") String lat, @RequestParam(value="lon") String lon){
		
		Node node = new Node();
		
		node.setLat(lat);
		node.setLon(lon);
		
		nodeRepository.save(node);
		
		return "OK";
	}
	@RequestMapping(value="/getNodes", method = RequestMethod.GET)
	public List<Node> getNodes(){
		return nodeRepository.findAll();
	}
}
