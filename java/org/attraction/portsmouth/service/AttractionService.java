package org.attraction.portsmouth.service;

import java.util.List;

import org.attraction.portsmouth.entity.Attraction;

public interface AttractionService {
	public void addAttraction(Attraction attraction);

	public List<Attraction> listAllAttraction();

	public List<Attraction> listAllAttraction(String username);
	public void removeAttractionById(Integer id);

	public Attraction getAttractionById(Integer id);

	public void updateAttraction(Attraction attraction);
	public List<Attraction> listAttractionBy(String searchValue,String filterValue);
	
	

}
