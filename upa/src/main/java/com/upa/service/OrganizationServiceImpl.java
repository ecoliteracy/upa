package com.upa.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upa.web.constant.OrganizationConstant;
import com.upa.web.dao.OrganizationDaoImpl;
import com.upa.web.model.entity.Organization;

@Service
public class OrganizationServiceImpl implements OrganizationService{

	private OrganizationDaoImpl organizationDaoImpl;
	
	@Autowired
	public void organizationDao(OrganizationDaoImpl orgDao){
		this.organizationDaoImpl = orgDao;
	}
	
	
	@Transactional
	public String addNewOrg(Organization org){
		org.setLastModifiedDate(new Date());
		org.setTzCode("CDT");
		org.setCreatedDate(new Date());
		
		//Validation - 1. Duplication Check
		if(organizationDaoImpl.getOrganization(org.getOrgName()) != null){
			return OrganizationConstant.DUPLICATED;
		}
		
		String result = organizationDaoImpl.saveOraganization(org);
		return result;
	}
	
	
	public Organization getOrganizationByOrgName(String orgName){
		Organization org = organizationDaoImpl.getOrganization(orgName);
		return org;
	}
	
}
