package org.attraction.portsmouth.service;

import java.util.List;

import org.attraction.portsmouth.entity.User;

public interface UserService {
	public User getUser(String username);

	public User getUser(String firstname, String lastname);

	public void addUser(User user);

	public List<String> listUserNames();
}
