
package org.attraction.portsmouth.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.attraction.portsmouth.entity.Attraction;
import org.attraction.portsmouth.entity.Search;
import org.attraction.portsmouth.entity.User;
import org.attraction.portsmouth.service.AttractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

@Controller
public class HomeController {
	
	@Autowired
	private AttractionService attractionService;

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String displayLogin(Map<String, Object> map) {
		map.put("user", new User());
	    map.put("search", new Search());
	    List<Attraction> attractions=new ArrayList<Attraction>();
	    for(Attraction att:attractionService.listAllAttraction()){
	      if(att.getStatus().equals("Live")){
	    	  attractions.add(att);
	      }
	    }
		map.put("attractionList",attractions);
		Gson gson = new Gson();
	    String attractionJson = gson.toJson(attractions);
	    map.put("attractionJson", attractionJson);
		return "home";
	}
	

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchAttraction(HttpServletRequest request, Map<String, Object> map) {
	    
		return "redirect:home";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchAttraction(HttpServletRequest request, Model m, Map<String, Object> map,@Valid Search search) {
		
		String searchVal=request.getParameter("search").trim();
		String filterVal=request.getParameter("filtertype").trim();
		List<Attraction> attractions=attractionService.listAttractionBy(searchVal, filterVal);
			if(searchVal!=null){
				//List<Attraction> allattractions=attractions.stream().filter(attr->attr.getStatus().equals("Live")).collect(Collectors.toList());  
				List<Attraction> allattractions=new ArrayList<Attraction>();
			    for(Attraction att:attractions){
			      if(att.getStatus().equals("Live")){
			    	  attractions.add(att);
			      }
			    }
				map.put("attractionList", allattractions);
				Gson gson = new Gson();
			    String attractionJson = gson.toJson(allattractions);
			    map.put("attractionJson", attractionJson);
			}else{
				map.put("message","Kindly choose an option from the drop down");
				//List<Attraction> allattractions=attractionService.listAllAttraction().stream().filter(attr->attr.getStatus().equals("Live")).collect(Collectors.toList());
				List<Attraction> allattractions=new ArrayList<Attraction>();
			    for(Attraction att:attractionService.listAllAttraction()){
			      if(att.getStatus().equals("Live")){
			    	  attractions.add(att);
			      }
			    }
				map.put("attractionList", allattractions);
				Gson gson = new Gson();
			    String attractionJson = gson.toJson(allattractions);
			    map.put("attractionJson", attractionJson);
			}
		if(attractions.size()==0){
			map.put("message","No result found");
		}
	    map.put("search", new Search());	
		return "home";

	}

}
