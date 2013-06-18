package ru.t_systems.demail.dao.user;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ru.t_systems.demail.model.user.Country;
import ru.t_systems.demail.sever.ServerInit;



public class CountryDAOImpl implements CountryDAO {
	

	private Session getCurrentSession() {
		
		return ServerInit.getSession().getCurrentSession();
		
		}

	public Country getCountry(int id) {
		Country country = (Country) getCurrentSession().get(Country.class, id);
		return country;
	}

	public List<Country> getAllCountry() {
		
				return getCurrentSession().createQuery("from Country").list();
		
	}

	public Country getCountry(String name) {
		List<Country> userList = new ArrayList<Country>();
		
		Query query = getCurrentSession().createQuery(
				"from Country u where u.id = :name");
		query.setParameter("name", Integer.valueOf(name));
		userList = query.list();
		if (userList.size() > 0)
			return userList.get(0);
		else
			return null;
	}
	
	

}
