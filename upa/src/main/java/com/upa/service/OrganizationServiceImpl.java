package com.upa.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.upa.web.dao.OrganizationDaoImpl;
import com.upa.web.model.entity.Organization;

@Service
public class OrganizationServiceImpl implements OrganizationService{

	private OrganizationDaoImpl organizationDaoImpl;
	
	
	@Transactional
	public String addNewOrg(Organization org){
		
		String result = organizationDaoImpl.saveOraganization(org);
		return result;
		
	}
}
