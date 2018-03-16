package org.attraction.portsmouth.service;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.attraction.portsmouth.dao.AttractionDAO;
import org.attraction.portsmouth.entity.Attraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AttractionServiceImpl implements AttractionService {
	@Autowired
	private AttractionDAO attractionDao;

	@Transactional
	public void addAttraction(Attraction attraction) {
		attractionDao.addAttraction(attraction);
	}

	@Transactional
	public List<Attraction> listAllAttraction() {
		return attractionDao.listAllAttraction();
	}

	@Transactional
	public List<Attraction> listAllAttraction(String username) {
		return attractionDao.listAllAttraction(username);
	}
	
	/*
	@Transactional
	public List<Attraction> listPropertyByPropertyStatus(String propertystatus){
		return propertyDao.listPropertyByPropertyStatus(propertystatus);
	}*/

	@Transactional
	public void removeAttractionById(Integer id) {
		attractionDao.removeAttractionById(id);

	}

	@Transactional
	public void updateAttraction(Attraction attraction) {
		attractionDao.updateAttraction(attraction);

	}

	@Transactional
	public Attraction getAttractionById(Integer id) {
		return attractionDao.getAttractionById(id);
	}

	@Transactional
	public List<Attraction> listAttractionBy(String searchValue, String filterValue) {
		return attractionDao.listAttractionBy(searchValue, filterValue);
	}

	

	

}
