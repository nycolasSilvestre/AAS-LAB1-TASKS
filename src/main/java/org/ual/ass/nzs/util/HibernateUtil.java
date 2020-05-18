package org.ual.ass.nzs.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	private static SessionFactory sessionFactory = null;
	private static final StandardServiceRegistry registry= new StandardServiceRegistryBuilder()
			.configure("hibernate.cfg.xml")
			.build();
	public HibernateUtil() {
		super();
	}
	
	public static SessionFactory getSessionFactory(){
		if(sessionFactory == null) {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		}
		return sessionFactory;
		
	}
}
