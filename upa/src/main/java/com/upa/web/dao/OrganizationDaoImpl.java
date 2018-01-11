package com.upa.web.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.upa.web.model.entity.Organization;

public class OrganizationDaoImpl {

	@Autowired
	private SessionFactory sessionFactory;
	
	public String saveOraganization(Organization org){
		sessionFactory.getCurrentSession().saveOrUpdate(org);
		return "SUCCESS";
	}
	
}
