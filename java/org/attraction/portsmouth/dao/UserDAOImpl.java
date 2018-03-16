package  org.attraction.portsmouth.dao;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.attraction.portsmouth.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User getUser(String username) {
		String hql = "FROM User user WHERE user.username =:username";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("username", username);
		List results = query.list();
		if (results.size() > 0) {
			return (User) results.get(0);
		} else {
			return null;
		}

	}

	@Override
	public List<String> listUserNames() {
		List<User> users = sessionFactory.getCurrentSession().createQuery("from User").list();
		List<String> usernames = new ArrayList<String>();
		for (User user : users) {
			usernames.add(user.getUsername());
		}
		return usernames;

	}

	@Override
	public User getUser(String firstname, String lastname) {
		String hql = "FROM User user WHERE user.firstname =:firstname and user.lastname=:lastname";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("firstname", firstname);
		query.setParameter("lastname", lastname);
		List results = query.list();
		if (results.size() > 0) {
			return (User) results.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void addUser(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

}
