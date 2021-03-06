package com.upa.web.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.upa.service.OrganizationService;
import com.upa.web.constant.OrganizationConstant;
import com.upa.web.model.entity.AppUser;
import com.upa.web.model.entity.Organization;

@Controller
public class OrganizationController extends BaseController{

	
	private OrganizationService orgSrv;
	
	ModelAndView model = new ModelAndView();
	
	@Autowired(required=true)
	@Qualifier(value="orgSrv")
	public void OrganizationService(OrganizationService orgSrv){
		this.orgSrv = orgSrv;
	}
	 
	@RequestMapping(value="/organizationView")
	public ModelAndView getData(@SessionAttribute("appuser") AppUser appuser){
		model = new ModelAndView("profiles/organization/organization");
		
		Map<String,String> orgTypeList = new LinkedHashMap<String,String>();
		orgTypeList.put("CMP", "COMPANY");
		orgTypeList.put("NGO", "NON PROFIT");

		model.addObject("org", new Organization());
		model.addObject("appuser", appuser);
		model.addObject("orgTypeList", orgTypeList);

		return model;
	}
	
	@RequestMapping("/submitNewOrg")
	public ModelAndView submit(@SessionAttribute("appuser") AppUser appuser, @ModelAttribute Organization org){
		String result = orgSrv.addNewOrg(org);
		if(result.equals(OrganizationConstant.SUCCESS)){
			model = new ModelAndView("profiles/organization/orgConfirm");
			model.addObject("org", orgSrv.getOrganizationByOrgName(org.getOrgName()));
			return model;
		}else{
			model.addObject("org", new Organization());
			model.addObject("result", getAlertMessage(result,"EN"));
			return model;
		}
	}
	
    @RequestMapping(value="/returnMenu", method = RequestMethod.GET)
    public ModelAndView returnMenu() {
        ModelAndView model = new ModelAndView("index");
    	return model;
    }

	
	
}