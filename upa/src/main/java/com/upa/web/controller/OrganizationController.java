package com.upa.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.upa.web.model.entity.AppUser;
import com.upa.web.model.entity.Organization;

@Controller
public class OrganizationController {

	 
	@RequestMapping(value="/organizationView")
	public ModelAndView getData(@SessionAttribute("appuser") AppUser appuser){
		
		System.out.println("OrganizationController @ getData()");
		System.out.println();
		
		ModelAndView model = new ModelAndView("profiles/organization/organization");//index
		
		model.addObject("company", new Organization());
		model.addObject("appuser", appuser);
		return model;
	}
	
	@RequestMapping("/submitNewOrg")
	public ModelAndView submit(@SessionAttribute("appuser") AppUser appuser, @ModelAttribute Organization org){
		
		System.out.println("OrganizationController @ submit()");
		System.out.println();
		
		return null;
	}
	
	
}