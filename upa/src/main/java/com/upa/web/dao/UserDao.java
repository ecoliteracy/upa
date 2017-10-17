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
	
	
	public boolean isValidUserPassword(Login l){

		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try{
			tx = session.beginTransaction();
			String HQL_QUERY = "select * from upa.app_user where user_id = :userId and user_password = :password ";
			Query query = session.createQuery(HQL_QUERY);
			query.setParameter("userId", l.getUserId());
			query.setParameter("password", l.getPassword());

			AppUser hs = (AppUser) query.getSingleResult();

			if(hs != null){
				return true;
			}else{
				return false;
			}
			

		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			return false;
		}catch(NoResultException e){
			e.printStackTrace();
			return false;
		}finally {
			session.close(); 
		}
	}

}
