package ru.t_systems.demail.sever;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class ServerInit {
	private static SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();

	public static SessionFactory getSession() {
		return sessionFactory;
	}
}
	
