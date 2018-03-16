package org.attraction.portsmouth.service;

import java.util.List;

import org.attraction.portsmouth.dao.RoleDAO;
import org.attraction.portsmouth.dao.UserDAO;
import org.attraction.portsmouth.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDAO roleDao;

	@Transactional
	public void setRole(String username,String role) {
		roleDao.setRole(username,role);
	}

}
