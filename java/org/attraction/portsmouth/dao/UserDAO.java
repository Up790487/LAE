package  org.attraction.portsmouth.dao;

import java.util.List;

import org.attraction.portsmouth.entity.User;

public interface UserDAO {
	public User getUser(String username);

	public void addUser(User user);

	public User getUser(String firstname, String lastname);

	public List<String> listUserNames();

}
