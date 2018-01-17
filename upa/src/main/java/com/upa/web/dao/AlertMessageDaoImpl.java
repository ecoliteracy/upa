package com.upa.web.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.upa.web.model.entity.AlertMessage;

@Repository
public class AlertMessageDaoImpl {

	@Autowired
	private SessionFactory sessionFactory;
	
	public AlertMessage getAllertMessage(String messageCode, String languageCode){
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			String SQL = "FROM AlertMessage where messageCode = :messageCode and languageCode = :languageCode ";
			Query query = session.createQuery(SQL);
			query.setParameter("messageCode", messageCode);
			query.setParameter("languageCode", languageCode);
			
			AlertMessage alertMessage = (AlertMessage) query.getSingleResult();
			tx.commit();
			
			return alertMessage; 
			
		}catch(HibernateException he){
			he.printStackTrace();
			return null;
		}catch(NoResultException nr){
			return null;
		}
		
	}
}
