package  org.attraction.portsmouth.dao;

import java.util.ArrayList;
import java.util.List;

import org.attraction.portsmouth.dao.AttractionDAO;
import org.attraction.portsmouth.entity.Attraction;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AttractionDAOImpl implements AttractionDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addAttraction(Attraction attraction) {
		sessionFactory.getCurrentSession().save(attraction);
	}

	@Override
	public void removeAttractionById(Integer id) {
		Attraction attraction = (Attraction) sessionFactory.getCurrentSession().load(Attraction.class, id);
		if (null != attraction) {
			sessionFactory.getCurrentSession().delete(attraction);
		}

	}

	@Override
	public void updateAttraction(Attraction attraction) {
		Session session = sessionFactory.getCurrentSession();
		session.update(attraction);

	}

	@Override
	public Attraction getAttractionById(Integer id) {
		Attraction attraction = (Attraction) sessionFactory.getCurrentSession().load(Attraction.class, id);
		Hibernate.initialize(attraction.getIdattraction());
		Hibernate.initialize(attraction.getName());
		Hibernate.initialize(attraction.getDescription());
		Hibernate.initialize(attraction.getStarthour());
		Hibernate.initialize(attraction.getEndhour());
		Hibernate.initialize(attraction.getPicture());
		Hibernate.initialize(attraction.getStatus());
		Hibernate.initialize(attraction.getUsername());
		Hibernate.initialize(attraction.getCategory());
		Hibernate.initialize(attraction.getAddress());

		return attraction;
	}

	@Override
	public List<Attraction> listAllAttraction() {
		return sessionFactory.getCurrentSession().createQuery("from Attraction").list();
	}

	@Override
	public List<Attraction> listAllAttraction(String username) {
		String hql = "FROM Attraction attraction WHERE attraction.username =:username";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("username", username);
		return query.list();
	}

	@Override
	public List<Attraction> listAttractionBy(String searchValue, String filterValue) {
		String hql="";
		
		if(searchValue.equals("") && filterValue.equals("")){
			return sessionFactory.getCurrentSession().createQuery("from Attraction").list();
		}else if(searchValue.equals("") && !filterValue.equals("")){
			hql = "FROM Attraction attraction WHERE attraction.category=:filterValue";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("filterValue", filterValue);
			return query.list();
		}else if(!searchValue.equals("") && filterValue.equals("")){
			hql = "FROM Attraction attraction WHERE attraction.name =:searchValue or "
					+ "attraction.address=:searchValue or attraction.description=:searchValue or "
					+ "attraction.starthour=:searchValue or attraction.endhour=:searchValue "
					+ "or attraction.category=:searchValue";
		
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("searchValue", searchValue);
			return query.list();
			
			
		}else if(!searchValue.equals("") && !filterValue.equals("")){
			hql = "FROM Attraction attraction WHERE (attraction.name =:searchValue or "
					+ "attraction.address=:searchValue or attraction.description=:searchValue or "
					+ "attraction.starthour=:searchValue or attraction.endhour=:searchValue or "
					+ "attraction.category=:searchValue) and (attraction.category=:filterValue)";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("filterValue", filterValue);
			query.setParameter("searchValue", searchValue);
			return query.list();
		}else{
			return sessionFactory.getCurrentSession().createQuery("from Attraction").list();
		}
	}
	
	
	
	/*@Override
	public List<Attraction> listPropertyByPaymentStatus(String paymentstatus) {
		String hql = "FROM Property property WHERE property.paymentstatus =:paymentstatus";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("paymentstatus", paymentstatus);
		return query.list();
	}*/
	
	

}
