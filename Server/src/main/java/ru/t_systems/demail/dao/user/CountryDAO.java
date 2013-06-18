package ru.t_systems.demail.dao.user;

import java.util.List;

import ru.t_systems.demail.model.user.Country;


public interface CountryDAO {
	
	public Country getCountry(int id);
	public List<Country> getAllCountry();
	public Country getCountry(String name);

}
