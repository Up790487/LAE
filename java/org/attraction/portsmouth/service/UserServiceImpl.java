package org.attraction.portsmouth.service;

import java.util.List;

import org.attraction.portsmouth.dao.UserDAO;
import org.attraction.portsmouth.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDao;

	@Transactional
	public User getUser(String username) {
		return userDao.getUser(username);
	}

	@Transactional
	public List<String> listUserNames() {
		// TODO Auto-generated method stub
		return userDao.listUserNames();
	}

	@Transactional
	public User getUser(String firstname, String lastname) {
		return userDao.getUser(firstname, lastname);
	}

	@Transactional
	public void addUser(User user) {
		userDao.addUser(user);

	}

}
