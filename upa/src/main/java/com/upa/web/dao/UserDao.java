package com.upa.web.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.upa.web.model.UserValidationResult;
import com.upa.web.model.entity.AppUser;

@Repository
public class UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public UserValidationResult isValidUserPassword(AppUser l){

		UserValidationResult uvr = new UserValidationResult();

		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try{
			tx = session.beginTransaction();
			String HQL_QUERY = "From AppUser where loginId = :loginId ";
			Query query = session.createQuery(HQL_QUERY, AppUser.class);
			query.setParameter("loginId", l.getLoginId());

			AppUser au = (AppUser) query.getSingleResult();
			
			uvr.setAppuser(au);
			
			if(au != null){
				if(au.getUserPassword().equals(l.getUserPassword())){
					uvr.setValidationResult("VALID");
					return uvr;	
				}else{
					uvr.setValidationResult("WRONG_PASSWORD");
					return uvr;
				}
			}else{
				uvr.setValidationResult("NOT_FOUND");
				return uvr;
			}
			
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			uvr.setValidationResult("ERROR");
			return uvr;
		}catch(NoResultException e){
			//e.printStackTrace();
			uvr.setValidationResult("NOT_FOUND");
			return uvr;
		}finally {
			session.close(); 
		}
	}
}