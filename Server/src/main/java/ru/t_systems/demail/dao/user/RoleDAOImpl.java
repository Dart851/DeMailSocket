package ru.t_systems.demail.dao.user;


import org.hibernate.Session;

import ru.t_systems.demail.model.user.Role;
import ru.t_systems.demail.sever.ServerInit;



public class RoleDAOImpl implements RoleDAO {
	
		
	private Session getCurrentSession() {
		return ServerInit.getSession().getCurrentSession();
	}
	
	public Role getRole(int id) {
		Role role = (Role) getCurrentSession().get(Role.class, id);
		return role;
	}
	public void saveRole(Role role) {
		getCurrentSession().save(role);
		
	}

}
