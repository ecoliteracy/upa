package com.upa.web.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.upa.web.model.UserValidationResult;
import com.upa.web.model.entity.AppUser;
import com.upa.web.model.entity.UserSalaryType;

@Repository
public class UserDao {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SessionFactory sessionFactory;
	
	//private SessionFactory sessionFactory = getSessionFactory();
	
	public UserDao(){}
	
	public UserValidationResult isValidUserPassword(AppUser l){

		UserValidationResult uvr = new UserValidationResult();

		Session session = sessionFactory.openSession();
		//Session session = getOpenSession();
		Transaction tx = null;

		try{
			tx = session.beginTransaction();
			String HQL_QUERY = "From AppUser where userId = :userId ";
			Query query = session.createQuery(HQL_QUERY, AppUser.class);
			query.setParameter("userId", l.getUserId());

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
		}catch(Exception e){
			e.printStackTrace();
			uvr.setValidationResult("ERROR");
			return uvr;
		}finally {
			session.close();
		}
	}
	
	
	public UserSalaryType getUserSalaryType(Integer userSeq){
		UserSalaryType userSalaryType = new UserSalaryType();
		
		Session session = sessionFactory.openSession();
		//Session session = getOpenSession();
		Transaction tx = null;

		try{
			tx = session.beginTransaction();
			String HQL_QUERY = "From UserSalaryType where appUser.userSeq = :userSeq ";
			Query query = session.createQuery(HQL_QUERY, UserSalaryType.class);
			query.setParameter("userSeq", userSeq);

			userSalaryType = (UserSalaryType) query.getSingleResult();
			 						
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			return null;
		}catch(NoResultException e){
			e.printStackTrace();
			return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		finally {
			session.close(); 
		}		
		return userSalaryType;
	}
	
	public String addUserSalaryType(UserSalaryType userSalaryType){
		logger.trace("UserDao - addUserSalaryType");
		Session session = sessionFactory.openSession();
		//Session session = getOpenSession();
		
		//Transaction transaction = null;
		try{
		    //transaction = session.getTransaction();
		    //transaction.begin();
			
		    session.beginTransaction();
		    session.getSession().merge(userSalaryType);
			session.getTransaction().commit();

			//sessionFactory.getCurrentSession().saveOrUpdate(userSalaryType);
			//transaction.commit();
		}catch (Exception e){
//		    if (transaction != null) {
//		        transaction.rollback();
//		      }
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return "SUCCESS";
	}
	
}