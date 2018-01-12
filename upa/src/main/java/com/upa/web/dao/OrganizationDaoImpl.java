package com.upa.web.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.upa.service.EntityUtils;
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
		
		sessionFactory.getCurrentSession().saveOrUpdate(org);
		return "SUCCESS";
	}
	
}
