package org.attraction.portsmouth.dao;


import java.util.List;

import org.attraction.portsmouth.entity.Attraction;

public interface AttractionDAO {
	public void addAttraction(Attraction attraction);

	public List<Attraction> listAllAttraction();

	public List<Attraction> listAllAttraction(String username);
	
	public List<Attraction> listAttractionBy(String searchValue,String filterValue);
	
	public void removeAttractionById(Integer id);

	public Attraction getAttractionById(Integer id);

	public void updateAttraction(Attraction attraction);
	


	
}
