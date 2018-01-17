package com.upa.web.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.upa.service.EntityUtils;
import com.upa.web.constant.OrganizationConstant;
import com.upa.web.model.entity.Organization;

@Repository
public class OrganizationDaoImpl {

	@Autowired
	private SessionFactory sessionFactory;
	
	public OrganizationDaoImpl(){};
	
	public OrganizationDaoImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	public String saveOraganization(Organization org){
		System.out.println("OrganizationDaoImpl.saveOraganization");
		
		EntityUtils.setupAuditTrail(org, true);
		System.out.println(org.getOrgName());
		System.out.println(org.getOrgType());
		System.out.println(org.getTzCode());
		
		try{
			sessionFactory.getCurrentSession().saveOrUpdate(org);
		}catch(HibernateException he){
			he.printStackTrace();
			return he.getMessage();
		}
		
		return OrganizationConstant.SUCCESS;
	}
	
	public Organization getOrganization(String orgName){
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try{
			tx = session.beginTransaction();
			String HQL_QUERY = "FROM Organization where orgName = :orgName";
			Query query = session.createQuery(HQL_QUERY);
			query.setParameter("orgName", orgName);
			
			Organization org = (Organization) query.getSingleResult();
			tx.commit();

			return org;
			
 		}catch(HibernateException he){
			he.printStackTrace();
			return null;
		}catch(NoResultException nr){
			return null;
		}
	}	
}
