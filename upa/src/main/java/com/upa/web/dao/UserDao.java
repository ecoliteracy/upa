package com.upa.web.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.upa.web.model.AppUser;

import Login.Login;

@Repository
public class UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public String isValidUserPassword(Login l){

		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try{
			tx = session.beginTransaction();
			String HQL_QUERY = "From AppUser where userId = :userId ";
			Query query = session.createQuery(HQL_QUERY, AppUser.class);
			query.setParameter("userId", l.getUserId());

			System.out.println("++++++++++++");
			System.out.println(l.getUserId());
			System.out.println(l.getPassword());
			
			AppUser hs = (AppUser) query.getSingleResult();
			
			if(hs != null){
				if(hs.getUserPassword().equals(l.getPassword())){
					return "VALID";	
				}else{
					return "WRONG_PASSWORD";
				}
			}else{
				return "NOT_FOUND";
			}
			

		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			return "ERROR";
		}catch(NoResultException e){
			e.printStackTrace();
			return "NOT_FOUND";
		}finally {
			session.close(); 
		}
	}

}
