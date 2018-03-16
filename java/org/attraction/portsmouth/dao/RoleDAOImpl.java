package  org.attraction.portsmouth.dao;

import org.attraction.portsmouth.entity.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAOImpl implements RoleDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void setRole(String username, String role) {
		Role userRole = new Role();
		userRole.setUsername(username);
		userRole.setRole(role);
		sessionFactory.getCurrentSession().save(userRole);
	}

}
