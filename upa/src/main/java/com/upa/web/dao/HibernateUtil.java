package com.upa.web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class HibernateUtil {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public SessionFactory sessionFactory;



	public HibernateUtil(){
	}
	
	public Session getOpenSession(){
		return sessionFactory.openSession();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
}
